package com.devdoyen.feedback_compose

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * A floating action button that displays a feedback dialog when clicked.
 *
 * This composable automatically handles dialog state management and integrates
 * with email functionality for sending feedback.
 *
 * @param config Configuration object containing all customization options
 * @param modifier Modifier to be applied to the floating action button
 * @param isBetaBuild Function that returns true if this is a beta build.
 *                   Used with enableBetaOnly config to conditionally show the button
 */
@Composable
fun FeedbackButton(
    config: FeedbackConfig,
    modifier: Modifier = Modifier,
    isBetaBuild: () -> Boolean = { false }
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Only show in beta builds if enableBetaOnly is true
    if (config.enableBetaOnly && !isBetaBuild()) {
        return
    }

    FloatingActionButton(
        onClick = { showDialog = true },
        modifier = modifier,
        containerColor = if (config.buttonColor != Color.Unspecified)
            config.buttonColor
        else
            FloatingActionButtonDefaults.containerColor
    ){
        Icon(
            imageVector = config.buttonIcon,
            contentDescription = config.buttonContentDescription
        )
    }

    // Show dialog when state is true
    if (showDialog) {
        FeedbackDialog(
            config = config,
            onDismiss = { showDialog = false },
            onSendFeedback = { feedback ->
                // Execute pre-send validation if provided
                val shouldProceed = config.onBeforeSend?.invoke(feedback) ?: true

                if (shouldProceed) {
                    // Send the feedback email
                    FeedbackSender.sendFeedbackEmail(
                        context = context,
                        emailTo = config.emailTo,
                        subject = config.emailSubject,
                        feedback = feedback
                    )

                    // Execute post-send callback if provided
                    config.onAfterSend?.invoke(feedback)

                    // Close the dialog
                    showDialog = false
                }
            }
        )
    }
}