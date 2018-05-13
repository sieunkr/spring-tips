# 5월중순~6월중순 1달 사용해본 이후에 후기 작성 예정


# Visual Studio Code

## 기본 셋팅
[Ctrl  + Shift + x] 를 실행하면, 마켓플레이스에서 확장 프로그램을 검색할 수 있다. 스프링 부트 관련해서 전부 설치하자. 

 - Language Support for Java(TM) by Red Hat
 - Java Extension Pack
 - Spring Initializr Java Support
 - Spring Boot Java Support
 - Spring Boot Tools
 - Spring Boot Application Properties Support
 - Spring Boot Extension Pack
 - Gradle Language Support

https://code.visualstudio.com/docs/java/java-spring-boot


## Spring Boot IN Visual Studio Code

#### Spring Initialzr
Ctrl + Shift + P 로 **Command Palette** 를 띄울 수 있는데, Spring Boot Gradle Project --> Java --> 부트 버전 선택 --> 디펜던시 선택으로 신규 프로젝트를 생성할 수 있다. 

참고로 F1 을 클릭해도 된다. 

#### Java 코드를 수정해보자.
특별한 설정을 하지 않았는데, 신기하게도 수정한 코드가 바로반영된다.  05.png 
아마 Debugger for Java 라는 확장팩 때문인 것으로 추측된다.  근데 만약 신규 메서드를 추가하면, 아래와 같이 코드가 코드 Replace가 실패되었다는 알림 메시지가 표시된다. Yes 를 클릭하면 부트가 재시작된다. 물론, 상단에 재시작 버튼을 클릭해도 임베디드 서버가 재시작한다. 

아래 링크를 참고하자. 
https://blogs.msdn.microsoft.com/visualstudio/2018/02/02/hot-code-replacement-for-java-comes-to-visual-studio-code/

> 혹시 Intellij 에서도 비슷한 기능이 지원하나요?  지원 안한다면 Visual Studio Code 의 해당 기능은 정말 멋진것 같습니다. 



https://code.visualstudio.com/docs/java/java-debugging

#### jar 파일 빌드
tasks.json 을 설정하여 구성할 수 있는 것 같지만, 일단 해당 방법을 정확히 모르겠다. 일단, 심플하게 터미널에서 Gradle 명령어를 실행해보자. 

```java
./gradlew build
```
정상적으로 jar 파일이 빌드가 되는 것을 확인할 수 있다. 



## Intellij 비교
https://www.slant.co/versus/1958/5982/~intellij-idea_vs_visual-studio-code


https://stackshare.io/stackups/intellij-idea-vs-visual-studio-code

https://www.quora.com/How-does-IntelliJ-IDEA-compare-to-Visual-Studio




