package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 供应商家：(Supplier)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Supplier")
public class Supplier implements Serializable {

    // Supplier编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplier_id;

    // 供应商家
    @Basic
    private String supplier;
    // 公司地址
    @Basic
    private String company_address;












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
