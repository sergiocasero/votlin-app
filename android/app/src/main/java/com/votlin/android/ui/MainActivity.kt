package com.votlin.android.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val queryTextChangedListeners: MutableList<(String) -> Unit> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(createView(AnkoContext.create(this)))
    }

    /*override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(SEARCH_QUERY_KEY, searchQuery)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            id = R.id.fragment_container
        }
    }*/

    companion object {
        private const val SEARCH_QUERY_KEY = "SearchQuery"
    }
}
