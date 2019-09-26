
package com.androidtutorialshub.project.api

import com.androidtutorialshub.project.data.Item
import com.androidtutorialshub.project.data.RepoResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RepositoryRetriever {
  private val service: ApiService

  companion object {
    const val BASE_URL = " http://smktesting.herokuapp.com/"
  }

  init {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) //1
        .addConverterFactory(GsonConverterFactory.create()) //2
        .build()
    service = retrofit.create(ApiService::class.java) //3
  }

  fun getRepositories(callback: Callback<RepoResult>) { //4
      service.searchProdList().enqueue(callback)
  }
}