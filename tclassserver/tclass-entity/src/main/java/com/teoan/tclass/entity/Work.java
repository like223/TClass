package com.teoan.tclass.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Work)实体类
 *
 * @author Teoan
 * @since 2020-07-24 22:47:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "work",resultMap = "WorkMap")
public class Work extends Model<Work> {


    private Integer id;
    /**
     * 发布者
     */
    private Integer sId;
    /**
     * 发布者名字
     */
    @TableField(exist = false)
    private String sName;

    /**
     * 作业编辑人Id
     */
    private Integer editSId;

    /**
     * 通知编辑人名字
     */
    @TableField(exist = false)
    private String editSName;

    /**
     * 作业启用状态
     */
    @TableLogic
    private Boolean deleted;
    /**
     * 作业名称
     */
    private String name;
    /**
     * 文件名规范
     */
    private String fileNameFormat;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 最晚提交时间
     */
    private Date lastTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}