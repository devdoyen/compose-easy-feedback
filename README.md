# ComposeEasyFeedback 🚀

A simple and customizable feedback system for Jetpack Compose applications. Perfect for collecting user feedback during beta testing and app development.

## ✨ Features

- **🎯 One-line integration** - Add feedback functionality with a single composable
- **🎨 Material 3 design** - Modern UI that fits seamlessly into your app
- **📧 Email integration** - Automatically sends feedback via email with device info
- **🔧 Beta-only display** - Show feedback button only in debug/beta builds
- **⚙️ Fully customizable** - Colors, text, validation, and callbacks
- **📱 Device info collection** - Automatically includes app version, device, and Android info
- **✅ Validation support** - Built-in character limits and custom validation
- **🎭 Multiple themes** - Easy color customization for different use cases

## 📦 Installation

Add JitPack repository to your project's `settings.gradle.kts` or root `build.gradle`:

```kotlin
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.devdoyen:compose-easy-feedback:v1.0.0")
}
```

## 🚀 Quick Start

### Basic Usage

```kotlin
import com.devdoyen.feedback.ComposeEasyFeedback

@Composable
fun MyApp() {
    Scaffold(
        floatingActionButton = {
            ComposeEasyFeedback.FeedbackFAB(
                emailTo = "feedback@yourapp.com"
            )
        }
    ) {
        // Your app content
    }
}
```

### Beta-Only Display

```kotlin
ComposeEasyFeedback.FeedbackFAB(
    emailTo = "feedback@yourapp.com",
    isBetaBuild = { BuildConfig.DEBUG } // Only show in debug builds
)
```

## 🎨 Advanced Customization

### Custom Colors and Text

```kotlin
ComposeEasyFeedback.FeedbackFAB(
    emailTo = "support@myapp.com",
    config = FeedbackConfig(
        emailTo = "support@myapp.com",
        emailSubject = "My App Feedback",
        dialogTitle = "Share Your Thoughts",
        dialogPlaceholder = "Tell us what you think...",
        buttonColor = Color(0xFF4CAF50), // Green theme
        maxCharacters = 300,
        enableBetaOnly = false // Always show
    )
)
```

### With Validation and Callbacks

```kotlin
ComposeEasyFeedback.FeedbackFAB(
    emailTo = "feedback@myapp.com",
    config = FeedbackConfig(
        emailTo = "feedback@myapp.com",
        onBeforeSend = { feedback ->
            // Require at least 10 characters
            if (feedback.length < 10) {
                false // Prevent sending
            } else {
                true // Allow sending
            }
        },
        onAfterSend = { feedback ->
            // Log analytics or show success message
            println("Feedback sent: ${feedback.length} characters")
        }
    )
)
```

## ⚙️ Configuration Options

| Parameter | Description | Default |
|-----------|-------------|---------|
| `emailTo` | Recipient email address | **Required** |
| `emailSubject` | Email subject line | "App Feedback" |
| `enableBetaOnly` | Show only in beta builds | `true` |
| `buttonIcon` | FAB icon | `Icons.Default.Send` |
| `buttonColor` | FAB background color | Material default |
| `dialogTitle` | Dialog header text | "Send Feedback" |
| `dialogPlaceholder` | Input field placeholder | "Share your thoughts..." |
| `maxCharacters` | Character limit | 500 |
| `onBeforeSend` | Validation callback | `null` |
| `onAfterSend` | Success callback | `null` |

## 📧 Email Content

The library automatically includes helpful information in the feedback email:

```
Feedback:
[User's feedback text]

---
Device Information:
App Version: 1.0.0
Device: Samsung Galaxy S21
Android: 13 (API 33)
Build Type: debug
```

## 🎯 Use Cases

- **Beta testing** - Collect feedback from beta testers
- **User research** - Gather user opinions and suggestions
- **Bug reporting** - Allow users to report issues easily
- **Feature requests** - Let users suggest improvements
- **Internal apps** - Get feedback from team members

## 📱 Sample App

Check out the included sample app for examples of:
- Basic integration
- Custom color themes
- Validation and callbacks
- Multiple feedback buttons

## 🛠️ Requirements

- **Minimum SDK**: 24 (Android 7.0)
- **Compile SDK**: 35
- **Jetpack Compose**: 1.6.8+
- **Kotlin**: 2.0.0+

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Inspired by the need for simple feedback collection in Android apps
- Thanks to all beta testers and contributors

## 📞 Support

If you have any questions or issues, please:
- Check the [sample app](app/) for examples
- Open an [issue](https://github.com/devdoyen/compose-easy-feedback/issues)
- Email: [ysndy1234@gmail.com]

---

**Made with ❤️ by [DevDoyen](https://github.com/devdoyen)**

⭐ **If this library helped you, please give it a star!** ⭐
