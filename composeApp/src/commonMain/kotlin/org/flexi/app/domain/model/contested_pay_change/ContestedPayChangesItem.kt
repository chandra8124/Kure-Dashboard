package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestedPayChangesItem(

    @SerialName("id") var id: String? = null,
    @SerialName("reason") var reason: String? = null,
    @SerialName("payDetails") var payDetails: PayDetails? = PayDetails(),
    @SerialName("oldPayDetails") var oldPayDetails: OldPayDetails? = OldPayDetails(),
    @SerialName("statusHistory") var statusHistory: ArrayList<StatusHistory> = arrayListOf(),
    @SerialName("updatedUser") var updatedUser: UpdatedUser? = UpdatedUser(),
    @SerialName("createdDate") var createdDate: String? = null,
    @SerialName("updatedDate") var updatedDate: String? = null,
    @SerialName("user") var user: User? = User()

)