package com.xingxd.codegen.domain;

import java.io.Serializable;

/**
 * 获取可在指定类别中使用的表列的描述。 仅返回与类别、模式、表和列名称标准匹配的列描述。它们根据 TABLE_CAT、TABLE_SCHEM、TABLE_NAME 和 ORDINAL_POSITION 进行排序。
 * 
 * @author Xing,XiuDong
 * @version builder 2010.02.02
 */
public class PrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pk_type;

	private String pk_name;

	public String getPk_type() {
		return pk_type;
	}

	public void setPk_type(String pk_type) {
		this.pk_type = pk_type;
	}

	public String getPk_name() {
		return pk_name;
	}

	public void setPk_name(String pk_name) {
		this.pk_name = pk_name;
	}
	
}
