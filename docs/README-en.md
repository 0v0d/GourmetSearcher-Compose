Here is the English version of the provided content:
[日本語はこちら](README.md)

## App Name

GourmetSearcher

## App Specifications

This is a Compose version of an Android app that allows you to search for restaurants near your current location using keywords.

<img src="app.gif" width="314" alt="App demo video">

### About the API Key

This app uses the [Hot Pepper Gourmet Search API](https://webservice.recruit.co.jp/doc/hotpepper/reference.html).
To use it, you need to register your email address [here](https://webservice.recruit.co.jp/register/).
After registration, an API Key will be sent to your email, which you can set in the following format in the `local.properties` file located in the root directory of the project:

```properties
API_KEY="YOUR_API_KEY"
```

### Issues and Usage Notes

- When using a virtual device, there is an issue where GPS cannot be obtained.</br>
  (On actual devices, it works without any issues.)

### Supported Devices and OS

#### Supported OS

Android 14

## Development Environment

Android Studio Koala Feature Drop 2024.1.2 Canary 7

- Compile SDK Version: 34
- Minimum SDK Version: 32
- Target SDK Version: 34
- Java: VERSION_17
- Gradle: 8.8
- Gradle Plugin: 8.5.0
- minSdk: 32
- targetSdk: 34
- kotlinCompilerExtensionVersion: 1.5.3

### Development Language

- Kotlin 2.0.0

### Real Device Environment

- Redmi 12 (Android 14)

## Application Features

### Feature List

- Restaurant Search: Search for nearby restaurants using the Hot Pepper Gourmet Search API.
- Retrieve Restaurant Information: Get detailed information about restaurants using the Hot Pepper Gourmet Search API.
- Map App Integration: Display the restaurant's location on a map app.
- Keyword Search: Filter the search results from the Hot Pepper Gourmet Search API by entering keywords.

### Screen Overview

- Keyword Input Screen (InputSearchTermsFragment): Enter keywords, and if a radius is selected, navigate to the location search screen.
- Location Search Screen (LocationSearchFragment): If GPS is successfully obtained, navigate to the restaurant list screen.
- Restaurant Search Results Screen (RestaurantListViewFragment): Display a list of restaurants from the search results and navigate to the restaurant detail screen when selected.
- Restaurant Detail Screen (RestaurantDetailFragment): Display detailed information about the restaurant. When the Map button is pressed, navigate to the map app. When the "Hot Pepper" button is pressed, display the web page.

## APIs, SDKs, and Libraries Used

### Android
- Accompanist Permissions
- AndroidX Core KTX
- AndroidX DataStore Preferences
- AndroidX Lifecycle Runtime KTX
- AndroidX Activity Compose
- AndroidX Compose BOM
- AndroidX Compose UI
- AndroidX Compose UI Graphics
- AndroidX Compose UI Test Manifest
- AndroidX Compose UI Tooling
- AndroidX Compose UI Tooling Preview
- AndroidX Material3
- AndroidX Navigation UI KTX
- AndroidX Navigation Runtime KTX
- AndroidX Navigation Compose
- AndroidX Hilt Navigation Compose
- Play Services Location

### Image Library
- Coil Compose

### Static Analysis
- Detekt Rules Twitter
- Detekt Formatting
- Detekt Rules

### Collections
- Kotlinx Collections Immutable

### Serialization
- Kotlinx Serialization JSON

### Dependency Injection
- Dagger Hilt Android Compiler
- Dagger Hilt Android

### Networking
- Retrofit
- Retrofit Converter Moshi
- Moshi Kotlin

### Debug Tools
- LeakCanary

### Unit Testing
- JUnit
- Dagger Hilt Android Testing
- Mockito Core
- AndroidX Runner
- Kotlinx Coroutines Test
- AndroidX Core Testing

### Android Testing
- AndroidX JUnit
- AndroidX Espresso Core
- AndroidX UI Automator
- AndroidX UI Test JUnit4 Android

### Others
- AndroidX Compose Material Icons Extended
- AndroidX Lifecycle Runtime Compose Android

### Plugins
- Android Application
- JetBrains Kotlin Android
- Kotlin Kapt
- Dagger Hilt Android
- Kotlin Parcelize
- Secrets Gradle Plugin
- Detekt
- Serialization
- Compose Compiler