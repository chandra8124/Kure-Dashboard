package org.flexi.app.presentation.ui.screens.payroll

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.flexi.app.domain.model.contested_pay_change.ContestedPayChanges
import org.flexi.app.domain.model.contested_pay_change.ContestedPayChangesItem
import org.flexi.app.domain.model.payPeriod.PayPeriodItem
import org.flexi.app.domain.model.payrollSalaryListing.PayrollSalaryItem
import org.flexi.app.domain.model.setUpFieldList.SetUpFieldListItem
import org.flexi.app.domain.usecase.ResultState
import org.flexi.app.presentation.ui.components.ClaimStatusDataUI
import org.flexi.app.presentation.ui.components.ErrorBox
import org.flexi.app.presentation.ui.components.LoadingBox
import org.flexi.app.presentation.viewmodels.MainViewModel
import org.flexi.app.theme.LocalThemeIsDark
import org.flexi.app.theme.teal
import org.flexi.app.utils.Constant.BASE_URL
import org.koin.compose.koinInject


class PayrollSalary : Screen {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val isDark by LocalThemeIsDark.current
        val viewModel: MainViewModel = koinInject()
        var payrollSalaryList by remember { mutableStateOf<List<PayrollSalaryItem>?>(null) }
        var setUpFieldList by remember { mutableStateOf<SetUpFieldListItem?>(null) }
        var contestedPayChanges by remember { mutableStateOf<List<ContestedPayChangesItem>?>(null) }
        var payPeriodDataList by remember { mutableStateOf<List<PayPeriodItem>?>(null) }

        LaunchedEffect(Unit) {
            viewModel.payrollSalaryListing()
            viewModel.setUpFieldList()
            viewModel.contestedPayChanges()
            viewModel.payPeriod()
        }
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() {
            refreshScope.launch {
                viewModel.payrollSalaryListing()
                viewModel.setUpFieldList()
                viewModel.contestedPayChanges()
                viewModel.payPeriod()
                //productIdsList?.let { viewModel.getProductById(it) }
                refreshing = false
            }
        }

        val refreshState = rememberPullRefreshState(refreshing, ::refresh)

        val payrollSalary by viewModel.payrollSalary.collectAsState()
        when (payrollSalary) {
            is ResultState.Error -> {
                val error = (payrollSalary as ResultState.Error).error
                ErrorBox(error)
            }

            ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (payrollSalary as ResultState.Success).response
                payrollSalaryList = response.data
            }
        }

        val setUpFieldListData by viewModel.setUpFieldListData.collectAsState()
        when (setUpFieldListData) {
            is ResultState.Error -> {
                val error = (setUpFieldListData as ResultState.Error).error
                ErrorBox(error)
            }

            ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (setUpFieldListData as ResultState.Success).response
                setUpFieldList = response.data
            }
        }

        val contestedPayChangesData by viewModel.contestedPayChangesData.collectAsState()
        when (contestedPayChangesData) {
            is ResultState.Error -> {
                val error = (contestedPayChangesData as ResultState.Error).error
                ErrorBox(error)
            }

            ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (contestedPayChangesData as ResultState.Success).response
                contestedPayChanges = response.data
            }
        }

         val payPeriodData by viewModel.payPeriodData.collectAsState()
        when (payPeriodData) {
            is ResultState.Error -> {
                val error = (payPeriodData as ResultState.Error).error
                ErrorBox(error)
            }

            ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (payPeriodData as ResultState.Success).response
                payPeriodDataList = response.data
            }
        }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Payroll",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) Color.White else Color.Black
                    )
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null
                    )
                }
            }
        ) {
            Box(
                Modifier
                    .padding(top = 40.dp)
                    .pullRefresh(refreshState),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = 34.dp,
                            start = 6.dp,
                            end = 6.dp
                        ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    LazyRow(modifier = Modifier.padding(5.dp)) {
                        setUpFieldList?.adminFacility.let { adminFacilityList ->
                            adminFacilityList?.forEach {
                                item {
                                    setUpFieldListScreen(it.displayName, it.id, it.address)
                                }
                            }

                        }
                    }

                    LazyRow(modifier = Modifier.padding(5.dp)) {
                        contestedPayChanges?.let { adminFacilityList ->
                            adminFacilityList.forEach {
                                item {
                                    setUpFieldListScreen(
                                        it.user?.firstName ?: "",
                                        it.id ?: "",
                                        it.reason ?: ""
                                    )
                                }
                            }

                        }
                    }


                     LazyRow(modifier = Modifier.padding(5.dp)) {
                         payPeriodDataList?.let { adminFacilityList ->
                            adminFacilityList.forEach {
                                item {
                                    setUpFieldListScreen(
                                        it.payPeriod?: "",
                                        it.payDate ?: "",
                                         "${it.startDate} - ${it.endDate}"
                                    )
                                }
                            }

                        }
                    }


                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(300.dp),
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalArrangement = Arrangement.Center,
                            contentPadding = PaddingValues(6.dp)
                        ) {
                            payrollSalaryList?.let { payrollSalary ->
                                items(payrollSalary) { item ->
                                    PayrollSalaryItemScreen(item)
                                }
                            }
                        }

                    }

                }
                PullRefreshIndicator(
                    refreshing = refreshing,
                    state = refreshState,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )
            }
        }
    }

}


@Composable
fun PayrollSalaryItemScreen(
    products: PayrollSalaryItem,
) {
    val isDark by LocalThemeIsDark.current
    val navigator = LocalNavigator.current
    val image: Resource<Painter> =
        asyncPainterResource(data = BASE_URL + products.id)
    // val discountedPrice = products.coPayStatus
    Card(
        modifier = Modifier.wrapContentWidth()
            .wrapContentHeight().padding(5.dp)
            .clickable {
                // navigator?.push(DetailScreen(products))
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(6.dp),

        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(10.dp))
        {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 4.dp, end = 3.dp, bottom = 4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier.wrapContentWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = products.therapistName ?: "",
                        //maxLines = 2,
                        // minLines = 2,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )


                }

                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = products.facilityName.first(),
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        textAlign = TextAlign.Start,
                        color = Color.DarkGray
                    )

                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = products.discipline.firstOrNull() ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.End,
                        color = Color.Gray
                    )
                }
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "WEEK DATE : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                    )

                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = "${products.weekStartDate} - ${products.weekEndDate}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}


@Composable
fun setUpFieldListScreen(text: String, text2: String, text3: String) {


    if (text.isEmpty()) {
        return
    }

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

        Column(modifier = Modifier.padding(10.dp)) {

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.wrapContentWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 4.dp),
                text = text,
                color = teal,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = text2,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,

                )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = text3,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,

                )
        }

    }
}