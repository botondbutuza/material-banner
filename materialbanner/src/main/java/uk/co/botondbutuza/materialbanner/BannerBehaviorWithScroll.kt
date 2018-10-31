package uk.co.botondbutuza.materialbanner

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


/**
 * Adds banner behaviour functionality on top of scrolling behaviour.
 */
class BannerBehaviorWithScroll(context: Context, attr: AttributeSet) : AppBarLayout.ScrollingViewBehavior(context, attr) {

    /**
     * Since we want all the goodies of ScrollingViewBehavior, with added Banner goodies, we have to
     * dpeend on Banner and whatever our parent depends on.
     */
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is BannerNotificationContent || super.layoutDependsOn(parent, child, dependency)
    }

    /**
     * If Banner's changed, let's catch it and deal with it, otherwise pass it on to parent to
     * deal with it if it wishes.
     */
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return when (dependency) {
            is BannerNotificationContent -> {

                // changing child.translationY is not good enough cause then the bottom will be cut off
                val params = child.layoutParams as CoordinatorLayout.LayoutParams
                params.topMargin = (dependency.translationY + dependency.height).toInt()
                child.layoutParams = params
                true
            }
            else -> {       // deals with scrolling behaviour
                super.onDependentViewChanged(parent, child, dependency)
            }
        }
    }
}