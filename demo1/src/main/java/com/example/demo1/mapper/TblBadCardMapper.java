package com.example.demo1.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo1.model.TblBadCard;

import java.util.Map;

/**
 * @author LiangXiJie
 * @description
 * @date Created in  2019/6/19  14:17
 */
public interface TblBadCardMapper extends BaseMapper<TblBadCard> {

    int deleteByPrimaryKey(Integer id);

    Integer insert(TblBadCard record);

    int insertSelective(TblBadCard record);

    TblBadCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblBadCard record);

    int updateByPrimaryKey(TblBadCard record);

    int updateBadCardBizStatus(Map<String, Object> map);
}
