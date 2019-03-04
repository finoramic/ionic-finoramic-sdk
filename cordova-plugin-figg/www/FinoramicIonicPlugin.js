var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'coolMethod', [arg0]);
};

// add module prefix if mutiple funciton is present else not required
module.exports.initiate = function (arg0, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'initiate', [arg0]);
};

// add module prefix if mutiple funciton is present else not required
module.exports.signIn = function (arg0, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'signIn', [arg0]);
};

// add module prefix if mutiple funciton is present else not required
module.exports.sendSMS = function (arg0, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'sendSMS', [arg0]);
};
