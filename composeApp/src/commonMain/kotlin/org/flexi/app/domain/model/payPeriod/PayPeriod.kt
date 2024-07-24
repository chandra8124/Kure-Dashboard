package org.flexi.app.domain.model.payPeriod

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayPeriodItem(
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String,
    @SerialName("payDate") val payDate: String,
    @SerialName("payPeriod") val payPeriod: String
)
@Serializable
data class PayPeriod(
    @SerialName("status") val status: String,
    @SerialName("data") val data: List<PayPeriodItem>
)