package com.github.sasakitomohiro.indicatorviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import com.github.sasakitomohiro.indicatorviewsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val image = ImageView(this).apply {
            setImageResource(R.drawable.triangle)
        }

        binding.root.doOnLayout {
            binding.indicator.count = 8
            binding.indicator.selectedIndex = 1

            binding.indicator.getSelectedCell()?.doOnLayout { cell ->
                val constraintLayout = binding.root as ConstraintLayout
                constraintLayout.addView(image)
                image.y = binding.indicator.y + cell.height
                image.x = cell.x
            }
        }
    }
}
