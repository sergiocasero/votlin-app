package com.votlin.android.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.votlin.android.R
import com.votlin.android.extensions.hideMe
import com.votlin.android.extensions.showMe
import com.votlin.client.presentation.DetailPresenter
import com.votlin.client.presentation.DetailView
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : RootActivity<DetailView>(), DetailView {

    companion object {
        fun getCallingIntent(context: Context, talkId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TALK_ID, talkId)
            return intent
        }

        const val TALK_ID = "TALK_ID"
    }

    override val presenter: DetailPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_detail

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<DetailPresenter>() with provider {
            DetailPresenter(
                    view = this@DetailActivity,
                    errorHandler = instance(),
                    executor = instance(),
                    repository = instance()
            )
        }
    }

    override fun showProgress() {
        progressView.showMe()
    }

    override fun hideProgress() {
        progressView.hideMe()
    }

    override fun initializeUI() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun registerListeners() {
        // Do nothing
    }

    override fun getTalkId(): Int {
        return intent.extras.getInt(TALK_ID)
    }

    override fun navigateToList() {
        finish()
    }

    override fun showRate(rate: Int) {
        // TODO, implement this!
    }

    override fun showTalk(talk: Talk) {
        val color = when (talk.track) {
            Track.BUSINESS -> R.color.track_business
            Track.DEVELOPMENT -> R.color.track_development
            Track.MAKER -> R.color.track_maker
            Track.ALL -> R.color.track_all
        }
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, color)))
        name.text = talk.name
        description.text = talk.description
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}