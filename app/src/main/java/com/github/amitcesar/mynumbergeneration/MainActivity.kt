package com.github.amitcesar.mynumbergeneration

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.github.amitcesar.mynumbergeneration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
       val navHostFragment =  supportFragmentManager.findFragmentById(R.id.fcvContent) as? NavHostFragment
        navHostFragment?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){
            btnDraw.setOnClickListener {
                when(btnDraw.text){
                    getString(R.string.sortear) -> {
                        navController?.navigate(R.id.action_fragment_configuracao_de_sorteio_to_resultadoSorteioFragment)
                        btnDraw.text = getString(R.string.sortear_novamente)
                        btnDraw.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            AppCompatResources.getDrawable(this@MainActivity,
                                R.drawable.ic_sorting_again)
                            ,null)
                    }
                    getString(R.string.sortear_novamente) -> {
                        navController?.popBackStack()
                        btnDraw.text = getString(R.string.sortear)
                    }
                }
            }
        }
    }
}