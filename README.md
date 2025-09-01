# Surviving-the-Code

코딩 테스트(코테) 대비를 위한 문제 풀이 및 코드 스타일 관리 프로젝트입니다.

## 폴더 구조

- 각 참가자별 디렉토리(`jimin`, `jugwang`, `juha`, `kihong`, `minseon`)와 주차별 문제 풀이 코드 및 README
- `docs/`: 각 언어별 린팅 가이드 문서
- `scripts/`: 파이썬 코드 린트 스크립트
- `checkstyle.xml`: Java 코드 스타일 설정 파일
- `lint-python`: 파이썬 린트 설정 파일

## 주요 기능

- 다양한 언어(파이썬, 자바, 자바스크립트, Go)별 코드 스타일 가이드 제공
- 참가자별/주차별 코딩 테스트 문제 풀이 코드 관리
- 자동 린트 및 포맷팅 스크립트 제공

## 사용법

1. 각자 자신의 폴더에 주차별 문제 풀이 코드를 작성합니다.
2. 코드 스타일을 맞추기 위해 린트 및 포맷팅 도구를 사용합니다.
   - 파이썬: `black`, `isort`, `flake8`
   - 자바: `checkstyle`
   - 기타 언어: `docs/` 내 가이드 참고
3. PR 제출 전 린트 스크립트(`scripts/lint_python.sh`) 실행 권장

## 참고 문서

- [LINTING_PYTHON.md](docs/LINTING_PYTHON.md)
- [LINTING_JAVA.md](docs/LINTING_JAVA.md)
- [LINTING_JAVASCRIPT.md](docs/LINTING_JAVASCRIPT.md)
- [LINTING_GO.md](docs/LINTING_GO.md)

## file & 브랜치 & PR 규칙

### file 네이밍
- file 이름: `BOJ{문제번호}.{언어에 맞는 확장자}`
   - JAVA의 경우 디렉토리를 생성 후 Main.java 생성 시 디렉토리 명을 해당 파일로 설정
   - 또는 파일을 해당 이름으로 생성 후 class 네이밍: `public final class {파일이름}`

### 브랜치 네이밍

- 스터디 주차 브랜치: `week{N}-{이름}` (예: week9-kihong)
- 기타 브랜치: 목적에 맞는 이름 사용(이슈에서 생성되는 대로) (예: fix-python-lint, update-ci-config)

### PR(Pull Request)

- PR 제목: `Week{N} {이름}` (예: Week9 Jungeun)
- PR 설명에는 다음 내용을 반드시 포함:
   - pr 탬플릿에 맞게 작성

### 코드 리뷰 규칙

- PR 작성자
  - 리뷰 코멘트에 대한 응답은 모두 작성
  - 수정 필요 시 pr 드래프트 실행
  - 코드 수정 후에는 리뷰어에게 Re-review 요청 (소용돌이 버튼 클릭)
  - 피드백을 반영한 경우, 해당 커밋 링크를 코멘트로 남기기
  - 모든 리뷰어가 Approve 했다면 직접 Merge 진행

- 리뷰어
  - 할당된 PR은 책임감을 가지고 리뷰
  - 리뷰 완료 후 반드시 Approve 진행