package com.example.darajaapi.services;

import com.example.darajaapi.payload.AuthStk;
import com.example.darajaapi.payload.CallBackResponse;
import com.example.darajaapi.payload.MpesaResponse;
import com.example.darajaapi.payload.StkPayload;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

@Service
public class StkPushServices {
    @Value("${DEV_TOKEN_URL}")
            private String URL;
    @Value("${DEV_CONSUMER_KEY}")
            private String DEV_CONSUMER_KEY;
    @Value("${DEV_CONSUMER_SECRET}")
            private String DEV_CONSUMER_SECRET;
    @Value("${DEV_SHORT_CODE}")
            private String DEV_SHORT_CODE;
    @Value("${DEV_PASS_KEY}")
            private String DEV_PASS_KEY;
    @Value("${DEV_MPESA_URL}")
            private String DEV_MPESA_URL;
    Logger logger = LoggerFactory.getLogger(StkPushServices.class);
    static Gson gson = new Gson();
    public String stkToken(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String keys = DEV_CONSUMER_KEY.concat(":").concat(DEV_CONSUMER_SECRET);
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .addHeader("Authorization", "Basic "+Base64.getEncoder().encodeToString(keys.getBytes()))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            assert response.body() != null;
            AuthStk res = gson.fromJson(responseBody, AuthStk.class);
            return res.getAccess_token();
        }catch (IOException e){
            e.printStackTrace();
            return e.toString();
        }

    }

    public String sendStkPush(String req){
        String token = stkToken();
        StkPayload result = gson.fromJson(req, StkPayload.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String isoString = sdf.format(new Date());
        String timestamp  = Pattern.compile("[^\\d]").matcher(isoString.split("\\.")[0]).replaceAll("");

        //generating password for the request
        String ssss = DEV_SHORT_CODE.concat(DEV_PASS_KEY).concat(timestamp);
        String password = Base64.getEncoder().encodeToString(ssss.getBytes());

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("BusinessShortCode", DEV_SHORT_CODE);
        json.put("Password", password);
        json.put("Timestamp", timestamp);
        json.put("TransactionType", "CustomerPayBillOnline");
        json.put("Amount", result.getAmount());
        json.put("PartyA", "254".concat(result.getPhoneNumber().toString()));
        json.put("PartyB", DEV_SHORT_CODE);
        json.put("PhoneNumber", "254".concat(result.getPhoneNumber().toString()));
        json.put("CallBackURL", "https://mydomain.com/path");
        json.put("AccountReference", "Baby Girl Usha lala sina sms");
        json.put("TransactionDesc", "Payment of X");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(json));
        Request request = new Request.Builder()
                .url(DEV_MPESA_URL)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            assert response.body() != null;
            MpesaResponse res = gson.fromJson(responseBody, MpesaResponse.class);
            logger.info(res.getCustomerMessage());
            return responseBody;
        }catch (IOException e){
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String callBackFunction(String req){
        CallBackResponse response = gson.fromJson(req, CallBackResponse.class);
        return req;
    }
}