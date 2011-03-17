Maven Spring-REST-App-Archetype
===============================

Generate
--------

Create your copy with maven archetype generate:

    mvn archetype:generate -DarchetypeGroupId=com.philippkoelmel  -DarchetypeArtifactId=spring-rest-webapp-archetype  -DarchetypeVersion=0.0.1-SNAPSHOT  -DarchetypeRepository=https://github.com/Hechtenheld/maven-repo/raw/master/snapshots

Customize
---------

In order to setup your own project, you need to modify these files.

### src/main/webapp/WEB-INF/web.xml

The skeleton assumes that you use JNDI in production. There is a reference to a `jdbc/production` in this file. Modify it as you wish.

    <resource-ref>
    	<description>Production Database</description>
    	<res-ref-name>jdbc/production</res-ref-name>
    	<res-type>javax.sql.DataSource</res-type>
    	<res-auth>Container</res-auth>
    </resource-ref>

### src/main/resources/META-INF/spring-context/datasource.xml

This file defines the reference to the production database.

    <bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
    	<property name="jndiName" value="java:comp/env/jdbc/production" />
    </bean>
	
### src/test/resources/test-datasource.properties

Configures a JDBC datasource for testing that points to a HSQLDB and a database called `skeleton`. Find more options in `test-datasource.xml`. If you use another DBMS, please also change the dependencies to HSQL in the `pom.xml`.

    driverClass=org.hsqldb.jdbcDriver
    jdbcUrl=jdbc:hsqldb:mem:test
    user=sa
    password=
    databasePlatform=org.hibernate.dialect.HSQLDialect

### src/main/resources/META-INF/spring-context/application.xml

All context files are imported by this one. Except the datasource definition which is imported by the `dispatcher-servlet.xml` if run in a web container or `test-context.xml` if a test is executed. In this file just change the `base-package` attribute to your package name.

    <context:component-scan base-package="your.package.skeleton" />

### src/main/resources/META-INF/spring-context/dao.xml

The `entityManagerFactory` is defined in this file. You need to change the property `persistenceUnitName`. 

    <bean id="entityManagerFactory"
    	class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    	<property name="persistenceUnitName" value="skeletonPU" />
    	<property name="dataSource" ref="dataSource" />
    	<property name="jpaVendorAdapter" ref="jpaVendorAdapter">
    	</property>
    </bean>

### src/main/resources/META-INF/persistence.xml

Add you entity classes here. Maybe you also want to rename the PU by changing the attribute `name` of the `persistence-unit` tag.

    <persistence version="2.0"
    	xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
    	<persistence-unit name="skeletonPU">
    		<provider>org.hibernate.ejb.HibernatePersistence</provider>
    		<class>com.philippkoelmel.skeleton.skull.Skull</class>
    	</persistence-unit>
    </persistence>

### src/main/resources/META-INF/spring-context/datasource.xml

If you are not using MySQL for your production database, change [`databasePlatform`](http://docs.jboss.org/hibernate/core/3.5/api/org/hibernate/dialect/package-summary.html). 

    <bean id="jpaVendorAdapter"
    	class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
    	<property name="generateDdl" value="true" />
    	<property name="showSql" value="true" />
    	<property name="databasePlatform" value="org.hibernate.dialect.MySQL5Dialect" />
    </bean>

### pom.xml

You can do two things here:
1. If you change the DBMS for testing, be sure to change the HSQL dependency also
2. You can change the version via the properties defined

    <dependency>
    	<groupId>org.hsqldb</groupId>
    	<artifactId>hsqldb-j5</artifactId>
    	<version>${org.hsqldb.version}</version>
    </dependency>
	...
    <properties>
    	<org.springframework.version>3.0.5.RELEASE</org.springframework.version>
    	<org.hibernate.version>3.5.4-Final</org.hibernate.version>
    	<c3p0.version>0.9.1</c3p0.version>
    	<junit.version>4.8.2</junit.version>
    ...

Eclipse
-------

* [Eclipse Helios for Java EE Developer](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)
Via Help/Eclipse Marketplace:
* SpringSource Tool Suite
* Spring IDE
* Maven Integration for Eclipse

