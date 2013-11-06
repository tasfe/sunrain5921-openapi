package org.sunrain.openapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DefaultDateAdapter extends XmlAdapter<String, Date> {

	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Date unmarshal(String v) throws Exception {
		try {
			return getFormatter().parse(v);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String marshal(Date v) {
		return getFormatter().format(v);
	}
	
	protected DateFormat getFormatter() {
		return formatter;
	}
}
