package com.votlin.android.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.votlin.presentation.Shared
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView(AnkoContext.create(this)))
    }

    private fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            textView {
                text = Shared().text
            }
        }
    }
}
