import frida
import sys

def on_message(message, data):
    print(message)

code = """
Java.perform(function() {
 var Activity = Java.use("com.example.vulnapp.MainActivity");
 Activity.setOutput.implementation = function() {
     console.log("[ + ] Function called and return true")
     return true;
 }
});
"""

print("**********HELLO")
process = frida.get_usb_device().attach('vulnapp')
script = process.create_script(code)

print("Executing the JS code")

script.load()
sys.stdin.read()

