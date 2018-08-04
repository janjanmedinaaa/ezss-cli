# EzSS - CSS CLI Tool

EzSS is a java-based CLI tool in creating and compressing CSS Files. EzSS allows auto-generation of CSS Files based on HTML Classes and Ids using CSS Best Practices. EzSS also allows CSS Compression. 

## Requirements

You first need to install Java Runtime Environment and Java Virtual Machine on your device to run the JAR File.

## Installation
1. Download the ZIP file or clone using `git clone https://github.com/janjanmedinaaa/ezss-cli.git`
2. Unzip the file and import `ezss.jar` to your Project Directory

## Usage

``` 
java -jar ezss.jar [command] [...files] 
```

## Sample Generated CSS File 

```
/*
	!!!DON'T FORGET TO UPDATE THESE INFORMATION!!!
	Filename: index.html
	Author: Janjan Medina
	Author URI: https://github.com/janjanmedinaaa
	Project: JAVA\EzSS
*/

/***** General Styles *****/

body {
	margin: 0;
	padding: 0;
	width: 100%;
}

/* Small devices (tablets, 768px and up) */

@media (min-width: 768px) {

}

/* Small devices (desktops, 992px and up) */

@media (min-width: 992px) {

}

/* Small devices (large desktops, 1200px and up) */

@media (min-width: 1200px) {

}
```
## Commands

### Create

CSS File Generation
``` 
java -jar ezss.jar create index.html     //Single CSS file Generation 

java -jar ezss.jar create one.html two.html three.html     //Multiple CSS file Generation 

java -jar ezss.jar generate one.php     //Generate and Gen also works like Create
```

All Command
``` 
java -jar ezss.jar create all     //Generates CSS for all HTML files in the current directory
```

Custom name
``` 
java -jar ezss.jar create index.html custom-name.css     //Generates CSS File with custom name
```

### Compress

CSS Compression
``` 
java -jar ezss.jar compress styles.css     //Compress single CSS file

java -jar ezss.jar compress one.css two.css three.css     //Compress multiple CSS files

java -jar ezss.jar comp one.css     //Shortcuts also work
```

All Command
``` 
java -jar ezss.jar compress all     //Compress all CSS files in the current directory
```