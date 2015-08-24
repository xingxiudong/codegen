package com.xingxd.codegen.generator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xingxd.codegen.generator.impl.DaoGenerator;
import com.xingxd.codegen.generator.impl.DaoImplGenerator;
import com.xingxd.codegen.generator.impl.DomainGenerator;
import com.xingxd.codegen.generator.impl.FacadeGenerator;
import com.xingxd.codegen.generator.impl.FacadeImplGenerator;
import com.xingxd.codegen.generator.impl.IbatisSqlMapConfigXmlGenerator;
import com.xingxd.codegen.generator.impl.IbatisSqlMapXmlGenerator;
import com.xingxd.codegen.generator.impl.ServiceGenerator;
import com.xingxd.codegen.generator.impl.ServiceImplGenerator;

/**
 * @author Xing,XiuDong
 * @version builder 2010.02.03
 */
@Service("generatorFacatory")
public class GeneratorFacatory {
	@Resource
	private DomainGenerator domainGenerator;

	@Resource
	private DaoGenerator daoGenerator;

	@Resource
	private DaoImplGenerator daoImplGenerator;

	@Resource
	private ServiceGenerator serviceGenerator;

	@Resource
	private ServiceImplGenerator serviceImplGenerator;

	@Resource
	private IbatisSqlMapXmlGenerator ibatisSqlMapXmlGenerator;

	@Resource
	private IbatisSqlMapConfigXmlGenerator ibatisSqlMapConfigXmlGenerator;

	@Resource
	private FacadeGenerator facadeGenerator;

	@Resource
	private FacadeImplGenerator facadeImplGenerator;

	public DomainGenerator getDomainGenerator() {
		return domainGenerator;
	}

	public DaoGenerator getDaoGenerator() {
		return daoGenerator;
	}

	public DaoImplGenerator getDaoImplGenerator() {
		return daoImplGenerator;
	}

	public ServiceGenerator getServiceGenerator() {
		return serviceGenerator;
	}

	public ServiceImplGenerator getServiceImplGenerator() {
		return serviceImplGenerator;
	}

	public IbatisSqlMapXmlGenerator getIbatisSqlMapXmlGenerator() {
		return ibatisSqlMapXmlGenerator;
	}

	public IbatisSqlMapConfigXmlGenerator getIbatisSqlMapConfigXmlGenerator() {
		return ibatisSqlMapConfigXmlGenerator;
	}

	public FacadeGenerator getFacadeGenerator() {
		return facadeGenerator;
	}

	public FacadeImplGenerator getFacadeImplGenerator() {
		return facadeImplGenerator;
	}

}
