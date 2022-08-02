package com.bc.promote.common.base.session.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TokenModel implements Serializable {

	private String token;

	private Map<String, Object> properties = new HashMap<String, Object>();

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	public void removeProperty(String key) {
		properties.remove(key);
	}

	public Object getProperty(String key) {
		return properties.get(key);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenModel [properties=" + properties + ", token=" + token
				+ "]";
	}

}
