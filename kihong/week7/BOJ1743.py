"""백준 1743문제
모든 배열을 방문하면서 쓰레기의 군집을 찾고,
쓰레기의 크기를 BFS 알고리즘을 활용하여 return하는 형식
시간 복잡도는 모든 배열을 방문하기 때문에 O(nm)이다.
"""


def solve(board, m, n):
    from collections import deque

    # 가장 큰 음식물 크기
    max_result = 0

    # 방문 표시
    visited = [[False] * (m + 1) for _ in range(n + 1)]
    # 방향 선언
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    # 음식물의 집합을 찾아가면서 크기 return
    def bfs(x, y):
        q = deque()
        cnt = 1
        q.append((x, y))
        visited[y][x] = True
        while q:
            cx, cy = q.popleft()
            for i in range(4):
                nx = cx + dx[i]
                ny = cy + dy[i]
                if 0 < nx <= m and 0 < ny <= n:
                    if not visited[ny][nx] and board[ny][nx] == "#":
                        visited[ny][nx] = True
                        cnt += 1
                        q.append((nx, ny))
        return cnt

    # 모든 배열에 대해서 방문하면서 return
    for y in range(1, n + 1):
        for x in range(1, m + 1):
            if not visited[y][x] and board[y][x] == "#":
                max_result = max(max_result, bfs(x, y))
    return max_result


def main():
    cases = [
        {
            "n": 3,
            "m": 4,
            "board": [
                ["*", "*", "*", "*", "*"],
                ["*", "#", "*", "*", "*"],
                ["*", "*", "#", "#", "*"],
                ["*", "#", "#", "*", "*"],
            ],
            "expected": 4,
        },
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["board"], tc["m"], tc["n"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
