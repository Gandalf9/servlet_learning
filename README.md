# Learning about Servlets
Git project for learning about servlets

*To run this project in eclipse, import it as a Maven Project. Under [Properties] for the project, go to [Project Facets] and set this as a 3.1 [Dynamic Web Module] project and make sure the [Runtimes] tab has Tomcat 8 selected. Ensure [Java] is set to 1.8 in project facets. Under [Properties] for the project, check the [Deployment Assembly] and ensure that `src/main/webapp` has a deploy path of `/`*

* `0d4c00ee` - basic servlet project with a simple servlet configured in a `web.xml` file

* `e76048fb` - init parameters and servlet context parameters added to it

* `e8632ca7` - Annotations introduced for the servlet and init parameters

* `2f7b7fba` - Removed the web.xml file and introduced the spring `WebApplicationInitializer`

