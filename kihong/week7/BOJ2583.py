def solve(board, n, m):
    from collections import deque

    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    def bfs(x, y):
        q = deque()
        q.append((x, y))
        board[y][x] = 1
        cnt = 0
        while q:
            cx, cy = q.popleft()
            cnt += 1
            for i in range(4):
                nx = cx + dx[i]
                ny = cy + dy[i]
                if 0 <= nx < n and 0 <= ny < m:
                    if not board[ny][nx]:
                        board[ny][nx] = 1
                        q.append((nx, ny))
        return cnt

    order = []
    scope = 0
    for y in range(m):
        for x in range(n):
            if not board[y][x]:
                order.append(bfs(x, y))
                scope += 1
    return scope, sorted(order)


def main():
    import sys

    sys.stdin = open("kihong/week7/BOJ2583.txt", "r")

    for i in range(1, 2):
        m, n, k = map(int, input().split())
        board = [[False] * n for _ in range(m)]
        for _ in range(k):
            left_x, left_y, right_x, right_y = map(int, input().split())
            for x in range(left_x, right_x):
                for y in range(left_y, right_y):
                    board[y][x] = 2
        result, result_1 = solve(board, n, m)

        # 결과 값
        expect = int(input())
        expect_1 = list(map(int, input().split()))

        assert result == expect and result_1 == expect_1, (
            f"[{i}] 실패: "
            f"expected={expect} & {expect_1}, actual_result={result} & {result_1} "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
