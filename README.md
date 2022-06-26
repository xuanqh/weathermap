# WeatherApp-Android

## Mobile App Architecture

## Introduction

Mobile application follows the design M-V-VM design pattern.

The project's architecture and technologies can be represented on the illustration:


## View

The view is responsible for defining the structure, layout, and appearance of what the user sees on the screen.

## View-Model

The view model acts as an intermediary between the view and the model, and is responsible for handling the view logic. Typically, the view model interacts with the model by invoking methods in the model classes. The view model then provides data from the model in a form that the view can easily use. The view model retrieves data from the model and then makes the data available to the view.

## Model

This is a part of the model in MVVM. This is responsible to get the data request and communicate to remote and local data sources.

Files/Folders hierarchy structure

## Introduction

Implementation of M-V-VM design pattern. We would keep grouping project files based on features. As it makes the code more understandable and readable.

## Main package

com.weathermap

## Base package

com.weathermap.base
It contains base classes like screen, components, etc.

## Data package

com.weathermap.data
It contains all the models and services of app.

## Domain package
com.weathermap.domain
The Domain layer contains all the domain rules of app.

## Feature package
com.weathermap.features.{feature_name}.*
This contains features of the application. We should follow for each screen:
- views
- viewmodels
- activities
- fragments
- adapters
...

For example: We have word main features. Then we should have the file and folder hierarchy:
com.weathermap.features.main
 - MainActivity.kt
 - MainViewModel.kt
 - WeatherDayAdapter.kt

## Modules in Project
 - app: Main application module.
 - nativelib: module to store the secret key or api key

## Achievement
- The application is a simple Android application which is written by Kotlin.
- The application is able to retrieve the weather information from OpenWeatherMapsAPI.
- The application is able to allow user to input the searching term.
- The application is able to proceed searching with a condition of the search term length must be from 3 characters or above.
- The application is able to render the searched results as a list of weather items.
- The application is able to support caching mechanism so as to prevent the app from generating a bunch of API requests.
- The application is able to handle failures.
- The application is able to support the disability to scale large text for who can't see the text clearly.
- The application is able to support the disability to read out the text using VoiceOver controls.

## Build and Installation
Step1: Download and decompress the project source code.
Step2: Download and install Android Studio.
Step3: Run Android Studio.
Step4: On Android Studio then Click Open Android project then navigation to project folder to import the project.
Step5: Run App

Or to build and run the desktop application, run the following command
./gradlew run

