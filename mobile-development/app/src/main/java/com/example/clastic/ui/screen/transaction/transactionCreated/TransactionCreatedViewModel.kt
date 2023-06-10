package com.example.clastic.ui.screen.transaction.transactionCreated

import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.util.Locale

class TransactionCreatedViewModel(private val repository: Repository): ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _transactionDate = MutableStateFlow("")
    val transactionDate = _transactionDate.asStateFlow()

    private val _totalPoints = MutableStateFlow("")
    val totalPoints = _totalPoints.asStateFlow()

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
    }

    private fun formatNumber(number: Int): String {
        val numberFormat = NumberFormat.getInstance(Locale.getDefault())
        return numberFormat.format(number)
    }

    suspend fun getDropPointName(): String {
        val uid = repository.getLoggedInUser()?.userId
        return repository.getDropPointNameByOwnerId(uid!!)
    }
}