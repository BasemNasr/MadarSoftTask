package com.bn.madarsofttaskbassemnar.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bn.madarsofttaskbassemnar.ui.theme.SurfaceContainerHigh
import com.bn.madarsofttaskbassemnar.ui.theme.SurfaceContainerLowest

@Composable
fun AtelierTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isFocused by remember { mutableStateOf(false) }
    val isPopulated = value.isNotEmpty()
    val isActive = isFocused || isPopulated

    val backgroundColor by animateColorAsState(
        if (isFocused) SurfaceContainerLowest else SurfaceContainerHigh,
        label = "backgroundColor"
    )

    val labelSize by animateFloatAsState(
        if (isActive) 12f else 14f,
        label = "labelSize"
    )

    val labelOffset by animateDpAsState(
        if (isActive) 8.dp else 24.dp,
        label = "labelOffset"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .onFocusChanged { isFocused = it.isFocused }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .heightIn(min = 64.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Label
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = labelSize.sp,
                    color = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
                ),
                modifier = Modifier.offset(y = labelOffset - 16.dp)
            )

            // Input
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 8.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                keyboardOptions = keyboardOptions,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                singleLine = true
            )
        }
    }
}
