package com.xingxd.codegen.generator.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.xingxd.codegen.domain.Column;
import com.xingxd.codegen.domain.Table;
import com.xingxd.codegen.internal.Constants;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.04
 */
@Controller
public class IbatisSqlMapXmlGenerator extends AbstractGenerator {

	public void generate() throws SQLException {
		for (Table t : tableFactory.getTableList()) {
			for (Column c : t.getColumnList()) {
				if ("DATE".equalsIgnoreCase(c.getJdbcTypeShortName())) {
					c.setJdbcTypeShortName("DATETIME");
				}
			}

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("table", t);
			model.put("config", config);
			model.put("now", new Date());

			String content = super.getTemplateService().getContent("daoImplSqlMap.ftl", model);

			// write to file
			StringBuilder nameBuilder = new StringBuilder();
			if ("ssi2".equalsIgnoreCase(config.getFrame())) {
				nameBuilder.append(t.getJavaObjectCamelName()).append("Mapper").append(Constants.EXTEND_XML);
			} else {
				nameBuilder.append(t.getTable_name()).append(FILE_SUFFIX_IBATIS_SQLMAP_XML).append(Constants.EXTEND_XML);
			}

			StringBuilder pathBuilder = new StringBuilder();
			pathBuilder.append(config.getRealpath_dao_impl_maps()).append(File.separator).append(nameBuilder);

			logger.info(internal.getString("log.generator.run.file", nameBuilder.toString(), pathBuilder.toString()));

			super.writeToFile(pathBuilder.toString(), content);

		}
	}

}
