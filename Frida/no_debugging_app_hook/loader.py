import time

import frida


def my_message_handler(message, payload):
    print(message)
    print(payload)


device = frida.get_usb_device()
#pid = device.spawn(["com.android.keepass"])
#device.resume(pid)
#time.sleep(1)  # Without it Java.perform silently fails
#session = device.attach(pid)
session = device.attach("NoDebuggingApp")
with open("hook_debug_func.js") as f:
    script = session.create_script(f.read())
script.on("message", my_message_handler)
script.load()

# prevent the python script from terminating
raw_input()
