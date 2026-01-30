package com.backoffice.controller;

import com.mhframework.annotation.classes.Controller;
import com.mhframework.annotation.method.GetMapping;
import com.mhframework.handler.view.ModelView;

@Controller
public class ProdController {
    
    @GetMapping("/prod")
    public ModelView prod() {
        return new ModelView("prod.jsp");
    }
}
