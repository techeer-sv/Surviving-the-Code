def solve(board: list, n, m):

    def dfs(board, visited, x, y):
        visited[y][x] = True
        num = 0
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if (
                    0 <= nx < n
                    and 0 <= ny < m
                    and not visited[ny][nx]
                    and board[y][x] == board[ny][nx]
            ):
                num += dfs(board, visited, nx, ny)
        return num + 1

    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    visited = [[False] * n for _ in range(m)]
    w_sum = 0
    b_sum = 0
    for y in range(m):
        for x in range(n):
            if not visited[y][x]:
                num = dfs(board, visited, x, y)
                num *= num
                if board[y][x] == "W":
                    w_sum += num
                else:
                    b_sum += num
    return w_sum, b_sum


def main():
    cases = [
        {
            "n": 5,
            "m": 5,
            "board": [
                "WBWWW",
                "WWWWW",
                "BBBBB",
                "BBBWW",
                "WWWWW",
            ],
            "expected": (130, 65),
        }
    ]
    for i, tc in enumerate(cases):
        result = solve(tc["board"], tc["n"], tc["m"])
        assert tc["expected"] == result, (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
