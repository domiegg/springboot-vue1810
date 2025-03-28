package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 帮助中心：(HelpCenter)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HelpCenter")
public class HelpCenter implements Serializable {

    // HelpCenter编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "help_center_id")
    private Integer help_center_id;

    // 留言标题
    @Basic
    private String message_title;
    // 留言商家
    @Basic
    private Integer message_merchant;
    // 供应商家
    @Basic
    private String supplier;
    // 留言日期
    @Basic
    private Timestamp message_date;
    // 留言内容
    @Basic
    private String message_content;
    // 回复状态
    @Basic
    private String reply_status;
    // 留言回复
    @Basic
    private String message_reply;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
