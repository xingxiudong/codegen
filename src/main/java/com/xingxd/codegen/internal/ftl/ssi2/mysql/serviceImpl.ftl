package ${config.package_prefix}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${config.package_prefix}.dao.${table.javaObjectCamelName}Dao;
import ${config.package_prefix}.domain.${table.javaObjectCamelName};
import ${config.package_prefix}.service.${table.javaObjectCamelName}Service;

/**
 * This class is pruduct by Coder-AutoGenerator.
 *
 * @author SSI2Generator
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Service
public class ${table.javaObjectCamelName}ServiceImpl implements ${table.javaObjectCamelName}Service {

	@Resource
	private ${table.javaObjectCamelName}Dao ${table.javaObjectCamelName?uncap_first}Dao;

	public Long create${table.javaObjectCamelName}(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.insert(entity);
	}

	public int modify${table.javaObjectCamelName}(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.update(entity);
	}

	public int remove${table.javaObjectCamelName}(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.delete(entity);
	}

	public ${table.javaObjectCamelName} get${table.javaObjectCamelName}(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.select(entity);
	}

	public List<${table.javaObjectCamelName}> get${table.javaObjectCamelName}List(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.selectList(entity);
	}

	public Long get${table.javaObjectCamelName}Count(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.count(entity);
	}

	public List<${table.javaObjectCamelName}> get${table.javaObjectCamelName}PaginatedList(${table.javaObjectCamelName} entity) {
		return this.${table.javaObjectCamelName?uncap_first}Dao.selectPaginatedList(entity);
	}

}
