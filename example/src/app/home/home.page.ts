import { Component } from '@angular/core';
declare var FinoramicIonicPlugin: any;
const FINORAMIC_CLIENT_ID = 'com.figg';
const GOOGLE_CLIENT_ID = '695617984308-fl04vs5sb8cd3298prk5vimr7jupjivl.apps.googleusercontent.com';
const extraScopes = ['https://www.googleapis.com/auth/contacts.readonly'];
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
  onClickGetUrl() {
    FinoramicIonicPlugin.getUrl(FINORAMIC_CLIENT_ID,
      FINORAMIC_CLIENT_ID,
      FINORAMIC_CLIENT_ID,
      false,
      (response) => {
        console.log('Response', response);
      },
      (error) => {
        console.log('Error', error);
      });
  }
}
