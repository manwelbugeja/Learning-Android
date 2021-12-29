# Drozer Cheatsheet

## Packages
```
run app.package.list [-f tofind]
run app.package.info -a com.example.myapp
run app.package.attacksurfuce com.example.myapp
```

## Activities
```
run app.activity.info  -a com.example.myapp
run app.activity.start --component com.example.myapp com.example.myapp.myactivity
```

## Broadcast Receivers
```
run app.broadcast.info -a com.example.myapp -i
run app.broadcast.send --action android.intent.action.ACTION
```

