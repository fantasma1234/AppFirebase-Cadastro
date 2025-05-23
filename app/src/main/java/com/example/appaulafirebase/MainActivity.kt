package com.example.appaulafirebase

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appaulafirebase.ui.theme.AppAulaFirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAulaFirebaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navegacao()
                }
            }
        }
    }
}

@Composable
fun AppAulaFirebase(
    proximo: () -> Unit = {}
) {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val db = Firebase.firestore
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            Arrangement.Center
        ) {
            Text("Formulario de Cadastro")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { Text("CPF") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Button(onClick = {


                // Create a new user with a first and last name
                val user = hashMapOf(
                    "nome" to nome,
                    "idade" to idade,
                    "email" to email,
                    "senha" to senha,
                    "cpf" to cpf
                )

// Add a new document with a generated ID
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
                nome = ""
                idade = ""
                email = ""
                senha = ""
                cpf = ""
            }) {
                Text("Cadastrar")
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth(), Arrangement.Center
        ) {
            Button(onClick = proximo) {
                Text("Cadastrados")
            }
            Button(onClick = {
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d(TAG, "${document.id} => ${document.data}")
                            val nome = document.getString("nome")
                            val idade = document.get("idade")
                            val email = document.getString("email")
                            val senha = document.getString("senha")
                            val cpf = document.getString("cpf")
                            //resultado +=  "${document.id} => ${document.data} \n"
                            resultado += "Nome: $nome email: $email idade: $idade senha: $senha cpf: $cpf\n"
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }
            }) {
                Text("Exibir")
            }
        }

        Text(text = resultado.trim())
    }
}


@Preview(showBackground = true)
@Composable
fun AppAulaFirebasePreview() {
    AppAulaFirebaseTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppAulaFirebase()
        }
    }
}




