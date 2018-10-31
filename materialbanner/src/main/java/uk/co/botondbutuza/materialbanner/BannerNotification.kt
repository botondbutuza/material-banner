package uk.co.botondbutuza.materialbanner

import android.support.annotation.StringRes
import android.support.design.widget.CoordinatorLayout
import android.support.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView

class BannerNotification private constructor(private val content: BannerNotificationContent) {

    private fun setText(msg: CharSequence) {
        content.findViewById<TextView>(R.id.message).text = msg
    }

    fun setPrimaryAction(action: Builder.Action?) {
        val button = content.findViewById<Button>(R.id.actionSecondary)

        if (action == null) {
            button.visibility = View.GONE
        } else {
            button.text = action.label
            button.setOnClickListener(action.listener)
        }
    }

    fun overrideDismissAction(action: Builder.Action) {
        val button = content.findViewById<Button>(R.id.actionDismiss)
        button.text = action.label
        button.setOnClickListener(action.listener)
    }

    fun show() {
        content.show()
    }

    fun hide() {
        content.hide()
    }


    class Builder {
        var message: CharSequence = ""
        var primaryAction: Action? = null
        var dismissOverride: Action? = null

        data class Action(val label: CharSequence, val listener: View.OnClickListener)

        fun setMessage(msg: CharSequence): Builder {
            this.message = msg
            return this
        }

        fun setPrimaryAction(action: Action): Builder {
            this.primaryAction = action
            return this
        }

        fun overrideDismiss(action: Action): Builder {
            this.dismissOverride = action
            return this
        }
    }


    companion object {

        fun make(view: View, message: CharSequence): BannerNotification {
            val parent = findSuitableParent(view) ?: throw IllegalArgumentException(view.context.getString(R.string.error_no_parent))
            val inflater = LayoutInflater.from(view.context)
            val content = inflater.inflate(R.layout.design_banner, parent, false) as BannerNotificationContent

            val banner = BannerNotification(content)
            banner.setText(message)

            parent.addView(content, 0)
            return banner
        }

        fun make(view: View, builder: Builder): BannerNotification {
            val banner = make(view, builder.message)
            banner.setPrimaryAction(builder.primaryAction)

            if (builder.dismissOverride != null) {
                banner.overrideDismissAction(builder.dismissOverride as Builder.Action)
            }

            return banner
        }

        /**
         * Utility method.
         */
        fun make(view: View, @StringRes messageResId: Int) = make(view, view.context.getString(messageResId))

        /**
         * Finds a CoordinationLayout parent or cops out with null.
         */
        private fun findSuitableParent(view: View): ViewGroup? {
            if (view is CoordinatorLayout) {
                return view     // we found a suitable parent!
            }

            if (view is FrameLayout && view.id == android.R.id.content) {
                return null     // we didn't find a suitable parent and have bubbled up to content
            }

            return findSuitableParent(view.parent as View)
        }
    }
}