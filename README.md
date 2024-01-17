⚠️ This repository will be archived in January 2024 as it is no longer in development. ⚠️

# dp-interactives-api-client-java
A Java Client Library for the dp-interactives-api (https://github.com/ONSdigital/dp-interactives-api)

## Usage
### Including in your project

Add this to your dependencies in pom.xml:
```xml
    <dependency>
	    <groupId>com.github.onsdigital</groupId>
	    <artifactId>dp-interactives-api-client-java</artifactId>
	    <version>Tag</version>
	</dependency>
```

### Usage in code
To instatiate the API client, two parameters are needed:
- the URL of the interactives API being used
- a (long-lived) authentication token used to authenticate your calls
- exposes 2 endpoints [see Client.java]
	- publishCollection
	- getInteractivesForCollection (given collection id)
	- getInteractive (given id)
  