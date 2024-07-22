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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import kure_dashboard.composeapp.generated.resources.Res
import kure_dashboard.composeapp.generated.resources.Roboto_Bold
import org.flexi.app.domain.model.claimStatus.Adjudicated
import org.flexi.app.domain.model.claimStatus.BalanceOwed
import org.flexi.app.domain.model.claimStatus.ClaimStatusData
import org.flexi.app.domain.model.claimStatus.NonBilling
import org.flexi.app.domain.model.claimStatus.PendingReleased
import org.flexi.app.domain.model.claimStatus.ReBilled
import org.flexi.app.domain.model.claimStatus.ReleaseToPayer
import org.flexi.app.theme.LocalThemeIsDark
import org.flexi.app.theme.teal
import org.jetbrains.compose.resources.Font

@Composable
fun ClaimStatusDataUI(claimStatus: ClaimStatusData) {
    val isDark by LocalThemeIsDark.current
    val navigator = LocalNavigator.current

//    Column(
//        modifier = Modifier
//            .fillMaxWidth().wrapContentHeight()
//            .padding(vertical = 8.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(
//                text = "Featured",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = if (isDark) Color.White else Color.Black,
//                modifier = Modifier.padding(horizontal = 10.dp)
//            )
//            Text(
//                text = "See All",
//                fontSize = 14.sp,
//                color = Color(0xFFe85110),
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .clickable {
////                        navigator?.push(
////                            SeeAllProducts(
////                                claimStatus,
////                                books = null,
////                                category = "Featured"
////                            )
////                        )
//                    }
//                    .padding(horizontal = 16.dp)
//            )
//        }


    LazyRow(modifier = Modifier.padding(5.dp)) {
        item {
            claimStatus.claimNew?.pendingReleased?.let {
                PendingReleasedUI(it, claimStatus.displayStatusGroupName?.pendingReleased)
            }
        }
        item {
            claimStatus.claimNew?.reBilled?.let {
                PendingReleasedUI(it, claimStatus.displayStatusGroupName?.reBilled)
            }
        }
        item {
            claimStatus.claimNew?.releaseToPayer?.let {
                PendingReleasedUI(it, claimStatus.displayStatusGroupName?.releaseToPayer)
            }
        }
        item {
            claimStatus.claimNew?.nonBillable?.let {
                PendingReleasedUI(it, claimStatus.displayStatusGroupName?.nonBillable)
            }
        }
        item {
            claimStatus.claimNew?.adjudicated?.let {
                PendingReleasedUI(it, claimStatus.displayStatusGroupName?.adjudicated)
            }
        }
        item {
            claimStatus.claimNew?.balanceOwed?.let {
                PendingReleasedUI(it, claimStatus.displayStatusGroupName?.balanceOwed)
            }
        }
    }

}


@Composable
fun PendingReleasedUI(
    pendingReleased: Any,
    labelText: String?,
) {
    Card(
        modifier = Modifier.wrapContentWidth()
            .wrapContentHeight().padding(5.dp)
            .clickable {
                // navigator?.push(DetailScreen(products))
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        shape = RoundedCornerShape(6.dp),

        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = labelText ?: "",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center,
                color = Color.Black,
                maxLines = 1,
                fontFamily = FontFamily(Font(Res.font.Roboto_Bold)),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 3.dp, end = 3.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Divider(color = Color.Red, thickness = 1.dp)

            Row {

                when (pendingReleased) {
                    is PendingReleased -> {

                        pendingReleased.Pending?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.MissingNotes?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                    }

                    is ReBilled -> {

                        pendingReleased.ResubmittedClaims?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.ToSecondary?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.CorrectedClaims?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                    }

                    is Adjudicated -> {

                        pendingReleased.Approved?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.Denied?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                        pendingReleased.Deposited?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                        pendingReleased.DeniedSecondary?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                    }

                    is BalanceOwed -> {

                        pendingReleased.ClaimClosed?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.LrnBalanceDue?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                    }

                    is ReleaseToPayer -> {

                        pendingReleased.Accept?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.CLAIMRELEASED?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                    }

                    is NonBilling -> {


                        pendingReleased.WRITEOFFCLAIM_PARTIAL?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }

                        pendingReleased.WRITEOFFCLAIM?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                        pendingReleased.NONBILLABLE?.let {
                            cardDateWithStatus(
                                it.claimStatusName ?: "",
                                it.Total.toString(),
                                it.expectedAmount.toString()
                            )
                        }
                    }
                }

            }
        }

    }
}

@Composable
fun cardDateWithStatus(text: String, text2: String, text3: String) {

    if (text.isEmpty()) {
        return
    }
    Column(modifier = Modifier.padding(10.dp)) {

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.wrapContentWidth()
                .border(1.dp, teal, RoundedCornerShape(30.dp)),
        ) {
            Text(
                modifier = Modifier.wrapContentWidth().padding(start = 10.dp, end = 10.dp, bottom = 4.dp),
                text = text,
                color = teal,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text2,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            fontWeight = FontWeight.Bold,
            lineHeight = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,

            )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text3,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            fontWeight = FontWeight.Bold,
            lineHeight = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,

            )
    }
}

