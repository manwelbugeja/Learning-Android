# Drozer Cheatsheet

## Packages
Find installed packages
```
run app.package.list [-f tofind]
```
Information about package
```
run app.package.info -a com.example.myapp
run app.package.attacksurfuce com.example.myapp
run app.package.manifest com.example.myapp
```

## Activities
Find exported activities on device
```
run app.activity.info  -a com.example.myapp
```
FInd all activities on device including unexported
```
run app.activity.info  -a com.example.myapp -u
```
Start an activity
```
run app.activity.start --component com.example.myapp com.example.myapp.myactivity
```

## Broadcast Receivers
```
run app.broadcast.info -a com.example.myapp -i
run app.broadcast.send --action android.intent.action.ACTION
```

## Providers
Find content providers
```
run scanner.provider.finduris com.example.myapp
```

