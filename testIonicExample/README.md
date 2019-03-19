# cordov-plugin-figg

## Installation
`ionic cordova plugin add github link`

Inside home.page.ts.</br>
Declare plugin

```
declare var FinoramicIonicPlugin: any;
```
Add following variable above Class
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
Call the signIn Function for googleLogin with Finoramic
```
 FinoramicIonicPlugin.signIn(GOOGLE_CLIENT_ID);
```
For SMS Upload 
```
 FinoramicIonicPlugin.uploadSMS();
```
