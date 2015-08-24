package com.xingxd.codegen.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xingxd.codegen.service.Facade;
import com.xingxd.codegen.service.TableService;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.02
 */
@Service("facade")
public class FacadeImpl implements Facade {

	@Resource
	TableService tableService;

	public TableService getTableService() {
		return tableService;
	}

}
