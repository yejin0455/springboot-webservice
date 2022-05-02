# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스
<br>

![XL](https://user-images.githubusercontent.com/70681797/166172328-8f591cdd-daf1-485a-a88b-22ff42036f1f.jpg)


## 🗓 프로젝트 기간
- 2022.04.01~진행중 (7일차까지 완료)
<br>

## 📃 프로젝트 개요
- JPA, JUnit 테스트, 그레이들, 머스테치, 스프링 시큐리티를 활용한 소셜 로그인 등을 사용한 웹 애플리케이션 개발
- AWS EC2와 RDS를 사용한 서버 환경 구축
- 배포 자동화, 무중단 배포
<br>

## 🔎 요구사항
- 게시판 기능
  - 게시글 조회, 등록, 수정, 삭제

- 회원 기능
  - 구글/네이버 로그인
  - 로그인한 사용자만 글 작성 권한
<br>

## 🛠 사용 기술
- IntelliJ IDEA 2021.3.3 (Community Edition)
- Java8(JDK 1.8)
- gradle-4.10.2
- springBootVersion = '2.2.0.RELEASE'
- Spring Security Oauth2 Client
- JUnit4
- Mustache
- AWS-RDS-MariaDB
<br>

## 💾 DB

![db_Table](https://user-images.githubusercontent.com/70681797/166173360-961a4b20-d1a5-4353-8d56-c4a16cfb540e.PNG)

<br>

## 🖥 실행 화면

(추가 예정)

<br>

## ✏️ 기록

- 진행 현황
  - https://www.notion.so/yejin-10/eb1b0018feb04e8c90f38c0ce6ca1156

<br>

- BaseTimeEntity Class
  - 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리함
  - 데이터베이스에 직접 시간 입력 받지 않음

- URL 매핑 -> Controller

- index.js
  - API를 호출하는 JS

- 2개의 라이브러리(부트스트랩, 제이쿼리)를 레이아웃 방식으로 index.mustache에 추가
  - 레이아웃 방식 : 공통 영역을 별도의 파일로 분리하여 필요한 곳에서 가져다 쓰는 방식

- 부트스트랩, 제이쿼리는 머스테치 화면 어디서나 필요함 -> 레이아웃 폴더 생성해서 관리

- 페이지 로딩 속도를 높이기 위해 css는 header에, js는 footer에 위치
  - html은 위에서부터 코드 실행됨 -> js의 용량이 클수록 body 부분의 실행이 늦어짐 -> 화면이 다 그려진 후에 호출하는 게 좋음
  - css는 화면을 그리는 역할이므로 head에서 불러오는 것이 좋음, 그렇지 않으면 css가 깨진 화면을 사용자가 볼 수 있음
  - 부트스트랩은 제이쿼리가 꼭 있어야 하기 때문에 제이쿼리를 먼저 호출(부트스트랩이 제이쿼리에 의존)

- CustomOAuth2UserService Class
  - 구글 로그인 후 가져온 사용자의 정보(이메일, 이름, 사진 등)를 기반으로 가입 및 정보수정, 세션 저장 등의 기능 지원

- IndexController에서 세션 값을 가져오는 부분 수정
  - index메소드 외에 다른 컨트롤러와 메소드에서 세션 값이 필요하면 그때마다 직접 세션에서 값을 가져와야 함
  - -> 같은 코드가 계속해서 반복되어 불필요함
  - -> 메소드 인자로 세션 값을 바로 받을 수 있도록 변경

- 세션 저장소를 데이터베이스로 교체 후
  - h2-console에 세션을 위한 테이블 SPRING_SESSION, SPRING_SESSION_ATTRIBUTES 가 생성됨
  - -> JPA로 인해 테이블 자동 생성됨

- 그래도 여전히 세션이 풀림 !!
  - H2 기반으로 스프링이 재실행될 때 H2도 재시작되기 때문
  - 이후 AWS로 배포하게 되면 RDS를 사용하게 됨 -> 그때부터는 세션이 풀리지 않음

- 기존에는 바로 API를 호출할 수 있어 테스트 코드 역시 바로 API를 호출하도록 구성함
  하지만 시큐리티 옵션이 활성화되면 인증된 사용자만 API를 호출할 수 있음
  -> 기존의 API 테스트 코드들이 모두 인증에 대한 권한을 받지 못하였으므로, 테스트 코드마다 인증한 사용자가 호출한 것처럼 작동하도록 수정

- @WithMockUser는 MockMvc에서만 작동함
  - 현재 PostsApiControllerTest는 @SpringBootTest로만 되어 있음
  - -> 코드 변경

- AWS를 선택한 이유
  - 클라우드에서 기본적으로 지원하는 기능이 많아 개인이나 소규모 개발에 집중할 수 있음
  - 국내 점유율 높음
  - 사용자가 많아 국내 자료와 커뮤니티가 활성화되어 있음

<br>

## 💫 이슈
- (추가할 예정) 현재 노션에 정리 해 둠
  - https://www.notion.so/yejin-10/e63d65ae30904c7aa21f2617eb4dda03
<br>
