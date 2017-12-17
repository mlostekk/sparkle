# SPARKLE

## Backend

As backend service CouchDb was chosen. To install CouchDb please follow the instructions on [their website](http://couchdb.apache.org/).

#### Backend initialization

- To initialize the database and populate it with the data given, please execute the `init.sh` script in `/server`
- If you are not working on the same machine, plesae adjust the `SERVER_URL` variable in the script
- If you are getting an `Error: undefined` while executing the script (step `creating questions 'by category' view`) please make your you have the `nspr` package installed (`brew install nspr`)

## Android App

#### General Setup

- In order to be able to connect to the CouchDb please adjust the `baseUrl` in the file `/android/app/src/main/java/com/nomad5/sparkle/model/storage/retroCouch/CouchDbStore.kt` in line number `41`

#### Building

No special requirements are needed to build the app. The gradle scripts should take care of all dependencies. So feel free to use it in [AndroidStudio](https://developer.android.com/studio/index.html) or use

- `./gradlew build` to build the sources (output is in `android/app/build/outputs/apk`. To install it via command line use `adb install`)
- `./gradlew test` to run the unit tests

## General notes

- The task does not yet split the questions across categories. All questions for all categories are loaded on startup.
- Due to lack of time, just very simple unit tests were implemented for the Presenter and the View.
- Due to lack of time no instrumentation tests were implemented.
