"""
백준 11123
모든 칸에 대해서 검토하고 가므로 시간 복잡도는 O(n^2)이다.
"""


def solve(board, width, height):
    visited = [[False] * width for _ in range(height)]
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]

    def dfs(x, y, board):
        visited[y][x] = True
        stack = [(x, y)]
        while stack:
            cx, cy = stack.pop()
            for i in range(4):
                nx = dx[i] + cx
                ny = dy[i] + cy
                if 0 <= nx < width and 0 <= ny < height:
                    if board[ny][nx] == "#" and not visited[ny][nx]:
                        visited[ny][nx] = True
                        stack.append((nx, ny))

    cnt = 0
    for x in range(width):
        for y in range(height):
            if board[y][x] == "#" and not visited[y][x]:
                dfs(x, y, board)
                cnt += 1
    return cnt


def main():
    cases = [
        {
            "h": 4,
            "w": 4,
            "board": [
                "#.#.",
                ".#.#",
                "#.##",
                ".#.#",
            ],
            "expected": 6,
        }
    ]
    for i, tc in enumerate(cases):
        result = solve(tc["board"], tc["w"], tc["h"])
        assert tc["expected"] == result, (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
