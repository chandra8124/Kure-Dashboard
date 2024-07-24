package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestedPayChanges(

    @SerialName("status") var status: Boolean? = null,
    @SerialName("data") var data: ArrayList<ContestedPayChangesItem> = arrayListOf()

)