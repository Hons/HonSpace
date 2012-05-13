package com.hons.honspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hons.honspace.actionqueue.ActionQueue;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin/tick", method = RequestMethod.GET)
    public String getAnObject(ModelMap model) {
        
        ActionQueue.getInstance().tick();
        model.put("status", "Success");
        return "list";
    }
}
