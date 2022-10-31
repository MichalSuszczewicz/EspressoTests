
# Tests information

## changes to the project
- Migrated to AndroidX
- Added IdlingResource implementation
- Updated gradle dependencies
- Created set of Espresso tests using Page Object Pattern
- Created custom annotation *TestCaseInfo* for better readability
- Created custom Matcher to match cities at specific position
- Created custom view actions to wait for elements

## Test Cases / Bug report
*Test cases* are located in **Reports** directory in root of the project  
*Bug report* is located in **Reports** directory in root of the project
*Test report* is located in **Reports** directory in root of the project

## How to run tests

### Requirements:
- [Java client 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) added to environment path
- Android Studio with configured [Android SDK](https://developer.android.com/about/versions/12/setup-sdk)

- Device need to have disabled [power button camera shortcut](https://www.techbout.com/disable-power-button-camera-shortcut-android-47620/), otherwise one test will open camera instead lock and unlock the screen

### Run from console

To run all tests from console you can use this command: `./gradlew app:connectedAndroidTest`

### Run from IntelliJ IDEA
Add new configuration or simply run from the *test class*.


You can add configuration for all test:
- Go to Edit configuration
- Add *AndroidInstrumented Tests*
- As Module select *BB-Mobile-QA-assignment.app.androidTest*
- As Test select *All in Module*
- Apply and run it


## Test Implementation libraries
Test runs with gradle libraries:

```  
 androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0-alpha07'
 androidTestImplementation 'androidx.test:runner:1.4.0' androidTestImplementation'androidx.test:rules:1.4.1-alpha07
 androidTestImplementation 'androidx.test.ext:junit:1.1.4-alpha07'
 ```  


Test runner is:  `testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"`

### Environment
Test has been written and run on Pixel with Android 7 Nougat API level 24, Android 11 and Android 12



### Test report
After each execution Junit test report is generated and located in **app/build/reports/androidTests/connected/index.html**