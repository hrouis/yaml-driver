package com.braintek.drivers.yaml.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.braintek.drivers.yaml.core.model.YamlObject;

/**
 * 
 * @author Haythem
 *
 */
public class YamlParser {

	/**
	 * 
	 * @param filePath
	 */
	public YamlObject parseYaml(final InputStream  stream) {

		try  (BufferedReader bReader = new BufferedReader(new InputStreamReader(stream))){
			Tokenizer  tokenizer = new Tokenizer();
			Inflator inflator = new Inflator();
			
			tokenizer.init();
			//Get InputStream content as string 		
			String content = bReader.lines().collect(Collectors.joining("\n"));
			
			//Stream chars to be consumed by tokenizer
			content.chars().mapToObj(c -> String.valueOf((char) c)).forEach(tokenizer::parseToken);
			tokenizer.end();
			
			//tokenizer.getFieldsDescription().stream().forEach(System.out::println);
			//inflate objects from the meta data fields
			tokenizer.getFieldsDescription().stream().forEach(inflator::inflateYamlObject);
			return inflator.getResult();
			
		} catch (IOException e) {
			// TODO Add Logger 
			e.printStackTrace();
		}
		return null;
	}

}
