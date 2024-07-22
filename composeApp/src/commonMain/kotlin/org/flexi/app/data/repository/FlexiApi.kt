package org.flexi.app.data.repository

import org.flexi.app.domain.model.claim.ClaimModel
import org.flexi.app.domain.model.claimStatus.ClaimStatusModel
import org.flexi.app.domain.model.patientemsalldata.PatientEmSallData
import org.flexi.app.domain.model.summaryTable.SummaryTableModel
import org.koin.core.annotation.Single

@Single
interface FlexiApi {
    // suspend fun loginUser(email: String, password: String): String
    suspend fun claimData(): ClaimModel
    suspend fun summaryTable(): SummaryTableModel
    suspend fun claimStatus(): ClaimStatusModel
    suspend fun patientEmSallData(): PatientEmSallData
}