
package com.androidtutorialshub.project.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidtutorialshub.project.R
import com.androidtutorialshub.project.adapters.ProdListAdapter
import com.androidtutorialshub.project.api.RepositoryRetriever
import com.androidtutorialshub.project.data.RepoResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdListActivity : AppCompatActivity(), View.OnClickListener {

    private var searchView: SearchView? = null
    private val repoRetriever = RepositoryRetriever()

    private lateinit var prodList: RecyclerView
    private lateinit var appCompatButtonInfo: AppCompatButton

    private fun initViews() {

        prodList = findViewById<View>(R.id.prodList) as RecyclerView
        appCompatButtonInfo = findViewById<View>(R.id.moreInfo) as AppCompatButton

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prod_list)

        initViews()

        prodList.layoutManager = LinearLayoutManager(this)

        if (isNetworkConnected()) {
            repoRetriever.getRepositories(callback)
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                    .setMessage("Please check your internet connection and try again")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }


    private val callback = object : Callback<RepoResult> {
        override fun onFailure(call: Call<RepoResult>?, t: Throwable?) {
            Log.e("MainActivity", "Problem calling", t)
        }

        override fun onResponse(call: Call<RepoResult>?, response: Response<RepoResult>?) {
            response?.isSuccessful.let {
                val resultList = RepoResult(response?.body()?.items ?: emptyList())
                prodList.adapter = ProdListAdapter(resultList)
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.moreInfo -> info()
        }
    }

    fun info (){
        val accountsIntent = Intent(this@ProdListActivity, UsersListActivity::class.java)
        startActivity(accountsIntent)
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
