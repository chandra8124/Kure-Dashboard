package org.flexi.app.domain.model.claim

import kotlinx.serialization.Serializable


@Serializable
data class ClaimModel(
    val data: List<ClaimItem>?,
    val status: String?,
    // val message: String?,
    val total: Int?,
)

@Serializable
data class ClaimItem(
    val adjunctionDate: String?,
    val adjustedAmount: String?,
    val agingCategory: String?,
    val agingDays: String?,
    val allowedAmount: String?,
    val approvedAmount: String?,
    val billingId: String?,
    val billingTherapistNPI: String?,
    val checkDt1: String?,
    val claimAmount: String?,
    val claimBalance: String?,
    val claimNotesAdded: String?,
    val claimService: String?,
    val claimStatus: String?,
    val coPayAmount: String?,
    val coPayStatus: String?,
    val cptCode: String?,
    val cptCodesExpireDate: String?,
    val created_date: String?,
    val denialCode: String?,
    val dos: String?,
    val eftNumber: String?,
    val expectedAmount: String?,
    val facility: String?,
    val headerId: String?,
    val id: String?,
    val insuranceBalance: String?,
    val insurancePayment: String?,
    val insuranceResponsibility: String?,
    val insuranceType: String?,
    val isSession: String?,
    val issueFoundMSG: String?,
   // val learnerResponsibilityFromEob: String?,
    val lineCtrlNumbr: String?,
    val notAssined: String?,
    val paidStatus: String?,
    val patientDOB: String?,
    val patientGender: String?,
    val patientId: String?,
    //val patientInsurancelist: String?,
    val patientName: String?,
    val payerName: String?,
    val paymentAppliedAmount: String?,
    //val paymentAppliedDetails: String?,
    val paymentAppliedNumber: String?,
    val paymentBalance: String?,
    val reason: String?,
    val reasonCode: String?,
    val reasonDescription: String?,
    val refundAmount: String?,
    val releasetoclaim_date: String?,
    val renderingFirstName: String?,
    val renderingLastName: String?,
    val renderingNPI: String?,
    val revenue: String?,
    val serviceUnit: String?,
    // val transferToLearnerBalance: String?,
    val updated_date: String?,
    val uploadbillingId: String?,
    val uploadStatus: String?,
    val upload_date: String?,
    val varianceFromExpected: String?,
    val varianceWriteOff: String?,
    val writeOffAmount: String?,
   // val writeOffData: WriteOffData?,
   // val learnerBalance: LearnerBalance?,
)
@Serializable
data class LearnerBalance(
    val amount: String?,
    val learnerResponsibilityFromEob: String?,
    val status: String?,
   // val transferToLearnerBalance: String?,
   // val updatedDate: String?,
   // val updatedUser: UpdatedUser?,
)

@Serializable
data class WriteOffData(
    val amount: String?,
    val id: String?,
    val insuranceWriteOffAmount: String?,
    val learnerWriteOffAmount: String?,
   // val previousStatus: String?,
    val status: String?,
    val unassignedWriteOffAmount: String?,
    val varianceWriteOffAmount: String?,
)

@Serializable
data class UpdatedUser(
    val firstName: String?,
    val lastName: String?,
    val mobileNumber: String?,
    val userId: String?
)

