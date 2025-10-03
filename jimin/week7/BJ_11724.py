#시간복잡도 : O(N + M)
#각 노드(N개)는 최대 한 번 방문됨,각 간선(M개)은 양쪽에서 한 번씩 확인됨
from collections import deque
import sys

input = sys.stdin.readline

def bfs(start, graph, visited):
    queue = deque([start])   # 시작 노드를 큐에 넣음
    visited[start] = True    # 시작 노드 방문 체크

    while queue:             # 큐가 빌 때까지 반복
        node = queue.popleft()   # 큐에서 꺼냄
        # 현재 노드와 연결된 모든 노드 탐색
        for i in graph[node]:
            if not visited[i]:  # 아직 안 갔다면
                visited[i] = True
                queue.append(i) # 큐에 추가 → 다음에 탐색

def main():
    N, M = map(int, input().split())

    # 1. 그래프 저장 (인접 리스트)
    graph = [[] for _ in range(N + 1)]
    for _ in range(M):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)   # 무방향 → 양쪽 다 저장

    visited = [False] * (N + 1)

    # 2. 연결 요소 세기
    count = 0
    for i in range(1, N + 1):
        if not visited[i]:   # 아직 안 탐색한 정점이면
            bfs(i, graph, visited)  # BFS 탐색 시작
            count += 1       # 연결 요소 +1

    print(count)

main()