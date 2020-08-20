
package com.lishuaihua.materialdialogs.list

import com.lishuaihua.materialdialogs.MaterialDialog

typealias ItemListener =
    ((dialog: MaterialDialog, index: Int, text: CharSequence) -> Unit)?

typealias SingleChoiceListener =
    ((dialog: MaterialDialog, index: Int, text: CharSequence) -> Unit)?

typealias MultiChoiceListener =
    ((dialog: MaterialDialog, indices: IntArray, items: List<CharSequence>) -> Unit)?
