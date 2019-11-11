package com.example.demo1.service.impl;

import com.example.demo1.config.DynamicSource;
import com.example.demo1.mapper.SysMapper;
import com.example.demo1.mapper.TblBadCardMapper;
import com.example.demo1.model.TblBadCard;
import com.example.demo1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SysMapper sysMapper ;

    @Autowired
    private TblBadCardMapper tblBadCardMapper ;

    @DynamicSource
    @Override
    public List<Map<String, Object>> getList() {
        return this.sysMapper.getList();
    }

    @Override
    public TblBadCard selectByPrimaryKey(int id) {
        return this.tblBadCardMapper.selectByPrimaryKey(id);
    }
}
