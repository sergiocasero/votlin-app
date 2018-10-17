package com.votlin.android.ui.activity

import android.content.Context
import android.content.Intent
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
            TalksPresenter(errorHandler = AndroidErrorHandler(),
                    view = this@TalksActivity)
        }
    }

    override fun initializeUI() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(getString(R.string.all), TalksListFragment.newInstance(Track.ALL))
        viewPagerAdapter.addFragment(getString(R.string.business), TalksListFragment.newInstance(Track.BUSINESS))
        viewPagerAdapter.addFragment(getString(R.string.development), TalksListFragment.newInstance(Track.DEVELOPMENT))
        viewPagerAdapter.addFragment(getString(R.string.maker), TalksListFragment.newInstance(Track.MAKER))
        viewPager.adapter = viewPagerAdapter
        tab.setupWithViewPager(viewPager)
    }

    override fun registerListeners() {
        // Nothing to do yet.
    }

    override fun showProgress() {
        // Nothing to do yet
    }

    override fun hideProgress() {
        // Nothing to do yet
    }

}
