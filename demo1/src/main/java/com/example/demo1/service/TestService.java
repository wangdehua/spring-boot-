package com.example.demo1.service;

import com.example.demo1.model.TblBadCard;

import java.util.List;
import java.util.Map;

public interface TestService {

    public List<Map<String,Object>> getList();

    public TblBadCard selectByPrimaryKey(int id);

}
