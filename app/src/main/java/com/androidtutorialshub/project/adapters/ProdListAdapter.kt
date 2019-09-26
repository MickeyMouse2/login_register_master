
package com.androidtutorialshub.project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidtutorialshub.project.R
import com.androidtutorialshub.project.data.Item
import com.androidtutorialshub.project.data.RepoResult
import com.androidtutorialshub.project.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_prod_repo.view.*//1

class ProdListAdapter(private val repoList: RepoResult) : RecyclerView.Adapter<ProdListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_prod_repo, parent, false) //2
    return ViewHolder(view)
  }


  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindRepo(repoList.items[position]) //3
  }

  override fun getItemCount(): Int = repoList.items.size //4

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindRepo(repo: Item) { //5
      itemView.repoName.text = repo.title //7
      itemView.repoDescription.text = repo.text
      Picasso.get().load(repo.img_url).into(itemView.icon)
    }
  }
}