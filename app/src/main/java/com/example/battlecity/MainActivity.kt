package com.example.battlecity

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.battlecity.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val gridDrawer by lazy {
        GridDrawer(this)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.container.layoutParams =
            FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORIZONTAL_MAX_SIZE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                gridDrawer.drawGrid()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
                    if (layoutParams.topMargin > 0) {
                        layoutParams.topMargin += -CELL_SIZE
                    }
                }

                Direction.BOTTOM -> {
                    rotation = 180f
                    if (layoutParams.topMargin + height < HORIZONTAL_MAX_SIZE) {
                        layoutParams.topMargin += CELL_SIZE
                    }
                }

                Direction.RIGHT -> {
                    rotation = 90f
                    if (layoutParams.leftMargin + width < VERTICAL_MAX_SIZE) {
                        layoutParams.leftMargin += CELL_SIZE
                    }
                }

                Direction.LEFT -> {
                    rotation = 270f
                    if (layoutParams.leftMargin > 0) {
                        layoutParams.leftMargin += -CELL_SIZE
                    }
                }
            }
        }
        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}