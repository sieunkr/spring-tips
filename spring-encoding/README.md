

## API에서 한글이 깨져 나올 때

1. Response Headers 를 확인
2. Content-Type: application/javascript;charset=UTF-8 여부 확인

만약, application/json 으로 응답하고 있다면

application.properties 에

spring.http.encoding.charset=UTF-8  
spring.http.encoding.enabled=true  
spring.http.encoding.force=true  

를 추가하자. 

