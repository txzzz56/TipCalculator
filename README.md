# Tip Calculator

A simple Android app that calculates the tip and the total amount to pay for a bill. It is built with **Kotlin** and **Jetpack Compose** (Material 3), using a single screen.

## Features

- **Bill input**: enter the bill amount in a text field that opens the decimal number keyboard.
- **Tip percentage from the options menu**: open the ⋮ menu in the top app bar and choose:
  - `tips10`: 10% tip (default)
  - `tips15`: 15% tip
- **Live results**: the tip amount and the total update instantly as you type or change the tip option. Both are shown with two decimal places.
- **Background colour action button**: the ★ button in the top app bar cycles the screen background through white, light orange, light blue and light green.

## How it works

The app uses Compose state (`remember { mutableStateOf(...) }`) for the bill text, the selected tip percentage, the menu's open/closed state and the background colour. Whenever one of these changes, the UI recomposes automatically.

The calculation:

```kotlin
val bill  = billText.toDoubleOrNull() ?: 0.0   // invalid or empty input counts as 0
val tip   = bill * tipPercent / 100
val total = bill + tip
```

## Screen layout

| Element | Location | Purpose |
|---|---|---|
| Title "Tip Calculator" | Top app bar | App title |
| ★ button | Top app bar | Changes background colour |
| ⋮ menu | Top app bar | Choose `tips10` or `tips15` |
| "Bill amount" field | Main content | Enter the bill |
| Selected option / Tip amount / Total to pay | Below the field | Show the results |

## Project structure

```
TipCalCulator2/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/example/tipcalculator/
│       │   │   ├── MainActivity.kt        # All app logic and UI (TipApp composable)
│       │   │   └── ui/theme/              # Color.kt, Theme.kt, Type.kt
│       │   └── res/                       # Strings, launcher icons, themes
│       ├── test/                          # Local unit tests
│       └── androidTest/                   # Instrumented tests
├── gradle/libs.versions.toml              # Dependency versions
├── build.gradle.kts
└── settings.gradle.kts
```

## Tech stack

| | |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Build | Gradle (Kotlin DSL), Android Gradle Plugin 9.4.1 |
| Min SDK | 24 (Android 7.0) |
| Target / compile SDK | 37 |
| Package | `com.example.tipcalculator` |

## Getting started

### Requirements

- A recent version of Android Studio that supports AGP 9.4
- An Android emulator or a physical device running Android 7.0 or newer

### Run the app

1. Open the `TipCalCulator2` folder in Android Studio.
2. Let Gradle sync finish.
3. Choose an emulator or connected device and press **Run ▶**.

## Author

Mumtaz
