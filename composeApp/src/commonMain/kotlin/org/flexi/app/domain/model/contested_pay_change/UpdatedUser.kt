package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatedUser(

    @SerialName("userId") var userId: String? = null,
    @SerialName("mobileNumber") var mobileNumber: String? = null,
    @SerialName("firstName") var firstName: String? = null,
    @SerialName("lastName") var lastName: String? = null

)