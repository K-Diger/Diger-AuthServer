## Architecture Draft (Ver. 23/12/22)

![](architecture.png)

---

### 서버 가동 순서

- Eureka -> Config -> MicroServer ... -> API Gateway

### 고민해야할 내용

- API Gateway는 왜 Config 서버의 내용을 읽지 못하는 걸까?
- 마이크로 서버의 Spring Security에서 발생한 에러를 사용자에게 보여주는 메세지가 나타나지 않는 현상도 해결해야한다.
- 또한 PostService -> UserService 호출 시 UserService에서 발생하는 에러를 캐치하여 사용자에게 보여줄 수 있는 수단이 필요하다. 

- Feign의 예외처리

- Circuit Breaker가 왜 필요한가?
  - https://oliveyoung.tech/blog/2023-08-31/circuitbreaker-inventory-squad/

- 서비스 디스커버리가 왜 필요한가? API Gateway를 통해서 전부 관리가 될 것 같은데.
  - 운영중인 마이크로 서버의 IP가 변경된 상황에서 Service Discovery가 없다면 해당 게이트웨이에서도 변경된 마이크로 서버의 IP를 반영해줘야한다.
  - 하지만 Service Discovery를 사용하면 이 변경사항도 관리를 해주고 별칭으로 로드밸런싱이 가능하게 되는 관리적인 이점이 있다.


---

## `마일스톤 1`

- [x] `Netflix Eureka`를 활용하여 `Service Discovery`를 가동한다.

- [x] `API Gateway`를 가동하여 각 마이크로 서버에 요청을 `로드밸런싱`한다.
  - [x] `User Server`
  - [x] `Post Server`

### User Server

- [x] 유저는 회원가입을 할 수 있다.
- [x] 유저는 로그인을 할 수 있다.

### Post Server

- [x] 유저는 게시글을 작성할 수 있다.
  - [x] **유저**는 공지사항을 작성할 수 **없다**.

- [x] **관리자**는 공지사항을 작성할 수 **있다**.

### Config Server

- [x] 모든 마이크로서버가 사용할 설정 파일을 관리한다.


---

## `마일스톤 2`

- [ ] 마이크로서버와 통신방식을 `마일스톤 1`의 `OpenFeign`에서 `gRPC`로 변경한다.

- [ ] `Circuit Breaker`를 통해 `fault-tolerance(장애허용)`를 확보한다.

---

## `마일스톤 3`

- [ ] 관리자가 공지사항을 작성하면 모든 사용자에게 알림을 전송한다.
  - [ ] 이 때 `이벤트 기반` 처리를 위해 `메세지 브로커(Kafka)`를 활용한다.