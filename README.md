# 카페모아

소규모 카페 활성화를 위한 웹 서비스 -> 주문, 결제 가능 및 카페 홍보

개인카페도 경험할 수 있는 주문앱 서비스
<br>
<br>

![version](https://img.shields.io/badge/version-0.0.1-yellow?)
![ubuntu](https://img.shields.io/badge/ubuntu-19.04-brown?logo=ubuntu)
![html](https://img.shields.io/badge/html-html5-red?logo=html5)
![css](https://img.shields.io/badge/css-css3-red?logo=css3)
![sass](https://img.shields.io/badge/sass-1.23.0-red?logo=sass)
![javascript](https://img.shields.io/badge/javascript-es6-yellowgreen?logo=javascript)
![aws-rds](https://img.shields.io/badge/aws%20-rds-orange?logo=Amazon)
![aws-ec2](https://img.shields.io/badge/aws%20-ec2-orange?logo=Amazon)
![spring](https://img.shields.io/badge/spring-5.2.2-green?logo=spring)
![react](https://img.shields.io/badge/react-16.12.0-blue?logo=react)
![redux](https://img.shields.io/badge/redux-4.0.5-blue?logo=redux)
![MariaDB](https://img.shields.io/badge/mariadb-10.4.12-orange?logo=MariaDB)
![java](https://img.shields.io/badge/java-11+-green?logo=java)
![intellij](https://img.shields.io/badge/intellij-2019.3.1-green?logo=intellij_IDEA)
![Docker](https://img.shields.io/badge/docker-docker-purple?logo=Docker)
![Firebase](https://img.shields.io/badge/firebase-firebase-purple?logo=Firebase)

------
## 목차

- [카페모아](#카페모아)
- [소개](#소개)
- [기술스택](#기술스택)
- 시작해보기
    - [필수요소](#필수요소)
    - [설치](#설치)
    - [테스트](#테스트)
- [데이터베이스모델링](#데이터베이스모델링)
- [핵심기능](#핵심기능)




## 소개

```
ssafy
|-- cafemoa
|     -- Back-End
|       `-- doha
|       `-- sumin
|       `-- gayoung
|     -- Front-End
|       `-- sihyo
|       `-- kanghyun
|       `-- sumin
```


### 기술스택
| Component         | Technology                 |
| ----------------- | -------------------------- |
| Frontend          | React                      |
| Backend           | Spring Boot 2.1+, Java 11+ |
| Database          | Maria Database             |
| Persistence       | JPA                        |
| API Documentation | Swagger-UI                 |
| Client Build      | npm, yarn                  |
| Server Build      | Gradle                     |



## 시작해보기

### 필수요소

- Install Java 11 from the OpenJDK
- Install Node.js from the Node.js website
- [Yarn](https://yarnpkg.com/): We use Yarn to install our Node.js module dependencies (rather than using npm).
- [Git Flow](https://github.com/nvie/gitflow/wiki/Installation): We are following Git Flow for maintaining software versions.


### 설치

  ```
  $ git clone https://lab.ssafy.com/webmobile1-sub2/s02p12a301.git
  $ yarn install
  ```


- Go to your project folder from your terminal
- Run: `npm install` or `yarn install`
- After install, run: `npm run start` or `yarn start`
- It will open your browser([http://localhost:3000](http://localhost:3000/))


### 테스트

  ```
  $ yarn run test
  ```


## 데이터베이스모델링


- 그림
  <br>

## 배포 서버 URL
​
- Docker
​
- Firebase
<br>

## 핵심기능

### 손님
 **1)  카페 추천 페이지**
- 카페 정보 페이지에서 인기순, 거리순으로 open한 카페 기반으로 BEST 카페 추천
- 


**2)  카페 Detail페이지**

- 카페에 대한 정보 및 대표메뉴 소개
- 


**3)  주문 페이지**


- 메뉴 별 옵션 선택 이후 결제
- 


**4)  카페 별 후기 기능**
- 카페 별 손님들이 후기 작성
- 


### [ 사장님 ]


**1)  카페 정보수정 및 메뉴 수정**
- 카페 정보 수정 및 메뉴 수정
- 대표메뉴 설정


**2)  카페에 달린 댓글 확인 후 대댓글 달기**


### [ 관리자]
**1)  카페 승인 **
