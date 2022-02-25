# Learning-Android

## GlobalActionBarService
Application to learn accessibility services.
Creates an overlay that provides UI for hardware buttons.
Was modified to include a close button.
Was also modified to include a clickbot where the user sets the tap count and location.
### Sources
https://codelabs.developers.google.com/codelabs/developing-android-a11y-service#0

## ContentDemo
Application to learn writing a content provider.
### Sources 
https://github.com/sitepoint-editors/AndroidContentProvider

## ContentDemoAccess
Application to learn using a third party app's Content Provider.
### Sources 
https://stackoverflow.com/questions/5714224/accessing-custom-content-provider-from-different-app

## GlobalTouchListenerService
An app that listens to clicks on a device and saves them in an SQLite database using a Content Provider.
### Sources
https://stackoverflow.com/questions/7149923/android-how-to-wait-for-code-to-finish-before-continuing

## AlarmDirectBoot
An demo app that can work before a user unlocks the device on versions that support File Based Encryption (FBE)
### Sources
https://stackoverflow.com/questions/24822506/how-to-start-a-service-at-a-specific-time
https://www.javatpoint.com/android-alarmmanager
https://github.com/michaelJustin/android-notification/blob/master/app/src/main/java/com/example/notificationtest/MainActivity.java
https://stackoverflow.com/questions/41197416/alarmmanager-not-working-after-phone-reboot
https://www.androidauthority.com/how-to-store-data-locally-in-android-app-717190/
https://stackoverflow.com/questions/46445265/android-8-0-java-lang-illegalstateexception-not-allowed-to-start-service-inten

## ScopedStorageDemo
A demo app that shows scoped storage in action on Android Q and later

### Sources
https://github.com/LHM777/Scoped-Storage-Android-11-java-example-Save-bitmap-in-Android-using-MediaStore/blob/main/app/src/main/java/com/example/saveimage2021/MainActivity.java

## NoDebuggingApp
An application that detects if debugging is enabled, if so it quits. This helps protect the data of this app including source code because if an application runs while debuggable an attacker can extract data from the app. 

### Sources
Makan, Android Security Cookbook pg 196

## AccessExternalStorageDemo
An application that gets all pdfs stored on external storage

### Sources
https://stackoverflow.com/questions/8971328/getting-all-pdf-files-from-external-storage-on-android

## InsecureLoginApp
An application showcaseing the dangers of exported activities


