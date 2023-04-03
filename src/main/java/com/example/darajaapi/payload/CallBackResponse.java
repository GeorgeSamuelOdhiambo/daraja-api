package com.example.darajaapi.payload;

import com.example.darajaapi.payload.mpesa_payload.result_part_one;

public class CallBackResponse {
    public result_part_one Result;

    public result_part_one getResult() {
        return Result;
    }

    public void setResult(result_part_one result) {
        Result = result;
    }
}
