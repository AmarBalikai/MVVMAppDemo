package com.example.mvvmbloginformation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmbloginformation.model.ModelBlogInformation
import org.jetbrains.annotations.NotNull

class ViewModelBlogInformation(@NotNull application: Application): ViewModel()
{

    //private lateinit var repositoryViewModel: RepositoryViewModel
    var mBlogResponse: MutableLiveData<ModelBlogInformation> =
        MutableLiveData<ModelBlogInformation>()

    //var apiFailResponse= MutableLiveData<ApiFailModel>()
    init {
      //  repositoryViewModel = RepositoryViewModel(objApplication)
        //apiFailResponse.value= ApiFailModel()
        //repositoryViewModel.retrieveCountryFeaturesData(this)
    }
    /**
     * Calling API
     */
    fun getBlogInformation() {
    //    repositoryViewModel.retrieveCountryFeaturesData(this)
    }
}