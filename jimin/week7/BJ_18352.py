# 도시 수 N, 도로 수 M, 거리 K, 출발 도시 X 입력 받기.
# 인접 리스트로 단방향 그래프 저장.
# BFS로 시작 도시 X에서 출발 → 거리 배열(distance)을 이용해 최단 거리 기록.
# distance[X] = 0으로 초기화.
# 방문하지 않은 도시라면 distance[현재] + 1 값을 기록.
# BFS 끝난 뒤 distance[i] == K 인 도시만 출력.
# 없다면 -1 출력.

# 왜 BFS인가?
# 모든 도로 길이가 1이니까,
# "몇 번 이동했는지"가 곧 "최단 거리"예요.
# BFS는 레벨(깊이)별로 퍼져 나가기 때문에,
# distance[i] 배열에 "시작점에서 몇 번 이동했는지"를 자연스럽게 기록할 수 있어요.
# 즉, BFS로 탐색하면 출발 도시에서 각 도시까지의 최단 거리를 구할 수 있어요.

from collections import deque
import sys

input = sys.stdin.readline

def main():
    N, M, K, X = map(int, input().split())

    graph = [[] for _ in range(N + 1)]
    for _ in range(M):
        a, b = map(int, input().split())
        graph[a].append(b)  # 단방향

    # 거리 배열 (-1이면 아직 방문 X)
    distance = [-1] * (N + 1)
    distance[X] = 0

    # BFS
    queue = deque([X])
    while queue:
        now = queue.popleft()
        for nxt in graph[now]:
            if distance[nxt] == -1:   # 아직 방문 안 한 도시
                distance[nxt] = distance[now] + 1
                queue.append(nxt)

    # 정답 출력
    result = [i for i in range(1, N + 1) if distance[i] == K]

    if result:
        result.sort()
        for r in result:
            print(r)
    else:
        print(-1)

if __name__ == "__main__":
    main()
