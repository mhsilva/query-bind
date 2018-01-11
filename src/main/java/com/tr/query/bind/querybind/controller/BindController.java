package com.tr.query.bind.querybind.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tr.query.bind.querybind.service.BindQueryReplacator;

@RestController
public class BindController {
    @RequestMapping(path="/convertQuery", method=RequestMethod.POST)
    public @ResponseBody String convertQuery(@RequestParam("input") String input){
        BindQueryReplacator bindQueryReplacator = new BindQueryReplacator();
        String replaceBinds = bindQueryReplacator.replaceBinds(bindQueryReplacator.getFileContents(input));
        return replaceBinds;
    }
}
