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
    # 방문 여부를 기록할 2차원 배열 (처음엔 전부 False)
    visited = [[False] * n for _ in range(n)]
    # DFS를 위한 스택, 출발점 (0,0)에서 시작
    stack = [(0, 0)]

    # 스택이 빌 때까지 반복
    while stack:
        # 스택에서 현재 위치를 꺼냄
        x, y = stack.pop()

        # 현재 좌표가 보드 범위를 벗어나면 이 경로는 무효 → 건너뜀
        if x < 0 or y < 0 or x >= n or y >= n:
            continue

        # 이미 방문한 좌표라면 다시 방문할 필요 없음 → 건너뜀
        if visited[x][y]:
            continue

        # 처음 방문했으므로 방문 처리
        visited[x][y] = True

        # 현재 칸이 도착점(-1)이라면 성공 → True 반환
        if board[x][y] == -1:
            return True

        # 현재 칸에 적힌 숫자(점프할 칸 수) 확인
        jump = board[x][y]

        # 0이면 이동할 수 없으므로 더 탐색 불가 → 건너뜀
        if jump == 0:
            continue

        # 오른쪽, 아래쪽으로 점프 가능한 칸을 스택에 추가
        stack.append((x, y + jump))  # 오른쪽 이동
        stack.append((x + jump, y))  # 아래쪽 이동

    # 스택이 다 빌 때까지 도착점을 못 찾으면 실패 → False 반환
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
        assert tc["expected"] == result, (
            f"[{i}] 실패: expected={tc['expected']}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
