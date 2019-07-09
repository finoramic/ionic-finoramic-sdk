# ionic-finoramic-sdk

## Installation

1. Download Ionic Plugin

```
> ionic cordova plugin add https://github.com/finoramic/ionic-finoramic-sdk.git
```

We use the following versions in our app level build.gradle

```
dependencies {
  implementation project(':finoramic-android-sdk')
  implementation 'com.google.android.gms:play-services-location:16.0.0'
  implementation 'com.google.android.gms:play-services-auth:16.0.1'
  implementation 'com.google.firebase:firebase-auth:16.0.5'
  implementation "com.android.support:appcompat-v7:26.1.0"
  implementation 'com.android.support.constraint:constraint-layout:1.1.3'
}
```
NOTE: Any deviation in these versions may cause an error, please contact us if you need to use different versions.


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
|**environment**|required|String|Variable to choose **sandbox** or **production** environments|

```
constructor(){
  FinoramicIonicPlugin.initiate(<CLIENT_ID>, <CLIENT_USER_ID>, <ENVIRONMENT>, CallbackContext);
}
```

4. Create a googleSignIn Button

5. When this button is clicked, call getGoogleSignIn method.

|param|required|value|comments|
|---|---|---|---|
|**redirect_url**|required|string|URL to redirect to after login|
|fetch_profile|optional|boolean|If set to true, Finoramic will send user’s Google profile details along with redirect|
|**CallbackContext**|required|this|must contain success and error methods|

```
FinoramicIonicPlugin.getGoogleSignIn(<REDIRECT_URL>, <FETCH_PROFILE>, CallbackContext);
```

This opens the WebView and prompts the user to select a Google account to sign in with.

6. Upon successful login, google profile will be sent in the success method of callback context (if fetch_profile is set to true).

The format of data is a JSONString

#### Success
|param|value|comments|
|---|---|---|
|user_id|string|Client Unique User Identifier|
|google_user_id|string|Google Unique User Identifier|
|name|string|Name of user|
|email|string|Email of user|
|display_name|string|Display Name of user|
|given_name|string|Given Name of user|
|photo_url|string|Photo URL of user|

#### Error
|param|value|comments|
|---|---|---|
|login|string|Status of login. eg.**error**|
|code|number|Error code|
|message|string|Message of error|


Sample response will look like
```
{
  "user_id": "abc123",
  "google_user_id": "10746794874287731198",
  "name": "Ravi Verma",
  "email": "ravi.verma1337@gmail.com",
  "display_name": "Ravi Verma",
  "given_name": "Ravi",
  "photo_url": "https: //lh3.googleusercontent.com/-5dNnBkloY1w/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rcHQYnxaoHvgE6U2dcSiNYRpk0lGA/s86-c/photo.jpg"
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
    FinoramicIonicPlugin.initiate(<CLIENT_ID>, <CLIENT_USER_ID>, <ENVIRONMENT>,
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
      });
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
