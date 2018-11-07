package com.votlin.android.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.votlin.android.R
import com.votlin.android.extensions.hideMe
import com.votlin.android.extensions.showMe
import com.votlin.android.navigator.navigateToTalkDetailActivity
import com.votlin.android.ui.adapter.TalksAdapter
import com.votlin.client.presentation.TalksListPresenter
import com.votlin.client.presentation.TalksListView
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.android.synthetic.main.fragment_talks.*

class TalksListFragment : RootFragment<TalksListView>(), TalksListView {

    companion object {
        private const val TRACK = "track"

        fun newInstance(track: Track): TalksListFragment {
            val fragment = TalksListFragment()

            val bundle = Bundle()
            bundle.putString(TRACK, track.toString())

            fragment.arguments = bundle
            return fragment
        }
    }

    override val presenter: TalksListPresenter by instance()

    override val layoutResourceId: Int = R.layout.fragment_talks
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<TalksListPresenter>() with provider {
            TalksListPresenter(
                view = this@TalksListFragment,
                errorHandler = instance(),
                executor = instance(),
                repository = instance()
            )
        }
    }

    private val talksAdapter = TalksAdapter { presenter.onTalkClicked(it) }

    override fun initializeUI() {
        talks.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = talksAdapter
        }
    }

    override fun registerListeners() {
        // Nothing to do here
    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onTrackChanged(getTrack())
    }

    private fun getTrack(): Track {
        val bundle = arguments
        val track = bundle?.getString(TRACK)

        track?.let {
            return Track.valueOf(it)
        } ?: throw IllegalArgumentException("This fragment only can works if you pass the track")
    }

    override fun showTalks(talks: List<Talk>) {
        talksAdapter.replace(talks)
    }

    override fun goToTalkScreen(id: Int) {
        val listActivity = activity
        if (listActivity != null) {
            navigateToTalkDetailActivity(listActivity, id)
        }
    }

}