package com.lishuaihua.materialdialogdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lishuaihua.materialdialogs.MaterialDialog
import com.lishuaihua.materialdialogs.customview.customView
import com.lishuaihua.materialdialogs.lifecycle.lifecycleOwner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dialog = MaterialDialog(MainActivity@ this)
            .cancelable(false)
            .cancelOnTouchOutside(false)
            .customView(R.layout.layout_custom_progress_dialog_view)
            .lifecycleOwner(this)
        tv_show.setOnClickListener {
            dialog.show()
        }

    }
}