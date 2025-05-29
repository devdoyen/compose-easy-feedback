package com.devdoyen.feedback_compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

/**
 * Internal composable function that displays the feedback dialog.
 * This function is not meant to be used directly by library consumers.
 *
 * @param config Configuration object containing dialog settings
 * @param onDismiss Callback executed when dialog should be dismissed
 * @param onSendFeedback Callback executed when user submits feedback
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedbackDialog(
    config: FeedbackConfig,
    onDismiss: () -> Unit,
    onSendFeedback: (String) -> Unit
) {
    var feedbackText by remember { mutableStateOf("") }
    val remainingChars = config.maxCharacters - feedbackText.length

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Dialog title
                Text(
                    text = config.dialogTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Feedback input field
                OutlinedTextField(
                    value = feedbackText,
                    onValueChange = { newText ->
                        if (newText.length <= config.maxCharacters) {
                            feedbackText = newText
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = {
                        Text(
                            text = config.dialogPlaceholder,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    maxLines = 5,
                    colors = OutlinedTextFieldDefaults.colors()
                )

                // Character counter
                Text(
                    text = "${feedbackText.length}/${config.maxCharacters}",
                    fontSize = 12.sp,
                    color = if (remainingChars < 50)
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Cancel button
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = config.dialogCancelButton,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Send button
                    Button(
                        onClick = {
                            if (feedbackText.isNotBlank()) {
                                onSendFeedback(feedbackText.trim())
                            }
                        },
                        enabled = feedbackText.isNotBlank()
                    ) {
                        Text(text = config.dialogSendButton)
                    }
                }
            }
        }
    }
}