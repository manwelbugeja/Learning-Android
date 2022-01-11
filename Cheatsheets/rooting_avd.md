# Rooting AVD
Start emulator with writable system
```
emulator -avd Pixel_XL_API_28 -writable-system
```

Restart ADB as root and remount
```
adb root
adb remount
```

Download superSU app on computer
```
git clone https://github.com/0xFireball/root_avd
```

Install apk to AVD
This may not work, if so, download the SuperSU app from store or similar
```
adb install root_avd/SuperSU/common/Superuser.apk
```

Push su binary
```
adb push root_avd/SuperSU/x86/su /system/bin/su
```

Changeb su permissions
```
adb shell chmod 06755 /system/bin/su
```

Install su
```
adb shell /system/bin/su --install
adb shell /system/bin/su --daemon&
adb shell setenforce 0
```

Open SuperSU and update su... leave it as is if download fails

source:
https://stackoverflow.com/questions/5095234/how-to-get-root-access-on-android-emulator
