package cordova.plugin.figg;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;

import com.figg.sdk.android.Constants;
import com.figg.sdk.android.FinoramicSdk;

import static com.figg.sdk.android.Constants.PERMISSIONS;
/**
 * This class echoes a string called from JavaScript.
 */
public class FinoramicIonicPlugin extends CordovaPlugin {
    private final String CLIENT_ID = "com.figg";
	private final int SIGN_IN_REQUEST = 10001;
	private final int PERMISSION_ALL = 10002;
	private final String TAG = "finoramic-sdk";
	private final String[] extraScopes = {"https://www.googleapis.com/auth/contacts.readonly"};

	private final String GOOGLE_CLIENT_ID = "695617984308-fl04vs5sb8cd3298prk5vimr7jupjivl.apps.googleusercontent.com";


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("initiate")) {
            this.initiate(args, callbackContext);
            return true;
        }
        if (action.equals("signIn")) {
            this.signIn(args, callbackContext);
            return true;
        }
        if (action.equals("sendSMS")) {
            this.sendSMS(args, callbackContext);
            return true;
        }
        return false;
    }

    private void initiate(JSONArray args, CallbackContext callbackContext){
        Context context= this.cordova.getActivity().getApplicationContext();
        if (args != null) {
            try {
                String result = "success";
                //Write function to call finoramic API and return result via callbackContext.success
                FinoramicSdk.init(context, CLIENT_ID);
                callbackContext.success(result);
            } catch (Exception e) {
                //TODO: handle exception
                callbackContext.error("Something went wrong"+ e);
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void signIn(JSONArray args, CallbackContext callbackContext){
        // Activity activity = this.cordova.getActivity();
        Context context = this.cordova.getActivity().getApplicationContext();

        if (args != null) {
            try {
                String result = "success";
                //Write function to call finoramic API and return result via callbackContext.success
                Intent signInIntent = FinoramicSdk.getSignInIntent(context, GOOGLE_CLIENT_ID, extraScopes);
                cordova.setActivityResultCallback (this);
                cordova.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cordova.getActivity().startActivityForResult(signInIntent, SIGN_IN_REQUEST);
                    }
                });
                callbackContext.success(result);
            } catch (Exception e) {
                //TODO: handle exception
                callbackContext.error("Something went wrong"+ e);
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");

		// Result returned from launching the Intent from FinoramicSdk.getSignInIntent(...)
		if (requestCode == SIGN_IN_REQUEST) {
			if (resultCode == Activity.RESULT_OK) {
				String gAccount = data.getStringExtra("account");
				if (gAccount != null) {
					Log.d(TAG, "CLIENT RECEIVED GOOGLE ACCOUNT : " + gAccount);
					// Do whatever you want with your Google User
				} else {
					Log.d(TAG, "CLIENT DID NOT RECEIVE GOOGLE ACCOUNT");
					// Handle unsuccessful sign-in
				}
			}
		}
	}

    private void sendSMS(JSONArray args, CallbackContext callbackContext){
        if (args != null) {
            try {
                String result = "success";
                //Write function to call finoramic API and return result via callbackContext.success
                callbackContext.success(result);
            } catch (Exception e) {
                //TODO: handle exception
                callbackContext.error("Something went wrong"+ e);
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
