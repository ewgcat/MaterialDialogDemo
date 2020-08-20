package com.lishuaihua.materialdialogs.lifecycle

import androidx.lifecycle.LifecycleOwner
import com.lishuaihua.materialdialogs.MaterialDialog


fun MaterialDialog.lifecycleOwner(owner: LifecycleOwner? = null): MaterialDialog {
  val observer = DialogLifecycleObserver(::dismiss)
  val lifecycleOwner = owner ?: (windowContext as? LifecycleOwner
      ?: throw IllegalStateException(
          "$windowContext is not a LifecycleOwner."
      ))
  lifecycleOwner.lifecycle.addObserver(observer)
  return this
}
