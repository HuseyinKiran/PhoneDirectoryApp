package com.huseyinkiran.phonedirectoryapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.huseyinkiran.phonedirectoryapp.room.ContactDatabase
import com.huseyinkiran.phonedirectoryapp.ui.theme.PhoneDirectoryAppTheme
import com.huseyinkiran.phonedirectoryapp.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts.db"
        ).build()
    }

    private val viewModel by viewModels<ContactViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneDirectoryAppTheme {
                val state by viewModel.state.collectAsState()
               ContactScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}

