## Synopsis

A POC for an event based Yaml Parser written in **Java 8**.


## Code Example

YamlObject result = new YamlParser().parseYaml(getClass().getResourceAsStream("/infos.yaml"));


## Motivation

Just for fun.


## Tests

for the moment there is only one test that parses the file and prints the Yaml Object to the console.
com.braintek.drivers.yaml.parser.YamlparserTest

## TODO 

- ADD unit testing 
- Implements Object ot Yaml file deflator

