package com.example.clastic.ui.screen.transaction.createTransaction

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.clastic.R
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.Transaction
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CreateTransactionViewModel(private val repository: Repository): ViewModel() {
    private val _listOfPoints = MutableStateFlow(listOf(0))
    private val _totalPoints = MutableStateFlow(0)
    private val _listOfWeights = MutableStateFlow(listOf(0f))
    private val _listOfPlasticTypes = MutableStateFlow(listOf(""))

    private val _plasticRowCount = MutableStateFlow(1)
    val plasticRowCount: StateFlow<Int> get() = _plasticRowCount

    private val _formattedPoints = MutableStateFlow("0")
    val formattedPoints: StateFlow<String> get() = _formattedPoints

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _dropPointName = MutableStateFlow("")
    val dropPointName = _dropPointName.asStateFlow()

    fun removeLast() {
        val currentList = _listOfPoints.value.toMutableList()
        val currentTypeList = _listOfPlasticTypes.value.toMutableList()
        val currentWeightList = _listOfWeights.value.toMutableList()
        if (currentList.size > 1) {
            currentList.removeAt(currentList.size - 1)
            _listOfPoints.value = currentList
            currentTypeList.removeAt(currentTypeList.size - 1)
            _listOfPlasticTypes.value = currentTypeList
            currentWeightList.removeAt(currentWeightList.size - 1)
            _listOfWeights.value = currentWeightList
            updateTotalPoints()
            updatePlasticRowCount()
            updateFormattedPoints()
        }
    }

    fun addValue() {
        val currentWeightList = _listOfWeights.value.toMutableList()
        val currentList = _listOfPoints.value.toMutableList()
        currentList.add(0)
        _listOfPoints.value = currentList
        currentWeightList.add(0f)
        _listOfWeights.value = currentWeightList
        updateTotalPoints()
        updatePlasticRowCount()
        updateFormattedPoints()
    }

    fun addType() {
        val currentList = _listOfPlasticTypes.value.toMutableList()
        currentList.add("")
        _listOfPlasticTypes.value = currentList
    }

    fun changeValueAtId(id: Int, newValue: Int, newWeight: Float) {
        val currentWeightList = _listOfWeights.value.toMutableList()
        val currentList = _listOfPoints.value.toMutableList()
        if (id in currentList.indices) {
            currentList[id] = newValue
            _listOfPoints.value = currentList
            currentWeightList[id] = newWeight
            _listOfWeights.value = currentWeightList
            updateTotalPoints()
            updateFormattedPoints()
        }
    }

    fun changeTypeAtId(id: Int, newType: String) {
        val currentList = _listOfPlasticTypes.value.toMutableList()
        if (id in currentList.indices) {
            currentList[id] = newType
            _listOfPlasticTypes.value = currentList
        }
    }

    fun getCurrentDateTimeText(): String {
        val currentDateTime = Timestamp.now()
        return getCurrentDateTimeString(currentDateTime)
    }

    private fun getMapSummary(): Map<String, Map<String, Any>> {
        val transactionMap = mutableMapOf<String, MutableMap<String, Any>>()

        for (i in _listOfPlasticTypes.value.indices) {
            val type = _listOfPlasticTypes.value[i]
            val weight = _listOfWeights.value[i]
            val points = _listOfPoints.value[i]

            val typeMap = transactionMap[type]

            if (typeMap != null) {
                val currentWeight = typeMap["weight"] as Float
                val currentPoints = typeMap["points"] as Int

                typeMap["weight"] = currentWeight + weight
                typeMap["points"] = currentPoints + points
            } else {
                val newTypeMap = mutableMapOf<String, Any>()
                newTypeMap["weight"] = weight
                newTypeMap["points"] = points

                transactionMap[type] = newTypeMap
            }
        }

        val resultMap = mutableMapOf<String, Map<String, Any>>()
        for ((type, typeMap) in transactionMap) {
            resultMap[type] = typeMap
        }
        return resultMap
    }

    suspend fun getNameByUid(uid: String) {
        _username.value = repository.getNameByUid(uid)
    }

    suspend fun uploadTransaction(context: Context, userId: String, date: String,
                                  navigateToTransactionCreated: (String) -> Unit) {
        if ("" in _listOfPlasticTypes.value || 0f in _listOfWeights.value ||
                    0 in _listOfPoints.value
        ) {
            createToast(context, context.getString(R.string.fill_all_data))
        } else {
            repository.getLoggedInUser()?.let { owner ->
                val transaction = Transaction(
                    userId = userId,
                    ownerId = owner.userId,
                    transactionDate = date,
                    totalPoints = _totalPoints.value,
                    transactionList = getMapSummary()
                )
                val transactionId = repository.createTransaction(transaction)
                navigateToTransactionCreated(transactionId)
            }
        }
    }

    suspend fun getDropPointName() {
        _dropPointName.value = repository.getDropPointName()
    }

    fun showAlertDialog(context: Context, navigateToHome: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(context.getString(R.string.warning))
        alertDialogBuilder.setMessage(context.getString(R.string.go_to_home))
        alertDialogBuilder.setCancelable(false)

        alertDialogBuilder.setPositiveButton("Ya") { _, _ ->
            navigateToHome()
        }

        alertDialogBuilder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun createToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun updateTotalPoints() {
        _totalPoints.value = _listOfPoints.value.sum()
    }

    private fun updatePlasticRowCount() {
        _plasticRowCount.value = _listOfPoints.value.size
    }

    private fun updateFormattedPoints() {
        val numberFormat = NumberFormat.getInstance(Locale.getDefault())
        _formattedPoints.value = numberFormat.format(_totalPoints.value)
    }

    private fun getCurrentDateTimeString(timestamp: Timestamp): String {
        val currentTimestamp = timestamp.toDate()
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy 'jam' HH:mm:ss",
            Locale("id", "ID")
        )
        return formatter.format(currentTimestamp)
    }
}