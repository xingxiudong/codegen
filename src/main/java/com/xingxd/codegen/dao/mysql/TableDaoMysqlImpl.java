package com.xingxd.codegen.dao.mysql;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xingxd.codegen.dao.TableDao;
import com.xingxd.codegen.domain.PrimaryKey;
import com.xingxd.codegen.domain.Table;
import com.xingxd.codegen.utils.UtilsString;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.08
 */
public class TableDaoMysqlImpl extends BaseDaoMysqlImpl implements TableDao {

	public Long selectAllTableCount() throws SQLException {
		Long count = new Long("0");
		DatabaseMetaData metaData = super.getConnection(false).getMetaData();
		ResultSet rs = metaData.getTables(null, dataSource.getUsername().toUpperCase(), null, new String[] { "TABLE" });

		while (rs.next()) {

			if (rs.getString("TABLE_NAME").contains("$") || StringUtils.isBlank(rs.getString("TABLE_NAME"))) {
				continue;
			}
			count++;
		}
		return count;
	}

	public List<Table> selectAllTableList() throws SQLException {
		List<Table> tableList = new ArrayList<Table>();

		DatabaseMetaData metaData = super.getConnection(false).getMetaData();
		ResultSet rs = metaData.getTables(null, dataSource.getUsername().toUpperCase(), null, new String[] { "TABLE" });

		while (rs.next()) {

			if (rs.getString("TABLE_NAME").contains("$") || StringUtils.isBlank(rs.getString("TABLE_NAME"))) {
				continue;
			}

			Table table = new Table();
			table.setType_cat(rs.getString("TABLE_CAT"));
			table.setType_schem(rs.getString("TABLE_SCHEM"));
			table.setTable_name(rs.getString("TABLE_NAME"));
			table.setTable_type(rs.getString("TABLE_TYPE"));
			table.setRemarks(rs.getString("REMARKS"));
			table.setJavaObjectCamelName(UtilsString.camelize(table.getTable_name()));

			logger.debug(table.toString());
			
			logger.info("Get primary-key...");
			ResultSet rs2 = metaData.getPrimaryKeys(null, null, table.getTable_name());
			List<PrimaryKey> primaryKeyList = new ArrayList<PrimaryKey>();
			while (rs2.next()) {
				PrimaryKey pk = new PrimaryKey();
				pk.setPk_name(rs2.getString(4));
				logger.info("primary key : {}", pk.getPk_name());
				primaryKeyList.add(pk);
			}
			rs2.close();

			table.setPrimaryKeyList(primaryKeyList);
			
			tableList.add(table);
		}
		rs.close();
		return tableList;
	}

	public Long selectTableCount(String tableNames) throws SQLException {
		String[] tableNameArray = tableNames.split(",");

		Long count = new Long("0");
		for (String s : tableNameArray) {
			if (s.contains("$") || StringUtils.isBlank(s)) {
				continue;
			}
			count++;
		}
		return count;
	}

	public List<Table> selectTableList(String tableNames) throws SQLException {
		List<Table> tableList = new ArrayList<Table>();

		String[] tableNameArray = tableNames.split(",");

		for (String s : tableNameArray) {
			if (s.contains("$") || StringUtils.isBlank(s)) {
				continue;
			}

			s = UtilsString.removeQuote(s).trim().toUpperCase();

			Table t = new Table();
			t.setTable_name(s);
			t.setJavaObjectCamelName(UtilsString.camelize(t.getTable_name()));
			
			logger.info("Get primary-key...");
			DatabaseMetaData metaData = super.getConnection(false).getMetaData();
			ResultSet rs2 = metaData.getPrimaryKeys(null, null, t.getTable_name());
			List<PrimaryKey> primaryKeyList = new ArrayList<PrimaryKey>();
			while (rs2.next()) {
				PrimaryKey pk = new PrimaryKey();
				pk.setPk_name(rs2.getString(4));
				logger.info("primary key : {}", pk.getPk_name());
				primaryKeyList.add(pk);
			}
			rs2.close();

			t.setPrimaryKeyList(primaryKeyList);
			
			tableList.add(t);
		}

		return tableList;
	}

}
