package com.example.darajaapi.controler;

import com.example.darajaapi.services.StkPushServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api")
public class StkPushController {

    @Autowired
    StkPushServices stkPushServices;
    @PostMapping("/stk")
    public String stkPush(@RequestBody String req) throws IOException {
        return stkPushServices.sendStkPush(req);
    }

    @PostMapping("/callBackFunction")
    public String callBackFunction(@RequestBody String req){
        return StkPushServices.callBackFunction(req);
    }
}
