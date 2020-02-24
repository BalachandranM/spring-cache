# spring-cache-api
*This project is all about getting started with Redis Cache
*Added a glimpse of RestControllerAdvice
*Added a glimpse of Exception handler
*Added a glimpse of H2 embedded database

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId></groupId>
	<artifactId></artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name></name>
	<description>Spring Boot Micro Service</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.10.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
		<dep.scope>compile</dep.scope>
		<springfox-version>2.5.0</springfox-version>
		<aspectj.version>1.8.10</aspectj.version>
		<springdatajpa.version>1.10.5.RELEASE</springdatajpa.version>
		<org.hibernate-em-version>4.3.8.Final</org.hibernate-em-version>
		<jackson.version>2.8.4</jackson.version>
		<ojdbc.version>12.1.0.1</ojdbc.version>
		<config.version>1.4.2.RELEASE</config.version>
		<admin.version>1.5.4</admin.version>
		<consul-starter.version>1.3.0.RELEASE</consul-starter.version>
		<cloud-consul.version>1.3.0.RELEASE</cloud-consul.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.apache.tomcat</groupId>
					<artifactId>tomcat-jdbc</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- 
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
			<type>jar</type>
		</dependency>
		 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.json</groupId>
			<artifactId>json</artifactId>
			<version>20160810</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>${jackson.version}</version>
			<scope>${dep.scope}</scope>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>${springfox-version}</version>
			<scope>${dep.scope}</scope>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>${springfox-version}</version>
			<scope>${dep.scope}</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>		
		<!-- Oracle JDBC driver -->
		<dependency>
			<groupId>com.oracle.jdbc</groupId>
			<artifactId>ojdbc7</artifactId>
			<version>${ojdbc.version}</version>
			<scope>${dep.scope}</scope>
		</dependency>		
		<!-- Power mock -->
		<dependency>
			<groupId>org.powermock</groupId>
			<artifactId>powermock-module-junit4</artifactId>
			<version>1.6.6</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.powermock</groupId>
			<artifactId>powermock-api-mockito</artifactId>
			<version>1.6.6</version>
			<scope>test</scope>
		</dependency>		

		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>		
		<!-- Spring config server - external properties -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-client</artifactId>
			<version>${config.version}</version>
		</dependency>		
		<!-- Spring boot admin client - monitoring the purpose -->
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-client</artifactId>
			<version>${admin.version}</version>
		</dependency>		
		<!-- Spring Cloud Stream Rabbit -->
		<!-- <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-stream-rabbit</artifactId>
			<version>${starter-rabbit.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-stream-binder-rabbit</artifactId>
			<version>${binder-rabbit.version}</version>
		</dependency>  -->		
		<!-- Consul properties -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-consul-all</artifactId>
			<version>${consul-starter.version}</version>
		</dependency>
		<!--Spring Cloud Consul Dependency -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-consul-dependencies</artifactId>
			<version>${cloud-consul.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-oxm</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web-services</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.ws</groupId>
			<artifactId>spring-ws-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.ws</groupId>
			<artifactId>spring-ws-support</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient</artifactId>
			<version>4.5.2</version>
		</dependency>
		<dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-mail</artifactId> 
            <version>2.0.1.RELEASE</version> 
        </dependency>
        <dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.13</version>
		</dependency>

	</dependencies>	
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
