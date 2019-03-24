package com.sample.myapplication.view.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.myapplication.utils.listeners.BackButtonHandlerListener
import com.sample.myapplication.utils.listeners.BackPressListener
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), BackPressListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected var mActivity: BaseActivity<*, *>? = null

    var baseActivity: BaseActivity<*, *>? = null
        private set

    private var mRootView: View? = null

    lateinit var viewDataBinding: T
        private set

    lateinit var injectedViewModel: V
        private set

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: Class<V>


    private var backButtonHandler: BackButtonHandlerListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        injectedViewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel)
        setHasOptionsMenu(false)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(bindingVariable, injectedViewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }


    override fun onResume() {
        super.onResume()
        backButtonHandler?.addBackPressListener(this)
    }

    override fun onPause() {
        super.onPause()
        backButtonHandler?.removeBackPressListener(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.baseActivity = activity
            backButtonHandler = context
        }
    }

    override fun onDetach() {
        backButtonHandler?.removeBackPressListener(this)
        backButtonHandler = null
        baseActivity = null
        super.onDetach()
    }

    override fun onBackPress(): Boolean {
        return false
    }
}