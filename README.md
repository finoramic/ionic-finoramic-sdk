# ionic-finoramic-sdk

## Installation

1. Download Ionic Plugin

```
> ionic cordova plugin add https://github.com/finoramic/ionic-finoramic-sdk.git
```

2. Declare the plugin in a .ts file

```
declare var FinoramicIonicPlugin: any;
```

3. Call initiate() function to initiate the sdk. This method must be called as soon as application starts

```
constructor(){
  FinoramicIonicPlugin.initiate(FINORAMIC_CLIENT_ID, CallbackContext);
}
```

|param|value|comments|
|---|---|---|
|FINORAMIC_CLIENT_ID|string|Value provided by Finoramic|
|CallbackContext||must contain success and error methods|

4. Create a googleSignIn Button

5. Call getUrl to fetch the url of Finoramic OAuth for Google Login. Use this url for onClick of Google Login Button.

```
 FinoramicIonicPlugin.getUrl(FINORAMIC_CLIENT_ID, user_id, redirect_uri, fetch_profile);
```

|param|value|comments|
|---|---|---|
|FINORAMIC_CLIENT_ID|string|Value provided by Finoramic|
|user_id|string|Client Unique Identifier for user|
|redirect_uri|string|Url to redirect to after login|
|fetch_profile|boolean|true value returns user google profile as query param along with redirect|

6. After login, user will be redirected to redirect_uri. If fetch_profile is set to true, user google profile will be sent encoded as query param.

|param|value|comments|
|---|---|---|
|clientInput|JSON|Contains client input in encoded JSON format |
|finoramicOutput|JSON|Contains users google profile info in encoded JSON format|

When decoded, response will look like
```
clientInput: {
  "clientUserId": "abc123"
}
```
```
finoramicOutput: {
  "googleUserId": "10746794874287731198",
  "email": "ravi.verma1337@gmail.com",
  "response": {
    "id": "10746794874287731198",
    "name": "Ravi Verma",
    "displayName": "Ravi Verma",
    "familyName": "Verma",
    "givenName": "Ravi",
    "displayNameLastFirst": "Verma, Ravi",
    "email": "ravi.verma1337@gmail.com",
    "dob": {
      "year": 1990,
      "month": 2,
      "day": 15
    }
  }
}
```

For error cases, response will look like
```
finoramicOutput: {
  "googleUserId": "10746794874287731198",
  "email": "ravi.verma1337@gmail.com",
  "login": "error",
  "code": 500,
  "message": "Internal Server Error"
}
```

6. To start uploading SMS, call the uploadSMS. Permissions for READ.SMS, ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION will be asked.
```
 FinoramicIonicPlugin.uploadSMS(args, CallbackContext);
```

|param|value|comments|
|---|---|---|
|args|string|Send empty string|
|CallbackContext||must contain success and error methods|

## Example

```
import { Component } from '@angular/core';

declare var FinoramicIonicPlugin: any;

const FINORAMIC_CLIENT_ID = '<Client ID>';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})

export class HomePage {
  constructor() {
    FinoramicIonicPlugin.initiate(FINORAMIC_CLIENT_ID);
  }
  onClickLogin() {
    FinoramicIonicPlugin.signIn(GOOGLE_CLIENT_ID,
      (response) => {
        console.log('Response', response);
      },
      (error) => {
        console.log('Error', error);
      });
  }
  onClickSMS() {
    FinoramicIonicPlugin.uploadSMS('',
      (response) => {
        console.log('Response', response);
      },
      (error) => {
        console.log('Error', error);
      });
  }
}
```

## Uninstallation
```
ionic cordova plugin rm ionic-finoramic-sdk
```

## DISCLAIMER

The information contained within this document is confidential and proprietary to Varignon Technologies Private Limited. Recipients may not disclose, duplicate, distribute or otherwise disseminate this information without the express, written authorization or permission for the future Calls. Recipients are expected to accept these conditions without exceptions unless this organization is notified to the contrary.  Recipients are expected to communicate this information to their employees, or anyone else having access to this document. This information shall be safeguarded with the same degree of protection the recipient would afford its own proprietary data.
