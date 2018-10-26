package com.votlin.android.ui.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.votlin.android.R
import com.votlin.android.error.AndroidErrorHandler
import com.votlin.android.ui.adapter.ViewPagerAdapter
import com.votlin.android.ui.fragment.TalksListFragment
import com.votlin.client.presentation.TalksPresenter
import com.votlin.client.presentation.TalksView
import com.votlin.model.Track
import kotlinx.android.synthetic.main.activity_talks.*

class TalksActivity : RootActivity<TalksView>(), TalksView {

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, TalksActivity::class.java)
    }

    override val presenter: TalksPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_talks

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<TalksPresenter>() with provider {
            TalksPresenter(
                errorHandler = AndroidErrorHandler(),
                view = this@TalksActivity
            )
        }
    }

    override fun initializeUI() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(getString(R.string.all), TalksListFragment.newInstance(Track.ALL))
        viewPagerAdapter.addFragment(getString(R.string.business), TalksListFragment.newInstance(Track.BUSINESS))
        viewPagerAdapter.addFragment(getString(R.string.development), TalksListFragment.newInstance(Track.DEVELOPMENT))
        viewPagerAdapter.addFragment(getString(R.string.maker), TalksListFragment.newInstance(Track.MAKER))
        viewPager.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = 4
        }
        tab.setupWithViewPager(viewPager)
    }

    override fun registerListeners() {
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                // nothing to do
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // nothing to do
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> presenter.onTrackSelected(Track.ALL)
                    1 -> presenter.onTrackSelected(Track.BUSINESS)
                    2 -> presenter.onTrackSelected(Track.DEVELOPMENT)
                    3 -> presenter.onTrackSelected(Track.MAKER)
                }
            }

        })
    }

    override fun showProgress() {
        // Nothing to do yet
    }

    override fun hideProgress() {
        // Nothing to do yet
    }

    override fun navigateToTrackList(track: Track) {
        // Nothing to do here because viewPager automatically handle this behaviour
    }

}
