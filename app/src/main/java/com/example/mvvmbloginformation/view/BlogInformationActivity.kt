package com.example.mvvmbloginformation.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmbloginformation.R
import com.example.mvvmbloginformation.adapter.AdapterBlog
import com.example.mvvmbloginformation.utils.Constant
import com.example.mvvmbloginformation.utils.Constant.Companion.somethingWentWrong

import com.example.mvvmbloginformation.utils.NetworkConnection
import com.example.mvvmbloginformation.utils.toast
import com.example.mvvmbloginformation.viewmodel.ViewModelBlogInformation
import kotlinx.android.synthetic.main.activity_blog_information.*

class BlogInformationActivity : AppCompatActivity() {
    private lateinit var mViewModelBlogInformation: ViewModelBlogInformation
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var mAdapter: AdapterBlog
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var isSwipeToRefreshCall: Boolean = false

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_information)
        /**
         * Initialize ViewModel
         * */
        mViewModelBlogInformation =
            ViewModelProvider(this).get(ViewModelBlogInformation::class.java) //mViewModelStore
        /**
         * Initialise Dialog
         * */
        setupDialog()

        /**
         * Swipe to refresh listener
         * */
        swipeToRefresh.setOnRefreshListener {
            isSwipeToRefreshCall = true
            getBlogFromViewModel()
        }
        /**
         * Adapter Initialize
         * */
        mAdapter = AdapterBlog(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        blog_list.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = mAdapter
        }

        /**
         * API Live data observer
         * */
        mViewModelBlogInformation.mBlogResponse.observe(this, Observer {
            if (isSwipeToRefreshCall)
                swipeToRefresh.isRefreshing = false
            else
                hideDialog()
            mAdapter.setList(it.data)
        })
        /**
         * API status live data observer
         * */
        mViewModelBlogInformation.mBlogResponseStatus.observe(this, Observer {
            it.let {
                when (it.status) {
                    Constant.statusFail -> {
                        if (swipeToRefresh.isRefreshing)
                            swipeToRefresh.isRefreshing = false
                        hideDialog()
                        toast(somethingWentWrong)
                    }
                    Constant.statusLoading ->
                        showDialog()
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupDialog() {
        builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()

    }

    /**
     * Showing dialog when api call
     */
    private fun showDialog() {

        dialog.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    /**
     * Hiding dialog
     */
    private fun hideDialog() {
        dialog.let {
            if (it.isShowing) {
                it.hide()
                it.dismiss()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.let {
            if (it.isShowing) {
                it.hide()
                it.dismiss()
            }
        }
    }

    /**
     * This method for get data from the viewModel
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getBlogFromViewModel() {

        if (NetworkConnection.isNetworkConnected()) {
            if (!isSwipeToRefreshCall)
                showDialog()
            mViewModelBlogInformation.getBlogInformation()
        } else {
            if (swipeToRefresh.isRefreshing)
                swipeToRefresh.isRefreshing = false
            toast(getString(R.string.device_not_connected_to_internet))
        }

    }

}


