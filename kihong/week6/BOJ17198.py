def solve(board, l_x, l_y):
    from collections import deque

    visit = [[False] * 10 for _ in range(10)]

    def bfs(board, x, y):
        q = deque()
        count = 0
        q.append((x, y, count))
        dx = [0, 0, -1, 1]
        dy = [1, -1, 0, 0]
        while q:
            cx, cy, cnt = q.popleft()
            for i in range(4):
                nx = dx[i] + cx
                ny = dy[i] + cy
                if 0 <= nx < 10 and 0 <= ny < 10:
                    if not visit[ny][nx] and board[ny][nx] == ".":
                        visit[ny][nx] = cnt + 1
                        q.append((nx, ny, cnt + 1))
                    if board[ny][nx] == "B":
                        return cnt
        return False

    return bfs(board, l_x, l_y)


def main():
    cases = [
        {
            "l_x": 5,
            "l_y": 8,
            "board": [
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", "B", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", "R", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", "L", ".", ".", ".", "."],
                [".", ".", ".", ".", ".", ".", ".", ".", ".", "."],
            ],
            "expected": 7,
        }
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["board"], tc["l_x"], tc["l_y"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
