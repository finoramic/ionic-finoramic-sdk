var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'coolMethod', [arg0]);
};

module.exports.initiate = function (arg0, arg1, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'initiate', [arg0, arg1]);
};

module.exports.uploadSMS = function (arg0, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'uploadSMS', [arg0]);
};

module.exports.getUrl = function (arg0, arg1, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'getUrl', [arg0, arg1]);
}

module.exports.getGoogleSignIn = function (arg0, arg1, success, error) {
    exec(success, error, 'FinoramicIonicPlugin', 'getGoogleSignIn', [arg0, arg1]);
}
