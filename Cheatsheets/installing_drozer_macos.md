# Installing Drozer on Macos

Create a python 2 environment
```
mkdir drozer
cd drozer
pipenv install --python 2.7.18
```

Download the drozer .whl file
```
wget https://github.com/mwrlabs/drozer/releases/download/2.4.4/drozer-2.4.4-py2-none-any.whl
```

Install it
```
pip install drozer-2.4.4-py2-none-any.whl
```

## Running drozer
After pushing the server to the android device, running it and forwarding the port with adb
```
pipenv shell
drozer console connect
```
