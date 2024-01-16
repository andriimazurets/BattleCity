package com.example.battlecity

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.battlecity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_DPAD_UP -> move(Direction.UP)
            KEYCODE_DPAD_DOWN -> move(Direction.BOTTOM)
            KEYCODE_DPAD_LEFT -> move(Direction.LEFT)
            KEYCODE_DPAD_RIGHT -> move(Direction.RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun move(direction: Direction) {
        with(binding.myTank) {
            val layoutParams = layoutParams as FrameLayout.LayoutParams

            when (direction) {
                Direction.UP -> {
                    rotation = 0f
                    layoutParams.topMargin += -50
                }

                Direction.BOTTOM -> {
                    rotation = 180f
                    layoutParams.topMargin += 50
                }

                Direction.LEFT -> {
                    rotation = 270f
                    layoutParams.leftMargin -= 50
                }

                Direction.RIGHT -> {
                    rotation = 90f
                    layoutParams.leftMargin += 50
                }
            }
        }
        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}