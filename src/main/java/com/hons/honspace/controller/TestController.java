package com.hons.honspace.controller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    //Next two require a properties entry "hello"

    @Autowired
    @Qualifier("runtimeProperties")
    private Properties props;
    
    @RequestMapping(value = "/proptest1", method = RequestMethod.GET)
    public String props1(ModelMap model){
        model.put("hello", props.get("hello"));
        return "list";
    }

    @Value("#{runtimeProperties['hello']}") String  a;
    @Value("#{runtimeProperties['gridstorage.use.localmap']}") String  b;
    @Value("#{runtimeProperties['gridstorage.use.memcache']}") String  c;
    @Value("#{runtimeProperties['gridstorage.use.hbase']}") String  d;
    
    @RequestMapping(value = "/proptest2", method = RequestMethod.GET)
    public String props2(ModelMap model){
        model.put("hello", a);
        model.put("1", b);
        model.put("2", c);
        model.put("3", d);
        return "list";
    }
}
