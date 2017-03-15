package com.braintek.drivers.yaml.core;

/**
 * Yaml field description in the char stream, object containing indent depth,
 * event and position of char
 * 
 * @author haythem
 *
 */
public class YamlFieldMetaData {

	/**
	 * 
	 * @param position
	 * @param type
	 * @param indentLevel
	 */
	public YamlFieldMetaData( EventType type, int indentLevel, String value) {
		this.type = type;
		this.indentDepth = indentLevel;
		this.value = value;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	private EventType type;
	private int indentDepth;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIndentDepth() {
		return indentDepth;
	}

	public void setIndentDepth(int indentDepth) {
		this.indentDepth = indentDepth;
	}

	@Override
	public String toString() {

		return "[ YamlFieldMetaData : { type : " + type + " , indentDepth: "
				+ indentDepth + " , value : " + value + " }]";
	}

}
