package ${config.package_prefix}.dao.mybatis;

import org.springframework.stereotype.Repository;

import ${config.package_prefix}.dao.${table.javaObjectCamelName}Dao;
import ${config.package_prefix}.domain.${table.javaObjectCamelName};
import com.ebiz.ssi2.dao.mybitis.EntityMybatisDaoImpl;

/**
 * This class is pruduct by Coder-AutoGenerator.
 *
 * @author SSI2Generator
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Repository
public class ${table.javaObjectCamelName}DaoSqlMapImpl extends EntityMybatisDaoImpl<${table.javaObjectCamelName}> implements ${table.javaObjectCamelName}Dao {

}
