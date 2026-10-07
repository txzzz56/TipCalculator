package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalCulatorTheme
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalCulatorTheme { TipApp() }
        }
    }
}

// TopAppBar is experimental in Material 3, so we need @OptIn
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipApp() {
    // State variables: UI updates automatically when these change
    var billText by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(10) }
    var menuOpen by remember { mutableStateOf(false) }

    // Background colours for the action button
    val colors = listOf(Color.White, Color(0xFFFFF3E0), Color(0xFFE3F2FD), Color(0xFFE8F5E9))
    var colorIndex by remember { mutableIntStateOf(0) }

    // Calculation for the bill
    val bill = billText.toDoubleOrNull() ?: 0.0
    val tip = bill * tipPercent / 100
    val total = bill + tip

    Scaffold(
        containerColor = colors[colorIndex],
        topBar = {
            TopAppBar(
                title = { Text("Tip Calculator") },
                actions = {
                    // Action button (not in the menu): changes background colour
                    IconButton(onClick = { colorIndex = (colorIndex + 1) % colors.size }) {
                        Text("★")
                    }
                    // Options menu (⋮) with tips10 and tips15
                    Box {
                        IconButton(onClick = { menuOpen = true }) {
                            Text("⋮", fontSize = 24.sp)
                        }
                        DropdownMenu(
                            expanded = menuOpen,
                            onDismissRequest = { menuOpen = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("tips10") },
                                onClick = { tipPercent = 10; menuOpen = false }
                            )
                            DropdownMenuItem(
                                text = { Text("tips15") },
                                onClick = { tipPercent = 15; menuOpen = false }
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues).padding(16.dp)) {
            // Bill input with number keyboard
            OutlinedTextField(
                value = billText,
                onValueChange = { billText = it },
                label = { Text("Bill amount") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Spacer(Modifier.height(16.dp))
            // Results, formatted to 2 decimal places
            Text("Selected option: tips$tipPercent")
            Text("Tip amount: $%.2f".format(tip))
            Text("Total to pay: $%.2f".format(total))
        }
    }
}