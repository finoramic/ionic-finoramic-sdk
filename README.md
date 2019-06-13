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

The initiate method takes 3 params

|param|required|value|comments|
|---|---|---|---|
|**client_id**|required|String|Provided by Finoramic|
|**user_id**|required|String|Client Unique User Identifier|
|**context**|required|this|Current class context|


```
constructor(){
  FinoramicIonicPlugin.initiate(<CLIENT_ID>, <CLIENT_USER_ID>, CallbackContext);
}
```

4. Create a googleSignIn Button

5. When this button is clicked, call getGoogleSignIn method.

|param|required|value|comments|
|---|---|---|---|
|**redirect_url**|required|string|URL to redirect to after login|
|fetch_profile|optional|boolean|If set to true, Finoramic will send user’s Google profile details along with redirect|
|**CallbackContext**|required|this|must contain success and error methods|
|dev_env||boolean|Set to true while testing in sandbox environment|

```
FinoramicIonicPlugin.getGoogleSignIn(<REDIRECT_URL>, <FETCH_PROFILE>, CallbackContext, <DEV_ENV>);
```

This opens the WebView and prompts the user to select a Google account to sign in with.

6. Upon successful login, google profile will be sent in the success method of callback context (if fetch_profile is set to true).

The format of data is a JSONString

|param|value|comments|
|---|---|---|
|clientInput|JSON|Contains client input in encoded JSON format |
|finoramicOutput|JSON|Contains users google profile info in encoded JSON format|

When parsed, response will look like
```
{
  clientInput: {
    "clientUserId": "abc123"
  },
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

7. To start uploading SMS, call the uploadSMS as soon as SMS permissions are granted. Permissions for READ.SMS, ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION will be asked.

```
 FinoramicIonicPlugin.uploadSMS(args, CallbackContext);
```

|param|value|comments|
|---|---|---|
|args|string|Send empty string|
|CallbackContext|this|must contain success and error methods|

## Example

```
import { Component } from '@angular/core';

declare var FinoramicIonicPlugin: any;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})

export class HomePage {
  constructor() {
    FinoramicIonicPlugin.initiate(<CLIENT_ID>, <CLIENT_USER_ID>,
      (response) => {
        // Value of response here is string "success"
        // Handle successful initiate
      },
      (error) => {
        // Value of error here is string of error
        // Handle unsuccessful initiate
      });
  }
  onClickLogin() {
    FinoramicIonicPlugin.getGoogleSignIn(<REDIRECT_URL>, <FETCH_PROFILE>,
      (response) => {
        // Handle successful sign-in
      },
      (error) => {
        // Handle unsuccessful sign-in
      }, <DEV_ENV>);
  }
  onClickSMS() {
    FinoramicIonicPlugin.uploadSMS('',
      (response) => {
        // Value of response here is string "success"
        // Handle successful sms upload
      },
      (error) => {
        // Value of error here is string of error
        // Handle unsuccessful sms upload
      });
  }
}
```

## DISCLAIMER

The information contained within this document is confidential and proprietary to Varignon Technologies Private Limited. Recipients may not disclose, duplicate, distribute or otherwise disseminate this information without the express, written authorization or permission for the future Calls. Recipients are expected to accept these conditions without exceptions unless this organization is notified to the contrary.  Recipients are expected to communicate this information to their employees, or anyone else having access to this document. This information shall be safeguarded with the same degree of protection the recipient would afford its own proprietary data.
