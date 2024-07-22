package org.flexi.app.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import org.flexi.app.domain.model.summaryTable.SummaryData
import org.flexi.app.theme.LocalThemeIsDark
import org.flexi.app.theme.blue
import org.flexi.app.theme.green
import org.flexi.app.theme.teal

@Composable
fun SummaryTableUI(summaryData: SummaryData) {
    val isDark by LocalThemeIsDark.current
    val navigator = LocalNavigator.current

    Card(
        modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp, color = Color.LightGray
        ),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Claims",
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = if (isDark) Color.White else Color.Black,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Row {
                    Text(
                        text = "All",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
//                        navigator?.push(
//                            SeeAllProducts(
//                                claimStatus,
//                                books = null,
//                                category = "Featured"
//                            )
//                        )
                        }.padding(horizontal = 16.dp)
                    )

                    Text(
                        text = "Approved",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
//                        navigator?.push(
//                            SeeAllProducts(
//                                claimStatus,
//                                books = null,
//                                category = "Featured"
//                            )
//                        )
                        }.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = "Denied",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.clickable {
//                        navigator?.push(
//                            SeeAllProducts(
//                                claimStatus,
//                                books = null,
//                                category = "Featured"
//                            )
//                        )
                        }.padding(horizontal = 16.dp)
                    )
                }



            }
            Spacer(modifier = Modifier.height(4.dp))

            Divider(color = Color.Gray, thickness = 1.dp)


            LazyRow {
                item {
                    ProductionUI(summaryData)
                }
                item {
                    HoursUi(summaryData)
                }
                item {
                    ClaimsUI(summaryData)
                }
                item {
                    PerServiceContracts(summaryData)

                }


                item {
                    PaymentResponsibilityFromEob(summaryData)
                }


                item {

                    EOBVSExpected(summaryData)
                }
            }


        }

    }
}

@Composable
fun SummaryTableUIItem(
    text: String,
    text2: String,
    text3: String,
    width: Int = 100,
    showBorder: Boolean = false
) {


    Column(modifier = Modifier.padding(5.dp).wrapContentWidth()) {

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = text,
            color = blue,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )

        // Spacer(modifier = Modifier.height(4.dp))

        if (showBorder) {
            Row(
                modifier = Modifier.wrapContentWidth()
                    .border(1.dp, green, RoundedCornerShape(30.dp)),
            ) {
                Text(
                    modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp, bottom = 3.dp),
                    text = text2,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = green,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                )
            }
        } else {
            Text(
                modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
                text = text2,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = green,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        if (showBorder) {
            Row(
                modifier = Modifier.wrapContentWidth()
                    .border(1.dp, teal, RoundedCornerShape(30.dp)),
            ) {
                Text(
                    modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp, bottom = 3.dp),
                    text = text3,
                    textAlign = TextAlign.Center,
                    color = teal,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                )
            }
        } else {
            Text(
                modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
                text = text3,
                color = teal,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
            )
        }


    }
}

@Composable
fun PaymentResponsibilityFromEob(summaryData: SummaryData) {

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 4.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = "Payment Responsibility from EOB",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )

       // Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row {
                SummaryTableUIItem(
                    "INSURANCE",
                    summaryData.insuranceTotal ?: "",
                    summaryData.insuranceWriteOffAmount ?: "", showBorder = true
                )
                SummaryTableUIItem(
                    "LEARNER",
                    summaryData.learnerTotal ?: "",
                    summaryData.learnerWriteOffAmount ?: "", showBorder = true
                )
                SummaryTableUIItem(
                    "UNASSIGNED",
                    summaryData.notAssined ?: "",
                    summaryData.notColllectableNotAssigned ?: "", showBorder = true
                )
            }
        }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row {
                SummaryTableUIItem(
                    summaryData.adjInsurance ?: "",
                    summaryData.depositedInsurance ?: "",
                    summaryData.accRecInsurance ?: "", showBorder = true
                )
                SummaryTableUIItem(
                    summaryData.adjLearner ?: "",
                    summaryData.depositedLearner ?: "",
                    summaryData.accRecLearner ?: "", showBorder = true
                )
                SummaryTableUIItem(
                    summaryData.adjNotAssigned ?: "",
                    summaryData.depositedNotAssigned ?: "",
                    summaryData.adjNotAssigned ?: "", showBorder = true
                )
            }
        }

    }
}



@Composable
fun EOBVSExpected(summaryData: SummaryData) {

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 4.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = "EOB VS Expected",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )

      //  Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row {
                SummaryTableUIItem(
                    "VARIANCE",
                    summaryData.variance ?: "",
                    summaryData.varianceWriteOffAmount ?: "", showBorder = true
                )
            }
        }


        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            SummaryTableUIItem(
                summaryData.adjVariance ?: "",
                summaryData.depositedVariance ?: "",
                summaryData.adjVariance ?: "", showBorder = true
            )
        }
    }
}



@Composable
fun PerServiceContracts(summaryData: SummaryData) {

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 4.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = "Per Service Contracts",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )

       // Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row {
                SummaryTableUIItem(
                    "EXPECTED PAYMENTS",
                    summaryData.expectedAmountTotal ?: "",
                    summaryData.notColllectableExpected ?: "", showBorder = true
                )
            }

        }

        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            SummaryTableUIItem(
                summaryData.adjAmount ?: "",
                summaryData.depositedAmount ?: "",
                summaryData.accRecAmount ?: "", showBorder = true
            )

        }

    }
}

@Composable
fun ClaimsUI(summaryData: SummaryData) {

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 4.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = "",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )

       // Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row {
                SummaryTableUIItem(
                    "CLAIMS",
                    summaryData.totalAmount ?: "",
                    summaryData.notColllectablePercentText ?: ""
                )
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            SummaryTableUIItem(
                summaryData.adjPercentText ?: "",
                summaryData.accRecPercentText ?: "",
                summaryData.accRecPercentText ?: "", width = 260
            )
            }

    }
}



@Composable
fun HoursUi(summaryData: SummaryData) {

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 4.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = "",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )

       // Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row {
                SummaryTableUIItem(
                    "HOURS",
                    summaryData.totalDuration ?: "",
                    summaryData.notColllectablePercent ?: ""
                )
            }
        }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            SummaryTableUIItem(
                summaryData.adjPercent ?: "",
                summaryData.depositedPercent ?: "",
                summaryData.accRecPercent ?: ""
            )
        }

    }
}

@Composable
fun ProductionUI(summaryData: SummaryData) {

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 4.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp),
            text = "",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
        )


        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
           // elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Row {
                SummaryTableUIItem("", "PRODUCTION", "NOT COLLECTABLE")
            }

        }

        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp),
            //elevation = CardDefaults.cardElevation(4.dp)
        ) {

            SummaryTableUIItem(
                "ADJUSTED PRODUCTION",
                "PAYMENTS (DEPOSITED)",
                "ACCOUNTS RECEIVABLE",
                width = 200
            )
        }
    }
}


