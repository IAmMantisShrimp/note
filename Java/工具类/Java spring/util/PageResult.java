package com.example.backend.util;

import lombok.Data;

import java.util.List;

/**
 * @author HuaRunSheng
 * @date 2022/10/9 11:51
 * @description :分页数据工具类
 */
@Data
public class PageResult extends Result{
    // 总记录数
    private long total;
    // 分页数据
    private List rows;

    public PageResult(long total, List list){
        this.setFlag(true);
        this.setMessage("分页查询成功");
        this.total=total;
        this.rows=list;
    }

}
