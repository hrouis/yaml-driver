package com.braintek.drivers.yaml.parser;

import org.junit.Test;

import com.braintek.drivers.yaml.core.model.YamlObject;
import com.braintek.drivers.yaml.parser.YamlParser;

/**
 * 
 * @author haythem
 *
 */
public class YamlParserTest {
	
	@Test
	public void test() {
		
		YamlObject result = new YamlParser().parseYaml(getClass().getResourceAsStream("/infos.yaml"));
		System.out.println(result);
		
	}
	

}
