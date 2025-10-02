def solve(n, board):

    from collections import deque

    visited = [False] * (n + 1)

    def bfs(board, start):
        q = deque()
        q.append(start)
        visited[start] = True
        while q:
            current = q.popleft()
            for next in board[current]:
                if not visited[next]:
                    visited[next] = True
                    q.append(next)

    cnt = 0
    for idx in range(1, n + 1):
        if not visited[idx]:
            bfs(board, idx)
            cnt += 1
    return cnt


n, m = map(int, input().split())
board = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, input().split())
    board[a].append(b)
    board[b].append(a)
print(solve(n, board))


def main():
    cases = [
        {
            "n": 6,
            "m": 5,
            "board": [[], [2, 5], [1, 5], [4], [3, 6], [2, 1], [4]],
            "expected": 2,
        },
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["n"], tc["board"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
