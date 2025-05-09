//Login - Diseño y logica para que el usuario ingrese sus crendenciales.
package com.example.projecto.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Primario
import com.example.projecto.ui.theme.Texto
import com.example.projecto.ui.theme.TextoSecundario
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projecto.R
import com.example.projecto.data.User
import com.example.projecto.data.UserViewModel
import com.example.projecto.ui.theme.Primario
import com.example.projecto.ui.theme.Texto
import com.example.projecto.ui.theme.TextoSecundario
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Secundario

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    val context = LocalContext.current
    val user = userViewModel.user
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors=listOf(
                    Fondo,
                    Fondo,
                    Primario
                ),
                startY=0.5f * LocalDensity.current.density,
                endY= LocalDensity.current.density * 1000f
            )
        )
        .padding(24.dp))
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly)
        {
            Text(
                color = Primario,
                text = "Bienvenido a nuestra app",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Image(painter = painterResource(id = R.drawable.baseline_login_24),
                contentDescription = "Imagen de inicio de sesion",
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.6f)
                    .padding(8.dp))
            {
                Column {

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Nombre", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small,
                        singleLine = true,
                        textStyle = TextStyle(color = Secundario))


                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small,
                        textStyle = TextStyle(color = Secundario),
                        visualTransformation = PasswordVisualTransformation(),  // Esto oculta el texto
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))  // Teclado adecuado)

                    Spacer(modifier = Modifier.height(70.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp))
                    {
                        Button(
                            onClick = {
                                navController.navigate("register") {
                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(start = 8.dp),
                            border = BorderStroke(1.dp, Primario),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Fondo,
                            ),
                            contentPadding = PaddingValues(),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 16.dp))
                        {
                            Text("Registrarme", color = Texto)
                        }
                        Button(
                            onClick = {
                                if (username == user?.nombre && password == user.password) {
                                    navController.navigate("home") // Cambiar "home" por la tencera pantalla
                                } else {
                                    Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(end = 8.dp),
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(1.dp, Primario),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Fondo,
                            ),
                            contentPadding = PaddingValues(),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 16.dp))
                        {
                            Text("Iniciar Sesion", color = Texto)

                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginScreenPreview(){
    val fakeUserViewModel = object : UserViewModel() {
        init {
            user = User(nombre = "Juan", password = "123456" , email = "juan@ejemplo.com") // ejemplo
        }
    }
    LoginScreen(
        navController = rememberNavController(),
        userViewModel = fakeUserViewModel
    )
}