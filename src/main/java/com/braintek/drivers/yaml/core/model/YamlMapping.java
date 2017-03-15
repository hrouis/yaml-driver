package com.braintek.drivers.yaml.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YamlMapping implements YamlField {
	
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<YamlField> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<YamlField> attributes) {
		this.attributes = attributes;
	}

	private List<YamlField> attributes = new ArrayList<>();

	@Override
	public String toString() {
		return "[ Yaml Mapping Field { key : " + key + " , value : " + attributes.stream().map(YamlField::toString).collect(Collectors.joining("\n")) //
				
				+ "} ]";

	}
	
	

}
