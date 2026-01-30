package com.backoffice.controller;

import com.mhframework.annotation.classes.Controller;
import com.mhframework.annotation.method.GetMapping;
import com.mhframework.handler.view.ModelView;

@Controller
public class StagingController {
    

    @GetMapping("/staging")
    public ModelView staging() {
        ModelView modelView = new ModelView("staging.jsp");
        return modelView;
    }

    @GetMapping("/st2")
    public String stagingFaharoa() {
        return "Staging Faharoa";
    }
}
