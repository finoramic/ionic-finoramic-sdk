# cordova-plugin-finoramic

## Installation </br>
### To Add plugin
`ionic cordova plugin add https://github.com/finoramic/ionic-finoramic-sdk.git`

### To remove plugin
`ionic cordova plugin rm cordova-plugin-finoramic`

## API </br>
1. Inside .ts file</br>
Declare plugin

```
declare var FinoramicIonicPlugin: any;
```
2. Add following variable above Class</br>
FINORAMIC_CLIENT_ID will be given by us.</br>
GOOGLE_CLIENT_ID from firebase console
```
const FINORAMIC_CLIENT_ID = '';
const GOOGLE_CLIENT_ID = '';
```
3. Create a constructor and call the below function.These function should be called on Application opens.
```
constructor(){
  FinoramicIonicPlugin.initiate(FINORAMIC_CLIENT_ID);
}
```
4. Call the signIn Function for googleLogin with Finoramic anywhere inside the class.
```
 FinoramicIonicPlugin.signIn(GOOGLE_CLIENT_ID);
```
5. For SMS Upload (Permissions for READ.SMS ,ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION will be asked on calling these function)
```
 FinoramicIonicPlugin.uploadSMS();
```
