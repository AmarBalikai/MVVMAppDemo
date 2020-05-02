package com.example.mvvmbloginformation.callbacks

import androidx.lifecycle.MutableLiveData
import com.example.mvvmbloginformation.model.ModelBlogInformation

interface ResponseCallback
{
    fun onSuccess(data: MutableLiveData<ModelBlogInformation>?)
    fun onError(error:String?)
}