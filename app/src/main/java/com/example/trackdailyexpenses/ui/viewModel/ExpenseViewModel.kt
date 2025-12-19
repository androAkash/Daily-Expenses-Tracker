package com.example.trackdailyexpenses.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackdailyexpenses.data.local.entity.TransactionEntity
import com.example.trackdailyexpenses.data.local.entity.TransactionType
import com.example.trackdailyexpenses.data.local.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class TransactionState(
    val transactions: List<TransactionEntity> = emptyList(),
    val isOpen: Boolean = false
)
class ExpenseViewModel (val repository: ExpenseRepository): ViewModel() {
    private val _state = MutableStateFlow(TransactionState())
    val state : StateFlow<TransactionState> = _state.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions(){
        viewModelScope.launch {
            repository.allTransaction.collect { transactions->
                _state.value = _state.value.copy(transactions = transactions)
            }
        }
    }
    fun openDialog(){
        _state.value = _state.value.copy(isOpen = true)
    }
    fun closeDialog(){
        _state.value = _state.value.copy(isOpen = false)
    }
    fun addTransaction(amount:Double,type: TransactionType,note:String){
        viewModelScope.launch {
            val transaction = TransactionEntity(
                amount = amount,
                type = type,
                note = note
            )
            repository.insert(transaction)
            closeDialog()
        }
    }
    fun deleteTransaction(transaction: TransactionEntity){
        viewModelScope.launch {
            repository.delete(transaction)
        }
    }
    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}