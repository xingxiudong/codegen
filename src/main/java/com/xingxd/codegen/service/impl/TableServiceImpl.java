package com.xingxd.codegen.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xingxd.codegen.dao.ColumnDao;
import com.xingxd.codegen.dao.TableDao;
import com.xingxd.codegen.domain.Column;
import com.xingxd.codegen.domain.PrimaryKey;
import com.xingxd.codegen.domain.Table;
import com.xingxd.codegen.service.TableService;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.02
 */
@Service
public class TableServiceImpl extends BaseServiceImpl implements TableService {

	@Resource
	ColumnDao columnDao;

	@Resource
	TableDao tableDao;

	public List<Table> getAllTableList() throws SQLException {

		List<Table> tableList = tableDao.selectAllTableList();

		for (int i = 0; i < tableList.size(); i++) {
			Table t = tableList.get(i);
			String name = t.getTable_name();
			logger.info("Searching table[mode = all], {}.", name);

			List<Column> cList = columnDao.selectColumnList(name);

			// set primary-key
			for (Column col : cList) {
				for (PrimaryKey pk : t.getPrimaryKeyList()) {
					if (pk != null && pk.getPk_name().equalsIgnoreCase(col.getColumn_name())) {
						col.setIsPrimaryKey(true);
					}
				}
			}

			tableList.get(i).setColumnList(cList);
		}

		logger.info("Table List Size : {}", tableList.size());
		return tableList;
	}

	public List<Table> getTableList(String table_names) throws SQLException {
		List<Table> tableList = tableDao.selectTableList(table_names);

		for (Table t : tableList) {
			logger.info("Searching table[mode = given], {}.", t.getTable_name());

			List<Column> cList = columnDao.selectColumnList(t.getTable_name());

			// set primary-key
			for (Column col : cList) {
				for (PrimaryKey pk : t.getPrimaryKeyList()) {
					if (pk != null && pk.getPk_name().equalsIgnoreCase(col.getColumn_name())) {
						col.setIsPrimaryKey(true);
					}
				}
			}
			t.setColumnList(cList);
		}

		logger.info("Table List Size : {}", tableList.size());
		return tableList;
	}

	public Long getTableCount(String tableNames) throws SQLException {
		return tableDao.selectTableCount(tableNames);
	}

}
