from collections import deque
import sys
input = sys.stdin.readline

# BFS 탐색 함수: 하나의 영역 크기를 계산
def bfs(start_y, start_x, grid, visited, M, N):
    queue = deque([(start_y, start_x)])
    visited[start_y][start_x] = True
    area = 1  # 현재 영역 크기 (시작 칸 포함)

    # 상하좌우 이동 방향
    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    while queue:
        y, x = queue.popleft()
        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]
            # 1) 격자 범위 안에 있고
            if 0 <= ny < M and 0 <= nx < N:
                # 2) 아직 방문하지 않았으며
                # 3) 빈칸(0)이라면
                if not visited[ny][nx] and grid[ny][nx] == 0:
                    visited[ny][nx] = True
                    queue.append((ny, nx))
                    area += 1  # 넓이 증가
    return area

def main():
    M, N, K = map(int, input().split())  # 세로, 가로, 직사각형 개수
    grid = [[0] * N for _ in range(M)]   # 격자 초기화 (전부 빈칸=0)
    visited = [[False] * N for _ in range(M)]  # 방문 여부

    # 직사각형 영역을 1로 칠하기
    for _ in range(K):
        x1, y1, x2, y2 = map(int, input().split())
        for y in range(y1, y2):
            for x in range(x1, x2):
                grid[y][x] = 1

    areas = []  # 각 영역 넓이 저장
    for y in range(M):
        for x in range(N):
            if grid[y][x] == 0 and not visited[y][x]:
                size = bfs(y, x, grid, visited, M, N)
                areas.append(size)

    areas.sort()
    print(len(areas))   # 영역 개수
    print(*areas)       # 각 영역 넓이 (정렬된 상태)


main()
