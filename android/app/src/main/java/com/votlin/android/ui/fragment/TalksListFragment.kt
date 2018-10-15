package com.votlin.android.ui.fragment

import android.os.Bundle
import android.view.View
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.votlin.android.R
import com.votlin.android.extensions.hideMe
import com.votlin.android.extensions.showMe
import com.votlin.client.presentation.TalksListPresenter
import com.votlin.client.presentation.TalksListView
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
                    errorHandler = instance())
        }
    }

    override fun initializeUI() {
        // Nothing to do here
    }


    override fun registerListeners() {
        // Nothing to do here
    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (userVisibleHint) {
            presenter.onViewVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed) {
            presenter.onViewVisible()
        }
    }

    override fun getTrack(): Track {
        val bundle = arguments
        val track = bundle?.getString(TRACK)

        try {
            return Track.valueOf(track!!)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("This fragment only can works if you pass the track")
        }
    }

    override fun showTalks(talks: List<Track>) {
        // todo: add talks to adapter
    }

}