package uk.co.botondbutuza.materialbanner

import android.content.Context
import android.support.annotation.RestrictTo
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.Button

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class BannerNotificationContent(context: Context, attributeSet: AttributeSet?) : ConstraintLayout(context, attributeSet) {

    private var laidOut = false
    private val dismissAction by lazy { findViewById<Button>(R.id.actionDismiss) }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        // if onLayout is called repeatedly, translation information is reset, so let's not do that
        if (!laidOut && height > 0) {
            translationY = -height.toFloat()
        }

        laidOut = true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        dismissAction.setOnClickListener { hide() }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        dismissAction.setOnClickListener(null)
    }

    fun show() {
        animate()
            .translationY(0f)
            .setDuration(250)
            .start()
    }

    fun hide() {
        animate()
            .translationYBy(-height.toFloat())
            .setDuration(250)
            .start()
    }
}