<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.package_dao}.${table.javaObjectCamelName}Dao">
	<cache />

	<resultMap id="${table.javaObjectCamelName?uncap_first}Map" type="${table.javaObjectCamelName}">
		<#list table.columnList as col>
		<#if col.isPrimaryKey!false>
		<id column="${col.column_name}" property="${col.column_name?lower_case}" />
		<#else>
		<result column="${col.column_name}" property="${col.column_name?lower_case}" />
		</#if>
		</#list>
	</resultMap>

	<sql id="r_clause_gather_default">
		<!-- Equal query -->
		<#list table.columnList as col>
		<if test="${col.column_name?lower_case} != null and ${col.column_name?lower_case} != ''"> and ${col.column_name} = ${"#"}{${col.column_name?lower_case}}</if>
		</#list>

		<!-- Like query -->
		<#list table.columnList as col><#if col.jdbcTypeShortName == 'VARCHAR'>
		<if test="param.${col.column_name?lower_case}_like != null and param.${col.column_name?lower_case}_like != ''"> and UPPER(${col.column_name}) like CONCAT('%',UPPER(${"#"}{param.${col.column_name?lower_case}_like}),'%')</if>
		</#if></#list>
		
		<!-- Number compare query -->
		<#list table.columnList as col>
		<#if col.jdbcTypeShortName == 'DECIMAL'>
		<if test="param.${col.column_name?lower_case}_gt != null and param.${col.column_name?lower_case}_gt != ''"><![CDATA[ and ${col.column_name} > ${"#"}{param.${col.column_name?lower_case}_gt}]]></if>
		<if test="param.${col.column_name?lower_case}_lt != null and param.${col.column_name?lower_case}_lt != ''"><![CDATA[ and ${col.column_name} < ${"#"}{param.${col.column_name?lower_case}_lt}]]></if>
		<if test="param.${col.column_name?lower_case}_ge != null and param.${col.column_name?lower_case}_ge != ''"><![CDATA[ and ${col.column_name} >= ${"#"}{param.${col.column_name?lower_case}_ge}]]></if>
		<if test="param.${col.column_name?lower_case}_le != null and param.${col.column_name?lower_case}_le != ''"><![CDATA[ and ${col.column_name} <= ${"#"}{param.${col.column_name?lower_case}_le}]]></if>
		</#if>
		</#list>
		
		<!-- Date compare query -->
		<#list table.columnList as col>
		<#if col.jdbcTypeShortName == 'DATETIME' || col.jdbcTypeShortName == 'DATE' || col.jdbcTypeShortName == 'TIMESTAMP'>
		<if test="param.${col.column_name?lower_case}_ge != null and param.${col.column_name?lower_case}_ge != ''"><![CDATA[ and ${col.column_name} >= date_format (${"#"}{param.${col.column_name?lower_case}_ge}, '%Y-%m-%d %H:%i:%S')]]></if>
		<if test="param.${col.column_name?lower_case}_lt != null and param.${col.column_name?lower_case}_lt != ''"><![CDATA[ and ${col.column_name} < date_format (${"#"}{param.${col.column_name?lower_case}_lt}, '%Y-%m-%d %H:%i:%S')]]></if>
		<if test="param.${col.column_name?lower_case}_ge_now == 'true'"><![CDATA[ and ${col.column_name} >= date_format (now(), '%Y-%m-%d %H:%i:%S')]]></if>
		<if test="param.${col.column_name?lower_case}_lt_now == 'true'"><![CDATA[ and ${col.column_name} < date_format (now(), '%Y-%m-%d %H:%i:%S')]]></if>
		</#if>
		</#list>
	</sql>

	<sql id="ud_clause_gather_default">
		<choose>
			<when test="<#list table.primaryKeyList as pk><#if (pk_index > 0)> and </#if>${pk.pk_name?lower_case} != null</#list>"><#list table.primaryKeyList as pk><#if (pk_index > 0)> and </#if>${pk.pk_name?lower_case}=${"#"}{${pk.pk_name?lower_case}}</#list></when>
			<when test="<#list table.primaryKeyList as pk><#if (pk_index > 0)> or </#if>${pk.pk_name?lower_case} == null</#list>">
				<if test="pks != null">
					<#if (table.primaryKeyList?size == 1)><#list table.primaryKeyList as pk>${pk.pk_name} in <foreach item="item" index="index" collection="pks" open="(" separator="," close=")">${"#"}{item}</foreach></#list><#else>1=0</#if>
				</if> 
			</when>
			<otherwise>1=0</otherwise>
		</choose>
	</sql>

	<sql id="orderby_clause_gather_default">
		<if test="row.sort == null or row.sort == ''"> <#list table.columnList as col><#if (col_index == 0)>${col.column_name?upper_case}<#else><#break/></#if></#list> </if>
		<if test="row.sort != null and row.sort != ''">
			<choose>
				<#list table.columnList as col>
				<when test="row.sort == '${col.column_name?lower_case}'"> ${col.column_name?upper_case} </when>
				</#list>
				<otherwise> <#list table.columnList as col><#if (col_index == 0)>${col.column_name?upper_case}<#else><#break/></#if></#list> </otherwise>
			</choose>
		</if>
		<if test="row.order == null or row.order == ''"> ASC </if>
		<if test="row.order != null and row.order != ''">
			<choose>
				<when test="row.order == 'asc' or row.order == 'ASC'">ASC</when>
				<when test="row.order == 'desc' or row.order == 'DESC'">DESC</when>
				<otherwise> ASC </otherwise>
			</choose>
		</if>
	</sql>

	<select id="count" parameterType="${table.javaObjectCamelName}" resultType="long">
		select count(1) from ${table.table_name?upper_case}
		<where>
			<include refid="r_clause_gather_default"/>
		</where>
	</select>

	<select id="select" parameterType="${table.javaObjectCamelName}" resultMap="${table.javaObjectCamelName?uncap_first}Map">
		select * from ${table.table_name?upper_case}
		<where>
			<include refid="r_clause_gather_default"/>
		</where>
	</select>

	<select id="selectList" parameterType="${table.javaObjectCamelName}" resultMap="${table.javaObjectCamelName?uncap_first}Map">
		select * from ${table.table_name?upper_case}
		<where>
			<include refid="r_clause_gather_default"/>
		</where>
		order by
		<include refid="orderby_clause_gather_default"/>
	</select>

	<select id="selectPaginatedList" parameterType="${table.javaObjectCamelName}" resultMap="${table.javaObjectCamelName?uncap_first}Map">
		select * from ${table.table_name?upper_case}
		<where>
			<include refid="r_clause_gather_default"/>
		</where>
		order by
		<include refid="orderby_clause_gather_default"/>
		limit ${"#"}{row.first}, ${"#"}{row.count}
	</select>

	<insert id="insert" parameterType="${table.javaObjectCamelName}">
		<![CDATA[insert into ${table.table_name?upper_case} ( ]]>
		<trim suffixOverrides=","> 
			<#list table.columnList as col>
			<if test="${col.column_name?lower_case} != null and ${col.column_name?lower_case} != ''">${col.column_name},</if>
			</#list>
		</trim>
		<![CDATA[ ) values ( ]]>
		<trim suffixOverrides=",">
			<#list table.columnList as col>
			<if test="${col.column_name?lower_case} != null and ${col.column_name?lower_case} != ''">${"#"}{${col.column_name?lower_case}},</if>
			</#list>
		</trim>
		<![CDATA[ ) ]]>
	</insert>

	<update id="update" parameterType="${table.javaObjectCamelName}">
		update ${table.table_name?upper_case}
		<set>
			<#list table.columnList as col>
			<if test="${col.column_name?lower_case} != null and ${col.column_name?lower_case} != ''">${col.column_name} = ${"#"}{${col.column_name?lower_case}},</if>
			</#list>
		</set>
		<where>
			<include refid="ud_clause_gather_default"/>
		</where>
	</update>
	
	<delete id="delete" parameterType="${table.javaObjectCamelName}">
		delete from ${table.table_name?upper_case}
		<where>
			<include refid="ud_clause_gather_default"/>
		</where>
	</delete>

</mapper>