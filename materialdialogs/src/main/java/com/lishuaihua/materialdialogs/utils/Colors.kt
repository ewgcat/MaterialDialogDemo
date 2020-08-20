
package com.lishuaihua.materialdialogs.utils

import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.utils.MDUtil.resolveColor
import com.lishuaihua.materialdialogs.utils.MDUtil.resolveColors

@ColorInt @CheckResult
internal fun MaterialDialog.resolveColor(
  @ColorRes res: Int? = null,
  @AttrRes attr: Int? = null,
  fallback: (() -> Int)? = null
): Int = resolveColor(windowContext, res, attr, fallback)

@CheckResult
internal fun MaterialDialog.resolveColors(
  attrs: IntArray,
  fallback: ((forAttr: Int) -> Int)? = null
) = resolveColors(windowContext, attrs, fallback)

@ColorInt @CheckResult
internal fun Int.adjustAlpha(alpha: Float): Int {
  return Color.argb((255 * alpha).toInt(), Color.red(this), Color.green(this), Color.blue(this))
}
