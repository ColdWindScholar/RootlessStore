package com.baidaidai.rootless_store.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baidaidai.rootless_store.domain.execute.model.ResultTag
import com.baidaidai.rootless_store.ui.model.RootLessStoreExecuteScreenViewModel

@Composable
fun ExecuteScreen(
    contentPaddingValues: PaddingValues,
    executeScreenViewModel: RootLessStoreExecuteScreenViewModel
){
    val executeLog by executeScreenViewModel.executeLog.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(contentPaddingValues),

        contentPadding = PaddingValues(
            vertical = 15.dp,
            horizontal = 15.dp
        )
    ) {
        itemsIndexed(executeLog){ ListIndex, ListContent ->
            when(ListContent.resulTag){
                ResultTag.Normal -> {
                    Text(
                        text = ListContent.content,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                ResultTag.RedLine -> {
                    Text(
                        text = ListContent.content,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }
            }
        }
    }
}