package org.flexi.app.domain.model.summaryTable

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SummaryTableModel(
    @SerialName("status") var status: Boolean? = null,
    @SerialName("data") var data: SummaryData? = SummaryData()
)

@Serializable
data class SummaryData(
    @SerialName("totalDuration") var totalDuration: String? = null,
    @SerialName("totalAmount") var totalAmount: String? = null,
    @SerialName("expectedAmountTotal") var expectedAmountTotal: String? = null,
    @SerialName("insuranceTotal") var insuranceTotal: String? = null,
    @SerialName("learnerTotal") var learnerTotal: String? = null,
    @SerialName("notAssined") var notAssined: String? = null,
    @SerialName("variance") var variance: String? = null,
    @SerialName("notColllectablePercent") var notColllectablePercent: String? = null,
    @SerialName("notColllectablePercentText") var notColllectablePercentText: String? = null,
    @SerialName("notColllectableExpected") var notColllectableExpected: String? = null,
    @SerialName("insuranceWriteOffAmount") var insuranceWriteOffAmount: String? = null,
    @SerialName("learnerWriteOffAmount") var learnerWriteOffAmount: String? = null,
    @SerialName("notColllectableNotAssigned") var notColllectableNotAssigned: String? = null,
    @SerialName("varianceWriteOffAmount") var varianceWriteOffAmount: String? = null,
    @SerialName("adjPercent") var adjPercent: String? = null,
    @SerialName("adjPercentText") var adjPercentText: String? = null,
    @SerialName("adjAmount") var adjAmount: String? = null,
    @SerialName("adjInsurance") var adjInsurance: String? = null,
    @SerialName("adjLearner") var adjLearner: String? = null,
    @SerialName("adjNotAssigned") var adjNotAssigned: String? = null,
    @SerialName("adjVariance") var adjVariance: String? = null,
    @SerialName("depositedPercent") var depositedPercent: String? = null,
    @SerialName("depositedText") var depositedText: String? = null,
    @SerialName("depositedAmount") var depositedAmount: String? = null,
    @SerialName("depositedInsurance") var depositedInsurance: String? = null,
    @SerialName("depositedLearner") var depositedLearner: String? = null,
    @SerialName("depositedNotAssigned") var depositedNotAssigned: String? = null,
    @SerialName("depositedVariance") var depositedVariance: String? = null,
    @SerialName("accRecPercent") var accRecPercent: String? = null,
    @SerialName("accRecPercentText") var accRecPercentText: String? = null,
    @SerialName("accRecAmount") var accRecAmount: String? = null,
    @SerialName("accRecInsurance") var accRecInsurance: String? = null,
    @SerialName("accRecLearner") var accRecLearner: String? = null,
    @SerialName("accRecNotAssigned") var accRecNotAssigned: String? = null,
    @SerialName("accRecVariance") var accRecVariance: String? = null

)