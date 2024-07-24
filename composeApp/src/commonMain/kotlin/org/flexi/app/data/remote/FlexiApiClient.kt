package org.flexi.app.data.remote

import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import org.flexi.app.domain.model.claim.ClaimModel
import org.flexi.app.domain.model.claimStatus.ClaimStatusModel
import org.flexi.app.domain.model.contested_pay_change.ContestedPayChanges
import org.flexi.app.domain.model.login.LoginResponse
import org.flexi.app.domain.model.patientemsalldata.PatientEmSallData
import org.flexi.app.domain.model.payPeriod.PayPeriod
import org.flexi.app.domain.model.payrollSalaryListing.PayrollSalaryListing
import org.flexi.app.domain.model.setUpFieldList.SetUpFieldList
import org.flexi.app.domain.model.summaryTable.SummaryTableModel
import org.flexi.app.utils.Constant.BASE_URL
import org.flexi.app.utils.Constant.BASE_URL_NODE
import org.flexi.app.utils.Constant.TIME_OUT

object FlexiApiClient {
    val supaBaseClient = createSupabaseClient(
        supabaseUrl = "https://flrflqyxquvzhlvfcbit.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZscmZscXl4cXV2emhsdmZjYml0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ3MjA3MDgsImV4cCI6MjAzMDI5NjcwOH0.HjJVA5yZdXIKHmICMxucgOJqYSz-APT_pYyEKr9FvaE"
    ){
        install(Auth)
        install(ComposeAuth)
    }

    val token = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5MTc3Nzc3NzAyNTUiLCJhdWRpZW5jZSI6ImFuZHJvaWQiLCJ0b2tlblR5cGUiOiJVc2VyIiwiaWF0IjoxNzIxODA2MzU0LCJleHAiOjE3MjI0MTExNTR9.c0SMdMT6GHFlHF47tPqV2iMBALgnT-RszKWcdnYaioQ"


    private val client = HttpClient {
        install(ContentNegotiation) {
            json(contentType = ContentType.Application.Json,
                json = Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                    println("cs singh testing app 1")
                }
            }
            //filter { filter -> filter.url.host.contains("192.168.10") }
            println("cs singh testing app 2 : ${HttpHeaders.Authorization} ")
            //sanitizeHeader { header -> header==HttpHeaders.Authorization }
        }
        install(HttpTimeout) {
            socketTimeoutMillis = TIME_OUT
            connectTimeoutMillis = TIME_OUT
            requestTimeoutMillis = TIME_OUT
        }
    }
//{"deviceType":"android","mobile":"917777774125","password":"Raj@123"}
    @OptIn(InternalAPI::class)
    suspend fun loginUser(email: String, password: String): LoginResponse {
        val url = BASE_URL_NODE + "api/v0.1/auth"
        val loginRequest = Parameters.build {
            append("deviceType", "android")
            append("mobile", email)
            append("password", password)
        }
        return client.post(url) {
            body = FormDataContent(loginRequest)
        }.body()
    }
//
//    @OptIn(InternalAPI::class)
//    suspend fun signupUser(username: String, email: String, password: String): String {
//        val formData = Parameters.build {
//            append("userName", username)
//            append("email", email)
//            append("password", password)
//            append("fullName", "null")
//            append("address", "null")
//            append("postalCode", "12341")
//            append("city", "null")
//            append("country", "null")
//            append("phoneNumber", "null")
//            append("imageUrl", "/upload/products/users/avatar-1.webp")
//            append("userRole", "Customer")
//        }
//
//        val url = BASE_URL + "v1/signup"
//        return client.post(url) {
//            body = FormDataContent(formData)
//        }.body()
//    }
//
//    suspend fun getProducts(): List<Products> {
//        return client.get(BASE_URL + "v1/products").body()
//    }
//
//    suspend fun getPromotionsList(): List<PromotionsProductsItem> {
//        return client.get(BASE_URL + "v1/promotions").body()
//    }
//
//    suspend fun getCategoriesList(): List<Categories> {
//        return client.get(BASE_URL + "v1/categories").body()
//    }
//
//    suspend fun getBooksList(): List<BooksItem> {
//        return client.get(BASE_URL + "v1/books").body()
//    }
//
//    suspend fun getCartListByUserId(id: Long): List<CartItem> {
//        return client.get(BASE_URL + "v1/cart/user/1").body()
//    }
//
//    suspend fun getProductById(id: List<Long>): List<Products> {
//        val idString = id.joinToString(",")
//        return client.get(BASE_URL + "v1/products/userId/$idString").body()
//    }
//
//    @OptIn(InternalAPI::class)
//    suspend fun addToCart(productId: Long, quantity: Int, userId: Long): CartItem {
//        val url = BASE_URL + "v1/cart"
//        val formData = Parameters.build {
//            append("productId", productId.toString())
//            append("quantity", quantity.toString())
//            append("userId", userId.toString())
//        }
//        return client.post(url) {
//            body = FormDataContent(formData)
//        }.body()
//    }
//
//    suspend fun getCartItem(cartId: Long): CartItem {
//        return client.get(BASE_URL + "v1/cart/cartId/$cartId").body()
//    }
//
//    suspend fun deleteCartItemById(cartId: Long): Boolean {
//        val url = BASE_URL + "v1/cart/item/$cartId"
//        return try {
//            val response = client.delete(url)
//            response.status.isSuccess()
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    suspend fun getUserData(id: Int): User {
//        return client.get(BASE_URL + "v1/users/$id").body()
//    }
//
//    @OptIn(InternalAPI::class)
//    suspend fun updateUsersAddress(
//        address: String,
//        city: String,
//        country: String,
//        postalCode: Long,
//    ): Boolean {
//        val url = BASE_URL + "v1/users/address/1"
//        val formData = Parameters.build {
//            append("address", address)
//            append("city", city)
//            append("country", country)
//            append("postalCode", postalCode.toString())
//        }
//        return client.put(url) {
//            body = FormDataContent(formData)
//        }.body()
//    }
//
//    @OptIn(InternalAPI::class)
//    suspend fun placeOrder(
//        userId: Int,
//        productIds: Int,
//        totalQuantity: String,
//        totalPrice: Int,
//        selectedColor: String,
//        paymentType: String,
//    ): Order {
//        val url = BASE_URL + "v1/order"
//        val formData = Parameters.build {
//            append("userId", userId.toString())
//            append("productIds", productIds.toString())
//            append("totalQuantity", totalQuantity)
//            append("totalPrice", totalPrice.toString())
//            append("selectedColor", selectedColor)
//            append("paymentType", paymentType)
//        }
//        return client.post(url) {
//            body = FormDataContent(formData)
//        }.body()
//    }
//
//    suspend fun deleteUserCart(id: Int): String {
//        return client.delete(BASE_URL + "v1/cart/$id").body()
//    }
//
//    suspend fun getMyOrders(userId: Int): List<Order> {
//        return client.get(BASE_URL + "v1/order/userId/$userId").body()
//    }
//
//    suspend fun getJsonData(): JsonArray {
//        return client.get("https://raw.githubusercontent.com/Kotlin/KMP-App-Template-Native/main/list.json")
//        {
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//        }.body()
//    }
//
//    suspend fun getJsonData2(): ClaimModel2 {
//        return client.get("https://official-joke-api.appspot.com/random_joke")
//        {
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//        }.body()
//    }
//
//    @OptIn(InternalAPI::class)
//    suspend fun updateCountry(
//        usersId: Int,
//        countryName: String,
//    ): Boolean {
//        val url = BASE_URL + "v1/users/country/$usersId"
//        val formData = Parameters.build {
//            append("countryName", countryName)
//        }
//        return client.put(url) {
//            body = FormDataContent(formData)
//        }.body()
//    }
//
//    @OptIn(InternalAPI::class)
//    suspend fun updateUsersDetails(
//        usersId: Int,
//        username: String,
//        fullName: String,
//        email: String,
//        address: String,
//        city: String,
//        country: String,
//        postalCode: Long,
//        phoneNumber: String
//    ): Boolean {
//        val url = BASE_URL + "v1/users/userDetail/$usersId"
//        val formData = Parameters.build {
//            append("username", username)
//            append("fullName", fullName)
//            append("email", email)
//            append("address", address)
//            append("city", city)
//            append("country", country)
//            append("postalCode", postalCode.toString())
//            append("phoneNumber", phoneNumber)
//        }
//        return client.put(url) {
//            body = FormDataContent(formData)
//        }.body()
//    }

    @OptIn(InternalAPI::class)
    suspend fun claimData(): ClaimModel {
        val url = BASE_URL + "api/v0.1/dashboard/billing/claimalldata"

        return client.post(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append("Authorization",token)
            }
            body =
                "{\"startDate\":\"2023-06-12\",\"endDate\":\"2024-06-11\",\"query\":\"\",\"params\":{\"sortby\":\"\",\"sorttype\":\"\",\"facility\":[],\"claimStatus\":[],\"cptCode\":[],\"payer\":[],\"insuranceType\":[],\"extraStatus\":[],\"agingCategories\":[],\"patientName\":\"\",\"pendingLearnerBalance\":false,\"showClaimsWithVariance\":false,\"performanceStatus\":\"\"},\"offset\":\"0\",\"limit\":\"20\"}\n"
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun summaryTable(): SummaryTableModel {
        val url = BASE_URL_NODE + "api/v0.1/claim/summary-table"

        return client.post(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body =
                "{\"startDate\":\"2023-06-12\",\"endDate\":\"2024-06-11\",\"query\":\"\",\"params\":{\"sortby\":\"\",\"sorttype\":\"\",\"facility\":[],\"claimStatus\":[],\"cptCode\":[],\"payer\":[],\"insuranceType\":[],\"extraStatus\":[],\"agingCategories\":[],\"patientName\":\"\",\"pendingLearnerBalance\":false,\"showClaimsWithVariance\":false,\"performanceStatus\":\"\"},\"offset\":\"0\",\"limit\":\"20\"}\n"
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun claimStatus(): ClaimStatusModel {
        val url = BASE_URL + "api/v0.1/dashboard/billing/claimtotalstatus"

        return client.post(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body =
                "{\"startDate\":\"2023-06-12\",\"endDate\":\"2024-06-11\",\"query\":\"\",\"params\":{\"sortby\":\"\",\"sorttype\":\"\",\"facility\":[],\"claimStatus\":[],\"cptCode\":[],\"payer\":[],\"insuranceType\":[],\"extraStatus\":[],\"agingCategories\":[],\"patientName\":\"\",\"pendingLearnerBalance\":false,\"showClaimsWithVariance\":false,\"performanceStatus\":\"\"},\"offset\":\"0\",\"limit\":\"20\"}\n"
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun patientEmSallData(): PatientEmSallData {
        val url = BASE_URL + "api/v0.1/dashboard/facility/patientemsalldata"

        return client.post(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body ="{\"params\":{\"claimStatus\":[\"ADDTOCLAIMQUEUE\"],\"insuranceType\":\"All\"}}"
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun payrollSalaryListing(): PayrollSalaryListing {
        val url = BASE_URL + "api/v0.1/payroll/salary/listing"

        return client.post(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body ="{\"startDate\":\"2023-07-16\",\"endDate\":\"2024-07-13\",\"params\":{}}"
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun setUpFieldList(): SetUpFieldList {
        val url = BASE_URL + "api/v0.1/dashboard/provider/setupfieldlist"

        return client.get(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body =""
        }.body()
    }
    @OptIn(InternalAPI::class)
    suspend fun contestedPayChanges(): ContestedPayChanges {
        val url = BASE_URL_NODE + "api/v0.1/employee/contested-pay-change"

        return client.get(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body =""
        }.body()
    }
 @OptIn(InternalAPI::class)
    suspend fun payPeriod(): PayPeriod {
        val url = BASE_URL + "api/v0.1/payroll/paydate/list"

        return client.post(url) {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append(
                    "Authorization",
                    token
                )
            }
            body =""
        }.body()
    }
}