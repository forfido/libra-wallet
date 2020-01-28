Libra Wallet
=============================================

오픈소스 jlibra 를 활용한 리브라 화폐에 대한 전자지갑 어플리케이션  
Libra Blockchain 은 현재 출시 전으로 TestNet 망을 이용

## Live Demo

https://www.palibra.net


## 기능   
- 회원 계정당 가입 시 한개 리브라 계좌 생성
- 소셜 로그인(Google, Facebook)
- 회원가입/로그인
- 잔액조회
- 송금
- 청구하기 (QRCode 사용)
- 친구 찾기
- 리브라 코인 채굴(Testnet만 가능)


## 앱 구동하기

### 사전 설치 필요 목록
- Java 1.8+ 
- Maven 3.0+
- Node.js (https://nodejs.org/en/)
- H2 Database Engine (https://www.h2database.com/html/download.html)

### 서브모듈 체크아웃
```
$ git submodule init
$ git submodule update
```

### 실행하기
1. H2 실행  
  `[윈도우 검색 단축키: Windows Key + S] - [H2 console 실행] - [브라우저 로그인 http://localhost:8082]` 
2. 백인드 WebAPI 실행  
    `IDE에서 WalletapiApplication 실행`  
3. 프론트앤드 Vue 실행
```aidl
$ cd walletweb
$ npm run serve
``` 
  
 