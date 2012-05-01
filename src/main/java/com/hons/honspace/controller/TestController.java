package com.hons.honspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hons.honspace.command.TestCommand;

@Controller
public class TestController {
    
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String getTestOne(ModelMap model) {

        TestCommand tc = new TestCommand();
        model.put("model", tc);
        return "list";
    }
}
