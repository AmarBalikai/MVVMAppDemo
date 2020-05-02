package com.example.mvvmbloginformation.callbacks

import androidx.lifecycle.MutableLiveData
import com.example.mvvmbloginformation.model.ModelBlogInformation

interface NetworkCallback
{
    fun onSuccess(data: MutableLiveData<ModelBlogInformation>?)
    fun onError(error:String?)
}