package com.example.mvvmbloginformation.model

data class ModelBlogInformation(var message:String,var error:String,var status:String,var data: ArrayList<DataInformation>,var isError:Boolean=false)
