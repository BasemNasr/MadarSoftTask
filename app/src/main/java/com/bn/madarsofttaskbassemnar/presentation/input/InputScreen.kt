package com.bn.madarsofttaskbassemnar.presentation.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import com.bn.madarsofttaskbassemnar.R
import com.bn.madarsofttaskbassemnar.presentation.components.AtelierButton
import com.bn.madarsofttaskbassemnar.presentation.components.AtelierTextField
import com.bn.madarsofttaskbassemnar.ui.theme.PrimaryFixed
import com.bn.madarsofttaskbassemnar.ui.theme.SurfaceContainerLow
import com.bn.madarsofttaskbassemnar.ui.theme.SurfaceContainerLowest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    onNavigateToDisplay: () -> Unit,
    viewModel: InputViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onNavigateToDisplay()
            viewModel.resetSavedState()
        }
    }

    val context = androidx.compose.ui.platform.LocalContext.current
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it.asString(context))
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Architecture,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            stringResource(R.string.screen_title_archive),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Context Header
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.input_header_workspace),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.input_title_add_contributor),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.input_description),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Form Canvas
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(SurfaceContainerLowest)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                AtelierTextField(
                    value = uiState.name,
                    onValueChange = viewModel::onNameChange,
                    label = stringResource(R.string.label_full_name)
                )

                AtelierTextField(
                    value = uiState.jobTitle,
                    onValueChange = viewModel::onJobTitleChange,
                    label = stringResource(R.string.label_job_title)
                )

                AtelierTextField(
                    value = uiState.age,
                    onValueChange = viewModel::onAgeChange,
                    label = stringResource(R.string.label_age),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                // Gender Identity Selection
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(R.string.label_gender_identity),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        GenderCard(
                            label = stringResource(R.string.gender_male),
                            icon = Icons.Default.Male,
                            isSelected = uiState.gender == "MALE",
                            onClick = { viewModel.onGenderChange("MALE") },
                            modifier = Modifier.weight(1f)
                        )
                        GenderCard(
                            label = stringResource(R.string.gender_female),
                            icon = Icons.Default.Female,
                            isSelected = uiState.gender == "FEMALE",
                            onClick = { viewModel.onGenderChange("FEMALE") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                AtelierButton(
                    text = stringResource(R.string.button_save_entry),
                    onClick = viewModel::saveUser,
                    icon = Icons.Default.Save
                )
            }
        }
    }
}

@Composable
fun GenderCard(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor by animateColorAsState(
        if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
        label = "borderColor"
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLow)
            .clickable { onClick() }
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}
