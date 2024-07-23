package org.flexi.app.presentation.ui.screens.payroll

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.DeliveryDining
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MapsHomeWork
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kure_dashboard.composeapp.generated.resources.Res
import kure_dashboard.composeapp.generated.resources.no_item_found
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.flexi.app.domain.model.payrollSalaryListing.PayrollSalaryItem
import org.flexi.app.domain.model.payrollSalaryListing.PayrollSalaryListing
import org.flexi.app.domain.model.products.Products
import org.flexi.app.domain.usecase.ResultState
import org.flexi.app.presentation.ui.components.ErrorBox
import org.flexi.app.presentation.ui.components.LoadingBox
import org.flexi.app.presentation.ui.screens.payment.model.Order
import org.flexi.app.presentation.viewmodels.MainViewModel
import org.flexi.app.theme.LocalThemeIsDark
import org.flexi.app.utils.Constant.BASE_URL
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject


class MyOrdersContent : Screen {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val isDark by LocalThemeIsDark.current
        val viewModel: MainViewModel = koinInject()
        var payrollSalaryList by remember { mutableStateOf<List<PayrollSalaryItem>?>(null) }
        var productList by remember { mutableStateOf<List<Products>?>(null) }

        LaunchedEffect(Unit) {
            viewModel.payrollSalaryListing()
        }
//        LaunchedEffect(productIdsList) {
//           // productIdsList?.let { viewModel.getProductById(it) }
//        }
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() {
            refreshScope.launch {
                viewModel.payrollSalaryListing()
                //productIdsList?.let { viewModel.getProductById(it) }
                refreshing = false
            }
        }

        val refreshState = rememberPullRefreshState(refreshing, ::refresh)

        val myOrderState by viewModel.payrollSalary.collectAsState()
        when (myOrderState) {
            is ResultState.Error -> {
                val error = (myOrderState as ResultState.Error).error
                 ErrorBox(error)
            }

            ResultState.Loading -> {
                 LoadingBox()
            }

            is ResultState.Success -> {
                val response = (myOrderState as ResultState.Success).response
                payrollSalaryList = response.data
            }
        }
        val productState by viewModel.productItem.collectAsState()
        when (productState) {
            is ResultState.Error -> {
                val error = (productState as ResultState.Error).error
                 ErrorBox(error)
            }

            ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (productState as ResultState.Success).response
                productList = response
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
                        imageVector = Icons.Outlined.ShoppingBag,
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
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
                                    MyOrderItems(item)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrderItems(
    payrollSalaryItem: PayrollSalaryItem
) {
    val isDark by LocalThemeIsDark.current
    val navigator = LocalNavigator.current
    var trackOrder by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(190.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
//                val image: Resource<Painter> = asyncPainterResource(BASE_URL + products.imageUrl)
//                KamelImage(
//                    resource = image,
//                    contentDescription = null,
//                    modifier = Modifier.width(125.dp)
//                        .height(90.dp)
//                        .clip(RoundedCornerShape(6.dp)),
//                    contentScale = ContentScale.Crop
//                )
                Spacer(modifier = Modifier.width(4.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = payrollSalaryItem.therapistName?:"",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta.copy(
                            alpha = 0.65f
                        ),
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 0.dp, end = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = payrollSalaryItem.employmentStatus?:"")

                        Text(
                            text = payrollSalaryItem.primaryFacility?:"",
                        )
                    }

                    Text(text = "${payrollSalaryItem.weekStartDate} - ${payrollSalaryItem.weekEndDate}")

                }
            }

        }
    }
    if (trackOrder) {
        val sheetState = rememberModalBottomSheetState()
        val progressStatus = payrollSalaryItem.status?:""
        ModalBottomSheet(
            onDismissRequest = {
                trackOrder = !trackOrder
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (payrollSalaryItem.status == "Completed") "Order Delivered" else "Track Your Order Progress",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = if (payrollSalaryItem.status == "Completed") Color(0xFF5821c4) else if (isDark) Color.White else Color.Black
                    )
                    Icon(
                        modifier = Modifier.clickable {
                            trackOrder = !trackOrder
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                OrderStatusItem(
                    icon = Icons.Outlined.MapsHomeWork,
                    text = "UpBox Bag",
                    status = "Shop",
                    time = "02:50 PM",
                    orderProgress = progressStatus
                )
                OrderStatusItem(
                    icon = Icons.Outlined.DeliveryDining,
                    text = "On the way",
                    status = "Delivery",
                    time = "03:20 PM",
                    orderProgress = progressStatus
                )
                OrderStatusItem(
                    icon = Icons.Outlined.LocationOn,
                    text = "5482 Adobe Falls Rd #15San Diego",
                    status = "House",
                    time = "03:45 PM",
                    orderProgress = progressStatus
                )
            }
        }
    }
}

enum class ProgressStatus {
    ON_PROGRESS,
    ON_THE_WAY,
    COMPLETED
}

@Composable
fun OrderStatusItem(
    icon: ImageVector,
    text: String,
    status: String,
    time: String,
    orderProgress: String,
) {
    val isDark by LocalThemeIsDark.current
    val progressStatus = when (orderProgress) {
        "On Progress" -> ProgressStatus.ON_PROGRESS
        "On The Way" -> ProgressStatus.ON_THE_WAY
        "Completed" -> ProgressStatus.COMPLETED
        else -> ProgressStatus.ON_PROGRESS
    }

    val backgroundColor = when (progressStatus) {
        ProgressStatus.ON_PROGRESS -> if (status == "Shop") Color(0xFF5821c4) else Color.LightGray
        ProgressStatus.ON_THE_WAY -> if (status == "Shop" || status == "Delivery") Color(0xFF5821c4) else Color.LightGray
        ProgressStatus.COMPLETED -> Color(0xFF5821c4)
    }

    val textColor =
        if (progressStatus == ProgressStatus.COMPLETED) Color(0xFF5821c4) else if (isDark) Color.White else Color.Black

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp)
                .background(backgroundColor, shape = CircleShape)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                color = textColor
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = status,
                    fontSize = 13.sp,
                    color = textColor
                )
                Text(
                    text = ".",
                    fontSize = 13.sp,
                    color = textColor,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = time,
                    fontSize = 13.sp,
                    color = textColor
                )
            }
        }
    }

    if (status != "House") {
        VerticalDivider(
            color = backgroundColor,
            thickness = 3.dp,
            modifier = Modifier.fillMaxHeight(0.08f)
                .padding(horizontal = 16.dp)
        )
    }
}