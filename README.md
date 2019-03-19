# cordov-plugin-figg

## Installation </br>
### To Add plugin
`ionic cordova plugin add https://github.com/finoramic/ionic-finoramic-sdk.git`

### To remove plugin
`ionic cordova plugin rm cordova-plugin-figg`

Inside .ts file</br>
Declare plugin

```
declare var FinoramicIonicPlugin: any;
```
Add following variable above Class</br>
FINORAMIC_CLIENT_ID will be given by us.</br>
GOOGLE_CLIENT_ID from firebase console
```
const FINORAMIC_CLIENT_ID = '';
const GOOGLE_CLIENT_ID = '';
```
Create a constructor and call the below function
```
constructor(){
  FinoramicIonicPlugin.initiate(FINORAMIC_CLIENT_ID);
}
```
Call the signIn Function for googleLogin with Finoramic anywhere inside the class.
```
 FinoramicIonicPlugin.signIn(GOOGLE_CLIENT_ID);
```
For SMS Upload 
```
 FinoramicIonicPlugin.uploadSMS();
```
