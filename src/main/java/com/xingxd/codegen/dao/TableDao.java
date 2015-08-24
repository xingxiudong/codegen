package com.xingxd.codegen.dao;

import java.sql.SQLException;
import java.util.List;

import com.xingxd.codegen.domain.Table;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.02
 */
public interface TableDao {

	public List<Table> selectTableList(String table_names) throws SQLException;

	public List<Table> selectAllTableList() throws SQLException;

	public Long selectTableCount(String table_names) throws SQLException;

	public Long selectAllTableCount() throws SQLException;

}
