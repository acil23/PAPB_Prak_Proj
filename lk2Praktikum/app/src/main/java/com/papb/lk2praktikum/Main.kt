package com.papb.lk2praktikum

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.papb.lk2praktikum.navigation.NavigationItem
import com.papb.lk2praktikum.navigation.Screen
import com.papb.lk2praktikum.screen.MatkulScreen
import com.papb.lk2praktikum.screen.ProfileScreen
import com.papb.lk2praktikum.screen.TugasScreen
import com.papb.lk2praktikum.viewmodel.TugasViewModel
import com.papb.lk2praktikum.viewmodel.TugasViewModelFactory

class Main: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainContent()
        }
    }
}

@Composable
fun MainContent(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    Scaffold (
        bottomBar = { BottomBar(navController)},
        modifier = modifier
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Matkul.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Matkul.route) {
                MatkulScreen()
            }
            composable(Screen.Tugas.route) {
                val tugasViewModel: TugasViewModel = viewModel(
                    factory = TugasViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                TugasScreen(viewModel = tugasViewModel)
            }

            composable(Screen.Profil.route) {
                ProfileScreen()
            }
        }
    }

}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavigationBar (
        modifier = modifier
    ) {
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.matkul),
                icon = Icons.Default.Search,
                screen = Screen.Matkul
            ),
            NavigationItem(
                title = stringResource(R.string.tugas),
                icon = Icons.Default.Favorite,
                screen = Screen.Tugas
            ),
            NavigationItem(
                title = stringResource(R.string.profil),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profil
            )
        )
        navigationItem.map{item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title)},
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

