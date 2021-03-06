package com.rtc.feedprodution.ui.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.userDetails.ResUserDetails
import com.rtc.feedprodution.repository.LoginRepository
import com.rtc.feedprodution.util.DataState
import com.rtc.feedprodution.util.MyPreference
import com.rtc.feedprodution.util.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class LoginViewModel
@ViewModelInject
constructor(
    private val loginRepository:LoginRepository,
    private val myPreference: MyPreference,
    @Assisted private val savedStateHandle: SavedStateHandle
)
    : ViewModel() {

    fun  getSp():MyPreference{
        return myPreference
    }

    private val _dataStateLogin: SingleLiveEvent<DataState<ResUserDetails>> = SingleLiveEvent()

    val dataStateLogin: SingleLiveEvent<DataState<ResUserDetails>>
        get() = _dataStateLogin

    private val _dataStateMaster: SingleLiveEvent<DataState<ResMasters>> = SingleLiveEvent()

    val dataStateMaster: SingleLiveEvent<DataState<ResMasters>>
        get() = _dataStateMaster


    fun setEventData(mainStateEvent: LoginStateEvent,userName: String?,
                      password: String?,
                      grant_type: String){
        viewModelScope.launch {
            when(mainStateEvent){
                is LoginStateEvent.GetLoginEvent -> {
                    loginRepository.getLoginDetails(userName,
                        password,
                        grant_type)
                        .onEach {dataState ->
                            _dataStateLogin.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                LoginStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}




sealed class LoginStateEvent{

    object GetLoginEvent: LoginStateEvent()

    object None: LoginStateEvent()
}

