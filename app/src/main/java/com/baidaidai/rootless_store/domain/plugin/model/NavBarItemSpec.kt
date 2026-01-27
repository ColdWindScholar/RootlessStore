package com.baidaidai.rootless_store.domain.plugin.model

import androidx.compose.ui.graphics.painter.Painter

data class NavBarItemSpec(
    val number: Int,
    val pattern: Painter,
    val contentDeprecated: String,
    val destination: String
)