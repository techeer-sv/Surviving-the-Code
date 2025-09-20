import sys

input = sys.stdin.readline
sys.setrecursionlimit(10**6)
INF = sys.maxsize


def solve(board: list, n, m, r):
    board = [sorted(_list) for _list in board]
    visited = [[-1, 0] for _ in range(n + 1)]
    order = 1

    def dfs(board, node, depth):
        nonlocal order
        visited[node][0] = depth
        visited[node][1] = order
        order += 1
        for i in board[node]:
            if visited[i][1] == 0:
                dfs(board, i, depth + 1)

    dfs(board, r, 0)
    sum = 0
    for a, b in visited[1:]:
        sum += a * b

    return sum


def main():
    cases = [
        {
            "n": 5,
            "m": 5,
            "r": 1,
            "board": [[], [4, 2], [1, 3, 4], [2, 4], [1, 2, 3], []],
            "expect": 20,
        }
    ]

    for i, tc in enumerate(cases, 1):
        result = solve(tc["board"], tc["n"], tc["m"], tc["r"])
        assert result == tc["expect"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
