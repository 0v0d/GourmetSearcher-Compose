[日本語のREADMEはこちら](README.md)

## App Name

**GourmetSearcher**

## App Overview

This is a Compose-based Android application that allows users to search for nearby restaurants by keywords.  

<img src="app.gif" width="320" alt="App demo video">

## Screens Overview

- **Keyword Input Screen**  
  A screen where users can input the keyword they want to search for and select the search range (radius). After setting the search criteria, users can navigate to the Location Search screen.  

- **Location Search Screen**  
  A screen for retrieving the user's current GPS location. Once the location is successfully acquired, users can proceed to the Restaurant List screen, where nearby restaurants are displayed.  

- **Restaurant Search Results Screen**  
  A screen that displays a list of restaurants matching the search criteria. Selecting a restaurant takes the user to the Restaurant Detail screen, where detailed information is displayed.  

- **Restaurant Detail Screen**  
  A screen showing detailed information about a selected restaurant. By tapping the "Map" button, users can view the location in a map application. Additionally, tapping the "Hot Pepper" button opens the restaurant's webpage.  

## Features

- **Restaurant Search**  
  Uses the Hot Pepper Gourmet Search API to find restaurants near the user's current location.  

- **Retrieve Restaurant Information**  
  Fetches detailed information about restaurants using the Hot Pepper Gourmet Search API.  

- **Map App Integration**  
  Enables users to view restaurant locations in their preferred map application.  

- **Keyword Search**  
  Allows users to refine search results by inputting specific keywords, making it easier to find restaurants that match their preferences.  

## Directory Structure

Below is an overview of the project’s primary directories and their purposes:

```bash
.github/            # Manages GitHub Actions workflows and Dependabot settings
app/                # Entry point of the application
core/               # Contains shared logic (e.g., API clients, caching, utilities)
docs/               # Documentation and related assets
feature-keyword/    # Code related to the keyword search functionality
feature-location/   # Code related to current location search functionality
feature-restaurant/ # Code for restaurant list and detail functionality
shared-ui/          # Reusable UI components
scripts/            # Scripts for tasks like static analysis
gradle/             # Gradle dependencies and version management files
```

## Module Details

### app/
The entry point of the app, including:  
- `MainActivity.kt`: Starts the application.  
- `NavigationGraph.kt`: Manages screen navigation.

### core/
Contains common functionality, including:  
- **NetworkModule**: Configures API communication.  
- **CacheManager**: Manages data caching.  
- **PreferencesManager**: Handles local settings.  

### feature-keyword/
Manages the UI, logic, and repository for keyword search.  
- `InputKeywordScreen.kt`: Keyword input screen.  
- **UseCases**: Manages user search history.  

### feature-location/
Handles location data retrieval and nearby restaurant searches.  
- `GetCurrentLocationUseCase.kt`: Retrieves the current location.  
- `PermissionEffects.kt`: Manages location permission requests.  

### feature-restaurant/
Manages the display of restaurant lists and details.  
- `RestaurantListViewModel.kt`: Logic for the restaurant list screen.  
- `RestaurantDetailContent.kt`: Components for the detail screen.  

### shared-ui/
Reusable UI components for the app.  
- **CustomOutlinedButton**: Custom buttons.  
- **Dialog**: Standard dialog components.  
- **ImageCard**: Displays restaurant images in card format.  

### API Key Setup

This app uses the [Hot Pepper Gourmet Search API](https://webservice.recruit.co.jp/doc/hotpepper/reference.html).  
To use this service, you need to [register your email address](https://webservice.recruit.co.jp/register/).  
After registration, an API key will be sent to your email. Set up your API key as follows:

1. Create or edit the `local.properties` file in the project's root directory.  
2. Add the following line:  
   ```properties
   API_KEY="YOUR_API_KEY"
   ```

### Known Issues and Notes

- When using an emulator, GPS data may not be retrieved correctly.  
  (The app works without issues on physical devices.)

## Target Devices and OS

### Target OS
- Android 14

## Development Environment

- **Android Studio**: Koala Feature Drop 2024.1.2 Canary 7  
- **Compile SDK Version**: 35  
- **Minimum SDK Version**: 32  
- **Target SDK Version**: 35  
- **JVM Version**: 17  
- **Gradle Version**: 8.10.2  
- **Gradle Plugin Version**: 8.7.3  

### Programming Language
- Kotlin 2.0.21  

### Tested Device
- Redmi 12 (Android 14)  

## Licenses

This project uses third-party open source libraries.
Each library is distributed under its own license.
