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

    private val image by lazy {
        ImageView(this).apply {
            setImageResource(R.drawable.triangle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.root.doOnLayout {
            binding.indicator.count = 9
            binding.indicator.maxVisibleCount = 6
            binding.indicator.selectedIndex = 1

            binding.indicator.getSelectedCell()?.doOnLayout { cell ->
                val constraintLayout = binding.root as ConstraintLayout
                constraintLayout.addView(image)
                image.doOnLayout {
                    image.y = binding.indicator.y + cell.height
                    image.x = binding.indicator.x + cell.x + (cell.width - image.width) / 2
                }
            }
        }

        binding.prev.setOnClickListener {
            binding.indicator.previous()
            moveTriangle()
        }

        binding.next.setOnClickListener {
            binding.indicator.next()
            moveTriangle()
        }
    }

    private fun moveTriangle() {
        binding.indicator.getSelectedCell()?.let { cell ->
            image.y = binding.indicator.y + cell.height
            image.x = binding.indicator.x + cell.x + (cell.width - image.width) / 2
        }
    }
}
