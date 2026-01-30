package com.backoffice.controller;

import com.mhframework.annotation.classes.Controller;
import com.mhframework.annotation.method.GetMapping;
import com.mhframework.handler.view.ModelView;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public ModelView home() {
        return new ModelView("home.jsp");
    }

    @GetMapping("/mihary-test")
    public ModelView miharyTest() {
        ModelView modelView = new ModelView("mihary-test.jsp");

        String[] tst = new String[] {
            "Test 1", "Test 2", "Test 3"
        };

        modelView.addData("tst", tst);

        return modelView;
    }
}
