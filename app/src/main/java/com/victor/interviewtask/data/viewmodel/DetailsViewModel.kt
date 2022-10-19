package com.victor.interviewtask.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.interviewtask.data.model.DetailsItem
import com.victor.interviewtask.data.model.TodosItem
import com.victor.interviewtask.data.network.remote.RetroInstance
import com.victor.interviewtask.data.network.remote.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class DetailsViewModel: ViewModel()  {

    var allTodoLiveData: MutableLiveData<DetailsItem>

    init {
        allTodoLiveData = MutableLiveData()
    }
    //This function returns the recyclerListLiveData
    fun getDetails(): MutableLiveData<DetailsItem> {
        return allTodoLiveData
    }

    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun makeApiCall(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getTdoDetails(id)
                allTodoLiveData.postValue(response)
            }catch (e: SocketTimeoutException){
                e.message
            }
        }
    }
}