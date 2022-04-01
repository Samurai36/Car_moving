package viktor.khlebnikov.gb.carmoving

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import viktor.khlebnikov.gb.carmoving.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.car.setOnClickListener {
            startMoving(binding.car)
        }
    }

    private fun startMoving(view: View) {
        val animation = TranslateAnimation(0f, 500f, 0f, 0f)
        animation.duration = 1000
        animation.fillAfter = true
        view.startAnimation(animation)
    }

}
