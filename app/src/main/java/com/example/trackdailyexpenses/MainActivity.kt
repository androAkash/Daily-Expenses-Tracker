package com.example.trackdailyexpenses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trackdailyexpenses.data.database.ExpenseDatabase
import com.example.trackdailyexpenses.data.local.repository.ExpenseRepository
import com.example.trackdailyexpenses.ui.screens.ExpenseScreen
import com.example.trackdailyexpenses.ui.theme.TrackDailyExpensesTheme
import com.example.trackdailyexpenses.ui.viewModel.ExpenseViewModel
import com.example.trackdailyexpenses.ui.viewModel.ExpenseViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: ExpenseViewModel by viewModels {
        // Get database instance
        val database = ExpenseDatabase.getDatabase(applicationContext)

        // Create repository
        val repository = ExpenseRepository(database.transactionDao())

        // Create ViewModel factory
        ExpenseViewModelFactory(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackDailyExpensesTheme {
                ExpenseScreen(viewModel = viewModel)
            }
        }
    }
}
