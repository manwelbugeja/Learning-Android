console.log("Script loaded successfully ");
Java.perform(function x() {


  var mainactivity = Java.use("com.example.nodebuggingapp.MainActivity");
    mainactivity.isDebuggable.implementation = function() {
      send("[ + ] isDebuggable() was called");
      return false;
    };
});
