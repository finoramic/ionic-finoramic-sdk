import { Component } from '@angular/core';
declare var FinoramicIonicPlugin: any;
const FINORAMIC_CLIENT_ID = 'com.figg';
const extraScopes = ['https://www.googleapis.com/auth/contacts.readonly'];
const USER_ID = '';
const REDIRECT_URL = '';
const FETCH_PROFILE = '';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})

export class HomePage {
  constructor() {
    FinoramicIonicPlugin.initiate(FINORAMIC_CLIENT_ID);
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
  onClickGetGoogleSignIn() {
    FinoramicIonicPlugin.getGoogleSignIn(FINORAMIC_CLIENT_ID,
      USER_ID,
      REDIRECT_URL,
      FETCH_PROFILE,
      (response) => {
        console.log('Response', response);
      },
      (error) => {
        console.log('Error', error);
      });
  }
}
