
package com.androidtutorialshub.project.api

import com.androidtutorialshub.project.data.Item
import com.androidtutorialshub.project.data.RepoResult
import retrofit2.http.GET
import retrofit2.Call


interface ApiService {
  @GET(" /api/products/?format=json") //sample search
  fun searchProdList(): Call<RepoResult>
}