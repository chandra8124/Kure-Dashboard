package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AcaBenefitClass (

  @SerialName("id"   ) var id   : String? = null,
  @SerialName("name" ) var name : String? = null

)