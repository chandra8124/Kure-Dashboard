package org.flexi.app.domain.model.payrollSalaryListing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayrollSalaryListing(
    @SerialName("status") var status: String? = null,
    @SerialName("total") var total: Double? = 0.0,
    @SerialName("data") var data: ArrayList<PayrollSalaryItem> = arrayListOf()
)

@Serializable
data class PayrollSalaryItem(
    @SerialName("Supervisor") var Supervisor: ArrayList<String> = arrayListOf(),
    @SerialName("absentHours") var absentHours: Double? = 0.0,
    @SerialName("adpAmount") var adpAmount: Double? = 0.0,
    @SerialName("adpHours") var adpHours: Double? = 0.0,
    @SerialName("adpPerHours") var adpPerHours: Double? = 0.0,
    @SerialName("approvedExpenses") var approvedExpenses: Double? = 0.0,
    @SerialName("approvedMileage") var approvedMileage: Double? = 0.0,
    @SerialName("baseHour") var baseHour: Double? = 0.0,
    @SerialName("basePay") var basePay: Double? = 0.0,
    @SerialName("cl2") var cl2: String? = null,
    @SerialName("cl2Hours") var cl2Hours: String? = null,
    @SerialName("consultPtoPayPerHour") var consultPtoPayPerHour: Double? = 0.0,
    @SerialName("consultTotalLeaveAmount") var consultTotalLeaveAmount: Double? = 0.0,
    @SerialName("consultTotalLeaveHours") var consultTotalLeaveHours: Double? = 0.0,
    @SerialName("discipline") var discipline: ArrayList<String> = arrayListOf(),
    @SerialName("employmentStatus") var employmentStatus: String? = null,
    @SerialName("expenses") var expenses: Double? = 0.0,
    @SerialName("extraShiftBaseAmount") var extraShiftBaseAmount: Double? = 0.0,
    @SerialName("extraShiftBaseHour") var extraShiftBaseHour: Double? = 0.0,
    @SerialName("extraShiftCompletedAmount") var extraShiftCompletedAmount: Double? = 0.0,
    @SerialName("extraShiftCompletedHour") var extraShiftCompletedHour: Double? = 0.0,
    @SerialName("extraShiftHours") var extraShiftHours: Double? = 0.0,
    @SerialName("facility") var facility: ArrayList<String> = arrayListOf(),
    //@SerialName("facilityDetails") var facilityDetails: ArrayList<FacilityDetails> = arrayListOf(),
    @SerialName("facilityName") var facilityName: ArrayList<String> = arrayListOf(),
    @SerialName("grossDetails") var grossDetails: String? = null,
    @SerialName("grossTotal") var grossTotal: Double? = 0.0,
    @SerialName("holidayPay") var holidayPay: Double? = 0.0,
    @SerialName("holidayPayPerHour") var holidayPayPerHour: Double? = 0.0,
    @SerialName("id") var id: String? = null,
   // @SerialName("incentivePay") var incentivePay: Int? = null,
   // @SerialName("incentiveRate") var incentiveRate: Int? = null,
    //@SerialName("incentivehrs") var incentivehrs: Int? = null,
    @SerialName("mileage") var mileage: Double? = 0.0,
    @SerialName("minimunPayAmount") var minimunPayAmount: Double? = 0.0,
    @SerialName("minimunPayHours") var minimunPayHours: Double? = 0.0,
   // @SerialName("minimunPayPerHour") var minimunPayPerHour: Int? = null,
   // @SerialName("nonClinicalAmount") var nonClinicalAmount: Int? = null,
    @SerialName("nonClinicalAmountTraining") var nonClinicalAmountTraining: Double? = 0.0,
    @SerialName("nonClinicalHour") var nonClinicalHour: String? = null,
    @SerialName("nonClinicalHourDenied") var nonClinicalHourDenied: String? = null,
    @SerialName("nonClinicalHourPending") var nonClinicalHourPending: String? = null,
    @SerialName("nonClinicalHourPendingTraining") var nonClinicalHourPendingTraining: String? = null,
    @SerialName("nonClinicalHourTraining") var nonClinicalHourTraining: String? = null,
   // @SerialName("nonClinicalHourTrainingOverTime") var nonClinicalHourTrainingOverTime: Double? = 0.0,
    //@SerialName("nonClinicalRate") var nonClinicalRate: Int? = null,
    @SerialName("npiNumber") var npiNumber: String? = null,
   // @SerialName("oneHourRateClinic") var oneHourRateClinic: Int? = null,
    @SerialName("overTime") var overTime: Double? = 0.0,
    @SerialName("overTimeAmountBase") var overTimeAmountBase: String? = null,
    @SerialName("overTimeAmountcl2") var overTimeAmountcl2: String? = null,
   // @SerialName("overTimeBasePerHour") var overTimeBasePerHour: Int? = null,
    //@SerialName("overTimeCl2PerHour") var overTimeCl2PerHour: Int? = null,
    @SerialName("overTimeHours") var overTimeHours: String? = null,
    @SerialName("overTimeHoursBase") var overTimeHoursBase: String? = null,
    @SerialName("overTimeHoursCl2") var overTimeHoursCl2: String? = null,
    @SerialName("overTimeHoursNCL") var overTimeHoursNCL: Double? = 0.0,
    @SerialName("payStatus") var payStatus: PayStatus? = PayStatus(),
    @SerialName("payStructure") var payStructure: String? = null,
    @SerialName("payType") var payType: String? = null,
    @SerialName("paymentType") var paymentType: String? = null,
    @SerialName("payrollId") var payrollId: String? = null,
    @SerialName("primaryFacility") var primaryFacility: String? = null,
    @SerialName("proctor") var proctor: Double? = 0.0,
    @SerialName("proctorHours") var proctorHours: String? = null,
    @SerialName("proctorPayPerHour") var proctorPayPerHour: Double? = 0.0,
   // @SerialName("proctorStatus") var proctorStatus: ArrayList<String> = arrayListOf(),
    @SerialName("ramp") var ramp: String? = null,
    @SerialName("rampHours") var rampHours: String? = null,
    //@SerialName("rampupPayPerHour") var rampupPayPerHour: Int? = null,
    //@SerialName("rate2") var rate2: String? = null,
   // @SerialName("rbtstatus") var rbtstatus: String? = null,
    @SerialName("satShiftCompletedAmount") var satShiftCompletedAmount: Double? = 0.0,
    @SerialName("satShiftHours") var satShiftHours: String? = null,
    @SerialName("status") var status: String? = null,
    @SerialName("therapistName") var therapistName: String? = null,
    //@SerialName("total") var total: Int? = null,
    @SerialName("totalClinicalHours") var totalClinicalHours: String? = null,
    @SerialName("totalDistance") var totalDistance: String? = null,
   // @SerialName("totalExpenceStatus") var totalExpenceStatus: ArrayList<String> = arrayListOf(),
    @SerialName("totalHolidays") var totalHolidays: String? = null,
   // @SerialName("totalMileageStatus") var totalMileageStatus: ArrayList<String> = arrayListOf(),
    @SerialName("totalNonClinicalHours") var totalNonClinicalHours: Double? = 0.0,
    @SerialName("totalNonClinicalHoursTraining") var totalNonClinicalHoursTraining: String? = null,
    @SerialName("totalProctorPaidHoursDenied") var totalProctorPaidHoursDenied: String? = null,
    @SerialName("totalProctorPaidHoursPending") var totalProctorPaidHoursPending: String? = null,
   // @SerialName("totalWorkHours") var totalWorkHours: Int? = null,
    //@SerialName("totalnoClStatus") var totalnoClStatus: ArrayList<String> = arrayListOf(),
    //@SerialName("totalnoClStatusTrainig") var totalnoClStatusTrainig: ArrayList<String> = arrayListOf(),
    @SerialName("userId") var userId: String? = null,
    @SerialName("weekEndDate") var weekEndDate: String? = null,
    @SerialName("weekStartDate") var weekStartDate: String? = null,
    @SerialName("workerType") var workerType: String? = null,
    //@SerialName("totalWorkHoursNonCl") var totalWorkHoursNonCl: Double? = 0.0,
    //@SerialName("workedHoursNonBillable") var workedHoursNonBillable: Int? = null,
    @SerialName("payDetails") var payDetails: PayDetails? = PayDetails(),
    @SerialName("ewID") var ewID: String? = null,
   // @SerialName("bankInfoData") var bankInfoData: BankInfoData? = BankInfoData(),
    //@SerialName("facilityWiseShiftWise") var facilityWiseShiftWise: FacilityWiseShiftWise? = FacilityWiseShiftWise()
)

@Serializable
data class FacilityDetails(
    @SerialName("name") var name: String? = null,
    @SerialName("address") var address: String? = null,
    @SerialName("sortName") var sortName: String? = null
)

@Serializable
data class FacilityWiseShiftWise(
    @SerialName("Compleat Kidz Gastonia") var CompleatKidzGastonia: Double? = 0.0,
    @SerialName("Compleat Kidz Hickory") var CompleatKidzHickory: Double? = 0.0
)

@Serializable
data class PayDetails(
    @SerialName("status") var status: String? = null,
    @SerialName("paidDate") var paidDate: String? = null
)

@Serializable
data class PayStatus(
    @SerialName("description") var description: String? = null,
    @SerialName("status") var status: String? = null,
    @SerialName("updatedUser") var updatedUser: UpdatedUser? = UpdatedUser(),
    @SerialName("updated_date") var updatedDate: String? = null
)

@Serializable
data class UserData(
    @SerialName("firstName") var firstName: String? = null,
    @SerialName("lastName") var lastName: String? = null,
    @SerialName("mobileNumber") var mobileNumber: String? = null
)

@Serializable
data class BankInfoData(
    @SerialName("notApplicable") var notApplicable: String? = null,
    @SerialName("paymentType") var paymentType: String? = null,
    @SerialName("bankName") var bankName: String? = null,
    @SerialName("bankAccountNumber") var bankAccountNumber: String? = null,
    @SerialName("routingNumber") var routingNumber: String? = null,
    @SerialName("accountType") var accountType: String? = null,
    @SerialName("bankAddress") var bankAddress: String? = null,
    @SerialName("bankSwiftCode") var bankSwiftCode: String? = null,
    @SerialName("updated_date") var updatedDate: String? = null,
    @SerialName("userData") var userData: UserData? = UserData()
)

@Serializable
data class UpdatedUser(
    @SerialName("firstName") var firstName: String? = null,
    @SerialName("lastName") var lastName: String? = null,
    @SerialName("mobileNumber") var mobileNumber: String? = null,
    @SerialName("userId") var userId: String? = null
)

