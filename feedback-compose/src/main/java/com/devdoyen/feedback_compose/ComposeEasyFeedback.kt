package com.devdoyen.feedback_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Main API object for the ComposeEasyFeedback library.
 *
 * This object provides simple, one-line integration for adding feedback
 * functionality to Jetpack Compose applications.
 *
 * Example usage:
 * ```
 * ComposeEasyFeedback.FeedbackFAB(
 *     emailTo = "feedback@yourapp.com"
 * )
 * ```
 */
object ComposeEasyFeedback {

    /**
     * Creates a feedback floating action button with email integration.
     *
     * This is the primary entry point for the library. It creates a floating
     * action button that, when clicked, opens a feedback dialog. The feedback
     * is sent via email with automatically collected device information.
     *
     * @param emailTo The recipient email address for feedback (required)
     * @param modifier Modifier to be applied to the floating action button
     * @param config Optional configuration object for customizing appearance and behavior.
     *               If not provided, uses default configuration with the specified emailTo
     * @param isBetaBuild Function that returns true if this is a beta build.
     *                    Defaults to always returning false (always show button).
     *                    Use { BuildConfig.DEBUG } to show only in debug builds
     *
     * @sample
     * ```
     * // Basic usage
     * ComposeEasyFeedback.FeedbackFAB(
     *     emailTo = "support@myapp.com"
     * )
     *
     * // Advanced usage with custom configuration
     * ComposeEasyFeedback.FeedbackFAB(
     *     emailTo = "feedback@myapp.com",
     *     config = FeedbackConfig(
     *         emailTo = "feedback@myapp.com",
     *         emailSubject = "My App Feedback",
     *         dialogTitle = "Share Your Thoughts",
     *         buttonColor = Color.Green,
     *         enableBetaOnly = false
     *     ),
     *     isBetaBuild = { BuildConfig.DEBUG }
     * )
     * ```
     */
    @Composable
    fun FeedbackFAB(
        emailTo: String,
        modifier: Modifier = Modifier,
        config: FeedbackConfig = FeedbackConfig(emailTo = emailTo),
        isBetaBuild: () -> Boolean = { false }
    ) {
        FeedbackButton(
            config = config.copy(emailTo = emailTo),
            modifier = modifier,
            isBetaBuild = isBetaBuild
        )
    }
}