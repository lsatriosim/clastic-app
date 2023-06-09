package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlasticDropDownMenu(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var plasticType by rememberSaveable {
        mutableStateOf("")
    }

    val cyanColor = colorResource(R.color.cyan_textfield)

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = plasticType,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = stringResource(R.string.type))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded,
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = cyanColor,
                unfocusedBorderColor = cyanColor,
                trailingIconColor = cyanColor,
                focusedTrailingIconColor = cyanColor,
                placeholderColor = Color.Gray,
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(120.dp)
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    plasticType = "PET"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.pet)
            }
            DropdownMenuItem(
                onClick = {
                    plasticType = "HDPE"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.hdpe)
            }
            DropdownMenuItem(
                onClick = {
                    plasticType = "PP"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.pp)
            }
            DropdownMenuItem(
                onClick = {
                    plasticType = "PVC"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.pvc)
            }
            DropdownMenuItem(
                onClick = {
                    plasticType = "LDPE"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.ldpe)
            }
            DropdownMenuItem(
                onClick = {
                    plasticType = "PS"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.ps)
            }
            DropdownMenuItem(
                onClick = {
                    plasticType = "Other"
                    onValueChange(plasticType)
                    isExpanded = false
                }
            ) {
                PlasticItemText(R.string.other)
            }
        }
    }
}

@Composable
fun PlasticItemText(
    stringId: Int
) {
    Text(
        text = stringResource(stringId)
    )
}

@Preview(showBackground = true)
@Composable
fun PlasticDropDownMenuPreview() {
    ClasticTheme {
        PlasticDropDownMenu(
            onValueChange = {}
        )
    }
}