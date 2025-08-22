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
