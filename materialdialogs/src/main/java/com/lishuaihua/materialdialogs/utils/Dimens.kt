
package com.lishuaihua.materialdialogs.utils

import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes
import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.utils.MDUtil.assertOneSet

internal fun MaterialDialog.dimen(
  @DimenRes res: Int? = null,
  @AttrRes attr: Int? = null,
  fallback: Float = 0f
): Float {
  assertOneSet("dimen", attr, res)
  if (res != null) {
    return windowContext.resources.getDimension(res)
  }
  requireNotNull(attr)
  val a = windowContext.theme.obtainStyledAttributes(intArrayOf(attr))
  try {
    return a.getDimension(0, fallback)
  } finally {
    a.recycle()
  }
}

internal fun View.dp(value: Int): Float {
  return TypedValue.applyDimension(COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics)
}
