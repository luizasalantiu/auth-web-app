##### Intro

Simple Java web application with basic authentication over SSL and private pages accessible by users with appropriate roles. The solution uses Front controller with command pattern (over-engineered for exercise purposes).

##### Test credentials

(*username*, *password*) <br />

- *username_pg_1*, *password_pg_1* -> user with access to page 1 <br />
- *username_pg_2*, *password_pg_2* -> user with access to page 2 <br />
- *username_pg_3*, *password_pg_3* -> user with access to page 3 <br />

##### Useful links:

- [Setting up SSL in Tomcat](http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html#Configuration). Since this only needed to run locally,
[these instructions](https://dzone.com/articles/setting-ssl-tomcat-5-minutes) were followed (no CSR involved)

- [Overview of Java servlets and application life cycle](http://stackoverflow.com/a/3106909/2575683)

- [jBCrypt](http://www.mindrot.org/projects/jBCrypt/) used for password hashing

##### Todo

- Anti-CSRF protection
- Use better servlet url mapping based on this [understanding url pattern in servlet mapping](http://stackoverflow.com/a/4140659/2575683)

<br />
Java 1.8.0; JUnit 4, Mockito 1.9.5; Eclipse Mars.1; Tomcat 7.0