package com.lishuaihua.materialdialogs.list

import android.util.Log
import androidx.annotation.ArrayRes
import androidx.annotation.CheckResult
import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.WhichButton.POSITIVE
import com.lishuaihua.materialdialogs.actions.setActionButtonEnabled
import com.lishuaihua.materialdialogs.internal.list.DialogAdapter
import com.lishuaihua.materialdialogs.internal.list.MultiChoiceDialogAdapter
import com.lishuaihua.materialdialogs.utils.MDUtil.assertOneSet
import com.lishuaihua.materialdialogs.utils.MDUtil.getStringArray


@CheckResult fun MaterialDialog.listItemsMultiChoice(
  @ArrayRes res: Int? = null,
  items: List<CharSequence>? = null,
  disabledIndices: IntArray? = null,
  initialSelection: IntArray = IntArray(0),
  waitForPositiveButton: Boolean = true,
  allowEmptySelection: Boolean = false,
  selection: MultiChoiceListener = null
): MaterialDialog {
  assertOneSet("listItemsMultiChoice", items, res)
  val array = items ?: windowContext.getStringArray(res).toList()

  if (getListAdapter() != null) {
    Log.w(
        "MaterialDialogs",
        "Prefer calling updateListItemsMultiChoice(...) over listItemsMultiChoice(...) again."
    )
    return updateListItemsMultiChoice(
        res = res,
        items = items,
        disabledIndices = disabledIndices,
        selection = selection
    )
  }

  setActionButtonEnabled(POSITIVE, allowEmptySelection || initialSelection.isNotEmpty())
  return customListAdapter(
      MultiChoiceDialogAdapter(
          dialog = this,
          items = array,
          disabledItems = disabledIndices,
          initialSelection = initialSelection,
          waitForActionButton = waitForPositiveButton,
          allowEmptySelection = allowEmptySelection,
          selection = selection
      )
  )
}

/**
 * Updates the items, and optionally the disabled indices, of a plain list dialog.
 *
 * @author Aidan Follestad (@lishuaihua)
 */
fun MaterialDialog.updateListItemsMultiChoice(
  @ArrayRes res: Int? = null,
  items: List<CharSequence>? = null,
  disabledIndices: IntArray? = null,
  selection: MultiChoiceListener = null
): MaterialDialog {
  assertOneSet("updateListItemsMultiChoice", items, res)
  val array = items ?: windowContext.getStringArray(res).toList()
  val adapter = getListAdapter()
  check(adapter is MultiChoiceDialogAdapter) {
    "updateListItemsMultiChoice(...) can't be used before you've created a multiple choice list dialog."
  }
  adapter.replaceItems(array, selection)
  disabledIndices?.let(adapter::disableItems)
  return this
}

/** Checks a set of multiple choice list items. */
fun MaterialDialog.checkItems(indices: IntArray) {
  val adapter = getListAdapter()
  if (adapter is DialogAdapter<*, *>) {
    adapter.checkItems(indices)
    return
  }
  throw UnsupportedOperationException(
      "Can't check items on adapter: ${adapter?.javaClass?.name ?: "null"}"
  )
}

/** Unchecks a set of multiple choice list items. */
fun MaterialDialog.uncheckItems(indices: IntArray) {
  val adapter = getListAdapter()
  if (adapter is DialogAdapter<*, *>) {
    adapter.uncheckItems(indices)
    return
  }
  throw UnsupportedOperationException(
      "Can't uncheck items on adapter: ${adapter?.javaClass?.name ?: "null"}"
  )
}

/** Toggles the checked state of a set of multiple choice list items. */
fun MaterialDialog.toggleItemsChecked(indices: IntArray) {
  val adapter = getListAdapter()
  if (adapter is DialogAdapter<*, *>) {
    adapter.toggleItems(indices)
    return
  }
  throw UnsupportedOperationException(
      "Can't toggle checked items on adapter: ${adapter?.javaClass?.name ?: "null"}"
  )
}

/** Checks all multiple choice list items. */
fun MaterialDialog.checkAllItems() {
  val adapter = getListAdapter()
  if (adapter is DialogAdapter<*, *>) {
    adapter.checkAllItems()
    return
  }
  throw UnsupportedOperationException(
      "Can't check all items on adapter: ${adapter?.javaClass?.name ?: "null"}"
  )
}

/** Unchecks all single or multiple choice list items. */
fun MaterialDialog.uncheckAllItems() {
  val adapter = getListAdapter()
  if (adapter is DialogAdapter<*, *>) {
    adapter.uncheckAllItems()
    return
  }
  throw UnsupportedOperationException(
      "Can't uncheck all items on adapter: ${adapter?.javaClass?.name ?: "null"}"
  )
}

/** Toggles the checked state of all multiple choice list items. */
fun MaterialDialog.toggleAllItemsChecked() {
  val adapter = getListAdapter()
  if (adapter is DialogAdapter<*, *>) {
    adapter.toggleAllChecked()
    return
  }
  throw UnsupportedOperationException(
      "Can't uncheck all items on adapter: ${adapter?.javaClass?.name ?: "null"}"
  )
}
