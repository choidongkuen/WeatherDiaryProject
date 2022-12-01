# WeatherDiaryProject
Open API 를 이용한 실전 날씨 일기 프로젝트 🌦️

## Open API(OpenWeatherMap API) 를 이용한 날씨 일기 서버 프로그램 구현하기

## 사용된 Tools(Technique)

- Spring
- Spring Boot
- Gradle 
- Maria DB
- JPA
- Postman
- Swagger
- Various Libraries

## CRUD 실행 예시

#### Create

<img width="1312" alt="스크린샷 2022-12-01 오전 4 43 39" src="https://user-images.githubusercontent.com/96874318/204894442-0fc73133-fb2c-4a97-98a1-b9b9973ec155.png">


#### Read

- 특정 날짜 일기 읽기(/read/diary)

<img width="1312" alt="스크린샷 2022-12-01 오전 4 48 07" src="https://user-images.githubusercontent.com/96874318/204894584-2f30124a-cf64-430f-9445-842835d3673d.png">


- 날짜의 범위에 맞는 일기 읽기(/read/diaries)

<img width="1312" alt="스크린샷 2022-12-01 오전 4 48 36" src="https://user-images.githubusercontent.com/96874318/204894697-70806cf8-5c6c-4a88-a7a8-5e179dfb33c2.png">


#### update
 특정 날짜 일기 text 수정(/update/diary)

![스크린샷 2022-12-01 오후 4 14 24](https://user-images.githubusercontent.com/96874318/204989600-43c1e5b1-7465-4337-bd85-c608bd31b4c8.png)

#### delete
 특정 날짜 일기 삭제(/delete/diary)

![스크린샷 2022-12-01 오후 4 15 19](https://user-images.githubusercontent.com/96874318/204989648-27e48a37-0ca6-42b9-aa7f-dc96bc722305.png)



## Swagger 실행 화면

<img width="1695" alt="스크린샷 2022-12-01 오후 4 11 05" src="https://user-images.githubusercontent.com/96874318/204989875-c1692649-7c61-4b0a-9bbc-818acca298be.png">


## 프로젝트 진행하면서 느낀점 🔥

Spring에 대해 알아간지는 얼마 되지 않았지만, 배우면 배울 수록 Spring은 정말 매력적인 프레임워크인것 같다.
정말 편리하기도 하지만 어떤 경우에는 멀게만 느껴지는 존재이다 😥
이번 프로젝트에서 습득한 지식이 정말 많지만, 특히 Swagger 를 통한 API doc를 직접 만들어 본 경험과 log4j를 통해 직접 로그를 남겨본 경험은
백엔드 개발자가 되기 위한 여정에서 굉장히 값진 경험이자 많은 것을 배울 수 있었다.
중간에 Spring boot version(3.0.0) 과 Swagger의 version이 맞지 않아 처음 의도와는 다르게 spring-doc 라이브러리를 사용하게 되었지만
이 또한 찾아가면서 해결해 가는 즐거움(?) 을 느낄 수 있었다.
아직은 많이 부족하지만 포기하지 않고 Spring과 친해지려고 더욱 노력해야 할 것 같다. 🤔



