package com.rtc.feedprodution.ui.orderPendingDetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.response.orderDispatchDetails.ResOrderDispatchDetails
import com.rtc.feedprodution.model.response.orderPendingDetails.ResOrderPendingDetails
import com.rtc.feedprodution.model.userDetails.ResUserDetails
import com.rtc.feedprodution.repository.DashboardRepository
import com.rtc.feedprodution.repository.OrderDetailsRepository
import com.rtc.feedprodution.util.DataState
import com.rtc.feedprodution.util.MyPreference
import com.rtc.feedprodution.util.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class OrderPendingDetailsViewModel
@ViewModelInject
constructor(
    private val orderDetailsRepository: OrderDetailsRepository,
    private val navController: NavController,
    private val myPreference: MyPreference,
    @Assisted private val savedStateHandle: SavedStateHandle
    )
    : ViewModel() {

    fun getNav(navigateUp:Int){
        navController.navigate(navigateUp);
    }

    fun  getSp(): MyPreference {
        return myPreference
    }

    private val _dataStateMaster: MutableLiveData<DataState<ResOrderPendingDetails>> = MutableLiveData()

    val dataStateMaster: LiveData<DataState<ResOrderPendingDetails>>
        get() = _dataStateMaster


    fun setPendingOrder(mainStateEvent: MainStateEvent,
                             token: String, o: Any){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetMasterEvent -> {
                    orderDetailsRepository.getPengingOrder(token, o)
                        .onEach {dataState ->
                            _dataStateMaster.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}


sealed class MainStateEvent{

    object GetMasterEvent: MainStateEvent()
    object None: MainStateEvent()
}

