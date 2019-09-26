
package com.androidtutorialshub.project.data

import com.google.gson.annotations.SerializedName

data class RepoResult(val items: List<Item>)

data class Item (
    val id: String = "",
    val title: String = "",
    val img_url: String = "",
    val text: String = ""
)