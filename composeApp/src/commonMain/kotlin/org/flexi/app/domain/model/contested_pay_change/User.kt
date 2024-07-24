package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(

    @SerialName("id") var id: String? = null,
    @SerialName("npiNumber") var npiNumber: String? = null,
    @SerialName("firstName") var firstName: String? = null,
    @SerialName("lastName") var lastName: String? = null,
    @SerialName("mobileNumber") var mobileNumber: String? = null,
    @SerialName("profilePictureUrl") var profilePictureUrl: String? = null,
    @SerialName("discipline") var discipline: String? = null

)