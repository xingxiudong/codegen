package com.xingxd.codegen.dao;

import java.sql.SQLException;
import java.util.List;

import com.xingxd.codegen.domain.Column;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.02
 */
public interface ColumnDao {

	public List<Column> selectColumnList(String tableName) throws SQLException;

}
