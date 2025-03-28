package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 市场监督：(MarketSupervision)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MarketSupervision")
public class MarketSupervision implements Serializable {

    // MarketSupervision编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_supervision_id")
    private Integer market_supervision_id;

    // 标题名称
    @Basic
    private String title_name;
    // 正文内容
    @Basic
    private String main_content;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
