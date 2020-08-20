package com.lishuaihua.materialdialogs.actions

import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.WhichButton
import com.lishuaihua.materialdialogs.internal.button.DialogActionButton
import com.lishuaihua.materialdialogs.utils.isVisible

/** Returns true if the dialog has visible action buttons. */
fun MaterialDialog.hasActionButtons(): Boolean {
  return view.buttonsLayout?.visibleButtons?.isNotEmpty() ?: false
}

/** Returns true if the given button is visible in the dialog. */
fun MaterialDialog.hasActionButton(which: WhichButton) = getActionButton(which).isVisible()

/** Returns the underlying view for an action button in the dialog. */
fun MaterialDialog.getActionButton(which: WhichButton): DialogActionButton {
  return view.buttonsLayout?.actionButtons?.get(which.index) ?: throw IllegalStateException(
      "The dialog does not have an attached buttons layout."
  )
}

/** Enables or disables an action button. */
fun MaterialDialog.setActionButtonEnabled(
  which: WhichButton,
  enabled: Boolean
) {
  getActionButton(which).isEnabled = enabled
}
