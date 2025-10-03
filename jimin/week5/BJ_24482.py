import sys

input = sys.stdin.readline
sys.setrecursionlimit(10**6)
INF = sys.maxsize


def solve(board: list, n, m, r):
    board = [sorted(_list, reverse=True) for _list in board]
    visited = [-1] * (n + 1)

    def dfs(board, node, depth):
        visited[node] = depth
        for i in board[node]:
            if visited[i] == -1:
                dfs(board, i, depth + 1)

    dfs(board, r, 0)
    return visited[1:]


def main():
    cases = [
        {
            "n": 5,
            "m": 5,
            "r": 1,
            "board": [[], [4, 2], [1, 3, 4], [2, 4], [1, 2, 3], []],
            "expect": [0, 3, 2, 1, -1],
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