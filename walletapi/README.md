# 개발환경 설정

## Maven 사전 빌드 사항
 1. jlibra-spring-boot-starter : maven install
 2. libra-message-signing : maven install
    * 그외 모듈 설명
      - jlibra : maven 레파지토리를 참조하기 때문에 소스만 submodule로 포함됨
      - java-libra-client & libra-payment-processor : 클라이언트 샘플 모듈
    
 3. 최종 walletapi 빌드 및 실행이 필요
    *pom.xml 에서 libra 참조 실패 (TODO : )       
 

## DB 설정
 * h2 db를 로컬에 설치하여 구동 시킨다. `port :9092`
  http://www.h2database.com/html/download.html
 * sql 쿼리는 IntelliJ에서 Database 창을 통해 수행  
  https://github.com/jojoldu/blog-code/tree/master/intellij-h2-client
 