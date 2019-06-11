import { Component } from '@angular/core';
declare var FinoramicIonicPlugin: any;
const CLIENT_ID = 'com.figg';
const CLIENT_USER_ID = '';
const REDIRECT_URL = '';
const FETCH_PROFILE = '';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})

export class HomePage {
  constructor() {
    FinoramicIonicPlugin.initiate(CLIENT_ID, CLIENT_USER_ID);
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
    FinoramicIonicPlugin.getGoogleSignIn(
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
