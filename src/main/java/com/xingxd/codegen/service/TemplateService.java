package com.xingxd.codegen.service;

import java.util.Map;

/**
 * @author Jin, QingHua
 */
public interface TemplateService {

	String getContent(String templateName, Map<String, Object> model);

}
