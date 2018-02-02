package com.tr.query.bind.querybind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tr.query.bind.querybind.service.BindQueryReplacator;
import com.tr.query.bind.querybind.service.GenericCollection;
import com.tr.query.bind.querybind.service.GenericCollectionServiceImpl;

@RestController
public class BindController {
    
    @Autowired
    private GenericCollectionServiceImpl service;
    
    @Autowired
    private BindQueryReplacator bindQueryReplacator;
    
    @RequestMapping(path="/convertQuery", method=RequestMethod.POST)
    public @ResponseBody String convertQuery(@RequestParam("input") String input){
        String replaceBinds = bindQueryReplacator.replaceBinds(bindQueryReplacator.getFileContents(input));
        return replaceBinds;
    }
    
    @RequestMapping(path="/contador", method=RequestMethod.GET)
    public @ResponseBody int convertQuery(){
        GenericCollection findByNome = service.findByNome("contador");
        if (findByNome == null) {
            return 0;
        }
        return findByNome.getCount();
    }
}
