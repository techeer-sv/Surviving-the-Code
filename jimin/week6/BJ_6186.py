# 격자 전체를 왼쪽 위부터 차례로 훑는다.
# '#'인데 아직 방문하지 않은 칸을 만나면 → 새로운 군집 발견!
# 거기서 DFS/BFS를 시작해 상하좌우로 이어진 '#'들을 전부 방문 처리한다.
# 이렇게 하면 같은 군집에 속한 모든 칸이 "탐색 완료"된다.
# 탐색이 끝나면 군집 개수 +1.
# 격자 전체를 다 돌 때까지 반복.

import sys
sys.setrecursionlimit(10000)

R, C = map(int, sys.stdin.readline().split())
grid = [list(sys.stdin.readline().strip()) for _ in range(R)]
visited = [[False]*C for _ in range(R)]

# 방향: 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    visited[x][y] = True
    for d in range(4):
        nx, ny = x + dx[d], y + dy[d]
        if 0 <= nx < R and 0 <= ny < C:
            if not visited[nx][ny] and grid[nx][ny] == '#':
                dfs(nx, ny)

clumps = 0
for i in range(R):
    for j in range(C):
        if grid[i][j] == '#' and not visited[i][j]:
            dfs(i, j)
            clumps += 1

print(clumps)
