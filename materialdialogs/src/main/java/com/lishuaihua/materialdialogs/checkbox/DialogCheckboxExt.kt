
@file:Suppress("unused")

package com.lishuaihua.materialdialogs.checkbox

import android.view.View
import android.widget.CheckBox
import androidx.annotation.CheckResult
import androidx.annotation.StringRes
import androidx.core.widget.CompoundButtonCompat
import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.R
import com.lishuaihua.materialdialogs.utils.MDUtil.assertOneSet
import com.lishuaihua.materialdialogs.utils.MDUtil.createColorSelector
import com.lishuaihua.materialdialogs.utils.MDUtil.maybeSetTextColor
import com.lishuaihua.materialdialogs.utils.MDUtil.resolveString
import com.lishuaihua.materialdialogs.utils.resolveColors

typealias BooleanCallback = ((Boolean) -> Unit)?

@CheckResult fun MaterialDialog.getCheckBoxPrompt(): CheckBox {
  return view.buttonsLayout?.checkBoxPrompt ?: throw IllegalStateException(
      "The dialog does not have an attached buttons layout."
  )
}

@CheckResult fun MaterialDialog.isCheckPromptChecked() = getCheckBoxPrompt().isChecked

/**
 * @param res The string resource to display for the checkbox label.
 * @param text The literal string to display for the checkbox label.
 * @param isCheckedDefault Whether or not the checkbox is initially checked.
 * @param onToggle A listener invoked when the checkbox is checked or unchecked.
 */
fun MaterialDialog.checkBoxPrompt(
  @StringRes res: Int = 0,
  text: String? = null,
  isCheckedDefault: Boolean = false,
  onToggle: BooleanCallback
): MaterialDialog {
  assertOneSet("checkBoxPrompt", text, res)
  view.buttonsLayout?.checkBoxPrompt?.run {
    this.visibility = View.VISIBLE
    this.text = text ?: resolveString(this@checkBoxPrompt, res)
    this.isChecked = isCheckedDefault
    this.setOnCheckedChangeListener { _, checked ->
      onToggle?.invoke(checked)
    }
    maybeSetTextColor(windowContext, R.attr.md_color_content)
    bodyFont?.let(this::setTypeface)

    val widgetAttrs = intArrayOf(R.attr.md_color_widget, R.attr.md_color_widget_unchecked)
    resolveColors(attrs = widgetAttrs).let {
      CompoundButtonCompat.setButtonTintList(
          this,
          createColorSelector(windowContext, checked = it[0], unchecked = it[1])
      )
    }
  }
  return this
}
