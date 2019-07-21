package com.msaviczki.themovieapp.helper.customview

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.msaviczki.themovieapp.R

class Toggle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    enum class RoundSwitchState(val value: Int) {
        ON_LEFT(0), ON_RIGHT(1);

        companion object {
            fun fromInt(value: Int): RoundSwitchState =
                values().find { v -> v.value == value } ?: ON_LEFT
        }
    }

    private val leftSwitch by lazy { findViewById<TextView>(R.id.left_switch) }
    private val rightSwitch by lazy { findViewById<TextView>(R.id.right_switch) }

    private var enabledColor: Int = 0
    private var textColor: ColorStateList? = null
    private var initialState: RoundSwitchState = RoundSwitchState.ON_LEFT
    private var onSwitchValueChanged: ((newValue: RoundSwitchState) -> Unit)? = null
    private var onLeftSwitchClick: ((newValue: Unit) -> Unit)? = null
    private var onRightSwitchClick: ((newValue: Unit) -> Unit)? = null
    var switchState: RoundSwitchState = RoundSwitchState.ON_LEFT
        private set

    init {
        View.inflate(context, R.layout.toggle, this)
        setup(context.obtainStyledAttributes(attrs, R.styleable.Toggle, defStyleAttr, defStyleRes))
        reset()
    }

    private fun setup(typedArray: TypedArray) {
        typedArray.run {
            initialState = RoundSwitchState.fromInt(getInt(R.styleable.Toggle_toggle_initialPosition, 0))

            background = getDrawable(R.styleable.Toggle_toggle_background)
            enabledColor = getColor(R.styleable.Toggle_toggle_separatorView, 0)
            textColor = ContextCompat.getColorStateList(context,
                getResourceId(R.styleable.Toggle_toggle_textColor, R.drawable.toggle_selector_text))
            leftSwitch.background = getDrawable(R.styleable.Toggle_toggle_switchBackgroundLeft)
            rightSwitch.background = getDrawable(R.styleable.Toggle_toggle_switchBackgroundRight)
            leftSwitch.setTextColor(textColor)
            rightSwitch.setTextColor(textColor)
            recycle()
        }

        leftSwitch.setOnClickListener {
            select(RoundSwitchState.ON_LEFT)
        }
        rightSwitch.setOnClickListener {
            select(RoundSwitchState.ON_RIGHT)
        }
    }

    fun select(state: RoundSwitchState) {
        switchState = state
        chooseSelector(switchState)
    }

    private fun reset() {
        switchState = initialState

        leftSwitch.isSelected = switchState == RoundSwitchState.ON_LEFT
        rightSwitch.isSelected = switchState == RoundSwitchState.ON_RIGHT

        onSwitchValueChanged?.invoke(switchState)
    }

    fun setValueChangedAction(action: (RoundSwitchState) -> Unit) {
        onSwitchValueChanged = action
    }

    private fun chooseSelector(state: RoundSwitchState) {
        when (state) {
            RoundSwitchState.ON_RIGHT -> {
                changeSelector(false, true, RoundSwitchState.ON_RIGHT)
            }
            RoundSwitchState.ON_LEFT -> {
                changeSelector(true, false, RoundSwitchState.ON_LEFT)
            }
        }
    }

    private fun changeSelector(left: Boolean, right: Boolean, state: RoundSwitchState) {
        leftSwitch.isSelected = left
        rightSwitch.isSelected = right
        switchState = state
        onSwitchValueChanged?.invoke(state)
    }
}