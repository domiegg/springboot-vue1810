package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 结果公示：(ResultPublicity)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ResultPublicity")
public class ResultPublicity implements Serializable {

    // ResultPublicity编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_publicity_id")
    private Integer result_publicity_id;

    // 招标项目
    @Basic
    private String bidding_project;
    // 招标分类
    @Basic
    private String bidding_classification;
    // 招标单位
    @Basic
    private Integer tendering_unit;
    // 责任单位
    @Basic
    private String responsible_unit;
    // 招标封面
    @Basic
    private String tender_cover;
    // 投标商家
    @Basic
    private Integer bidding_merchant;
    // 供应商家
    @Basic
    private String supplier;
    // 投标金额
    @Basic
    private Integer bid_amount;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
