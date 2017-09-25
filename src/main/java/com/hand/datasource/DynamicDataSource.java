package com.hand.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 定义Spring的动态数据源   未启用
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
     @Override
     protected Object determineCurrentLookupKey() {
         // 从自定义的位置获取数据源标识
         return DynamicDataSourceHolder.getDataSource();
     }

 }
