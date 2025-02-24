package com.example.tareaprogra

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.calyr.network.GithubRemoteDataSource
import com.calyr.network.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tareaprogra.ui.theme.TareaPrograTheme

class GitUiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = applicationContext
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                TareaPrograTheme{
                    Scaffold (
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        GitUi(
                            modifier = Modifier.padding(innerPadding),
                            context = context) }
                }
            }
        }
    }

/*@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TareaPrograTheme {
        Greeting2("Android")
    }
}*/
@Composable
fun GitUi(modifier: Modifier = Modifier , context: Context){
    val dataSource:GithubRemoteDataSource = GithubRemoteDataSource(RetrofitBuilder)
    var urlImage by remember { mutableStateOf(" ") }
    var userId by remember { mutableStateOf(" ") }
    Column (
        modifier=modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text= stringResource(id=R.string.github_ui_title)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = userId,
            onValueChange = {userId= it}
        )
        Button(onClick = {val show =Toast.makeText(context,userId,Toast.LENGTH_LONG).show()
        CoroutineScope(Dispatchers.IO).launch {
            val response = dataSource.getAvatarInfo("calyr")
            urlImage=response.url
        }}) {
            Text(text = stringResource(id=R.string.github_ui_button))
        }
        AsyncImage(
            model = urlImage,
            contentDescription = null
        )
    }
}