package com.example.clastic.ui.screen.transactionHistory.transactionHistoryDetail

import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.formatNumber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TransactionHistoryDetailViewModel(private val repository: Repository): ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _transactionDate = MutableStateFlow("")
    val transactionDate = _transactionDate.asStateFlow()

    private val _totalPoints = MutableStateFlow("")
    val totalPoints = _totalPoints.asStateFlow()

    private val _location = MutableStateFlow("")
    val location = _location.asStateFlow()

    private val _transactionList = MutableStateFlow<Map<String, Map<String, Any>>>(
        mapOf(
            "" to mapOf(
                "weight" to 0f,
                "points" to 0
            )
        )
    )
    val transactionList: StateFlow<Map<String, Map<String, Any>>>
        get() = _transactionList

    private suspend fun getNameByUid(uid: String): String {
        return repository.getNameByUid(uid)
    }

    suspend fun getTransaction(id: String) {
        val transaction =  repository.getTransactionById(id)
        _username.value = getNameByUid(transaction.userId)
        _totalPoints.value = formatNumber(transaction.totalPoints)
        _transactionDate.value = transaction.transactionDate
        _transactionList.value = transaction.transactionList
        _location.value = getDropPointName(transaction.ownerId)
    }

    private suspend fun getDropPointName(ownerId: String): String {
        return repository.getDropPointNameByOwnerId(ownerId)
    }
}