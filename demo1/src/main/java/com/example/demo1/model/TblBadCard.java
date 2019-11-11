package com.example.demo1.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * badCard Object
 * @author LiangXiJie
 * @date Created in  2019/6/19  10:47
 */
@Data
@TableName("tbl_bad_card")
public class TblBadCard {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 卡号
     */
    @TableField("card_no")
    private String cardNo;
    /**
     * 卡校验位
     */
    @TableField("card_chk_no")
    private String cardChkNo;
    /**
     * 互通卡标志
     */
    @TableField("ht_flag")
    private String htFlag;
    /**
     * 商户号
     */
    @TableField("mer_chant_id")
    private String merchantId;
    /**
     * 客服工单号
     */
    @TableField("support_no")
    private String supportNo;

    /**
     *  账户类型
     */
    @TableField("txn_type")
    private String txnType ;

    /**
     *  账户号
     */
    @TableField("account_no")
    private String accountNo ;

    /**
     * 损坏类型 : 0-非人为损坏 1-人为损坏
     */
    @TableField("txn_cost_flag")
    private String txnCostFlag ;

    /**
     *  单位号
     */
    @TableField("unit_id")
    private String unitId ;

    /**
     * SEID
     */
    @TableField("seid")
    private String seid;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 订单时间
     */
    @TableField("order_date")
    private String orderDate;
    /**
     * 手机号
     */
    @TableField("mobile_no")
    private String mobileNo;
    /**
     * 业务状态
     */
    @TableField("biz_status")
    private Integer bizStatus;
    /**
     * 退款状态
     *  0: 初始 , 1: 结果已获取(不查询联机) 2: 撤销结果获取(可以去查询联机) 3: 已退款
     */
    @TableField("refund_status")
    private Integer refundStatus;

    /**
     * 联机申请中心流水号
     */
    @TableField("center_seq")
    private String centerSeq;
    /**
     * 记录日志
     */
    @TableField("remark")
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     *  余额
     */
    @TableField("amt")
    private Integer amt ;
}
