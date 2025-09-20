def solve(board, n, m):
    def dfs(x, y, board):
        dx = [0, 1]
        dy = [1, 0]

        visited = [[False] * n for _ in range(m)]
        stack = [(x, y)]
        visited[y][x] = True
        while stack:
            cx, cy = stack.pop()
            if cx == n - 1 and cy == m - 1:
                return True
            for i in range(2):
                nx = cx + dx[i]
                ny = cy + dy[i]
                if 0 <= nx < n and 0 <= ny < m:
                    if not visited[ny][nx] and board[ny][nx] == 1:
                        visited[ny][nx] = True
                        stack.append((nx, ny))
        return False

    return "Yes" if dfs(0, 0, board) else "No"


def main():
    cases = [
        {
            "n": 5,
            "m": 4,
            "board": [
                [1, 1, 1, 1, 1],
                [0, 1, 0, 0, 1],
                [1, 0, 0, 0, 1],
                [0, 0, 0, 1, 1],
            ],
            "expected": "Yes",
        }
    ]

    for i, tc in enumerate(cases, 1):
        result = solve(tc["board"], tc["n"], tc["m"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
