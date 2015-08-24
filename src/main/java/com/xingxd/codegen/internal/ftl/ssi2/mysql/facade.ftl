package ${config.package_prefix}.service;

import com.ebiz.ssi2.service.BaseFacade;

/**
 * This class is pruduct by Coder-AutoGenerator.
 *
 * @author SSI2Generator
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface Facade extends BaseFacade {

	<#list tableList as table>
	${table.javaObjectCamelName}Service get${table.javaObjectCamelName}Service();
	
	</#list>
}