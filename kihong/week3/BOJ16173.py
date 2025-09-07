"""
백준 16173 문제 풀이
for 가장 끝에 도달할 때까지 반복
    만약 board[y][x] == -1 일때,
        반복 멈춤
    for 오른쪽과 아래 방향 고려
        만약 조건에 부합한다면 y, x의 위치를 업데이트
        이후 다시 처음으로 돌아가서 반복
만약 끝에 도달하지 못한다면 return False

해당 시간 복잡도는 O(n^2). 왜나하면 간석의 갯수는 모두 칸의 수 + 칸 끼리 연결된 간선의 수 이기 때문에
O(2n^2)즉, O(n^2)이다.
"""

import sys

sys.setrecursionlimit(10**6)


def solve(n, board):
    # 각 방향 선언
    dx = [0, 1]
    dy = [1, 0]

    # dfs 알고리즘을 활용해 문제 해결
    def dfs(x, y, board):
        # 가장 끝에 도달한다면 return True
        if board[y][x] == -1:
            return True
        # 각 방향에 따라 dfs 알고리즘 재귀적으로 수행
        for i in range(2):
            nx = dx[i] * board[y][x] + x
            ny = dy[i] * board[y][x] + y
            if (x <= nx < n and y < ny < n) or (x < nx < n and y <= ny < n):
                if dfs(nx, ny, board):
                    return True
        # 만약 가장 끝점에 도달하지 못했다면 return False
        return False

    return "HaruHaru" if dfs(0, 0, board) else "Hing"


def main():
    cases = [
        {"n": 2, "board": [[0, 96], [50, -1]], "expected": "Hing"},
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["n"], tc["board"])
        assert tc["expected"] == result, (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
