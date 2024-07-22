package org.flexi.app.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import org.flexi.app.domain.model.claim.ClaimItem
import org.flexi.app.theme.LocalThemeIsDark
import org.flexi.app.utils.Constant.BASE_URL

@Composable
fun ProductList(
    products: List<ClaimItem>,
    state: LazyGridState = rememberLazyGridState(),
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (products.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .wrapContentHeight()
                    .padding(bottom = 34.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "No items found",
                    tint = Color.Red,
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = "No items found",
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                state = state,
                modifier = modifier.fillMaxWidth()
                    .height(1200.dp).padding(bottom = 84.dp, top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { pro ->
                    ProductItem(pro)
                }
            }
        }
    }

}

@Composable
fun ProductItem(
    products: ClaimItem,
) {
    val isDark by LocalThemeIsDark.current
    val navigator = LocalNavigator.current
    val image: Resource<Painter> =
        asyncPainterResource(data = BASE_URL + products.id)
    val discountedPrice = products.coPayStatus
    Card(
        modifier = Modifier.wrapContentWidth()
            .wrapContentHeight().padding(5.dp)
            .clickable {
                // navigator?.push(DetailScreen(products))
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
//        border = BorderStroke(
//            width = 1.dp,
//            color = Color.LightGray
//        ),
        shape = RoundedCornerShape(6.dp),

        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(10.dp))
        {
//            KamelImage(
//                resource = image,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(180.dp)
//                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
//                contentScale = ContentScale.Crop,
//                animationSpec = tween(),
//                onLoading = {
//                    CircularProgressIndicator()
//                },
//                onFailure = {
//                    Text("Failed to Load")
//                }
//            )
//
//            Icon(
//                imageVector = Icons.Default.Favorite,
//                contentDescription = null,
//                tint = Color.Red,
//                modifier = Modifier
//                    .padding(top = 12.dp, end = 8.dp)
//                    .offset(y = 14.dp)
//                    .size(30.dp)
//                    .clip(CircleShape)
//                    .background(Color.White)
//                    .align(Alignment.BottomEnd)
//                    .padding(4.dp)
//                    .shadow(
//                        elevation = 8.dp,
//                        spotColor = Color.DarkGray,
//                        ambientColor = Color.Red,
//                        shape = CircleShape
//                    )
//            )

//            Box(
//                modifier = Modifier
//                    .padding(top = 4.dp, start = 4.dp)
//                    .wrapContentWidth()
//                    .padding(4.dp)
//                    .background(
//                        Color.Red.copy(alpha = 0.7f),
//                        RoundedCornerShape(topEnd = 14.dp, bottomStart = 14.dp)
//                    )
//                    .align(Alignment.TopStart)
//
//            ) {
//                Text(
//                    text = products.agingDays?:"",
//                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                    modifier = Modifier.padding(4.dp)
//                )
//            }
//        }



            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 4.dp, end = 3.dp, bottom = 4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

               // Spacer(modifier = Modifier.height(8.dp))
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start
//                ) {
//                    val rating = 7
//                    val filledStars = rating.toInt()
//                    val halfStarVisible = rating - filledStars >= 0.5
//                    val emptyStars = 5 - filledStars - if (halfStarVisible) 1 else 0
//
//                    repeat(filledStars) {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = null,
//                            tint = Color.Red.copy(alpha = 0.7f),
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//
//                    if (halfStarVisible) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.StarHalf,
//                            contentDescription = null,
//                            tint = Color.Red.copy(alpha = 0.7f),
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//
//                    repeat(emptyStars) {
//                        Icon(
//                            imageVector = Icons.Default.StarBorder,
//                            contentDescription = null,
//                            tint = Color.Red.copy(alpha = 0.7f),
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//
//                    Text(
//                        text = "8",
//                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//                }
//

//                Text(
//                    text = products.patientName ?: "",
//                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Start,
//                    color = if (isDark) Color.White else Color.Black,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.fillMaxWidth()
//                        .padding(start = 3.dp, end = 3.dp)
//                )

                Row(
                    modifier = Modifier.wrapContentWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(modifier = Modifier.weight(1f),
                        text =products.patientName?:"",
                        //maxLines = 2,
                       // minLines = 2,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )

                    Text(modifier = Modifier.wrapContentWidth(),
                        text = products.updated_date?:"",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = Color.Gray,
                    )
                }

                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.weight(1f),
                        text ="CPT CODE : ${products.cptCode}",
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        textAlign = TextAlign.Start,
                        color = Color.DarkGray
                    )

                    Text(modifier = Modifier.weight(1f),
                        text = products.insuranceType?:"",
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
                        text = products.facility?:"",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}