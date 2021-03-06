package com.rtc.feedprodution.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.rtc.feedprodution.model.HomeScreen
import com.rtc.feedprodution.repository.HomeRepository
import com.rtc.feedprodution.util.DataState
import com.rtc.feedprodution.util.Event
import com.rtc.feedprodution.util.EventObserver
import com.rtc.feedprodution.util.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel
@ViewModelInject
constructor(
    private val homeRepository: HomeRepository,
    private val navController: NavController,
    @Assisted private val savedStateHandle: SavedStateHandle
)
    : ViewModel() {

    fun getNav(navigateUp:Int){
        navController.navigate(navigateUp);
    }

    private val _dataState: SingleLiveEvent<DataState<List<HomeScreen>>> = SingleLiveEvent()

    val dataState: SingleLiveEvent<DataState<List<HomeScreen>>>
        get() = _dataState



    fun setStateEvent(mainStateEvent: MainStateEvent){

        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetMenusEvent -> {
                    homeRepository.getMenus()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {

                }
            }
        }
    }


}


sealed class MainStateEvent{

    object GetMenusEvent: MainStateEvent()

    object None: MainStateEvent()
}

