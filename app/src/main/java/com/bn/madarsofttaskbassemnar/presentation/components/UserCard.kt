package com.bn.madarsofttaskbassemnar.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.bn.madarsofttaskbassemnar.R
import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.ui.theme.PrimaryFixed
import com.bn.madarsofttaskbassemnar.ui.theme.SurfaceContainerLowest

@Composable
fun UserCard(
    user: User,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceContainerLowest)
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Avatar Placeholder
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PrimaryFixed),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (user.gender == "MALE") Icons.Default.Person else Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Delete Icon
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.content_desc_delete),
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onDelete() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = user.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                text = user.jobTitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MetaDataChip(
                    icon = Icons.Default.Info,
                    text = stringResource(R.string.user_age_format, user.age)
                )
                MetaDataChip(
                    icon = if (user.gender == "MALE") Icons.Default.Face else Icons.Default.Face,
                    text = if (user.gender == "MALE") stringResource(R.string.gender_male) else stringResource(R.string.gender_female)
                )
            }
        }
    }
}

@Composable
fun MetaDataChip(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}
