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

import com.example.mvvmbloginformation.utils.NetworkConnection
import com.example.mvvmbloginformation.viewmodel.ViewModelBlogInformation
import kotlinx.android.synthetic.main.activity_blog_information.*

class BlogInformationActivity : AppCompatActivity() {
    private lateinit var mViewModelBlogInformation: ViewModelBlogInformation
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var mAdapter: AdapterBlog
    private lateinit var linearLayoutManager: LinearLayoutManager

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_information)
        mViewModelBlogInformation =
            ViewModelProvider(this).get(ViewModelBlogInformation::class.java)
        setupDialog()


        swipeToRefresh.setOnRefreshListener {
            getBlogFromViewModel()

        }


        mAdapter = AdapterBlog(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        blog_list.layoutManager = linearLayoutManager
        blog_list.adapter = mAdapter

        mViewModelBlogInformation.mBlogResponse.observe(this, Observer {
            hideDialog()
            swipeToRefresh.isRefreshing = false
            mAdapter.setList(it.data)

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
            showDialog()
            mViewModelBlogInformation.getBlogInformation()
        } else {
            if (swipeToRefresh.isRefreshing) {
                swipeToRefresh.isRefreshing = false
            }
            Toast.makeText(
                applicationContext,
                getString(R.string.device_not_connected_to_internet),
                Toast.LENGTH_LONG
            ).show()
        }

    }

}


