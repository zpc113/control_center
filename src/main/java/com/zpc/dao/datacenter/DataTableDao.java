package com.zpc.dao.datacenter;

import org.apache.ibatis.annotations.Param;

/**
 * Created by 和谐社会人人有责 on 2017/12/10.
 */
public interface DataTableDao {

    void createTable(@Param("tableName") String tableName);

}
