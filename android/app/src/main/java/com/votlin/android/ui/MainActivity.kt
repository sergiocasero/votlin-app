package com.votlin.android.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.votlin.android.R
import com.votlin.client.presentation.PresenterView
import com.votlin.client.presentation.Shared
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), PresenterView {

    private val presenter = Shared(this, "Android")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        presenter.doShomething()
    }

    override fun showText(text: String) {
        this.text.text = text
    }


}
