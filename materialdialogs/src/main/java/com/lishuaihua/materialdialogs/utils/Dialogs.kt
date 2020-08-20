
package com.lishuaihua.materialdialogs.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope
import androidx.annotation.StringRes
import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.callbacks.invokeAll
import com.lishuaihua.materialdialogs.checkbox.getCheckBoxPrompt
import com.lishuaihua.materialdialogs.customview.CUSTOM_VIEW_NO_VERTICAL_PADDING
import com.lishuaihua.materialdialogs.utils.MDUtil.maybeSetTextColor
import com.lishuaihua.materialdialogs.utils.MDUtil.resolveDrawable
import com.lishuaihua.materialdialogs.utils.MDUtil.resolveString

@RestrictTo(Scope.LIBRARY_GROUP)
fun MaterialDialog.invalidateDividers(
  showTop: Boolean,
  showBottom: Boolean
) = view.invalidateDividers(showTop, showBottom)

internal fun MaterialDialog.preShow() {
  val customViewNoVerticalPadding = config[CUSTOM_VIEW_NO_VERTICAL_PADDING] as? Boolean == true
  this.preShowListeners.invokeAll(this)

  this.view.run {
    if (titleLayout.shouldNotBeVisible() && !customViewNoVerticalPadding) {
      // Reduce top and bottom padding if we have no title or buttons
      contentLayout.modifyFirstAndLastPadding(
          top = frameMarginVertical,
          bottom = frameMarginVertical
      )
    }
    if (getCheckBoxPrompt().isVisible()) {
      // Zero out bottom content padding if we have a checkbox prompt
      contentLayout.modifyFirstAndLastPadding(bottom = 0)
    } else if (contentLayout.haveMoreThanOneChild()) {
      contentLayout.modifyScrollViewPadding(bottom = frameMarginVerticalLess)
    }
  }
}

internal fun MaterialDialog.populateIcon(
  imageView: ImageView,
  @DrawableRes iconRes: Int?,
  icon: Drawable?
) {
  val drawable = resolveDrawable(windowContext, res = iconRes, fallback = icon)
  if (drawable != null) {
    (imageView.parent as View).visibility = View.VISIBLE
    imageView.visibility = View.VISIBLE
    imageView.setImageDrawable(drawable)
  } else {
    imageView.visibility = View.GONE
  }
}

internal fun MaterialDialog.populateText(
  textView: TextView,
  @StringRes textRes: Int? = null,
  text: CharSequence? = null,
  @StringRes fallback: Int = 0,
  typeface: Typeface?,
  textColor: Int? = null
) {
  val value = text ?: resolveString(this, textRes, fallback)
  if (value != null) {
    (textView.parent as View).visibility = View.VISIBLE
    textView.visibility = View.VISIBLE
    textView.text = value
    if (typeface != null) {
      textView.typeface = typeface
    }
    textView.maybeSetTextColor(windowContext, textColor)
  } else {
    textView.visibility = View.GONE
  }
}

internal fun MaterialDialog.hideKeyboard() {
  val imm =
    windowContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
  val currentFocus = currentFocus
  if (currentFocus != null) {
    currentFocus.windowToken
  } else {
    view.windowToken
  }.let {
    imm.hideSoftInputFromWindow(it, 0)
  }
}
