package com.example.mvvmbloginformation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmbloginformation.callbacks.ResponseCallback
import com.example.mvvmbloginformation.model.ModelBlogInformation
import com.example.mvvmbloginformation.repository.RepositoryViewModel
import org.jetbrains.annotations.NotNull

class ViewModelBlogInformation(@NotNull application: Application): AndroidViewModel(application),
    ResponseCallback
{
    private var repositoryViewModel: RepositoryViewModel = RepositoryViewModel()
    var mBlogResponse: MutableLiveData<ModelBlogInformation> =
        MutableLiveData<ModelBlogInformation>()

    init {
        repositoryViewModel.retrieveBlogsData(this)
    }
    /**
     * Calling API
     */
    fun getBlogInformation() {
        repositoryViewModel.retrieveBlogsData(this)
    }

    override fun onSuccess(data: MutableLiveData<ModelBlogInformation>?) {
        mBlogResponse.value=data?.value
    }

    override fun onError(error: String?) {
        mBlogResponse.value?.error= error.toString()
        mBlogResponse.value?.isError=true
    }
}