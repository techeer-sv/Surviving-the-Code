"""백준 1012
DFS 알고리즘을 활용하여, 군집을 확인하고 문제를 해결.
DFS 알고리즘을 활용하여 문제를 푸는 것이므로, 시간 복잡도는 O(nm)임.
왜나하면 DFS 시간 복잡도보다 각 행과 열을 방문하는 시간 복잡도가 더 크기 때문.

"""

import sys

sys.setrecursionlimit(10**6)


def solve(
    board,
    cavage_list,
    m,
    n,
):
    # 양배추 위치 기록
    for x, y in cavage_list:
        board[y][x] = 1

    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    visited = [[False] * m for _ in range(n)]

    # dfs 알고리즘을 활용하여 방문 기록
    def dfs(board, x, y, visited):
        visited[y][x] = True
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y
            if 0 <= nx < m and 0 <= ny < n:
                if not visited[ny][nx] and board[ny][nx]:
                    dfs(board, nx, ny, visited)

    count = 0
    # 각 행과 열을 모두 방문하며, 군집 확인
    for x in range(m):
        for y in range(n):
            if not visited[y][x] and board[y][x]:
                dfs(board, x, y, visited)
                count += 1
    return count


def main():
    cases = [
        # 첫 번째 테스트 케이스
        {
            "m": 10,
            "n": 8,
            "k": 17,
            "cabbage_list": [
                [0, 0],
                [1, 0],
                [1, 1],
                [4, 2],
                [4, 3],
                [4, 5],
                [2, 4],
                [3, 4],
                [7, 4],
                [8, 4],
                [9, 4],
                [7, 5],
                [8, 5],
                [9, 5],
                [7, 6],
                [8, 6],
                [9, 6],
            ],
            "expected_count": 5,
        },
        # 두 번째 테스트 케이스
        {"m": 10, "n": 10, "k": 1, "cabbage_list": [[5, 5]], "expected_count": 1},
    ]

    for i, tc in enumerate(cases):
        m, n = tc["m"], tc["n"]
        cabbage_list = tc["cabbage_list"]

        # 보드 초기화
        board = [[0] * m for _ in range(n)]

        # solve 함수 호출
        result = solve(board, cabbage_list, m, n)

        # 결과 검증
        assert tc["expected_count"] == result, (
            f"[{i}] 실패: " f"expected={tc['expected_count']}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
