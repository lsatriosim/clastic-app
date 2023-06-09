package com.example.clastic.ui.screen.transaction.createTransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberSmartRecord
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.clastic.ui.screen.transaction.components.AddRemoveButton
import com.example.clastic.ui.screen.transaction.components.PlasticInput
import com.example.clastic.ui.screen.transaction.components.SubmitTransactionButton
import com.example.clastic.ui.screen.transaction.components.TransactionTextField
import com.example.clastic.ui.screen.transaction.components.TransactionTopBar
import com.example.clastic.ui.theme.ClasticTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun CreateTransactionScreen(
    scannedUID: String?,
    navigateToHome: () -> Unit,
    navigateToTransactionCreated: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val mainScope = MainScope()
    val context = LocalContext.current
    val viewModel: CreateTransactionViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context
        )
    )
    val formattedPoints by viewModel.formattedPoints.collectAsState()
    val plasticRowCount by viewModel.plasticRowCount.collectAsState()
    val dropPointName by viewModel.dropPointName.collectAsState()
    val currentDate = viewModel.getCurrentDateTimeText()
    val username by viewModel.username.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNameByUid(scannedUID!!)
        viewModel.getDropPointName()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TransactionTopBar(
            navigateToHome = {
                viewModel.showAlertDialog(context, navigateToHome)
            },
            stringId = R.string.create_transaction
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 24.dp)
        ) {
            item {
                TransactionTextField(
                    text = username,
                    labelId = R.string.username,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp)
                )
            }
            item {
                TransactionTextField(
                    text = currentDate,
                    labelId = R.string.transaction_time,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            item {
                TransactionTextField(
                    text = dropPointName,
                    labelId = R.string.transaction_place,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            item {
                Text(
                    text = stringResource(R.string.plastic_transaction_detail),
                    color = colorResource(R.color.cyan_textfield),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }
            item {
                for (i in 0 until plasticRowCount) {
                    PlasticInput(
                        id = i,
                        onValueChanged = { newWeight, newValue, id->
                            viewModel.changeValueAtId(id, newValue, newWeight)
                        },
                        onTypeSelected = { newType, id ->
                            viewModel.changeTypeAtId(id, newType)
                        }
                    )
                }
            }
            item {
                AddRemoveButton(
                    onAdd = {
                        viewModel.addValue()
                        viewModel.addType()
                    },
                    onRemove = {
                        viewModel.removeLast()
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            item {
                Text(
                    text = stringResource(R.string.total_points),
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp,
                    color = colorResource(R.color.cyan_primary_variant)
                )
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FiberSmartRecord,
                        contentDescription = null,
                        tint = colorResource(R.color.cyan_primary_variant),
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Text(
                        text = "$formattedPoints pts",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = colorResource(R.color.cyan_primary_variant)
                    )
                }
            }
            item {
                SubmitTransactionButton(
                    onClick = {
                        mainScope.launch {
                            viewModel.uploadTransaction(context, scannedUID!!, currentDate, navigateToTransactionCreated)
                        }
                    },
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTransactionScreenPreview() {
    ClasticTheme {
        CreateTransactionScreen(
            scannedUID = "UXDADADADADADCAC",
            navigateToHome = {},
            navigateToTransactionCreated = {}
        )
    }
}