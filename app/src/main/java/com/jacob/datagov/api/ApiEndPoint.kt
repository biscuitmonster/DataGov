package com.jacob.datagov.api

import com.jacob.datagov.api.response.ResponseGetAction
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
    fun getActionData(): Call<ResponseGetAction>


}
