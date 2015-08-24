<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">

<sqlMapConfig>
	<settings cacheModelsEnabled="false" enhancementEnabled="true" lazyLoadingEnabled="true"
		useStatementNamespaces="false" statementCachingEnabled="true" classInfoCacheEnabled="true" />

	<typeHandler jdbcType="BLOB" javaType="[B"
		callback="org.springframework.orm.ibatis.support.BlobByteArrayTypeHandler" />
	<typeHandler jdbcType="CLOB" javaType="java.lang.String"
		callback="org.springframework.orm.ibatis.support.ClobStringTypeHandler" />
		
	<settings>
		<setting name="cacheEnabled" value="true" />
		<setting name="lazyLoadingEnabled" value="true" />
		<setting name="multipleResultSetsEnabled" value="true" />
		<setting name="useColumnLabel" value="true" />
		<setting name="useGeneratedKeys" value="false" />
		<setting name="defaultExecutorType" value="SIMPLE" />
		<setting name="defaultStatementTimeout" value="100" />
		<setting name="safeRowBoundsEnabled" value="false" />
		<setting name="mapUnderscoreToCamelCase" value="false" />
		<setting name="localCacheScope" value="SESSION" />
		<setting name="jdbcTypeForNull" value="OTHER" />
		<setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString" />
	</settings>

	<typeAliases>
		<#list tableList as table>
		<typeAlias alias="${table.javaObjectCamelName}" type="${config.package_domain}.${table.javaObjectCamelName}" />
		</#list>
	</typeAliases>
</sqlMapConfig>