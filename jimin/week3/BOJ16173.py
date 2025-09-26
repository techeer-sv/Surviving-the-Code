# 시간복잡도 : O(n²)
# dfs사용해서 풀기 이유 : 모든 경로중에서 끝 지점에 도달하는게 뭔지 찾아야해서
# 1.게임판을 2차원 리스트로 입력받음
# 출발점 (0,0)에서 시작해 DFS(스택)를 이용해 탐색
# 스택에서 좌표를 꺼내 범위를 벗어나면 무시
# 이미 방문한 좌표도 무시하고, 방문하지 않았다면 방문 처리
# 현재 칸에 적힌 숫자(점프할 칸 수) 확인
# 오른쪽, 아래쪽으로 점프 가능한 칸을 스택에 추가
# 해당 칸이 -1이면 도착 성공 → "HaruHaru" 출력
# 스택이 다 빌 때까지 도착점을 못 찾으면 실패 → False 반환


def a(board, n):
    visited = [[False] * n for _ in range(n)]
    stack = [(0, 0)]  # (y, x)

    while stack:
        y, x = stack.pop()

        if y < 0 or x < 0 or y >= n or x >= n:
            continue

        if visited[y][x]:
            continue

        visited[y][x] = True

        if board[y][x] == -1:
            return True

        jump = board[y][x]
        if jump == 0:
            continue

        # 오른쪽, 아래쪽
        stack.append((y, x + jump))
        stack.append((y + jump, x))

    return False


def solve(n, board):
    return "HaruHaru" if a(board, n) else "Hing"


def main():
    cases = [
        {"n": 2, "board": [[0, 96], [50, -1]], "expected": "Hing"},
        {"n": 3, "board": [[1, 1, 1], [1, 1, 1], [1, 1, -1]], "expected": "HaruHaru"},
        {"n": 3, "board": [[2, 2, 1], [2, 2, 1], [1, 1, -1]], "expected": "HaruHaru"},
        {"n": 2, "board": [[0, 1], [1, -1]], "expected": "Hing"},
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["n"], tc["board"])
        assert (
            tc["expected"] == result
        ), f"[{i}] 실패: expected={tc['expected']}, actual_result={result}"
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
