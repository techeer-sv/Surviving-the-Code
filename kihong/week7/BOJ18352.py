def solve(board, k, x, n):
    from collections import deque

    def bfs(start):
        result = []
        visited = [False] * (n + 1)
        q = deque()
        cnt = 0
        q.append((start, cnt))
        visited[start] = True
        while q:
            current, cnt = q.popleft()
            for next in board[current]:
                if not visited[next]:
                    visited[next] = cnt + 1
                    if cnt + 1 == k:
                        result.append(next)
                    q.append((next, visited[next]))

        return sorted(result)

    result = bfs(x)
    return result if result else [-1]


def main():
    import sys

    sys.stdin = open("kihong/week7/BOJ18352.txt", "r")

    for i in range(1, 5):
        n, m, k, x = map(int, input().split())
        board = [[] for _ in range(n + 1)]
        for _ in range(m):
            a, b = map(int, input().split())
            board[a].append(b)

        result = solve(board, k, x, n)
        # print(*result, sep="\n")

        # 결과 값
        expect = list(map(int, input().split()))

        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect} , actual_result={result} "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
