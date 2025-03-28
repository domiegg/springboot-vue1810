package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 责任单位：(ResponsibleUnit)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ResponsibleUnit")
public class ResponsibleUnit implements Serializable {

    // ResponsibleUnit编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "responsible_unit_id")
    private Integer responsible_unit_id;

    // 责任单位
    @Basic
    private String responsible_unit;
    // 单位地址
    @Basic
    private String work_address;












    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;


    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
