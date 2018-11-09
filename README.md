# Votlin App

<img src="logo.png" alt="Votlin Logo" />

## Description

Votlin is a small petshop with a master/detail structure. It lists the talks from Extremadura Digital Day event.

## Server

The server is based on ktor

## Web Page

The website is based on React

Visit the site [here](http://sergiocasero.es/edd)

## Android Application

The Android version is developed in Kotlin/JVM

You can download the app here: [Votlin in Google play](https://play.google.com/store/apps/details?id=com.votlin.android).


## iOS Application

With kotlin/native and Swift

# How to build and run

## Building the code

 * Make sure you have the Android SDK installed
 * Open the project in IntelliJ IDEA (2017.3 EAP recommended)
 * Create a file `local.properties` in the root directory of the project, pointing to your Android SDK installation. On Mac OS,
the contents should be `sdk.dir=/Users/<your username>/Library/Android/sdk`. On other OSes, please adjust accordingly.
 * Run `./gradlew build`

### Running the backend
 
 * Run `./gradlew backend:run` from the command line or from Gradle toolwindow
 * The backend will start serving on localhost:8080, with data stored in MySQL database


## Running the Android app

 * Create a run configuration of type "Android App"
 * Select module "app" in the run configuration settings
 * Run the configuration
 * Select the emulator or connected device, as normal

## Running the Web client

 * Make sure the backend is running on localhost:8080
 * Run `gradlew :frontend:run` in the 'web' directory to run webpack development server
 
 ## Running iOS
 
 * This is quite simple, open the xcodeproject file with xCode and click on "play" button, that's all ;)
 
 ### Thanks to:
 
 - Thanks to @Kotlin team, take a look to KotlinConf app -> https://github.com/JetBrains/kotlinconf-app
 - Thanks to @MarcinMaskala, He has 2 multiplatform repos that are really helpful -> https://github.com/MarcinMoskala
 - Thanks to @wiyarmir, he has also a multiplatform project that could help you -> https://github.com/wiyarmir
 - Thanks to @victorfriasv for the Votlin logo! Looks really nice! :) -> https://www.linkedin.com/in/victorfriasv
 
 ### TODO
 - iOS -> Fix UI thread issue.
 - TornadoFX -> Improve the UI
 - Tests
 - Error handling, maybe with Arrow datatypes?
 - Wiki -> Add how import and iOS framework and configure the build phase to use Kotlin from iOS
 
 ### Screenshots
 
 #### Android
<img src="https://github.com/sergiocasero/votlin-app/blob/master/art/Votlin_Android_Screenshot.png" alt="Android app" width="328" height="575" />

#### iOS
<img src="https://github.com/sergiocasero/votlin-app/blob/master/art/Votlin_iOS_Screenshot.png" alt="iOS app" width="336" height="589" />

#### Web
<img src="https://github.com/sergiocasero/votlin-app/blob/master/art/Votlin_Web_Screenshot.png" alt="Web app" width="527" height="656" />
 
 #### Demo gif
<img src="https://github.com/sergiocasero/votlin-app/blob/master/art/Votlin_demo.gif" alt="Demo GIF" width="328" height="575" />
 
 ### License
 
 ```
Copyright 2018 Sergio Casero & Daniel Llanos

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.```
