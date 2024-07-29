package com.parkro.server.config.firebase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FCMConfig {
//    @Bean
//    FirebaseMessaging firebaseMessaging() throws IOException {
//        ClassPathResource resource = new ClassPathResource("firebase/serviceAccountKey.json");
//
//        InputStream refreshToken = resource.getInputStream();
//
//        FirebaseApp firebaseApp = null;
//        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();
//
//        if(firebaseAppList != null && !firebaseAppList.isEmpty()) {
//            for (FirebaseApp app : firebaseAppList){
//                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
//                    firebaseApp = app;
//                }
//            }
//        } else {
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
//                    .build();
//            firebaseApp = FirebaseApp.initializeApp(options);
//        }
//        return FirebaseMessaging.getInstance(firebaseApp);
//    }
}
