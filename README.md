# SEARCH-800 Testing environment

This project includes a default configuration to test [SEARCH-800](https://issues.alfresco.com/jira/browse/SEARCH-800) ticket.

In `docker` folder, a Docker Compose template based in ACS 6.2.2 is patched with a modified `alfresco-repository-8.83.jar`.

Configuration has been set to use only TRANSACTIONAL query consistency:
```
-Dsolr.query.fts.queryConsistency=TRANSACTIONAL
```

Additionally a sample project named `search-800-1.0.jar` is deployed in order to test the operation invoking the following Web Script:

http://127.0.0.1:8080/alfresco/s/sample/helloworld

* When using provided `alfresco-repository-8.83.jar` within this project, the result is provided in the browser following this format:
```
{47dd0ef6-84e8-4b27-a261-d6334af327c5=Space Templates, 3c2a9a5f-51bb-4a44-91ec-3a41ddac0e75=Published, 6cf597c1-bb7d-4d9c-bf0a-5c289516d746=Software Engineering Project, ...}
```
* When using a released `alfresco-repository-8.83.jar`, an Exception is recovered when invoking this URL

## Using Alfresco 5.2

Test project for Alfresco 5.2 is provided in folder [search-800-52](search-800-52).

Just need to build the project and deploy the JAR file (search-800-52-1.0-SNAPSHOT.jar) to `tomcat/webapps/alfresco/WEB-INF/lib` folder.
