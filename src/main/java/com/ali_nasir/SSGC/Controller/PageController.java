package com.ali_nasir.SSGC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

    @GetMapping("/selection")
    public String selectionPage() {
        return "selection"; 
    }

    @GetMapping("/result")
    public String resultPage() {
        return "result"; 
    }
    @GetMapping("/analysis")
    public String analysisPage(){
        return "analysis";
    }
}
