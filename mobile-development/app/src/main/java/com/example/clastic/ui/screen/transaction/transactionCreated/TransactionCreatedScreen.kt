package com.example.clastic.ui.screen.transaction.transactionCreated

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clastic.R
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.ui.screen.transaction.components.TransactionDetailItem
import com.example.clastic.ui.screen.transaction.components.TransactionResultList
import com.example.clastic.ui.screen.transaction.components.TransactionTopBar
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun TransactionCreatedScreen(
    transactionId: String,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val viewModel: TransactionCreatedViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context
        )
    )
    var location by remember { mutableStateOf("") }
    val username by viewModel.username.collectAsState()
    val transactionDate by viewModel.transactionDate.collectAsState()
    val totalPoints by viewModel.totalPoints.collectAsState()
    val transactionList by viewModel.transactionList.collectAsState()

    LaunchedEffect(Unit) {
        location = viewModel.getDropPointName()
        viewModel.getTransaction(transactionId)
    }

    Column(
        modifier = modifier
    ) {
        TransactionTopBar(
            navigateToHome = navigateToHome,
            stringId = R.string.transaction_result
        )
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 12.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.transaction_created),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = colorResource(R.color.cyan_primary),
                        modifier = Modifier
                            .padding(top = 40.dp, bottom = 40.dp)
                            .size(150.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.transaction_information),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        TransactionDetailItem(
                            fieldName = stringResource(R.string.transaction_code),
                            fieldValue = transactionId
                        )
                        TransactionDetailItem(
                            fieldName = stringResource(R.string.receiver),
                            fieldValue = username
                        )
                        TransactionDetailItem(
                            fieldName = stringResource(R.string.location),
                            fieldValue = location
                        )
                        TransactionDetailItem(
                            fieldName = stringResource(R.string.time),
                            fieldValue = transactionDate
                        )
                        Divider(
                            color = colorResource(R.color.cyan_primary_variant),
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .height(2.dp)
                        )
                        Text(
                            text = stringResource(R.string.plastic_transaction_detail),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = colorResource(R.color.cyan_primary),
                        )
                        transactionList.forEach { (type, data) ->
                            TransactionResultList(
                                plasticType = type,
                                plasticWeight = (data["weight"] as Number).toFloat(),
                                totalPoints = (data["points"] as Number).toInt()
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "Total Point: $totalPoints pts",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.cyan_primary),
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                        )
                        Button(
                            onClick = navigateToHome,
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(R.color.cyan_primary)
                            ),
                            modifier = modifier
                                .heightIn(min = 48.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.back_to_home),
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionCreatedScreenPreview() {
    ClasticTheme {
        TransactionCreatedScreen(
            transactionId = "AIACOANOINOAINCIA",
            navigateToHome = {}
        )
    }
}