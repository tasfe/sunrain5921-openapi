package org.sunrain.openapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DateAdapter extends DefaultDateAdapter {
	
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	protected DateFormat getFormatter() {
		return formatter;
	}
}
