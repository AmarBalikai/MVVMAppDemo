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

    private lateinit var repositoryViewModel: RepositoryViewModel
    var mBlogResponse: MutableLiveData<ModelBlogInformation> =
        MutableLiveData<ModelBlogInformation>()

    init {
      //  repositoryViewModel = RepositoryViewModel(objApplication)
        //repositoryViewModel.retrieveCountryFeaturesData(this)
    }
    /**
     * Calling API
     */
    fun getBlogInformation() {
    //    repositoryViewModel.retrieveCountryFeaturesData(this)
    }

    override fun onSuccess(data: MutableLiveData<ModelBlogInformation>?) {

    }

    override fun onError(error: String?) {

    }
}