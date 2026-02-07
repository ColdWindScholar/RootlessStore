package com.baidaidai.rootless_store.ui.theme.colorscheme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun SourceListItemColor(): ListItemColors {
    val cs = MaterialTheme.colorScheme
    return ListItemColors(
        containerColor = cs.surfaceVariant,
        contentColor = cs.onSurface,
        leadingContentColor = cs.onSurfaceVariant,
        trailingContentColor = cs.onSurfaceVariant,
        overlineContentColor = cs.onSurfaceVariant,
        supportingContentColor = cs.onSurfaceVariant,

        // Disabled：不做 copy，不在这里“造颜色”
        // 让组件在 enabled=false 时用 ContentAlpha.disabled 进行衰减
        disabledContainerColor = cs.surfaceVariant,
        disabledContentColor = cs.onSurface,
        disabledLeadingContentColor = cs.onSurfaceVariant,
        disabledTrailingContentColor = cs.onSurfaceVariant,
        disabledOverlineContentColor = cs.onSurfaceVariant,
        disabledSupportingContentColor = cs.onSurfaceVariant,

        // Selected：用 container/onContainer（不用 copy）
        selectedContainerColor = cs.secondaryContainer,
        selectedContentColor = cs.onSecondaryContainer,
        selectedLeadingContentColor = cs.onSecondaryContainer,
        selectedTrailingContentColor = cs.onSecondaryContainer,
        selectedOverlineContentColor = cs.onSecondaryContainer,
        selectedSupportingContentColor = cs.onSecondaryContainer,

        // Dragged：更像“浮起的那一行”，用 surface/onSurface（不用 copy）
        draggedContainerColor = cs.surface,
        draggedContentColor = cs.onSurface,
        draggedLeadingContentColor = cs.onSurfaceVariant,
        draggedTrailingContentColor = cs.onSurfaceVariant,
        draggedOverlineContentColor = cs.onSurfaceVariant,
        draggedSupportingContentColor = cs.onSurfaceVariant,
    )
}
