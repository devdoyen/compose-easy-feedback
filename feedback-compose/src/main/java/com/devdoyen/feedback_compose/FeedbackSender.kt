package com.devdoyen.feedback_compose

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast

/**
 * Utility object for sending feedback emails with device information.
 */
object FeedbackSender {

    /**
     * Sends feedback via email with automatically collected device information.
     *
     * @param context Android context for accessing system services
     * @param emailTo Recipient email address
     * @param subject Email subject line
     * @param feedback User's feedback text
     */
    fun sendFeedbackEmail(
        context: Context,
        emailTo: String,
        subject: String,
        feedback: String
    ) {
        val deviceInfo = getDeviceInfo(context)
        val emailContent = """Feedback:
$feedback

---
Device Information:
$deviceInfo"""

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailTo))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, emailContent)
        }

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Choose Email App"))
        } catch (e: Exception) {
            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Collects device and app information for debugging purposes.
     *
     * @param context Android context for accessing package information
     * @return Formatted string containing device and app details
     */
    private fun getDeviceInfo(context: Context): String {
        val appVersion = try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }

        return """
            App Version: $appVersion
            Device: ${Build.MANUFACTURER} ${Build.MODEL}
            Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})
            Build Type: ${Build.TYPE}
        """.trimIndent()
    }
}