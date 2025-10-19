package com.cibertec.proygym

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.cibertec.proygym.fragments.ConfiguracionFragment
import com.cibertec.proygym.fragments.InicioFragment
import com.cibertec.proygym.fragments.PerfilFragment
import com.google.android.material.navigation.NavigationView

class InicioActivity : AppCompatActivity() {

    private lateinit var dlayMenu : DrawerLayout
    private lateinit var ivMenu : ImageView
    private lateinit var nvMenu : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        dlayMenu = findViewById(R.id.dlayMenu)
        ivMenu = findViewById(R.id.ivMenu)
        nvMenu = findViewById(R.id.nvMenu)

        ivMenu.setOnClickListener { dlayMenu.open() }

        nvMenu.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            dlayMenu.closeDrawers()

            when(menuItem.itemId){
                R.id.itInicio -> replaceFragment(InicioFragment())
                R.id.itPerfil -> replaceFragment(PerfilFragment())
                R.id.itConfiguracion -> replaceFragment(ConfiguracionFragment())
                R.id.itCerrarSesion -> startActivity(Intent(this, AccesoActivity::class.java)
                )
            }

            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dlayMenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(InicioFragment())
    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragment, fragment).commit()
    }
}