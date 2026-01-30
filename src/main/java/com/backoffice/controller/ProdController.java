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

    @GetMapping("/prod-2")
    public String prod2() {
        return "Product test 2";
    }
}
