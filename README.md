# Learning-Android

## Demos

### GlobalActionBarService
Application to learn accessibility services.
Creates an overlay that provides UI for hardware buttons.
Was modified to include a close button.
Was also modified to include a clickbot where the user sets the tap count and location.
#### Sources
https://codelabs.developers.google.com/codelabs/developing-android-a11y-service#0

### ContentDemo
Application to learn writing a content provider.
#### Sources 
https://github.com/sitepoint-editors/AndroidContentProvider

### ContentDemoAccess
Application to learn using a third party app's Content Provider.
#### Sources 
https://stackoverflow.com/questions/5714224/accessing-custom-content-provider-from-different-app

### GlobalTouchListenerService
An app that listens to clicks on a device and saves them in an SQLite database using a Content Provider.
#### Sources
https://stackoverflow.com/questions/7149923/android-how-to-wait-for-code-to-finish-before-continuing

### AlarmDirectBoot
An demo app that can work before a user unlocks the device on versions that support File Based Encryption (FBE)
#### Sources
https://stackoverflow.com/questions/24822506/how-to-start-a-service-at-a-specific-time
https://www.javatpoint.com/android-alarmmanager
https://github.com/michaelJustin/android-notification/blob/master/app/src/main/java/com/example/notificationtest/MainActivity.java
https://stackoverflow.com/questions/41197416/alarmmanager-not-working-after-phone-reboot
https://www.androidauthority.com/how-to-store-data-locally-in-android-app-717190/
https://stackoverflow.com/questions/46445265/android-8-0-java-lang-illegalstateexception-not-allowed-to-start-service-inten

### ScopedStorageDemo
A demo app that shows scoped storage in action on Android Q and later

#### Sources
https://github.com/LHM777/Scoped-Storage-Android-11-java-example-Save-bitmap-in-Android-using-MediaStore/blob/main/app/src/main/java/com/example/saveimage2021/MainActivity.java

### NoDebuggingApp
An application that detects if debugging is enabled, if so it quits. This helps protect the data of this app including source code because if an application runs while debuggable an attacker can extract data from the app. 

#### Sources
Makan, Android Security Cookbook pg 196

### AccessExternalStorageDemo
An application that gets all pdfs stored on external storage

#### Sources
https://stackoverflow.com/questions/8971328/getting-all-pdf-files-from-external-storage-on-android

### WebView Example
Shows basic webview usage: starting new webview; injecting JavaScript; overriding URL loading. This WebView example only loads URLs containing wikipedia. 

#### Sources
https://www.geeksforgeeks.org/deep-linking-in-android-with-example/

### Redirect WebView
Shows an application redirecting WebView to a specific URL. Contains 3 URLs,
- "https://en.wikipedia.org/wiki/Android_(operating_system)" Loads in WebView successfully, as should
- "https://www.google.com" Loads in web browser since it does not contain wikipedia, as should
- "https://stackoverflow.blog/2011/01/05/the-wikipedia-of-long-tail-programming-questions/" Loads in WebView, but should not. This happens because the check the WebView Example app does is incorrect. (It checks for URLs containing wikipedia)


### Reflection
#### Sources
### Dynamic Code Loading
#### Sources
### Alert Example
#### Sources
https://www.geeksforgeeks.org/android-alert-dialog-box-and-how-to-create-it/#:~:text=Android%20Alert%20Dialog%20is%20built,icon%20on%20Alert%20dialog%20box.
https://developer.android.com/training/notify-user/build-notification

### Alert Message
#### Sources
https://developer.android.com/training/notify-user/build-notification
https://stackoverflow.com/questions/19240194/how-to-create-app-shortcut-on-home-screen-on-android-phones-on-installation

### Transparent Activity
#### Sources
https://blog.mindorks.com/how-to-create-a-transparent-activity-in-android

## Basic attacks

### Starting external activity
An application which starts the hidde nactivity from InsecureLoginApp when the phone is conntected to a charger

#### Target: insecure-apps/InsecureLoginApp
An application showcaseing the dangers of exported activities

#### Attack: basic-attacks/StartExternalActivity
An application which starts the hidde nactivity from InsecureLoginApp when the phone is conntected to a charger

#### Sources
https://stackoverflow.com/questions/10211609/problems-with-action-power-connected/31091887
https://stackoverflow.com/questions/5940456/code-to-launch-external-app-explicitly

### Starting an unexported service from an other app by abusing broadcast receiver
In this attack, even though the service is not exported, it can be started by a broadcast receiver. This receiver is not protected, making it possible for a malicious app to start the service by sending the required attack.

#### Target: insecure-apps/InsecureServiceApp
The target that has the protected service and unprotected receiver.

#### Attack: basic-attacks/StartUnexportedService
The attack that sends the intent to trigger the reciever. 

### Acquiring a dangerous-level custom permssion 
This attack showcases getting a malicious application acquiring a target's dangerous-level custom permission without prompting the user—the only requirement being that the malevolent app is installed before the victim. This attack only works on android versions before Kitkat.

Important to note that the apps should be signed with a different signature to test this attack properly. This is because the scenario assumed is that the attacker cannot sign the app with the same signature. If he could, the attack would work on newer versions of Android as well as it would not be a problem for the Android system.

#### Target: insecure-apps/ContentDemo
An application that protects its content provider with a dangerous-level custom permission.

#### Attack: basic-attacks/DontentDemoAccess
An application that declares a normal-level custom permission with the same name as the desired target. 

#### Sources
https://commonsware.com/blog/2014/02/12/vulnerabilities-custom-permissions.html


