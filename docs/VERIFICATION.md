# 검증 결과

**최종 코드 기준: 디버그 빌드·단위 테스트 10개·기기 테스트 2개·모듈 의존성 검사 통과, 린트 오류 0개·경고 11개.**

## 실행 명령

```sh
ANDROID_SERIAL=emulator-5554 ./gradlew :app:assembleDebug testDebugUnitTest checkModuleDependencies lintDebug :app:connectedDebugAndroidTest
```

| 항목 | 실제 결과 |
|---|---|
| 명령 종료 코드 | 0 |
| 디버그 APK | app/build/outputs/apk/debug/app-debug.apk 생성 |
| 모듈 의존성 검사 | 위반 없음 |
| 단위 테스트 | 10개 통과, 실패·오류·건너뜀 0개 |
| 기기 테스트 | 2개 통과, 실패·오류·건너뜀 0개 |
| 기기 환경 | medium_phone AVD, Android 16 / API 36, 1080×2400, density 420 |
| Gradle JVM 조건 | JDK 21 |
| 린트 범위 | 전체 Android 모듈의 debug variant |
| 린트 결과 | 오류 0개, 경고 11개 |

## 검증 내용

| 대상 | 확인 내용 |
|---|---|
| Navigation3Navigator 단위 테스트 4개 | 인자별 백스택, 동일 최상단 중복 방지, 주문 완료 후 정리, 루트 보호, 홈 이동, 지원하지 않는 Route 실패 시 기존 스택 보존 |
| 상품 탐색 단위 테스트 2개 | 한 번의 타입 등록으로 서로 다른 상품 ID 처리, 내부 리뷰 Route 등록, 경로 인자 직렬화 |
| 장바구니 단위 테스트 2개 | 같은 상품 추가 시 수량 증가, 수량 0 이하 시 항목 제거 |
| 주문 단위 테스트 2개 | 빈 장바구니 주문 방지, 주문 스냅샷 유지, 장바구니 초기화, 중복 주문 방지 |
| 상품 탐색 기기 테스트 | 상품 상세의 ID 반영, Activity 재생성 후 유지, 리뷰 진입과 복귀 |
| 구매 흐름 기기 테스트 | 장바구니 수량 변경, 장바구니→상품 상세, 주문 금액, 주문 완료, 재생성 후 주문 유지, 뒤로 가기 시 시작 화면 복귀, 빈 장바구니의 주문 버튼 비활성화 |

## 남아 있는 경고

| 종류 | 개수 | 상태 |
|---|---|---|
| Gradle·AGP·라이브러리 업데이트 권고 | 9 | 유지한 버전에 대한 업데이트 안내 |
| OldTargetApi | 1 | targetSdk 36, compileSdk 37 |
| DataExtractionRules | 1 | allowBackup=false, 별도의 dataExtractionRules 미설정 |
| Compose 테스트 API deprecation | 컴파일 경고 1개 | 기존 createAndroidComposeRule 사용, 린트 경고 11개와 별도 |

## 제한 사항

- 릴리스 빌드와 API 24 기기 실행은 검증하지 않음.
- Activity 재생성은 검증했으며, 프로세스 종료 후 장바구니·주문 데이터 복구는 구현하지 않음.
- Navigator의 계약 분리를 검증했으며, 실제 Activity 혼합 탐색 구현은 포함하지 않음.
- 실제 서버·결제·배송은 포함하지 않음.

## 화면 확인

| 캡처 | 수동 확인 범위 |
|---|---|
| 상품 목록·상세·리뷰·장바구니·주문 확인·주문 완료 | 기본 세로 화면의 상품 이미지, 글자 배치, 하단 버튼, 금액 표시 |
| home-dark.png | 상품 목록 다크 모드 |
| home-landscape-dark.png | 상품 목록 가로 화면과 최대 콘텐츠 너비 |
| home-large-text.png | 상품 목록 글꼴 배율 1.3의 줄바꿈과 스크롤 |

**캡처 파일: [screenshots](screenshots), 추가 화면 검증은 위 범위에 한정.**
