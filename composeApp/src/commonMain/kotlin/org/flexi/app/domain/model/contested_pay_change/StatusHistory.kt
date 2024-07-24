package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusHistory(

    @SerialName("status") var status: String? = null,
    @SerialName("reason") var reason: String? = null,
    @SerialName("updated_date") var updatedDate: String? = null

)