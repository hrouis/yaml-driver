package com.braintek.drivers.yaml.parser;

import java.util.ArrayList;
import java.util.List;

import com.braintek.drivers.yaml.core.EventType;
import com.braintek.drivers.yaml.core.YamlFieldMetaData;

/**
 * 
 * @author haythem
 *
 */
public final class Tokenizer {

	private int indentDepth = 0;

	public void init() {

		fieldsMetaData = new ArrayList<>();

		fieldsMetaData.add(new YamlFieldMetaData( EventType.DocumentStart, indentDepth, null));
	}

	/**
	 * 
	 */
	public void end() {
		closeMappingAndSequence();
		fieldsMetaData.add(new YamlFieldMetaData(EventType.DocumentEnd, indentDepth, null));
	}

	private List<YamlFieldMetaData> fieldsMetaData;

	private boolean isSequence = false;

	public boolean isSequence() {
		return isSequence;
	}

	public void setSequence(boolean isSequence) {
		this.isSequence = isSequence;
	}

	public  boolean isMapping() {
		return isMapping;
	}

	public  void setMapping(boolean isMapping) {
		this.isMapping = isMapping;
	}

	private  boolean isMapping = false;

	public  List<YamlFieldMetaData> getFieldsDescription() {
		return fieldsMetaData;
	}

	public  void setFieldsDescription(final List<YamlFieldMetaData> fieldsDescription) {
		this.fieldsMetaData = fieldsDescription;
	}



	/***
	 * 
	 * @param tempChar
	 */
	public  void parseToken(final String tempChar) {

		switch (tempChar) {

		case "\n":
			fieldsMetaData.add(constructYamlFieldFromEvent(EventType.LineFeed));
			break;

		case ":":
			fieldsMetaData.add(constructYamlFieldFromEvent(EventType.Colon));
			break;
		case "-":
			if (getHistoricalEvent(2).equals(EventType.Colon) && !isSequence) {
				fieldsMetaData.add(constructYamlFieldFromEvent(EventType.SequenceStart));
				isSequence = true;
			}

			fieldsMetaData.add(constructYamlFieldFromEvent(EventType.SequenceItem));

			break;

		case " ":
			if ((getHistoricalEvent(1).equals(EventType.LineFeed)
					|| getHistoricalEvent(1).equals(EventType.WhiteSpace)) && !isMapping) {
				fieldsMetaData.add(constructYamlFieldFromEvent(EventType.MappingStart));
				isMapping = true;
				indentDepth++;
			}
			fieldsMetaData.add(constructYamlFieldFromEvent(EventType.WhiteSpace));
			break;
		default:
			if (EventType.LineFeed.equals(getHistoricalEvent(1))) {
				closeMappingAndSequence();
			}
			fieldsMetaData.add(constructYamlField(EventType.Char, tempChar));
			break;
		}
	}

	/**
	 * 
	 */
	private  void closeMappingAndSequence() {
		if (isSequence) {
			isSequence = false;
			fieldsMetaData.add(constructYamlFieldFromEvent(EventType.SequenceEnd));
		} else if (isMapping) {
			isMapping = false;
			fieldsMetaData.add(constructYamlFieldFromEvent(EventType.MappingEnd));
			indentDepth--;
		}
	}

	/**
	 * 
	 * @return
	 */
	private  EventType getHistoricalEvent(int travel) {
		int index = (fieldsMetaData.size() -travel) > 0 ? fieldsMetaData.size() -travel: 0; 
		return fieldsMetaData.get(index ).getType();
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	private  YamlFieldMetaData constructYamlFieldFromEvent(EventType event) {

		return constructYamlField(event, null);
	}

	private  YamlFieldMetaData constructYamlField(EventType event, String charString) {
		return new YamlFieldMetaData(event, indentDepth, charString);
	}
}
