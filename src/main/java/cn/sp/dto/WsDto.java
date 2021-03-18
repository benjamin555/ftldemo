package cn.sp.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: chenjiazhen
 * @Date: 2021/3/2 18:26
 */

public class WsDto {
    private String tableName;
    private List<String> fieldNames = Lists.newArrayList();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }
}
