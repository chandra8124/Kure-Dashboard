package org.flexi.app.presentation.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.flexi.app.domain.model.claim.ClaimItem
import org.flexi.app.domain.model.claimStatus.ClaimStatusData
import org.flexi.app.domain.model.patientemsalldata.PatiendEmSellModel
import org.flexi.app.domain.model.summaryTable.SummaryData
import org.flexi.app.domain.usecase.ResultState
import org.flexi.app.presentation.ui.components.ClaimStatusDataUI
import org.flexi.app.presentation.ui.components.ErrorBox
import org.flexi.app.presentation.ui.components.LoadingBox
import org.flexi.app.presentation.ui.components.ProductList
import org.flexi.app.presentation.ui.components.SummaryTableUI
import org.flexi.app.presentation.ui.components.TopAppBarWithProfile
import org.flexi.app.presentation.viewmodels.MainViewModel
import org.flexi.app.theme.LocalThemeIsDark
import org.koin.compose.koinInject

class HomeScreen : Screen {
    @OptIn(
        ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
        ExperimentalFoundationApi::class
    )
    @Composable
    override fun Content() {
        var productsList by remember { mutableStateOf<List<ClaimItem>?>(null) }
        var summaryTableData by remember { mutableStateOf<SummaryData?>(null) }
        var claimStatusData by remember { mutableStateOf<ClaimStatusData?>(null) }
        var patientEmSallObj by remember { mutableStateOf<List<PatiendEmSellModel>?>(null) }
        // var promoList by remember { mutableStateOf<List<PromotionsProductsItem>?>(null) }
        // var categoriesList by remember { mutableStateOf<List<Categories>?>(null) }
        // var booksList by remember { mutableStateOf<List<BooksItem>?>(null) }
        // var cartItemList by remember { mutableStateOf<List<CartItem>?>(null) }
        // var userData by remember { mutableStateOf<User?>(null) }
        // var isProfile by remember { mutableStateOf(false) }
        val viewModel: MainViewModel = koinInject()
        val scrollState = rememberScrollState()
        val navigator = LocalNavigator.current
        var selectedClaimStatus by remember { mutableStateOf("") }
        var query by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            viewModel.getClaimData()
            viewModel.getSummaryTable()
            viewModel.getClaimStatus()
            viewModel.patientEmSallData()
        }
        val state by viewModel.claimData.collectAsState()
        when (state) {
            is ResultState.Error -> {
                val error = (state as ResultState.Error).error
                ErrorBox(error)
            }

            is ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (state as ResultState.Success).response
                productsList = response.data
            }
        }


        val summaryTable by viewModel.summaryTable.collectAsState()
        when (summaryTable) {
            is ResultState.Error -> {
                val error = (summaryTable as ResultState.Error).error
                ErrorBox(error)
            }

            is ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (summaryTable as ResultState.Success).response
                summaryTableData = response.data
                print(response.data)
            }
        }


        val claimStatus by viewModel.claimStatus.collectAsState()
        when (claimStatus) {
            is ResultState.Error -> {
                val error = (claimStatus as ResultState.Error).error
                ErrorBox(error)
            }

            is ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (claimStatus as ResultState.Success).response
                claimStatusData = response.data
            }
        }


        val patientEmSall by viewModel.patientEmSall.collectAsState()
        when (patientEmSall) {
            is ResultState.Error -> {
                val error = (patientEmSall as ResultState.Error).error
                ErrorBox(error)
            }

            is ResultState.Loading -> {
                LoadingBox()
            }

            is ResultState.Success -> {
                val response = (patientEmSall as ResultState.Success).response
                patientEmSallObj = response.data
            }
        }

        var isDark by LocalThemeIsDark.current
        val isSearching = query.isNotBlank()

        val filteredProducts = productsList?.filter {
            it.id?.contains(query, ignoreCase = true) == true
        }

        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() {
            refreshScope.launch {

                delay(1500)
                viewModel.getClaimData()
                viewModel.getSummaryTable()
                viewModel.getClaimStatus()
                viewModel.patientEmSallData()
                refreshing = false
            }
        }

        //  val username = userData?.fullName.toString()
        //  val userProfile = BASE_URL + userData?.profileImage.toString()
        // val user = FlexiApiClient.supaBaseClient.auth.currentSessionOrNull()
        val refreshState = rememberPullRefreshState(refreshing, ::refresh)

        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                TopAppBarWithProfile(
                    name = "Chandra Singh",
                    onCartClicked = {
//                            cartItemList?.let { carts ->
//                                val mutableCartsList = carts.toMutableList()
//                                navigator?.push(CartList(mutableCartsList))
//                            }
                    },
                    profileImageUrl = "",
                    itemCount = 5,
                    onProfileClick = {
//                            if (user?.user?.email?.isEmpty() == true) {
//                                navigator?.push(LoginScreen())
//                            } else {
//                                isProfile = !isProfile
//                            }
                    }
                )
            }
        ) {
            Box(
                Modifier
                    .pullRefresh(refreshState),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2000.dp)
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding(),
                            start = 6.dp,
                            end = 6.dp
                        )
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
//                        TextField(
//                            value = query,
//                            onValueChange = { query = it },
//                            modifier = Modifier
//                                .weight(1f)
//                                .height(52.dp),
//                            shape = RoundedCornerShape(16.dp),
//                            placeholder = {
//                                Text(
//                                    "Search...",
//                                    fontSize = 14.sp
//                                )
//                            },
//                            leadingIcon = {
//                                Icon(
//                                    imageVector = Icons.Outlined.Search,
//                                    contentDescription = "Search",
//                                    tint = Color.Gray
//                                )
//                            },
//                            colors = TextFieldDefaults.textFieldColors(
//                                cursorColor = Color.Black,
//                                focusedIndicatorColor = Color.Transparent,
//                                unfocusedIndicatorColor = Color.Transparent
//                            ),
//                            textStyle = TextStyle(
//                                fontSize = 14.sp
//                            )
//                        )
                        //Spacer(modifier = Modifier.width(8.dp))
//                        Card(
//                            modifier = Modifier
//                                .size(48.dp)
//                                .clip(RoundedCornerShape(16.dp)),
//                            elevation = CardDefaults.cardElevation(8.dp)
//                        ) {
//                            Icon(
//                                imageVector = Icons.Outlined.FilterAlt,
//                                contentDescription = "Filter",
//                                modifier = Modifier
//                                    .clickable {
//                                        isDark = !isDark
//                                    }
//                                    .padding(12.dp),
//                                tint = Color.Gray,
//                            )
//                        }
                    }
                    if (isSearching) {
//                        val lazyState = rememberLazyGridState()
//                        LazyVerticalGrid(
//                            columns = GridCells.Adaptive(150.dp),
//                            state = lazyState,
//                            modifier = Modifier.fillMaxWidth()
//                                .height(1200.dp).padding(bottom = 34.dp, top = 10.dp),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp),
//                            verticalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            filteredProducts?.let { proList ->
//                                items(proList) { pro ->
//                                    HotSaleItem(pro)
//                                }
//                            }
//                        }
                    } else {
//                        promoList?.let { promotion ->
//                            PromotionCardWithPager(promotion)
//                        }
//                        productsList?.let { pro ->
//                            FeaturedList(pro)
//                            FoodList(pro)
//                            FurnituresList(pro)
//                            ElectronicsList(pro)
//                        }
//                        booksList?.let { bookList ->
//                            BooksList(bookList)
//                        }
                        productsList?.let { categories ->
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
//                                Text(
//                                    text = "Categories",
//                                    fontWeight = FontWeight.ExtraBold,
//                                    modifier = Modifier.padding(vertical = 8.dp),
//                                    textAlign = TextAlign.Start,
//                                    fontSize = 28.sp
//                                )
//                                Spacer(modifier = Modifier.height(6.dp))


                                claimStatusData?.let { claimStatus ->
                                    ClaimStatusDataUI(claimStatus = claimStatus)
                                }

                                summaryTableData?.let { claimStatus ->
                                    SummaryTableUI(summaryData = claimStatus)
                                }
                                LazyRow {
                                    item {
                                        Text(
                                            text = "All",
                                            fontWeight = if (selectedClaimStatus == "") FontWeight.Bold else FontWeight.Normal,
                                            textDecoration = if (selectedClaimStatus == "") TextDecoration.Underline else TextDecoration.None,
                                            color = if (selectedClaimStatus == "") if (isDark) Color.White else Color.Black else Color.Gray,
                                            modifier = Modifier.clickable {
                                                selectedClaimStatus = ""
                                            }
                                                .padding(horizontal = 8.dp)
                                        )
                                    }
                                    itemsIndexed(categories.distinctBy { it.claimStatus }) { index, category ->
                                        Text(
                                            text = category.claimStatus ?: "",
                                            fontWeight = if (selectedClaimStatus == category.claimStatus) FontWeight.Bold else FontWeight.Normal,
                                            textDecoration = if (selectedClaimStatus == category.claimStatus) TextDecoration.Underline else TextDecoration.None,
                                            color = if (selectedClaimStatus == category.claimStatus) Color.Black else Color.Gray,
                                            modifier = Modifier.clickable {

                                                selectedClaimStatus = category.claimStatus ?: ""
                                            }
                                                .padding(horizontal = 8.dp)
                                        )
                                    }
                                }

                                val filteredClaim = if (selectedClaimStatus == "") {
                                    productsList
                                } else {
                                    productsList?.filter { it.claimStatus == selectedClaimStatus }
                                }
                                filteredClaim?.let { claimList ->
                                    ProductList(products = claimList)
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
        /*   if (isProfile) {
               AlertDialog(
                   onDismissRequest = { isProfile = false },
                   title = {
                       Text(
                           text = userData?.fullName.toString(),
                           fontWeight = FontWeight.Bold,
                           fontSize = 20.sp,
                           modifier = Modifier.padding(bottom = 8.dp)
                       )
                   },
                   icon = {
                       val profile: Resource<Painter> =
                           asyncPainterResource(BASE_URL + userData?.profileImage.toString())
                       KamelImage(
                           resource = profile,
                           contentDescription = null,
                           modifier = Modifier.size(200.dp)
                               .clip(RoundedCornerShape(24.dp))
                               .border(
                                   width = 1.dp,
                                   color = Color.Gray,
                                   shape = RoundedCornerShape(24.dp)
                               )
                       )
                   },
                   text = {
                       Column {
                           Row(verticalAlignment = Alignment.CenterVertically) {
                               Icon(Icons.Default.VerifiedUser, contentDescription = null)
                               Spacer(modifier = Modifier.width(4.dp))
                               Text(
                                   text = "@" + userData?.username.toString(),
                                   fontSize = 16.sp
                               )
                           }
                           Spacer(modifier = Modifier.height(8.dp))
                           Row(verticalAlignment = Alignment.CenterVertically) {
                               Icon(Icons.Default.LocationOn, contentDescription = null)
                               Spacer(modifier = Modifier.width(4.dp))
                               Text(
                                   text = userData?.country ?: "",
                                   fontSize = 14.sp
                               )
                           }
                           Row(verticalAlignment = Alignment.CenterVertically) {
                               Icon(Icons.Default.Home, contentDescription = null)
                               Spacer(modifier = Modifier.width(4.dp))
                               Text(
                                   text = userData?.address ?: "",
                                   fontSize = 14.sp
                               )
                           }
                       }
                   },
                   confirmButton = {

                       Icon(
                           modifier = Modifier.clickable {
                               isProfile = !isProfile
                           },
                           imageVector = Icons.Outlined.Close,
                           contentDescription = null
                       )

                   },
                   dismissButton = {
                       TextButton(
                           onClick = { isProfile = false },
                           modifier = Modifier.padding(end = 8.dp)
                       ) {
                           Text(text = "")
                       }
                   },
                   modifier = Modifier.padding(16.dp),
                   shape = RoundedCornerShape(12.dp),
                   containerColor = MaterialTheme.colorScheme.surface,
                   textContentColor = MaterialTheme.colorScheme.onSurface
               )
           }*/
    }
}