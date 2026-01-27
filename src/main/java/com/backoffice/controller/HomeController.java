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
}
