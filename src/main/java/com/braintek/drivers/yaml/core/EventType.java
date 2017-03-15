package com.braintek.drivers.yaml.core;

/**
 * Yaml parsing events enum type
 * 
 * @author haythem
 *
 */
public enum EventType {

	DocumentEnd, //
	DocumentStart, //
	MappingEnd, //
	MappingStart, //
	Char, //
	SequenceEnd, //
	SequenceStart, //
	LineFeed, //
	Colon, //
	SequenceItem, //
	WhiteSpace //
}
