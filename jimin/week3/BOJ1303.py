# 시간 표기법 : O(N × M)
import io
import sys

input = sys.stdin.readline  # 빠른 입력을 위해 sys.stdin.readline 사용


# DFS (스택 사용) 함수: (y, x) 좌표에서 시작해 같은 팀(team) 군집을 탐색
def dfs(y, x, team, N, M, field, visited):
    stack = [(y, x)]  # (행, 열)
    visited[y][x] = True
    count = 1

    # 상, 하, 좌, 우
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    while stack:
        cy, cx = stack.pop()
        for dy, dx in directions:
            ny, nx = cy + dy, cx + dx
            if 0 <= ny < M and 0 <= nx < N:
                if not visited[ny][nx] and field[ny][nx] == team:
                    visited[ny][nx] = True
                    stack.append((ny, nx))
                    count += 1
    return count


def solve(N, M, field):
    visited = [[False] * N for _ in range(M)]
    white_power = 0
    blue_power = 0

    for y in range(M):
        for x in range(N):
            if not visited[y][x]:
                team = field[y][x]
                size = dfs(y, x, team, N, M, field, visited)
                if team == "W":
                    white_power += size * size
                else:
                    blue_power += size * size
    return white_power, blue_power


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
