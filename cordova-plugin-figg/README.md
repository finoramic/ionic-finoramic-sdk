# cordov-plugin-figg
Following are the steps to work on existing cordova plugin.

1. Make necessary changes in code.

2. Go to ionic project where to import plugin.\
Remove existing plugin if present
    ```
    ionic cordova plugin rm cordova-plugin-figg
    ```
    Add new plugin to project
    ```
    ionic cordova plugin add cordova-plugin-figg
    ```
    Build the project 
    ```
    ionic cordova build android
    ```
