# API Gateway Flow

- 사용자의 모든 요청은 API Gateway로 향한다.
  - 모든 요청에는 JWT 인증/인가가 필요하다.
    - API Gateway에서 해당 인증/인가를 수행한다.
  - 단, 회원가입과 로그인은 제외한다.

- 인증/인가를 정상적으로 수행한 요청은 적절한 MicroService로 Routing된다.