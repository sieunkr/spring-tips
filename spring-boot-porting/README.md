브런치에는.. 정상적으로 서비스 전환 이후에 
글 깔끔하게 정리해서 발행할 예정


# 스프링 부트 전환하기
레거시 스프링 프로젝트를, 스프링 부트로 전환하는 과정을 간단하게 정리한다. 사실 아직 진행하는 중이다. 부트 전환 경험자가 있다면 좋은 의견을 알려줬으면 좋겠다. 또한, 회사 소스는 보안상 외부에 글로 남길수가 없다. 아래 코드는 회사소스랑 상관 없이 필자가 따로 작성한 코드이다. 

#### 참고 레퍼런스
[https://d2.naver.com/helloworld/5626759](https://d2.naver.com/helloworld/5626759)
[https://www.slipp.net/wiki/pages/viewpage.action?pageId=25527284](https://www.slipp.net/wiki/pages/viewpage.action?pageId=25527284)


## 준비단계. 스프링 Bean, DI, 스프링 부트 이해
작업을 진행하기 전에 스프링 Bean의 개념을 이해 해야 한다. Bean 설정 방법과 Bean의 라이프사이클 등 기본 개념을 반드시 알고 있어야 한다. 

#### XML Bean 설정 방법
생략

#### Java - @Bean 어노테이션
생략

#### Java - @Component
생략

#### 스프링 DI 기본 개념
생략

그리고, 스프링 부트에 대해서 기본적으로 알고 있어야 한다. 

#### @SpringBootApplication 어노테이션이란?
생략

#### Autoconfiguration
생략

## 1단계. 최소한의 작업으로 일단 스프링 부트로 실행
심플한 프로젝트라면 한번에 전환해도 상관없다. 하지만, 스프링 부트로의 전환으로 예상하지 못한 장애가 발생할 수 있다. 이 글을 쓰고 있는 필자는 소심하고, 장애가 너무 싫다. 그래서, 최소한의 작업으로 일단 부트로 실행하는 것을 목표로 하자. 

#### 스프링 부트 디펜던시 추가 및 기존 디펜던시 제외 
아마도 레거시 프로젝트는 대부분 Maven 환경일 것이다. 당장, Gradle 로 변환하고 싶지만 Gradle 변환은 나중에 진행하자. pom.xml 에 스프링부트 디펜던시를 추가하자. 

````xml
<parent>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.2.RELEASE</version>
</parent>
생략..
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
생략..
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
	
````

스프링 부트 스타터 를 추가했으니, 스프링 관련 기존 설정을 모두 제거시키자. 필자는 아래와 같은 설정을 모두 제외시켰다.
 - org.springframework:spring-context
 - org.springframework:spring-webmvc

예를 들어서 아래와 같은 webmvc 설정이 있다고 가정하면... 지워버리면 된다. 
````
<!--지우자-->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-webmvc</artifactId>
</dependency>
````

등 스프링 관련 설정을 제외시켜준다.  사실, 제거를 안해도 오류가 발생하지는 않지만 지워주는것이 운영 상 좋을 듯 하다. 

> 스프링 부트는 디펜던시를 자동으로 추가해준다. 그러므로, 기존의 설정은 깔끔하게 지워버리자.


#### spring-boot-starter-parent 추가로 인한 불필요한 설정 제거

만약 기존 설정에 아래와 같이 설정되어 있다면, 지워도 된다.
````xml
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
````

아래 링크를 참고하자.
https://github.com/spring-projects/spring-boot/blob/v1.5.14.RELEASE/spring-boot-starters/spring-boot-starter-parent/pom.xml#L22


spring-boot-starter-parent 에 이미 선언이 되어있기 때문에 굳이 명시적으로 설정하지 않아도 된다. 물론, 명시적으로 써주는 것이 프로젝트 운영상 좋을 수는 있지만...어쩃든 spring-boot-starter-parent 에 설정된 정보를 미리 이해는 해야 한다. 

#### Jackson 라이브러리 업그레이드
아마도 대부분의 프로젝트에서 jackson 라이브러리를 사용하고 있었을 것이다. 사용하지 않는다면 작업할 필요는 없다. pom.xml 에서 jackson 라이브러리 디펜던시를 지우자.

````xml
<!-- 지우자 --> 
<!-- 참고로 org.codehaus.jackson 은 오래 전 패키지이다. com.fasterxml.jackson 으로 업그레이드 되었다. --> 
<dependency>
	<groupId>org.codehaus.jackson</groupId>
	<artifactId>jackson-mapper-asl</artifactId>
	<version>버전정보</version>
</dependency>
````

그렇다면 jack 라이브러리는 어떻게 사용할 수 있는가? 스프링부트에서 자동으로 디펜던시를 넣어준다. 

https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/1.5.2.RELEASE

캡처화면 참고
1. mvnrepository 화면
2. intellij 에서 추가된 화면

디펜던시만 추가한다고 끝이 아니다. 만약... org.codehaus 패키지를 사용한다면, com.fasterxml.jackson 으로 모두 변경해야 한다. 참고로, 각 클래스의 패키지 경로도 일부 바뀌었다. 필자는 아래와 같은 패키지를 모두 변경하였다. 

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
-->
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.codehaus.jackson.annotate.JsonProperty;
-->
import com.fasterxml.jackson.annotation.JsonProperty;

import org.codehaus.jackson.map.ObjectMapper;
-->
import com.fasterxml.jackson.databind.ObjectMapper;

import org.codehaus.jackson.map.util.JSONPObject;
-->
import com.fasterxml.jackson.databind.util.JSONPObject;


import org.codehaus.jackson.annotate.JsonIgnore;
-->
import com.fasterxml.jackson.annotation.JsonIgnore;

등등..전부 기억나지는 않지만, 신규 패키지로 변경한 이후에 오류가 발생하지는 않았다. 아직까지는...

#### 설정 파일 import  및 초기 실행 클래스 구현 

아래와 같이, 각종 XML 파일을 @ImportResource 어노테이션으로 추가한다.  
````java
@Configuration
@EnableAutoConfiguration
@ImportResource(value={ "classpath:servlet-context.xml",
                        "classpath:/경로/하이버네이트.xml",
                        "classpath:/경로/기타설정파일1.xml",
	                    "classpath:/경로/기타설정파일2.xml"})
@ComponentScan(basePackages = "패키지경로")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
````
아직까지는 @SpringBootApplication 를 사용하지는 않는다. 이렇게 수정하고 스프링부트를 실행해보자. 아마 잘 안된다. 오류가 날것이다. 왜냐면 각자 프로젝트 환경이 다 다르기 때문이다. 에러 로그를 보면서 스프링 부트 실행만 할 수 있도록 수정해보자. 상세한 설명을 할 수 없어서 미안하다. 참고로, 회사에서는 한두건 정도 추가 수정사항을 진행했다. (회사 라이브러리 관련 내용이라서 상세한 설명은 보안상 패스하겠다.)


## 2단계. XML 설정--> Java 설정으로 하나씩 전환하기

#### RestTemplate.xml

아래와 같이 restTemplate 을 생성하는 빈 설정을 자바 컨피그로 바꾸자. 
````xml
<bean id="restTemplate" class="org.springframework.web.client.RestTemplate">
생략
````

아래와 같이 자바 컨피그 설정으로 변경하면 된다. 

````java
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(60000);
		factory.setConnectTimeout(60000);

		HttpClient httpClient = HttpClientBuilder.create()
			.setMaxConnTotal(100)
			.setMaxConnPerRoute(20)
			.build();

		factory.setHttpClient(httpClient);
		return factory;
	}
}

//조금 더 깔끔하게 정리하면 아래와 같이 가능하다.
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate() {{
            setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder
                    .create()
                    .setConnectionManager(new PoolingHttpClientConnectionManager() {{
                        setDefaultMaxPerRoute(50);
                        setMaxTotal(200);
                    }}).build()));
        }};
    }
}
````

#### DataSource.xml
아직. 변경하지 못했다. XML을 import 하는 방식으로 유지하면서, 추후에 진행하기로 한다. 

#### Hibernate.xml
아직. 변경하지 못했다. XML을 import 하는 방식으로 유지하면서, 추후에 진행하기로 한다. 


## 남은 작업 들

검토 중...
