package com.example.demo1.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_config")
public class SysConfig {

    private String variable ;

    private String value ;

    private Date setTime ;

    private String setBy ;

}
