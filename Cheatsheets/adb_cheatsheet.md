# ADB Cheatsheet
## Packages
Get a list of all installed packages
```
adb shell pm list packages -f
```

Find apk location
```
adb shell pm path packagename
```

## Activities
Send a broadcast
```
adb shell am broadcast -a android.intent.action.ACTION
```

Start activity
```
adb shell am start -n packagename/.activityname
```

## Content Providers
```
adb shell content query --uri content://com.example.images/images
```

