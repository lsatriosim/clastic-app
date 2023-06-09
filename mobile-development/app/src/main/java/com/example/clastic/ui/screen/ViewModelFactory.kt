package com.example.clastic.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clastic.data.Repository
import com.example.clastic.di.Injection
import com.example.clastic.ui.screen.authentication.login.LoginViewModel
import com.example.clastic.ui.screen.authentication.register.RegisterViewModel
import com.example.clastic.ui.screen.classifier.ClassifierViewModel
import com.example.clastic.ui.screen.listArticle.ListArticleViewModel
import com.example.clastic.ui.screen.profile.ProfileViewModel
import com.example.clastic.ui.screen.splashScreen.SplashScreenViewModel
import com.example.clastic.ui.screen.transaction.createTransaction.CreateTransactionViewModel
import com.example.clastic.ui.screen.transaction.qrScanner.QRScannerScreenViewModel
import com.example.clastic.ui.screen.transaction.transactionCreated.TransactionCreatedViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> SplashScreenViewModel(repository) as T
            modelClass.isAssignableFrom(ListArticleViewModel::class.java) -> ListArticleViewModel(repository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository) as T
            modelClass.isAssignableFrom(QRScannerScreenViewModel::class.java) -> QRScannerScreenViewModel(repository) as T
            modelClass.isAssignableFrom(CreateTransactionViewModel::class.java) -> CreateTransactionViewModel(repository) as T
            modelClass.isAssignableFrom(TransactionCreatedViewModel::class.java) -> TransactionCreatedViewModel(repository) as T
            modelClass.isAssignableFrom(ClassifierViewModel::class.java) -> ClassifierViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}