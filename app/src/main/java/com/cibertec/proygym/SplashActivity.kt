package com.cibertec.proygym

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
// android = es nativa del framework y no es compatible con todas las versiones.
// androidx = es la version moderna, trae mejoras y compatibilidad en todas las versiones de android.
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val main = findViewById<ConstraintLayout>(R.id.main)
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)

        val fadeIn = ObjectAnimator.ofFloat(ivLogo, "alpha", 0f, 1f)

        val scaleX = ObjectAnimator.ofFloat(ivLogo, "scaleX", 0.7f, 1f)

        val scaleY = ObjectAnimator.ofFloat(ivLogo, "scaleY", 0.7f, 1f)


        val appearSet = AnimatorSet().apply {
            playTogether(fadeIn, scaleX, scaleY)
            duration = 1200
            startDelay = 50
            start()
        }

        // Transicion y expansion
        Handler(Looper.getMainLooper()).postDelayed({

            val constraintSet = ConstraintSet()
            constraintSet.clone(main)

            constraintSet.constrainWidth(R.id.ivLogo, 0)
            constraintSet.constrainHeight(R.id.ivLogo, 0)
            constraintSet.setDimensionRatio(R.id.ivLogo, "4:3")

            constraintSet.connect(
                R.id.ivLogo, ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, dpToPx(50)
            )

            constraintSet.connect(
                R.id.ivLogo, ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0
            )

            constraintSet.connect(
                R.id.ivLogo, ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0
            )

            constraintSet.clear(R.id.ivLogo, ConstraintSet.BOTTOM)

            // Transicion
            val transition = TransitionSet().apply {

                addTransition(ChangeBounds())
                duration = 1000

                interpolator = android.view.animation.AccelerateDecelerateInterpolator()
            }

            TransitionManager.beginDelayedTransition(main, transition)

            constraintSet.applyTo(main)
        }, 1400)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AccesoActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

}