
package com.lishuaihua.materialdialogs.internal.rtl

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.lishuaihua.materialdialogs.utils.setGravityStartCompat

class RtlTextView(
  context: Context,
  attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {
  init {
    setGravityStartCompat()
  }
}
