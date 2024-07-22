package org.flexi.app.domain.model.patientemsalldata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatientEmSallData(
    @SerialName("status") var status: String? = null,
    @SerialName("total") var total: Int? = null,
    @SerialName("cronTimeRelease") var cronTimeRelease: Int? = null,
    @SerialName("data") var data: ArrayList<PatiendEmSellModel> = arrayListOf()
)

@Serializable
data class PatiendEmSellModel(

    @SerialName("patientId") var patientId: String? = null,
    @SerialName("patientFName") var patientFName: String? = null,
    @SerialName("patientLName") var patientLName: String? = null,
    @SerialName("totalWorkedHr") var totalWorkedHr: String? = null,
    @SerialName("totalScheduleHr") var totalScheduleHr: String? = null,
    @SerialName("onlyScheduleHr") var onlyScheduleHr: String? = null,
    @SerialName("continuityArr") var continuityArr: ArrayList<String> = arrayListOf(),
    @SerialName("visitsTotal") var visitsTotal: Int? = null,
    @SerialName("totalVisitUnit") var totalVisitUnit: Int? = null,
    @SerialName("totalVisitAmount") var totalVisitAmount: Int? = null,
    @SerialName("billingReleasedTotal") var billingReleasedTotal: String? = null,
    @SerialName("claimedWorkedTotal") var claimedWorkedTotal: String? = null,
    @SerialName("readyForReleaseTotal") var readyForReleaseTotal: String? = null,
    @SerialName("pendingReleaseTotal") var pendingReleaseTotal: String? = null,
    @SerialName("cptCodesExpireDate") var cptCodesExpireDate: String? = null,
    @SerialName("approvedUnits") var approvedUnits: ArrayList<String> = arrayListOf(),
    @SerialName("visits") var visits: ArrayList<String> = arrayListOf(),
    @SerialName("billingReleased") var billingReleased: ArrayList<String> = arrayListOf(),
    @SerialName("claimedWorked") var claimedWorked: ArrayList<ClaimedWorked> = arrayListOf(),
    @SerialName("cronTimeRelease") var cronTimeRelease: Int? = null,
    @SerialName("readyForRelease") var readyForRelease: ArrayList<String> = arrayListOf(),
    @SerialName("pendingRelease") var pendingRelease: ArrayList<String> = arrayListOf()

)

@Serializable
data class ClaimedWorked(

    @SerialName("id") var id: String? = null,
    @SerialName("insuraneType") var insuraneType: String? = null,
    @SerialName("patientFName") var patientFName: String? = null,
    @SerialName("patientLName") var patientLName: String? = null,
    @SerialName("serviceUnit") var serviceUnit: Int? = null,
    @SerialName("cptCode") var cptCode: String? = null,
    @SerialName("headerId") var headerId: String? = null,
    @SerialName("therapistName") var therapistName: String? = null,
    @SerialName("claimAmount") var claimAmount: String? = null,
    @SerialName("dos") var dos: String? = null,
    @SerialName("adjunctionDate") var adjunctionDate: String? = null,
    @SerialName("claimStatus") var claimStatus: String? = null,
    @SerialName("denialCode") var denialCode: String? = null,
    @SerialName("previousClaimStatus") var previousClaimStatus: String? = null,
    @SerialName("updatedBy") var updatedBy: String? = null

)


