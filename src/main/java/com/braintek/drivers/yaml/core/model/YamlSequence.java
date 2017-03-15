package com.braintek.drivers.yaml.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author haythem
 * note : only simple sequence is supported by this parser
 */
public class YamlSequence  implements YamlField{
	
	private String key;
	private List<String> values = new ArrayList<>();
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		return "[ Yaml Sequene field { key : " + key + ", values: ( " + values.stream().collect(Collectors.joining(", ")) + " ) } ]";
	}

}
