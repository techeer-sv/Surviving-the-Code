# 시간 복잡도 : O(N × M)
# 쓰는 방법 : dfs
# 이유 : 모든 케이스를 조사해서 군집이 몇개 있는지 확인할 수 있기 때문

# 전체 풀이 방법
# 1.테스트 케이스 개수를 받기
# 2.각 테스트 케이스마다 M*N크기의 filed를 만들고 배추 위치는 1로표시
# 3. 밭 전체를 돌면서 1을 만나면 dfs실행
# 4. DFS가 한 번 실행될 때마다 지렁이 수를 1 늘려줌

# dfs 함수
# 1.stack에 처음 탐색할 위치 넣어주기
# 2. 탐색할 위치는 filed에서 0으로 바꿔주기(방문한 배추는 0으로 바꾸기)
# 3. 네 방향을 차례대로 확인한다.
# 4.(nx, ny)가 밭 범위 안에 있고, 아직 방문하지 않은 배추(1)라면: 방문 처리(field[ny][nx] = 0),스택에 (nx, ny) 추가
#

import sys

input = sys.stdin.readline


def dfs(start_x, start_y, field, n, m):
    stack = [(start_x, start_y)]
    field[start_y][start_x] = 0  # 방문 처리

    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    while stack:
        x, y = stack.pop()
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < m and 0 <= ny < n and field[ny][nx] == 1:
                field[ny][nx] = 0
                stack.append((nx, ny))


def solve(M, N, K, cabbage_list):
    field = [[0] * M for _ in range(N)]

    # 배추 위치 심기
    for X, Y in cabbage_list:
        field[Y][X] = 1

    worms = 0
    for y in range(N):
        for x in range(M):
            if field[y][x] == 1:
                dfs(x, y, field, N, M)
                worms += 1
    return worms


def main():
    cases = [
        {
            "M": 10,
            "N": 8,
            "K": 17,
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
            "expected": 5,
        },
        {
            "M": 10,
            "N": 10,
            "K": 1,
            "cabbage_list": [[5, 5]],
            "expected": 1,
        },
    ]

    for i, tc in enumerate(cases):
        result = solve(tc["M"], tc["N"], tc["K"], tc["cabbage_list"])
        assert (
            result == tc["expected"]
        ), f"[{i}] 실패: expected={tc['expected']}, actual={result}"
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
