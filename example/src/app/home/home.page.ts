import { Component } from '@angular/core';
declare var FinoramicIonicPlugin: any;
const CLIENT_ID = 'com.figg';
const CLIENT_USER_ID = 'abc123';
const REDIRECT_URL = 'https://www.finoramic.com';
const FETCH_PROFILE = true;
const DEV_ENV = true;

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
      }, DEV_ENV);
  }
}
