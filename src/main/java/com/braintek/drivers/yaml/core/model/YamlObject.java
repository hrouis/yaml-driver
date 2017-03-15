package com.braintek.drivers.yaml.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YamlObject {

	List<YamlField> fields;

	public List<YamlField> getFields() {
		return fields;
	}

	public YamlObject() {
		fields = new ArrayList<>();
	}

	@Override
	public String toString() {
		return " Yaml Object : \n" + fields.stream().map(YamlField::toString).collect(Collectors.joining("\n"));

	}

}
