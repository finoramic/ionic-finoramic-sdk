import { Component } from '@angular/core';
declare var FinoramicIonicPlugin: any;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})


export class HomePage {
  onClickLogin() {
    console.log('start');
    FinoramicIonicPlugin.initiate();
    console.log('1');
    FinoramicIonicPlugin.signIn();
    console.log('end');
    }
  onClickSMS() {
      console.log('start');
      FinoramicIonicPlugin.sendSMS();
      console.log('end');
    }
}
