package com.rtc.feedprodution.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.rtc.feedprodution.R
import androidx.lifecycle.Observer
import com.rtc.feedprodution.MainActivity
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.userDetails.ResUserDetails
import com.rtc.feedprodution.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
        
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        subscribeObserversLogin();
        loginViewModel.setEventData(LoginStateEvent.GetLoginEvent,
            "866462032279049","7","password")



    }



    private fun subscribeObserversLogin(){
        this.let {
            loginViewModel.dataStateLogin.observe(it, Observer { dataState ->
                 when(dataState){
                    is DataState.Success<ResUserDetails> -> {
                        displayProgressBar(false)
                        Log.d("dataState",dataState.data.toString());
                        loginViewModel.getSp().setUserID("8")
                        Log.d("Sunil",loginViewModel.getSp().getUserID())
                        loginViewModel.getSp().setToken("Bearer "+dataState.data.accessToken.toString())

                        finish()
                        val changePage = Intent(this, MainActivity::class.java)
                        startActivity(changePage)


                    }
                    is DataState.Error -> {
                        displayProgressBar(false)
                        displayError(dataState.exception.message)
                    }
                    is DataState.Loading -> {
                        displayProgressBar(true)
                    }
                }
            })
        }
    }

    private fun displayError(message: String?){
        Log.d("Sunil", "displayError: "+message)
    }


    private fun displayProgressBar(isDisplayed: Boolean){
        Log.d("Sunil", "displayBool: "+isDisplayed)
    }

}