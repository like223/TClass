package com.teoan.tclass.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Department)表实体类
 *
 * @author Teoan
 * @since 2020-07-25 22:09:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("serial")
public class Department extends Model<Department> {

    private Integer id;
    //部门名称
    private String name;
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