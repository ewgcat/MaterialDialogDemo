package com.lishuaihua.materialdialogs.internal.message

import android.text.style.URLSpan
import android.view.View

class CustomUrlSpan(
  url: String,
  private val onLinkClick: (String) -> Unit
) : URLSpan(url) {
  override fun onClick(widget: View) = onLinkClick(url)
}
