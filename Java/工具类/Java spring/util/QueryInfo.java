package com.example.backend.util;

import lombok.Data;

/**
 * @author HuaRunSheng
 * @date 2022/10/9 11:53
 * @description :查询工具类
 */
@Data
public class QueryInfo {
    // 第几页
    private Integer pageNumber;
    // 一页多少条数据
    private Integer pageSize;
    // 查询内容
    private String queryString;
}
