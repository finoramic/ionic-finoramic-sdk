import { Component } from '@angular/core';
declare var FinoramicIonicPlugin: any;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})


export class HomePage {
  constructor() {
    FinoramicIonicPlugin.initiate();
  }
  onClickLogin() {
    console.log('start');
    FinoramicIonicPlugin.signIn();
    console.log('end');
  }
  onClickSMS() {
    console.log('start');
    FinoramicIonicPlugin.uploadSMS();
    console.log('end');
  }
}
