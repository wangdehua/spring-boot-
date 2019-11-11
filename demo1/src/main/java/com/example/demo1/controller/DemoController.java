package com.example.demo1.controller;

import com.example.demo1.model.TblBadCard;
import com.example.demo1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {


    @Autowired
    private TestService testService ;

    @RequestMapping(value = "/test")
    public Map<String,Object> test(){
        List<Map<String, Object>> list = testService.getList();
        TblBadCard tblBadCard = this.testService.selectByPrimaryKey(1);
        Map<String,Object> map = new HashMap<>();
        return map ;
    }

    @RequestMapping(value = "/test1")
    public String test1(){
        return "test1";
    }

}
