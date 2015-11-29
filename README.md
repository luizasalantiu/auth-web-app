##### Intro

Simple Java web application with basic authentication and 3 private pages accessible by users with appropriate roles. Uses Front controller with command pattern.
Implements basic authentication over SSL, with password hashing done with [bcrypt](http://www.mindrot.org/projects/jBCrypt/).

##### Test credentials

(*username*, *password*) <br />

- *username_pg_1*, *password_pg_1* -> user with access to page 1 <br />
- *username_pg_2*, *password_pg_2* -> user with access to page 2 <br />
- *username_pg_3*, *password_pg_3* -> user with access to page 3 <br />

##### Useful links:

- [Setting up SSL in Tomcat](http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html#Configuration). Since I only needed to run this locally, ended up following [these instructions](https://dzone.com/articles/setting-ssl-tomcat-5-minutes) (no CSR involved).

- [Overview of Java servlets and application life cycle](http://stackoverflow.com/a/3106909/2575683)

##### Todo

- Anti-CSRF protection
- Use better servlet url mapping, after finding this: [Understanding url pattern in servlet mapping](http://stackoverflow.com/a/4140659/2575683)

<br />
Java 1.8.0; JUnit 4, Mockito 1.9.5; Eclipse Mars.1; Tomcat 7.0