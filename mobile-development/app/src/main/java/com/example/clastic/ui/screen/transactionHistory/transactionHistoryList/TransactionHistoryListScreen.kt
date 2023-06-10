package com.example.clastic.ui.screen.transactionHistory.transactionHistoryList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clastic.R
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.ui.screen.transactionHistory.components.TransactionCard
import com.example.clastic.ui.screen.transactionHistory.components.TransactionHistoryTopBar
import com.example.clastic.ui.theme.ClasticTheme
import kotlinx.coroutines.MainScope

@Composable
fun TransactionHistoryListScreen(
    navigateToProfile: () -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val viewModel: TransactionHistoryListViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context
        )
    )
    val transactionList by viewModel.transactionList.collectAsState()

    Column(
        modifier = modifier
    ) {
        TransactionHistoryTopBar(
            navigateToHome = navigateToProfile,
            stringId = R.string.transaction_history,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 12.dp)
        ) {
            for (i in transactionList.indices) {
                item {
                    TransactionCard(
                        id = transactionList[i].id,
                        onClick = { id ->
                            navigateToDetail(id)
                        },
                        date = transactionList[i].transactionDate,
                        location = transactionList[i].location,
                        points = transactionList[i].totalPoints,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionHistoryListScreenPreview() {
    ClasticTheme {
        TransactionHistoryListScreen(
            navigateToProfile = {},
            navigateToDetail ={},
        )
    }
}