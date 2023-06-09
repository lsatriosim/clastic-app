package com.example.clastic.ui.screen.transaction.qrScanner

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository

class QRScannerScreenViewModel(private val repository: Repository): ViewModel() {
    suspend fun isUserExist(uid: String): Boolean {
        return repository.isUserExist(uid)
    }

    fun showToast(context: Context, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ toast.cancel() }, 5000)
    }
}