package com.victor.interviewtask.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.interviewtask.data.model.TodosItem
import com.victor.interviewtask.data.network.remote.RetroInstance
import com.victor.interviewtask.data.network.remote.RetroService
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class TodosViewModel: ViewModel()  {

    var allTodoLiveData: MutableLiveData<List<TodosItem>>

    init {
        allTodoLiveData = MutableLiveData()
    }
    //This function returns the recyclerListLiveData
    fun getRecyclerListObserver(): MutableLiveData<List<TodosItem>> {
        return allTodoLiveData
    }

    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getAllTodos()
                allTodoLiveData.postValue(response)
            }catch (e: SocketTimeoutException){
                e.message
            }
        }
    }
}