package com.xingxd.codegen.generator.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.xingxd.codegen.domain.Table;
import com.xingxd.codegen.internal.config.Configuration;
import com.xingxd.codegen.service.Facade;
import com.xingxd.codegen.utils.UtilsString;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.08
 */
@Controller("databaseTableFactory")
public class DatabaseTableFactory {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected Configuration config;

	@Resource
	private Facade facade;

	public List<Table> tableList;

	@PostConstruct
	protected void init() throws SQLException {
		logger.info("Internal Resource Initialising starting...");
		List<Table> tableList = new ArrayList<Table>();
		if (isGenerateAllTables()) {
			tableList = facade.getTableService().getAllTableList();
		} else {
			tableList = facade.getTableService().getTableList(config.getTable_names());
		}
		this.tableList = tableList;
		logger.info("Internal Resource Initialising ended.");
	}

	protected Boolean isGenerateAllTables() {
		String tablenames = UtilsString.removeQuote(config.getTable_names()).trim();
		return StringUtils.isBlank(tablenames) || StringUtils.equalsIgnoreCase(tablenames, "all");
	}

	public List<Table> getTableList() {
		return tableList;
	}

}
