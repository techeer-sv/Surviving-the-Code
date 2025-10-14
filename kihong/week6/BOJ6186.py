"""백준 6186

시간 복잡도 O(rc)

문제 알고리즘 아이디어
- 각 배열을 모두 방문하면서, BFS 알고리즘을 활용하여 사이클을 체크함.
"""


def solve(board, r, c):
    visited = [[False] * c for _ in range(r)]
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]

    def solve(board, x, y):
        stack = [(x, y)]
        while stack:
            cx, cy = stack.pop()
            visited[cy][cx] = True
            for i in range(4):
                nx = dx[i] + cx
                ny = dy[i] + cy
                if 0 <= nx < c and 0 <= ny < r:
                    if not visited[ny][nx] and board[ny][nx] != ".":
                        stack.append((nx, ny))

    count = 0
    for x in range(c):
        for y in range(r):
            if not visited[y][x] and board[y][x] == "#":
                solve(board, x, y)
                count += 1
    return count


def main():
    cases = [
        {
            "r": 5,
            "c": 6,
            "board": [
                [".", "#", ".", ".", ".", "."],
                [".", ".", "#", ".", ".", "."],
                [".", ".", "#", ".", ".", "#"],
                [".", ".", ".", "#", "#", "."],
                [".", "#", ".", ".", ".", "."],
            ],
            "expected": 5,
        }
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["board"], tc["r"], tc["c"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
