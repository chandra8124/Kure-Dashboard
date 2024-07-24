package org.flexi.app.domain.model.contested_pay_change

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayDetails(

    @SerialName("acaBenefitClass") var acaBenefitClass: AcaBenefitClass? = AcaBenefitClass(),
    @SerialName("baseClinicalRatePerHour") var baseClinicalRatePerHour: String? = null,
    @SerialName("benefitEligibilityClass") var benefitEligibilityClass: BenefitEligibilityClass? = BenefitEligibilityClass(),
    @SerialName("chat_id") var chatId: String? = null,
    @SerialName("chooseThePercent") var chooseThePercent: String? = null,
    @SerialName("clinicalRatePerHour") var clinicalRatePerHour: String? = null,
    @SerialName("clinicalRateTwo") var clinicalRateTwo: String? = null,
    @SerialName("conversation_id") var conversationId: String? = null,
    @SerialName("cptCodes") var cptCodes: String? = null,
    @SerialName("differentialPay") var differentialPay: String? = null,
    @SerialName("feeSetup") var feeSetup: String? = null,
    @SerialName("holidayPayPerHour") var holidayPayPerHour: String? = null,
    @SerialName("incentivePay") var incentivePay: String? = null,
    @SerialName("internal_message_id") var internalMessageId: String? = null,
    @SerialName("milageReinbursmentPerMile") var milageReinbursmentPerMile: String? = null,
    @SerialName("minimumPay") var minimumPay: String? = null,
    @SerialName("minimumPayEndDate") var minimumPayEndDate: String? = null,
    @SerialName("minimumPayHoursEquivalents") var minimumPayHoursEquivalents: String? = null,
    @SerialName("minimumPayStartDate") var minimumPayStartDate: String? = null,
    @SerialName("naicsWorkerComp") var naicsWorkerComp: NaicsWorkerComp? = NaicsWorkerComp(),
    @SerialName("nonClinicalRatePerHour") var nonClinicalRatePerHour: String? = null,
    @SerialName("onboard_conversation_id") var onboardConversationId: String? = null,
    @SerialName("payPeriod") var payPeriod: String? = null,
    @SerialName("payStructure") var payStructure: String? = null,
    @SerialName("perYear") var perYear: String? = null,
    @SerialName("ptoPayPerHour") var ptoPayPerHour: String? = null,
    @SerialName("rampupDate") var rampupDate: String? = null,
    @SerialName("rampupRate") var rampupRate: String? = null,
    @SerialName("rateEffectiveDate") var rateEffectiveDate: String? = null,
    @SerialName("salary") var salary: String? = null,
    @SerialName("sei") var sei: Sei? = Sei(),
    @SerialName("therapistProctorRate") var therapistProctorRate: Int? = null,
    @SerialName("visitTypeArray") var visitTypeArray: ArrayList<VisitTypeArray> = arrayListOf(),
    @SerialName("workerType") var workerType: String? = null,
    @SerialName("reason") var reason: String? = null

)