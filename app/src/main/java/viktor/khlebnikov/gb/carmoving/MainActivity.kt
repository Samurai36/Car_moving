package viktor.khlebnikov.gb.carmoving

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import viktor.khlebnikov.gb.carmoving.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.apply {
            binding.car.setOnClickListener {
                car.translationX = 0f
                car.translationY = 0f
                car.rotation = 180f
                startMoving(binding.root, binding.car)
            }
        }
    }

    private val r: Random = Random()

    private fun startMoving(parent: ViewGroup, view: View) {
        val pw = parent.measuredWidth - view.measuredWidth
        val ph = parent.measuredHeight - view.measuredHeight
        val nx: Int = r.nextInt(pw)
        val ny: Int = r.nextInt(ph)

        val moveX = ValueAnimator.ofFloat(view.x, nx.toFloat()).apply {
            duration = 1000
            addUpdateListener {
                view.x = it.animatedValue as Float
            }
        }
        val moveY = ValueAnimator.ofFloat(view.y, ny.toFloat()).apply {
            duration = 1000
            addUpdateListener {
                view.y = it.animatedValue as Float
            }
        }

        val rotationAnimatorDown: ObjectAnimator =
            ObjectAnimator.ofFloat(view, "rotation", view.rotation, view.rotation + 90f)
        val animatorSet = AnimatorSet()
        animatorSet.play(moveX).before(moveY).before(rotationAnimatorDown)
        animatorSet.start()
    }
}