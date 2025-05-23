package com.example.appaulafirebase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Telas() {
    Principal,
    Cadastros
}

@Composable
fun Navegacao(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Telas.Principal.name,
        modifier = Modifier
    ) {
        composable(route = Telas.Principal.name) {
            AppAulaFirebase(
                proximo = {
                    navController.navigate(Telas.Cadastros.name)
                }
            )
        }
        composable(route = Telas.Cadastros.name) {
            ListaCadastrados(
                proximo = {
                    navController.navigate(Telas.Principal.name)
                }
            )
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    navController: NavHostController
) {
}