package com.example.mvvmbloginformation.network

import com.example.mvvmbloginformation.model.ModelBlogInformation
import com.example.mvvmbloginformation.utils.Constant
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface
{
    /**
     * This method is getting for list's of objects from server
     */
    @GET(Constant.blogUrl)
    fun getBlogList(): Call<ModelBlogInformation>
}