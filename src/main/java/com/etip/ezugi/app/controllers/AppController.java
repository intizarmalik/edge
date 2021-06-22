package com.etip.ezugi.app.controllers;

import com.etip.ezugi.app.constants.ApiEndPointsConstants;
import com.etip.ezugi.app.respone.AppBaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @GetMapping(value = ApiEndPointsConstants.GET_USERS)
    public AppBaseResponse getUsers() {
        AppBaseResponse appBaseResponse = new AppBaseResponse();
        String data = "data";
        appBaseResponse.setData(data);
        appBaseResponse.setResponseCode("ResponseCode");
        appBaseResponse.setResponseMessage("responseMessage");
        return appBaseResponse;
    }
}
