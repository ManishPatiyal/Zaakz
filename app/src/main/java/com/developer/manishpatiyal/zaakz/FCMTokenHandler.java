package com.developer.manishpatiyal.zaakz;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Gaurav on 28/8/17.
 */

public class FCMTokenHandler extends FirebaseInstanceIdService {


    private String TAG = "FCMTokenHandler";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String refreshedToken) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("tokenId", refreshedToken)
                .build();
        Request request = new Request.Builder()
                //.url("http://zaakz-b7baf.appspot.com/hello")
                .url("http://localhost:8080/hello")
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
        //    Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
