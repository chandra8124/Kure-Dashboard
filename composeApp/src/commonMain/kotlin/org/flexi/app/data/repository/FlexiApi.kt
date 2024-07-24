package org.flexi.app.data.repository

import org.flexi.app.domain.model.claim.ClaimModel
import org.flexi.app.domain.model.claimStatus.ClaimStatusModel
import org.flexi.app.domain.model.contested_pay_change.ContestedPayChanges
import org.flexi.app.domain.model.login.LoginResponse
import org.flexi.app.domain.model.patientemsalldata.PatientEmSallData
import org.flexi.app.domain.model.payPeriod.PayPeriod
import org.flexi.app.domain.model.payrollSalaryListing.PayrollSalaryListing
import org.flexi.app.domain.model.setUpFieldList.SetUpFieldList
import org.flexi.app.domain.model.summaryTable.SummaryTableModel
import org.koin.core.annotation.Single

@Single
interface FlexiApi {
     suspend fun loginUser(mobileNumber: String, password: String): LoginResponse
    suspend fun claimData(): ClaimModel
    suspend fun summaryTable(): SummaryTableModel
    suspend fun claimStatus(): ClaimStatusModel
    suspend fun patientEmSallData(): PatientEmSallData
    suspend fun payrollSalaryListing(): PayrollSalaryListing
    suspend fun setUpFieldList(): SetUpFieldList
    suspend fun contestedPayChanges(): ContestedPayChanges
    suspend fun payPeriod(): PayPeriod
}