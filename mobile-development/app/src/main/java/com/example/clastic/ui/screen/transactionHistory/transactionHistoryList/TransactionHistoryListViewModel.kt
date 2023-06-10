package com.example.clastic.ui.screen.transactionHistory.transactionHistoryList

import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionHistoryListViewModel(private val repository: Repository): ViewModel() {
    private val _transactionList = MutableStateFlow<List<Transaction>>(emptyList())
    val transactionList : StateFlow<List<Transaction>> get() = _transactionList

    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        scope.launch {
            fetchTransactionList()
        }
    }

    private suspend fun fetchTransactionList() {
        val uid = repository.getLoggedInUser()?.userId
        _transactionList.value = repository.getTransactionListByUid(uid!!)!!
    }

    suspend fun getDropPointNameByOwnerId(ownerId: String): String {
        return repository.getDropPointNameByOwnerId(ownerId)
    }
}