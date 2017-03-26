package com.doug.controller;

import com.doug.model.Greeting;
import com.doug.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {

        return "result";
    }

    @PostMapping("/shit")
    public String shitSubmit(@ModelAttribute Todo todo){

        return "index";
    }
//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public String getIndex() {
//
//        return "index";
//    }



}
