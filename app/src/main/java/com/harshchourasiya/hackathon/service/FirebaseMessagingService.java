package com.harshchourasiya.hackathon.service;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification();
    }

    private void sendNotification() {
        // Will Be there Soon
        // I removed the Service declaration from the androidManifest.xml file add it later
    }

}
