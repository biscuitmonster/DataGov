package com.jacob.datagov.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jacob.datagov.api.entity.Result


class ResponseGetAction {

    @SerializedName("help")
    @Expose
    var help: String? = null
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("result")
    @Expose
    var result: Result? = null

}