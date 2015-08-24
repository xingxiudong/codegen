package ${config.package_prefix}.service;

import java.util.List;

import ${config.package_prefix}.domain.${table.javaObjectCamelName};

/**
 * This class is pruduct by Coder-AutoGenerator.
 *
 * @author SSI2Generator
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface ${table.javaObjectCamelName}Service {

	Long create${table.javaObjectCamelName}(${table.javaObjectCamelName} entity);

	int modify${table.javaObjectCamelName}(${table.javaObjectCamelName} entity);

	int remove${table.javaObjectCamelName}(${table.javaObjectCamelName} entity);

	${table.javaObjectCamelName} get${table.javaObjectCamelName}(${table.javaObjectCamelName} entity);

	List<${table.javaObjectCamelName}> get${table.javaObjectCamelName}List(${table.javaObjectCamelName} entity);

	Long get${table.javaObjectCamelName}Count(${table.javaObjectCamelName} entity);

	List<${table.javaObjectCamelName}> get${table.javaObjectCamelName}PaginatedList(${table.javaObjectCamelName} entity);

}