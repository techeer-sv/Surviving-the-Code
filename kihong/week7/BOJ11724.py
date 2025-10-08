"""백준 11724
각 배열을 모두 방문하면서, BFS 알고리즘을 활용하여 연결 요소 개수 파악
모든 간선과 노드를 모두 방문하는 것이므로 O(n+m)
시간 복잡도 O(n+m)
"""


def solve(n, board):

    from collections import deque

    # 방문 배열 선언
    visited = [False] * (n + 1)

    # bfs 알고리즘을 활용하여 연결 요소 파악
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

    # 연결 요소 개수
    cnt = 0
    # 모든 배열을 돌아보면서 연결 요소 파악
    for idx in range(1, n + 1):
        if not visited[idx]:
            bfs(board, idx)
            cnt += 1
    return cnt


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
