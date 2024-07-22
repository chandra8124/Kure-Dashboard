package org.flexi.app.presentation.viewmodels

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.flexi.app.domain.model.books.BooksItem
import org.flexi.app.domain.model.category.Categories
import org.flexi.app.domain.model.claim.ClaimModel
import org.flexi.app.domain.model.claimStatus.ClaimStatusModel
import org.flexi.app.domain.model.patientemsalldata.PatientEmSallData
import org.flexi.app.domain.model.products.Products
import org.flexi.app.domain.model.promotions.PromotionsProductsItem
import org.flexi.app.domain.model.summaryTable.SummaryTableModel
import org.flexi.app.domain.model.user.User
import org.flexi.app.domain.repository.Repository
import org.flexi.app.domain.usecase.ResultState
import org.flexi.app.presentation.ui.screens.payment.model.Order
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _login = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val login: StateFlow<ResultState<String>> = _login.asStateFlow()

    private val _signup = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val signup = _signup.asStateFlow()

    private val _products = MutableStateFlow<ResultState<List<Products>>>(ResultState.Loading)
    val products: StateFlow<ResultState<List<Products>>> = _products.asStateFlow()

    private val _claimData = MutableStateFlow<ResultState<ClaimModel>>(ResultState.Loading)
    val claimData: StateFlow<ResultState<ClaimModel>> = _claimData.asStateFlow()

    private val _summaryTable = MutableStateFlow<ResultState<SummaryTableModel>>(ResultState.Loading)
    val summaryTable: StateFlow<ResultState<SummaryTableModel>> = _summaryTable.asStateFlow()


    private val _claimStatus = MutableStateFlow<ResultState<ClaimStatusModel>>(ResultState.Loading)
    val claimStatus: StateFlow<ResultState<ClaimStatusModel>> = _claimStatus.asStateFlow()

    private val _patientEmSall = MutableStateFlow<ResultState<PatientEmSallData>>(ResultState.Loading)
    val patientEmSall: StateFlow<ResultState<PatientEmSallData>> = _patientEmSall.asStateFlow()

    private val _promotions =
        MutableStateFlow<ResultState<List<PromotionsProductsItem>>>(ResultState.Loading)
    val promotions: StateFlow<ResultState<List<PromotionsProductsItem>>> = _promotions.asStateFlow()

    private val _categories = MutableStateFlow<ResultState<List<Categories>>>(ResultState.Loading)
    val categories: StateFlow<ResultState<List<Categories>>> = _categories.asStateFlow()

    private val _books = MutableStateFlow<ResultState<List<BooksItem>>>(ResultState.Loading)
    val books: StateFlow<ResultState<List<BooksItem>>> = _books.asStateFlow()

    private val _productItem = MutableStateFlow<ResultState<List<Products>>>(ResultState.Loading)
    val productItem: StateFlow<ResultState<List<Products>>> = _productItem.asStateFlow()

    private val _deleteItem = MutableStateFlow<ResultState<Boolean>>(ResultState.Loading)
    val deleteItem: StateFlow<ResultState<Boolean>> = _deleteItem.asStateFlow()

    private val _userData = MutableStateFlow<ResultState<User>>(ResultState.Loading)
    val userData: StateFlow<ResultState<User>> = _userData.asStateFlow()

    private val _updateAddress = MutableStateFlow<ResultState<Boolean>>(ResultState.Loading)
    val updateAddress: StateFlow<ResultState<Boolean>> = _updateAddress.asStateFlow()

    private val _placeOrder = MutableStateFlow<ResultState<Order>>(ResultState.Loading)
    val placeOrder: StateFlow<ResultState<Order>> = _placeOrder.asStateFlow()

    private val _deleteCart = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val deleteCart: StateFlow<ResultState<String>> = _deleteCart.asStateFlow()

    private val _myOrders = MutableStateFlow<ResultState<List<Order>>>(ResultState.Loading)
    val myOrders: StateFlow<ResultState<List<Order>>> = _myOrders.asStateFlow()

    private val _updateCountry = MutableStateFlow<ResultState<Boolean>>(ResultState.Loading)
    val updateCountry: StateFlow<ResultState<Boolean>> = _updateCountry.asStateFlow()

    private val _updateUsersDetails = MutableStateFlow<ResultState<Boolean>>(ResultState.Loading)
    val updateUsersDetails: StateFlow<ResultState<Boolean>> = _updateUsersDetails.asStateFlow()

    private val _signupUser = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val signupUser: StateFlow<ResultState<String>> = _signupUser.asStateFlow()

    private val _loginUser = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val loginUser: StateFlow<ResultState<String>> = _loginUser.asStateFlow()

    private val _logOut = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val logOut: StateFlow<ResultState<String>> = _logOut.asStateFlow()

    private val _loginWithGoogle = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val loginWithGoogle: StateFlow<ResultState<String>> = _loginWithGoogle.asStateFlow()


    fun logout(){
        viewModelScope.launch {
            _logOut.value = ResultState.Loading
            try {
               // FlexiApiClient.supaBaseClient.auth.signOut()
                _logOut.value = ResultState.Success("Logout Successfully...")
            }catch (e: Exception){
                _logOut.value = ResultState.Error(e)
            }
        }
    }
    fun login(
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            _loginUser.value = ResultState.Loading
            try {
//                FlexiApiClient.supaBaseClient.auth.signInWith(Email){
//                    email = userEmail
//                    password = userPassword
//                }
                _loginUser.value = ResultState.Success("Login Successfully...")
            }catch (e: Exception){
                _loginUser.value = ResultState.Error(e)
            }
        }
    }
    fun signUp(
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            _signupUser.value = ResultState.Loading
            try {
//                FlexiApiClient.supaBaseClient.auth.signUpWith(Email){
//                    email = userEmail
//                    password = userPassword
//                }
                _signupUser.value = ResultState.Success("Registered Successfully...")
            }catch (e: Exception){
                _signupUser.value = ResultState.Error(e)
            }
        }
    }


    fun getClaimData() {
        viewModelScope.launch {
            _claimData.value = ResultState.Loading
            try {
                val response = repository.claimData()
                _claimData.value = ResultState.Success(response)
            } catch (e: Exception) {
                _claimData.value = ResultState.Error(e)
            }
        }
    }


    fun getSummaryTable() {
        viewModelScope.launch {
            _summaryTable.value = ResultState.Loading
            try {
                val response = repository.summaryTable()
                _summaryTable.value = ResultState.Success(response)
            } catch (e: Exception) {
                _summaryTable.value = ResultState.Error(e)
            }
        }
    }


    fun getClaimStatus() {
        viewModelScope.launch {
            _claimStatus.value = ResultState.Loading
            try {
                val response = repository.claimStatus()
                _claimStatus.value = ResultState.Success(response)
            } catch (e: Exception) {
                _claimStatus.value = ResultState.Error(e)
            }
        }
    }


    fun patientEmSallData() {
        viewModelScope.launch {
            _patientEmSall.value = ResultState.Loading
            try {
                val response = repository.patientEmSallData()
                _patientEmSall.value = ResultState.Success(response)
            } catch (e: Exception) {
                _patientEmSall.value = ResultState.Error(e)
            }
        }
    }

}