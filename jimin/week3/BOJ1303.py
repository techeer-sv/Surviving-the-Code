"""
board를 차례대로 돌면서, visited를 참고하고, 각 객수를 더하기

for x in 가로:
    for y in 세로:
        만약에 방문한 보드가 아니라면
            dfs 알고리즘을 수행하여 군집 수집
            수집한 군집에 따라 병력 계산

[시간 복잡도 분석]
- 외부 이중 for문은 모든 n * m 칸을 한 번씩 순회합니다. (O(nm))
- DFS는 한 번 호출되면 connected component의 모든 칸을 방문합니다.
- visited 배열 덕분에 모든 칸은 DFS에 의해 정확히 한 번만 방문됩니다.
- 따라서, DFS 탐색에 필요한 총 시간은 모든 칸을 방문하는 시간과 동일합니다. (O(nm))
- 전체 시간 복잡도는 O(nm) + O(nm)으로, O(nm)입니다.

"""


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

