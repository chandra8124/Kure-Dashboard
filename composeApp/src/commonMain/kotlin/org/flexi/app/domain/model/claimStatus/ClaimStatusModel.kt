package org.flexi.app.domain.model.claimStatus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClaimStatusModel(
    @SerialName("status") var status: String? = null,
    @SerialName("data") var data: ClaimStatusData? = ClaimStatusData()

)
@Serializable
data class Adjudicated(
    @SerialName("Denied") var Denied: CommonData? = CommonData(),
    @SerialName("Approved") var Approved: CommonData? = CommonData(),
    @SerialName("Deposited") var Deposited: CommonData? = CommonData(),
    @SerialName("DeniedSecondary") var DeniedSecondary: CommonData? = CommonData()

)
@Serializable
data class BalanceOwed(
    @SerialName("Claim Closed")
    var ClaimClosed: CommonData? = CommonData(),
    @SerialName("Lrn.  Balance Due") var LrnBalanceDue: CommonData? = CommonData()

)
@Serializable
data class CommonData(
    @SerialName("claimStatus") var claimStatus: String? = null,
    @SerialName("claimStatusName") var claimStatusName: String? = null,
    @SerialName("Total") var Total: Int? = null,
    @SerialName("Amount") var Amount: Double? = null,
    @SerialName("expectedAmount") var expectedAmount: Double? = null

)
@Serializable
data class ClaimNew(
    @SerialName("pendingReleased") var pendingReleased: PendingReleased? = PendingReleased(),
    @SerialName("reBilled") var reBilled: ReBilled? = ReBilled(),
    @SerialName("adjudicated") var adjudicated: Adjudicated? = Adjudicated(),
    @SerialName("balanceOwed") var balanceOwed: BalanceOwed? = BalanceOwed(),
    @SerialName("releaseToPayer") var releaseToPayer: ReleaseToPayer? = ReleaseToPayer(),
    @SerialName("nonBillable") var nonBillable: NonBilling? = NonBilling()

)

@Serializable
data class ClaimStatusData(

    @SerialName("displayStatusGroupName") var displayStatusGroupName: DisplayStatusGroupName? = DisplayStatusGroupName(),
    @SerialName("totalClaim") var totalClaim: Int? = null,
    @SerialName("claimNew") var claimNew: ClaimNew? = ClaimNew(),
    @SerialName("allowedtotalAmt") var allowedtotalAmt: Double? = null,
    @SerialName("allowedtotalCollectedAmt") var allowedtotalCollectedAmt: Double? = null,
    @SerialName("insurancetotalAmt") var insurancetotalAmt: Double? = null,
    @SerialName("insurancetotalCollectedAmt") var insurancetotalCollectedAmt: Double? = null,
    @SerialName("learnertotalAmt") var learnertotalAmt: Double? = null,
    @SerialName("payerNameArray") var payerNameArray: ArrayList<String> = arrayListOf(),
    @SerialName("totalPayments") var totalPayments: Int? = null,
    @SerialName("insuranceBalance") var insuranceBalance: Double? = null,
    @SerialName("writeoff") var writeoff: Writeoff? = Writeoff(),
    @SerialName("learnertotalCollectedAmt") var learnertotalCollectedAmt: Double? = null

)
@Serializable
data class DisplayStatusGroupName(
    @SerialName("balanceOwed") var balanceOwed: String? = null,
    @SerialName("adjudicated") var adjudicated: String? = null,
    @SerialName("releaseToPayer") var releaseToPayer: String? = null,
    @SerialName("pendingReleased") var pendingReleased: String? = null,
    @SerialName("reBilled") var reBilled: String? = null,
    @SerialName("nonBillable") var nonBillable: String? = null

)
@Serializable
data class PendingReleased(

    @SerialName("Pending")
    var Pending: CommonData? = CommonData(),
    @SerialName("Missing Notes")
    var MissingNotes: CommonData? = CommonData(),
    @SerialName("RELEASETOCLAIM") var RELEASETOCLAIM: CommonData? = CommonData()

)

@Serializable
data class NonBilling(

    @SerialName("NONBILLABLE")
    var NONBILLABLE: CommonData? = CommonData(),
    @SerialName("WRITEOFFCLAIM")
    var WRITEOFFCLAIM: CommonData? = CommonData(),
    @SerialName("WRITEOFFCLAIM-PARTIAL")
    var WRITEOFFCLAIM_PARTIAL: CommonData? = CommonData()

)
@Serializable
data class ReBilled(
    @SerialName("ResubmittedClaims") var ResubmittedClaims: CommonData? = CommonData(),
    @SerialName("CorrectedClaims") var CorrectedClaims: CommonData? = CommonData(),
    @SerialName("ToSecondary") var ToSecondary: CommonData? = CommonData()

)

@Serializable
data class ReleaseToPayer(

    @SerialName("Accept") var Accept: CommonData? = CommonData(),
    @SerialName("CLAIMRELEASED") var CLAIMRELEASED: CommonData? = CommonData()

)
@Serializable
data class Writeoff(
    @SerialName("pendingWriteOffCount") var pendingWriteOffCount: Int? = null,
    @SerialName("approvedInsuranceBalance") var approvedInsuranceBalance: Int? = null,
    @SerialName("approvedLearnerBalance") var approvedLearnerBalance: Int? = null,
    @SerialName("approvedVarianceBalance") var approvedVarianceBalance: Int? = null

)