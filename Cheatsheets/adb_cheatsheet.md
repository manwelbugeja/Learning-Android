# ADB Cheatsheet
## Packages
Get a list of all installed packages
```
adb shell pm list packages -f
```
## Activities
Send a broadcast
```
adb shell am broadcast -a android.intent.action.ACTION
```
## Content Providers
```
adb shell content query --uri content://com.example.images/images
```

