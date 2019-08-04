package com.jacob.datagov.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Field {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}