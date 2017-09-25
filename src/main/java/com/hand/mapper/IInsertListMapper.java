//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.mapper;

import com.hand.mapper.provider.InsertListProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

public interface IInsertListMapper<T> {

    @InsertProvider(
        type = InsertListProvider.class,
        method = "dynamicSQL"
    )
    int insertList(List<T> var1);
}
