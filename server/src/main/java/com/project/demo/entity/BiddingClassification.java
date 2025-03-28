package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 招标分类：(BiddingClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BiddingClassification")
public class BiddingClassification implements Serializable {

    // BiddingClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidding_classification_id")
    private Integer bidding_classification_id;

    // 招标分类
    @Basic
    private String bidding_classification;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
