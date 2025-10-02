def solve(board, m, n):
    max_result = 0
    from collections import deque

    visited = [[False] * (m + 1) for _ in range(n + 1)]
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

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
