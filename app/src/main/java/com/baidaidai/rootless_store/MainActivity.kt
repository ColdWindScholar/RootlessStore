package com.baidaidai.rootless_store

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.ui.theme.RootLessStoreTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.topjohnwu.superuser.Shell

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootLessStoreTheme {
                Scaffold() { contentPadding->
                    GreetingScreen(contentPaddingValues = contentPadding)
                }
            }
        }
    }
}

private fun runShell(
    shellCommand: String
): String {
    val runShell = Shell.cmd(shellCommand).exec()
    return runShell.out.toString()
}

@Composable
fun GreetingScreen(
    contentPaddingValues: PaddingValues
){
    var value by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .padding(contentPaddingValues)
            .fillMaxSize()
            .wrapContentSize()
            .padding(horizontal = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .padding(30.dp)
                    .wrapContentSize()
            ) {
                Column{
                    OutlinedTextField(
                        value = value,
                        onValueChange = {value = it},
                        maxLines = 1,
                        label = {
                            Text("Shell Command")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        OutlinedButton(
                            onClick = {
                                value = ""
                            }
                        ) {
                            Text("Clear")
                        }
                        Button(
                            onClick = {
                                val shellOutput = runShell(value)
                                if (shellOutput.isNotEmpty()) {
                                    shellOutPutList.add(
                                        ShellResult(
                                            command = value,
                                            output = shellOutput
                                        )
                                    )
                                }
                                coroutineScope.launch {
                                    if(shellOutPutList.isNotEmpty()){
                                        lazyListState.scrollToItem(shellOutPutList.size-1)
                                    }
                                }
                            }
                        ) {
                            Text(text = "Run")
                        }
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )
        Card(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(30.dp)
            ) {
                LazyColumn(
                    state = lazyListState
                ) {
                    items(
                        items = shellOutPutList
                    ) { result ->
                        Text("~ ${result.command}")
                        result.output.forEach { line ->
                            Text(line.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun GreetingScreenPreview(){
    Scaffold() { contentPadding->
        GreetingScreen(contentPaddingValues = contentPadding)
    }
}
