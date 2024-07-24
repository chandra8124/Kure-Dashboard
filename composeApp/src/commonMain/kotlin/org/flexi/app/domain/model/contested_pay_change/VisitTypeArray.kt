package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VisitTypeArray(

    @SerialName("label") var label: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("amount") var amount: String? = null,
    @SerialName("completeAmount") var completeAmount: String? = null,
    @SerialName("deadlineAction") var deadlineAction: String? = null,
    @SerialName("deadline") var deadline: String? = null

)