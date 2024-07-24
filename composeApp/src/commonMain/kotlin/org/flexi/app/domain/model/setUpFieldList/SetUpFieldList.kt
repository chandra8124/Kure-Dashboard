package org.flexi.app.domain.model.setUpFieldList

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SetUpFieldList(
    @SerialName("status") val status: String,
    @SerialName("total") val total: Int,
    @SerialName("data") val data: SetUpFieldListItem
)

@Serializable
data class SetUpFieldListItem(
    @SerialName("employmentList") val employmentList: Map<String, String>,
    @SerialName("statusList") val statusList: Map<String, String>,
    @SerialName("facilityList") val facilityList: List<Facility>,
    @SerialName("sessionNotesCredentialList") val sessionNotesCredentialList: Map<String, String>,
    @SerialName("dsciplineList") val dsciplineList: Map<String, String>,
    @SerialName("insuranceGender") val insuranceGender: Map<String, String>,
    @SerialName("busibessUnits") val busibessUnits: Map<String, String>,
    @SerialName("companyCodes") val companyCodes: Map<String, String>,
    @SerialName("homeDepartments") val homeDepartments: Map<String, String>,
    @SerialName("reasonForHire") val reasonForHire: Map<String, String>,
    @SerialName("ethnicityRaceId") val ethnicityRaceId: Map<String, String>,
    @SerialName("ethnicity") val ethnicity: Map<String, String>,
    @SerialName("race") val race: Map<String, String>,
    @SerialName("preferredPronouns") val preferredPronouns: Map<String, String>,
    @SerialName("payFrequency") val payFrequency: Map<String, String>,
    @SerialName("benefitsEligibilityClass") val benefitsEligibilityClass: Map<String, String>,
    @SerialName("ACABenefitStatus") val ACABenefitStatus: Map<String, String>,
    @SerialName("naicsWorkersComp") val naicsWorkersComp: Map<String, String>,
    @SerialName("selfEmploymentIndividual") val selfEmploymentIndividual: Map<String, String>,
    @SerialName("taxIDType") val taxIDType: Map<String, String>,
    @SerialName("nationalIdentifier") val nationalIdentifier: Map<String, String>,
    @SerialName("maritalStatus") val maritalStatus: Map<String, String>,
    @SerialName("federalFillingStatus") val federalFillingStatus: Map<String, String>,
    @SerialName("workedInState") val workedInState: Map<String, String>,
    @SerialName("SuiSdiTaxCode") val SuiSdiTaxCode: Map<String, String>,
    @SerialName("includeInTimeSummaryPayroll") val includeInTimeSummaryPayroll: Map<String, String>,
    @SerialName("basisOfPay") val basisOfPay: Map<String, String>,
    @SerialName("paymentType") val paymentType: Map<String, String>,
    @SerialName("payClass") val payClass: Map<String, String>,
    @SerialName("nonClinicalPositionList") val nonClinicalPositionList: Map<String, String>,
    @SerialName("adminFacility") val adminFacility: List<Facility>
)

@Serializable
data class Facility(
    @SerialName("id") val id: String,
    @SerialName("sortname") val sortname: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("scheduleFacility") val scheduleFacility: String,
    @SerialName("address") val address: String
)