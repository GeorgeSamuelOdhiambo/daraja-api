package com.example.darajaapi.services;

import com.example.darajaapi.payload.StkPayload;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class StkPushServices {
    @Value("${DEV_TOKEN_URL}")
            private String DEV_TOKEN_URL;
    @Value("${DEV_CONSUMER_KEY}")
            private String CONSUMER_KEY;
    @Value("${DEV_CONSUMER_SECRET}")
            private String CONSUMER_SECRET;
    Logger logger = LoggerFactory.getLogger(StkPushServices.class);

    public ResponseBody stkToken(String req) throws IOException {

            Gson gson = new Gson();
            StkPayload result = gson.fromJson(req, StkPayload.class);
            String encodedString = Base64.getEncoder().encodeToString((CONSUMER_KEY+":"+CONSUMER_SECRET).getBytes());
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .method("GET", null)
                .addHeader("Authorization", "Basic cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==")
                .build();
        Response response = client.newCall(request).execute();
            logger.info(String.valueOf(response));
        return response.body();
    }
}