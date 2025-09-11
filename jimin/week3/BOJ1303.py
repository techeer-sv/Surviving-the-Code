# 시간 표기법 : O(N × M)
import io
import sys

input = sys.stdin.readline  # 빠른 입력을 위해 sys.stdin.readline 사용
# input()과 동일하게 동작하지만 속도가 더 빠름


# DFS (스택 사용) 함수: (x, y) 좌표에서 시작해 같은 팀(team) 군집을 탐색
def dfs(x, y, team, N, M, field, visited):
    stack = [(x, y)]  # 스택에 시작 좌표 넣기
    visited[y][x] = True  # 방문 처리
    count = 1  # 군집 크기(시작 좌표 포함)

    # 상, 하, 좌, 우 방향 (대각선은 포함하지 않음)
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    # 스택이 빌 때까지 반복
    while stack:
        cx, cy = stack.pop()  # 스택에서 하나 꺼냄
        for dx, dy in directions:  # 네 방향 확인
            nx, ny = cx + dx, cy + dy
            # 1) 전쟁터 범위 안에 있고
            if 0 <= nx < N and 0 <= ny < M:
                # 2) 방문하지 않았고
                # 3) 같은 팀 병사라면
                if not visited[ny][nx] and field[ny][nx] == team:
                    visited[ny][nx] = True  # 방문 처리
                    stack.append((nx, ny))  # 스택에 추가해서 이어 탐색
                    count += 1  # 군집 크기 증가
    return count  # 최종 군집 크기 반환


def solve(N, M, field):
    # 방문 여부 저장 (초기에는 전부 False)
    visited = [[False] * N for _ in range(M)]

    # 아군과 적군의 총 전투력
    white_power = 0
    blue_power = 0

    # 전쟁터 전체를 탐색
    for y in range(M):  # 세로
        for x in range(N):  # 가로
            if not visited[y][x]:  # 아직 방문하지 않은 칸이라면
                team = field[y][x]  # 병사 팀 색 ('W' 또는 'B')
                size = dfs(x, y, team, N, M, field, visited)  # 해당 군집 크기 계산
                if team == "W":  # 아군일 경우
                    white_power += size * size
                else:  # 적군일 경우 ('B')
                    blue_power += size * size
    return white_power, blue_power


# -----------------------------
# 테스트 코드 (추가)
# -----------------------------
def run_test(input_str, expected_output):
    backup_stdin = sys.stdin
    try:
        sys.stdin = io.StringIO(input_str)
        N, M = map(int, sys.stdin.readline().split())
        field = [list(sys.stdin.readline().strip()) for _ in range(M)]
        result = solve(N, M, field)
        output = f"{result[0]} {result[1]}"
        assert (
                output == expected_output
        ), f"실패: 기대값={expected_output}, 실제={output}"
        print(f"테스트 성공 ✅ 입력:\n{input_str.strip()}\n출력: {output}")
    finally:
        sys.stdin = backup_stdin


if __name__ == "__main__":
    run_test("5 5\nWBWWW\nWWWWW\nBBBBB\nBBBWW\nWWWBB\n", "130 65")
    run_test("3 3\nWWW\nBBB\nWWW\n", "18 9")
