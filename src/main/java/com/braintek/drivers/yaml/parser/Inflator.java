package com.braintek.drivers.yaml.parser;

import com.braintek.drivers.yaml.core.YamlFieldMetaData;
import com.braintek.drivers.yaml.core.model.YamlMapping;
import com.braintek.drivers.yaml.core.model.YamlObject;
import com.braintek.drivers.yaml.core.model.YamlScalar;
import com.braintek.drivers.yaml.core.model.YamlSequence;

/**
 * inflate Yaml object from MetaData
 * 
 * @author haythem
 *
 */
public class Inflator {

	private String tempKey;
	private YamlMapping mapping;
	private YamlSequence sequence;
	private boolean isSequence = false;
	YamlObject result = new YamlObject();

	public YamlObject getResult() {
		return result;
	}


	private StringBuffer buffer = new StringBuffer();

	public void inflateYamlObject(final YamlFieldMetaData fieldDescription) {
		switch (fieldDescription.getType()) {

		case Char:
			buffer.append(fieldDescription.getValue());
			break;
		case Colon:
			tempKey = buffer.toString();
			buffer.setLength(0);
			break;
		case DocumentEnd:
			break;
		case DocumentStart:
			break;
		case LineFeed:
			if (buffer.length() != 0 && !isSequence) {
				YamlScalar field = new YamlScalar();
				field.setKey(tempKey);
				field.setValue(buffer.toString());
				if (fieldDescription.getIndentDepth() > 0) {
					mapping.getAttributes().add(field);
				} else {
					result.getFields().add(field);
				}
				buffer.setLength(0);
			}
			break;
		case MappingEnd:
			result.getFields().add(mapping);
			break;
		case MappingStart:
			mapping = new YamlMapping();
			mapping.setKey(tempKey);
			break;
		case SequenceEnd:
			if (isSequence) {
				sequence.getValues().add(buffer.toString());
				buffer.setLength(0);
			}
			result.getFields().add(sequence);
			isSequence = false;
			break;
		case SequenceItem:
			if (buffer.length() != 0) {
				sequence.getValues().add(buffer.toString());
				buffer.setLength(0);
			}
			break;
		case SequenceStart:
			sequence = new YamlSequence();
			sequence.setKey(tempKey);
			isSequence = true;
			break;
		case WhiteSpace:
			if (buffer.length() > 0) {
				buffer.append(" ");
			}
			break;
		default:
			break;
		}
	}

}
