package com.devdoyen.feedback_compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email

/**
 * Configuration class for feedback dialog customization.
 *
 * @param emailTo The recipient email address for feedback
 * @param emailSubject The subject line for the feedback email
 * @param enableBetaOnly Whether to show the feedback button only in beta builds
 * @param buttonIcon The icon to display on the feedback FAB
 * @param buttonContentDescription Content description for accessibility
 * @param dialogTitle The title text displayed in the feedback dialog
 * @param dialogPlaceholder The placeholder text for the feedback input field
 * @param dialogSendButton The text for the send button
 * @param dialogCancelButton The text for the cancel button
 * @param buttonColor The color of the feedback FAB (Color.Unspecified for default)
 * @param onBeforeSend Callback executed before sending feedback. Return false to prevent sending
 * @param onAfterSend Callback executed after feedback is sent successfully
 * @param maxCharacters Maximum number of characters allowed in feedback text
 */
data class FeedbackConfig(
    val emailTo: String,
    val emailSubject: String = "App Feedback",
    val enableBetaOnly: Boolean = true,
    val buttonIcon: ImageVector = Icons.Default.Email,
    val buttonContentDescription: String = "Send Feedback",
    val dialogTitle: String = "Send Feedback",
    val dialogPlaceholder: String = "Share your thoughts or suggestions about the app...",
    val dialogSendButton: String = "Send",
    val dialogCancelButton: String = "Cancel",
    val buttonColor: Color = Color.Unspecified,
    val onBeforeSend: ((String) -> Boolean)? = null,
    val onAfterSend: ((String) -> Unit)? = null,
    val maxCharacters: Int = 500
)