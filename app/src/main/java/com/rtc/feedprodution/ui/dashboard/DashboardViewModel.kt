package com.rtc.feedprodution.ui.dashboard

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.userDetails.ResUserDetails
import com.rtc.feedprodution.repository.DashboardRepository
import com.rtc.feedprodution.util.DataState
import com.rtc.feedprodution.util.MyPreference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DashboardViewModel
@ViewModelInject
constructor(
    private val dashboardRepository: DashboardRepository,
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

    private val _dataStateLogin: MutableLiveData<DataState<ResUserDetails>> = MutableLiveData()

    val dataStateLogin: LiveData<DataState<ResUserDetails>>
        get() = _dataStateLogin

    private val _dataStateMaster: MutableLiveData<DataState<ResMasters>> = MutableLiveData()

    val dataStateMaster: LiveData<DataState<ResMasters>>
        get() = _dataStateMaster

    fun setAccessMasterEvent(mainStateEvent: MainStateEvent,
                             token: String, o: Any){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetMasterEvent -> {
                    dashboardRepository.getAccessMasters(token, o)
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

