package com.example.catfacts.models

import com.google.gson.annotations.SerializedName

data class CatFacts (
    @SerializedName("_id")
    var id: String?,

    @SerializedName("text")
    var text: String?
)