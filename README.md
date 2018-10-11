# Votlin App

This project is based on the official KotlinConf App -> https://github.com/JetBrains/kotlinconf-app

### Server

The server is based on ktor

### Web Page

The website is based on React

### Android Application

The Android version is developed in Kotlin/JVM

### iOS Application

With kotlin/native

## How to build and run

### Building the code

 * Make sure you have the Android SDK installed
 * Open the project in IntelliJ IDEA (2017.3 EAP recommended)
 * Create a file `local.properties` in the root directory of the project, pointing to your Android SDK installation. On Mac OS,
the contents should be `sdk.dir=/Users/<your username>/Library/Android/sdk`. On other OSes, please adjust accordingly.
 * Run `./gradlew build`

### Running the backend
 
 * Run `./gradlew backend:run` from the command line or from Gradle toolwindow
 * The backend will start serving on localhost:8080, with data stored in a local H2 database


### Running the Android app

 * Create a run configuration of type "Android App"
 * Select module "app" in the run configuration settings
 * Run the configuration
 * Select the emulator or connected device, as normal

### Running the Web client

 * Make sure the backend is running on localhost:8080
 * Run `gradlew votlin-js:run` in the 'web' directory to run webpack development server
