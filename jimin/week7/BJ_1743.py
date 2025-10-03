from collections import deque
import sys
input = sys.stdin.readline

# BFS 탐색 함수
def bfs(start_r, start_c, grid, visited, N, M):
    # 시작 좌표를 큐에 넣고 방문 처리
    queue = deque([(start_r, start_c)])
    visited[start_r][start_c] = True
    size = 1  # 현재 음식물 덩어리 크기 (시작칸 포함)

    # 상하좌우 탐색을 위한 방향 벡터
    dr = [-1, 1, 0, 0]  # 행(row) 변화량
    dc = [0, 0, -1, 1]  # 열(column) 변화량

    while queue:
        r, c = queue.popleft()  # 큐에서 하나 꺼내기

        # 현재 칸(r, c)에서 상하좌우 탐색
        for i in range(4):
            nr, nc = r + dr[i], c + dc[i]

            # 1. 범위 안에 있고
            if 0 <= nr < N and 0 <= nc < M:
                # 2. 아직 방문 안 했고
                # 3. 음식물이 있는 칸이라면
                if not visited[nr][nc] and grid[nr][nc] == 1:
                    visited[nr][nc] = True   # 방문 처리
                    queue.append((nr, nc))  # 큐에 추가
                    size += 1               # 덩어리 크기 +1
    return size  # BFS로 구한 한 덩어리의 크기 반환

def main():
    # N = 세로 길이, M = 가로 길이, K = 음식물 개수
    N, M, K = map(int, input().split())

    # 격자 초기화 (전부 0)
    grid = [[0] * M for _ in range(N)]
    visited = [[False] * M for _ in range(N)]  # 방문 체크 배열

    # 음식물 좌표 입력 받기
    for _ in range(K):
        r, c = map(int, input().split())
        grid[r-1][c-1] = 1  # 음식물이 있는 곳을 1로 표시 (인덱스 0부터 시작)

    max_size = 0  # 가장 큰 덩어리 크기 저장 변수

    # 격자 전체 탐색
    for r in range(N):
        for c in range(M):
            # 음식물이 있고 아직 방문 안 한 칸이면 BFS 시작
            if grid[r][c] == 1 and not visited[r][c]:
                comp_size = bfs(r, c, grid, visited, N, M)
                max_size = max(max_size, comp_size)  # 최대 크기 갱신

    print(max_size)  # 결과 출력

main()
