package com.example.mvvmbloginformation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmbloginformation.callbacks.ResponseCallback
import com.example.mvvmbloginformation.model.ModelBlogInformation
import com.example.mvvmbloginformation.model.ModelResponseStatus
import com.example.mvvmbloginformation.repository.RepositoryViewModel
import com.example.mvvmbloginformation.utils.Constant
import org.jetbrains.annotations.NotNull

class ViewModelBlogInformation(@NotNull application: Application) : AndroidViewModel(application),
    ResponseCallback {
    private var repositoryViewModel: RepositoryViewModel = RepositoryViewModel()
    var mBlogResponse: MutableLiveData<ModelBlogInformation> =
        MutableLiveData<ModelBlogInformation>()
    var mBlogResponseStatus: MutableLiveData<ModelResponseStatus> =
        MutableLiveData<ModelResponseStatus>()

    /**
     * one time initialize
     * */
    init {
        mBlogResponseStatus.value = ModelResponseStatus("", Constant.statusLoading)
        repositoryViewModel.retrieveBlogData(this)
    }

    /**
     * Calling API
     */
    fun getBlogInformation() {
        repositoryViewModel.retrieveBlogData(this)
    }

    /**
     * API success response
     * */

    override fun onSuccess(data: MutableLiveData<ModelBlogInformation>?) {
        mBlogResponse.value = data?.value
        mBlogResponseStatus.value = ModelResponseStatus("", Constant.statusSuccess)
    }

    /**
     * API failure response
     * */
    override fun onError(error: String?) {
        mBlogResponseStatus.value = ModelResponseStatus(error.toString(), Constant.statusFail)
    }
}