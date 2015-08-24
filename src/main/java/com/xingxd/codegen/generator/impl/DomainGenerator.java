package com.xingxd.codegen.generator.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.xingxd.codegen.domain.Column;
import com.xingxd.codegen.domain.Table;
import com.xingxd.codegen.internal.Constants;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.04
 */
@Controller
public class DomainGenerator extends AbstractGenerator {

	public void generate() throws SQLException {

		for (Table t : tableFactory.getTableList()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("table", t);
			model.put("config", config);
			model.put("now", new Date());

			// import package
			Set<String> packageImportList = new HashSet<String>();
			for (Column c : t.getColumnList()) {
				String type = c.getJavaTypeFullName();
				if (!StringUtils.startsWith(type, "java.lang.")) {
					packageImportList.add(type);
				}
			}
			Arrays.sort(packageImportList.toArray());
			model.put("packageImportList", packageImportList);

			String content = super.getTemplateService().getContent("domain.ftl", model);

			// write to file
			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append(t.getJavaObjectCamelName()).append(Constants.EXTEND_JAVA);

			StringBuilder pathBuilder = new StringBuilder();
			pathBuilder.append(config.getRealpath_domain()).append(File.separator).append(nameBuilder);

			logger.info(internal.getString("log.generator.run.file", nameBuilder.toString(), pathBuilder.toString()));

			super.writeToFile(pathBuilder.toString(), content);

		}
	}

}
