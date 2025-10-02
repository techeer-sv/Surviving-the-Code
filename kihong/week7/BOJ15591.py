def solve(n, board, cost, video):
    import sys
    from collections import deque

    MAX_SIZE = sys.maxsize

    def bfs(video):
        cnt = 0
        visited = [False] * (n + 1)
        visited[video] = -1

        weight = MAX_SIZE

        q = deque()
        q.append((video, weight))

        while q:
            video, weight = q.popleft()
            for i in board[video]:
                if not visited[i[0]]:
                    new_weight = min(weight, i[1])
                    if new_weight >= cost:
                        cnt += 1
                        visited[i[0]] = new_weight
                        q.append([i[0], new_weight])
        return cnt

    return bfs(video)


def main():
    import sys

    sys.stdin = open("kihong/week7/BOJ15591.txt", "r")

    for i in range(1, 2):

        n, q = map(int, input().split())
        board = [[] for _ in range(n + 1)]
        for _ in range(n - 1):
            a, b, r = map(int, input().split())
            board[a].append([b, r])
            board[b].append([a, r])
        result = []
        for _ in range(q):
            cost, video = map(int, input().split())
            result.append(solve(n, board, cost, video))

        # print(*result, sep="\n")

        # 결과 값
        expect = list(map(int, input().split()))

        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect} , actual_result={result} "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
