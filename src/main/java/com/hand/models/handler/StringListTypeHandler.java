package com.hand.models.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @Title StringListTypeHandler
 * @Description 对List<String>的mabatis的字段映射
 * @Author ZQian
 * @date: 2017/8/8 下午6:14
 */
@MappedTypes({List.class})
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    private static Logger LOG= LoggerFactory.getLogger(StringListTypeHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        StringBuilder result = new StringBuilder();
        for (String value : parameter)
            result.append(value).append(",");
        result.deleteCharAt(result.length() - 1);
        ps.setString(i, result.toString());
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.getStringList(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.getStringList(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement rs, int columnIndex) throws SQLException {
        return this.getStringList(rs.getString(columnIndex));
    }

    private List<String> getStringList(String value){
        return Arrays.asList(value.split(","));
    }
}
