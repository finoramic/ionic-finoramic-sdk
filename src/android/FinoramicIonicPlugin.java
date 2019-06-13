package cordova.plugin.figg;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;

import com.figg.sdk.android.Constants;
import com.figg.sdk.android.FinoramicSdk;

import static com.figg.sdk.android.Constants.PERMISSIONS;

public class FinoramicIonicPlugin extends CordovaPlugin {
	private final int SIGN_IN_REQUEST = 10001;
	private final int PERMISSION_ALL = 0;
	private final String TAG = "finoramic-sdk";
    public static final int PERMISSION_DENIED_ERROR = 20;

    public CallbackContext callbackContext;
    public Context context;


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.context= this.cordova.getActivity().getApplicationContext();
        this.callbackContext = callbackContext;
        if (action.equals("initiate")) {
            String clientId = args.getString(0);
            String clientUserId = args.getString(1);
            String environment = args.getString(2);
            this.initiate(clientId, clientUserId, environment, callbackContext);
            return true;
        }
        if (action.equals("uploadSMS")) {
            this.uploadSMS(args, callbackContext);
            return true;
        }
        if (action.equals("getUrl")) {
            String redirect_uri = args.getString(0);
            Boolean fetch_profile = Boolean.valueOf(args.getString(1));
            this.getUrl(redirect_uri, fetch_profile, callbackContext);
            return true;
        }
        if (action.equals("getGoogleSignIn")) {
            String redirect_uri = args.getString(0);
            Boolean fetch_profile = Boolean.valueOf(args.getString(1));
            this.getGoogleSignIn(redirect_uri, fetch_profile, callbackContext);
            return true;
        }
        return false;
    }

    private void initiate(String clientId, String clientUserId, String environment, CallbackContext callbackContext) {
        try {
            String result = "success";
            FinoramicSdk.init(context, clientId, clientUserId, environment);
            callbackContext.success(result);
        } catch (Exception e) {
            callbackContext.error("Something went wrong"+ e);
        }
    }

    private void uploadSMS(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            try {
                String result = "success";
                cordova.setActivityResultCallback (this);
                cordova.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    if (cordova.hasPermission(PERMISSIONS[0]) && cordova.hasPermission(PERMISSIONS[1]) && cordova.hasPermission(PERMISSIONS[2])) {
                        FinoramicSdk.sendSMS(context);
                    } else {
                        getReadPermission(PERMISSION_ALL);
                    }
                    }
                });
                callbackContext.success(result);
            } catch (Exception e) {
                callbackContext.error("Something went wrong"+ e);
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    protected void getReadPermission(int requestCode) {
        cordova.requestPermissions(this, requestCode, PERMISSIONS);
    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        if(requestCode == PERMISSION_ALL) {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FinoramicSdk.sendSMS(context);
            }
        }
    }

    private void getUrl(String redirectUrl, Boolean fetchProfile, CallbackContext callbackContext) {
        try {
            String url = FinoramicSdk.getUrl(redirectUrl, fetchProfile);
            callbackContext.success(url);
        } catch (Exception e) {
            callbackContext.error("Something went wrong"+ e);
        }
    }

    private void getGoogleSignIn(String redirectUrl, Boolean fetchProfile, CallbackContext callbackContext) {
        try {
            String result = "success";
            Intent signInIntent = FinoramicSdk.getGoogleSignIn(context, redirectUrl, fetchProfile);
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cordova.getActivity().startActivityForResult(signInIntent, SIGN_IN_REQUEST);
                }
            });
        } catch (Exception e) {
            callbackContext.error("Something went wrong"+ e);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");

        if (requestCode == SIGN_IN_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String gAccount = data.getStringExtra("account");
                if (gAccount != null) {
                    Log.d(TAG, "CLIENT RECEIVED GOOGLE ACCOUNT : " + gAccount);
                    callbackContext.success(gAccount);
                } else {
                    Log.d(TAG, "CLIENT DID NOT RECEIVE GOOGLE ACCOUNT");
                    callbackContext.error("unsuccessful sign-in");
                }
            }
        }
    }
}
