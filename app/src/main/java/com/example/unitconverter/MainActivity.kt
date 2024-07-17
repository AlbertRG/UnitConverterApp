package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Select") }
    var outputUnit by remember { mutableStateOf("Select") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }

    fun convertUnit() {
        when (inputUnit) {
            "Millimeters" -> {
                outputValue = when {
                    outputUnit == "Centimeters" -> {
                        "%.3f".format(inputValue.toDouble() * 0.1) + " cm"
                    }

                    outputUnit == "Meters" -> {
                        "%.3f".format(inputValue.toDouble() * 0.001) + " m"
                    }

                    outputUnit == "Feet" -> {
                        "%.3f".format(inputValue.toDouble() * 0.00328084) + " ft"
                    }

                    else -> {
                        "$inputValue mm"
                    }
                }
            }

            "Centimeters" -> {
                outputValue = when {
                    outputUnit == "Millimeters" -> {
                        "%.3f".format(inputValue.toDouble() * 10) + " mm"
                    }

                    outputUnit == "Meters" -> {
                        "%.3f".format(inputValue.toDouble() * 0.01) + " m"
                    }

                    outputUnit == "Feet" -> {
                        "%.3f".format(inputValue.toDouble() * 0.0328084) + " ft"
                    }

                    else -> {
                        "$inputValue cm"
                    }
                }

            }

            "Meters" -> {
                outputValue = when {
                    outputUnit == "Millimeters" -> {
                        "%.3f".format(inputValue.toDouble() * 1000) + " mm"
                    }

                    outputUnit == "Centimeters" -> {
                        "%.3f".format(inputValue.toDouble() * 100) + " cm"
                    }

                    outputUnit == "Feet" -> {
                        "%.3f".format(inputValue.toDouble() * 3.28084) + " ft"
                    }

                    else -> {
                        "$inputValue m"
                    }
                }
            }

            else -> {
                outputValue = when {
                    outputUnit == "Millimeters" -> {
                        "%.3f".format(inputValue.toDouble() * 304.8) + " mm"
                    }

                    outputUnit == "Centimeters" -> {
                        "%.3f".format(inputValue.toDouble() * 30.48) + " cm"
                    }

                    outputUnit == "Meters" -> {
                        "%.3f".format(inputValue.toDouble() * 0.3048) + " m"
                    }

                    else -> {
                        "$inputValue ft"
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Here all the UI elements will be stacked below each other
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineLarge,)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
        }, label = { Text("Enter value") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            // Here all the UI elements will be stacked next each other
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            inputUnit = "Millimeters"
                            iExpanded = false
                        })
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputUnit = "Centimeters"
                            iExpanded = false
                        })
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputUnit = "Meters"
                            iExpanded = false
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputUnit = "Feet"
                            iExpanded = false
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            outputUnit = "Millimeters"
                            oExpanded = false
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            outputUnit = "Centimeters"
                            oExpanded = false
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputUnit = "Meters"
                            oExpanded = false
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputUnit = "Feet"
                            oExpanded = false
                            convertUnit()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${outputValue}",
            style = MaterialTheme.typography.headlineMedium)
    }

}

//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}