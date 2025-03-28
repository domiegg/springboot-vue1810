package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 招标项目：(BiddingProject)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BiddingProject")
public class BiddingProject implements Serializable {

    // BiddingProject编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidding_project_id")
    private Integer bidding_project_id;

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
    // 单位地址
    @Basic
    private String work_address;
    // 招标封面
    @Basic
    private String tender_cover;
    // 招标简介
    @Basic
    private String introduction_to_bidding;

    // 点击数
    @Basic
    private Integer hits;






	// 计时器标题
	@Basic
	private String timer_title;

	// 计时开始时间
	@Basic
	private Timestamp timing_start_time;

	// 计时结束时间
	@Basic
	private Timestamp timing_end_time;







    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
