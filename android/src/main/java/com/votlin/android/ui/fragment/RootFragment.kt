package com.votlin.android.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.lazy
import com.votlin.android.extensions.toast
import com.votlin.android.ui.activity.RootActivity
import com.votlin.common.client.presentation.Presenter


/**
 * RootFragment
 */
abstract class RootFragment<out V : Presenter.View> : Fragment(), KodeinInjected, Presenter.View {

    abstract val presenter: Presenter<V>

    abstract val layoutResourceId: Int

    override val injector = KodeinInjector()

    abstract val fragmentModule: Kodein.Module

    val kodein by Kodein.lazy {
        extend((activity as RootActivity<*>).kodein)
        import(fragmentModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initializeDI()
        initializeUI()
        registerListeners()

        presenter.initialize()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutResourceId, container, false)

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private fun initializeDI() {
        inject(kodein)
    }

    abstract fun initializeUI()

    abstract fun registerListeners()

    override fun showError(error: String) {
        toast(error)
    }

    override fun showMessage(message: String) {
        toast(message, Toast.LENGTH_SHORT)
    }

}