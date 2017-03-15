package com.braintek.drivers.yaml.core.model;
/**
 * 
 * @author Haythem
 *
 */
public class YamlScalar  implements YamlField {
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[ Yaml Scalar Field { key : " + key + " , value : " + value + "} ]";
	}

}
