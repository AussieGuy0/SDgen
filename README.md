# SDgen [![Build Status](https://travis-ci.org/AussieGuy0/SDgen.svg?branch=master)](https://travis-ci.org/AussieGuy0/SDgen)

A random data generator to produce realistic data files for multiple file types (e.g. csv, log, json)

## Installation
If you're using Maven, just add the following to your `pom.xml`
```xml
<dependency>
    <groupId>au.com.anthonybruno</groupId>
    <artifactId>SdGen</artifactId>
    <version>0.3.0</version>
</dependency>
```

Alternatively, grab the [latest jar](https://github.com/AussieGuy0/SDgen/releases/tag/0.1.0) and chuck it on your class path.

## Usage
There are two main ways to use SDGen to generate random data. The first way is
to use an annotated plain old Java object like so:

```java
Gen.create()
    .use(Person.class)
    .generate(2) //Number of rows that will be generated
    .asCsv()
    .toFile("output.csv");
```


Where the Person class looks like this:

```java
public class Person {

   @Generation(NameGenerator.class) //Generator is used to create values
   private final String name; 
   
   @Range(min=18, max=70) //Range is used to limit the range of values
   private final int age;
   
   public Person(String name, int age) {
        this.name = name; 
        this.age = age;
   }
   
}
```

Annotations allow us to control how random values are generated. 
Omitting Annotations would produce Strings like: `f9j)32`, and ints like: `-34093`

`output.csv` would look something like this:

```csv
name, age
Bob, 40
Ashley, 22
```

Here is the same example using a fluent builder:

```java
Gen.create()
    .addField("Name", new NameGenerator())
    .addField("Age", new IntGenerator(18, 70)
    .generate(2) //number of rows to generate
    .asCsv()
    .toFile("output.csv");
```

### Generator
Generators are simple classes that generate random data. To create custom generators,
you just have to implement the `Generator` interface.

As the `Generator` interface is a functional interface, you can use lambdas for easy
creation of custom generators. For example:

```java
Gen.create()
    .addField("Name", () -> getRandomName()) 
```

For convenience, SDgen provides basic generators for all primitive data types. 

### Settings
A Settings object can be provided to provide control over the format that the data is produced.

For example, it might want to use semicolons `;` as the delimiter instead of using commas. 
It's possible to do this by:
```java
CsvSettings settings = new CsvSettings.Builder().setDelimiter(';').build()
Gen.start()
     .addField("name", () -> getRandomName())
     .addField("age", new IntGenerator(18, 80))
     .generate(1000)
     .asCsv(settings) 
```

## Contributing
To contribute, please fork the project and submit a pull request. 
The project is backed by `maven` which handles dependency management and
the build process.

For any pull request to be accepted, it needs to have all tests passing (can run tests via `mvn test`).
In addition, more tests should be created to cover added code.

### Contributors List
- [Jo-Hunter](https://github.com/Jo-Hunter)
- [styler3](https://github.com/styler3)

## Acknowledgements
- [Travis CI](https://travis-ci.org) for continuous integration
