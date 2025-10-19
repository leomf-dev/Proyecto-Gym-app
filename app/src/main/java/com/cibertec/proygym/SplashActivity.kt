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

        // Animacion "Fade in" | "Aparecer"
        // Opacidad
        val fadeIn = ObjectAnimator.ofFloat(ivLogo, "alpha", 0f, 1f)
        // Tamano Horizontal
        val scaleX = ObjectAnimator.ofFloat(ivLogo, "scaleX", 0.7f, 1f)
        // Tamano Vertical
        val scaleY = ObjectAnimator.ofFloat(ivLogo, "scaleY", 0.7f, 1f)

        // AnimatorSet() = Agrupa varias animaciones (objectAnimator) y las controla. Ejemp: Cuanto empiezan
        //                                                                duracion, retraso, cuando terminan.
        val appearSet = AnimatorSet().apply {
            playTogether(fadeIn, scaleX, scaleY)
            duration = 1200
            startDelay = 50
            start()
        }

        // Transicion y expansion
        Handler(Looper.getMainLooper()).postDelayed({
            // ConstraintSet() = Clase que representa las restricciones de un ContraintLayout
            // Atributo = Propiedad general de una vista de cualquier layout
            // Restriccion = Igual pero no lo tienen los demas layouts
            // Se crea un nuevo ContraintSet con los atributos actuales (o mejor dicho se clona)
            val constraintSet = ConstraintSet()
            constraintSet.clone(main)
            // Se cambian de tamano y dimension, para posteriormente hacer la animacion
            constraintSet.constrainWidth(R.id.ivLogo, 0)
            constraintSet.constrainHeight(R.id.ivLogo, 0)
            constraintSet.setDimensionRatio(R.id.ivLogo, "4:3")
            // Conecta el TOP (osea arriba) del ID (ivLogo) al TOP del PARENT (padre) con margen de 50dp
            constraintSet.connect(
                R.id.ivLogo, ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, dpToPx(50)
            )
            // Igual que arriba pero con margen 0
            constraintSet.connect(
                R.id.ivLogo, ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0
            )
            // Igual que arriba
            constraintSet.connect(
                R.id.ivLogo, ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0
            )
            // Se quita la restriccion para BOTTOM
            constraintSet.clear(R.id.ivLogo, ConstraintSet.BOTTOM)

            // Transicion
            val transition = TransitionSet().apply {
                // ChangeBounds() = Animara los cambios
                addTransition(ChangeBounds())
                duration = 1000
                // La interpolacion hace que la expansion empieze suave, acelere y luego frene.
                interpolator = android.view.animation.AccelerateDecelerateInterpolator()
            }
            // TransitionManager.begindelayerTransition() = Aqui se anima con los resultados
            TransitionManager.beginDelayedTransition(main, transition)
            // Aqui se aplican los cambios que se hicieron en el ConstraintSet
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

    // dp = density-independement pixel, es una unidad logica que no depende de la resolucion fisica
    //      de la pantalla. Se usa para que las interfaces se vean igual en distintos dispositivos
    // 1 dp = 1 pixel en una pantalla de densidad 160 dpi (mdpi)
    // px = unidad de medida real en la pantalla. Varian segun densidad del dispo.
    // ejem: pantalla de mdpi, 1dp = 1px.
    //       pantala de xxhdpi, 1dp = 3px (tiene 3 veces mas densidad)
    private fun dpToPx(dp: Int): Int {
        // resources.displaymetrics.density devuelve un factor de densidad que indica cuantos pixeles
        // equivalen a 1dp en el dispotivo actual
        return (dp * resources.displayMetrics.density).toInt()
    }

}