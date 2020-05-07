package com.example.mvvmbloginformation.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmbloginformation.callbacks.ResponseCallback
import com.example.mvvmbloginformation.model.ModelBlogInformation
import com.example.mvvmbloginformation.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel {
    /**
     * Method for API call
     * */
    fun retrieveBlogData(objCallback: ResponseCallback) {
        val listResponse: MutableLiveData<ModelBlogInformation> = MutableLiveData()

        val data: Call<ModelBlogInformation>? = ApiClient.build()?.getBlogList()
        val enqueue = data?.enqueue(object : Callback<ModelBlogInformation> {
            override fun onResponse(
                call: Call<ModelBlogInformation>,
                response: Response<ModelBlogInformation>
            ) {
                if (response.isSuccessful) {

                    listResponse.value = response.body()
                    /**
                     * Send success response to viewModel using this callback
                     */
                    objCallback.onSuccess(listResponse)
                }
            }

            override fun onFailure(call: Call<ModelBlogInformation>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onError(t.message)
            }
        })

    }
}
