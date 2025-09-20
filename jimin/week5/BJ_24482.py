import sys

sys.setrecursionlimit(10**6)
# 파이썬 기본 재귀 깊이는 약 1000으로 제한됨
# 이 문제는 최대 100,000개의 정점이 있을 수 있음
# DFS 탐색 시 재귀 호출이 100,000번 가까이 일어날 수 있으므로
# RecursionError 방지를 위해 재귀 제한을 충분히 크게 늘려야 함

input = sys.stdin.readline  # 빠른 입력 (백준에서 필수)


# -------------------------------
# DFS 탐색을 통해 각 노드의 깊이를 구하는 함수
# -------------------------------
def dfs_depths(N, M, R, edges):
    """
    N : 정점(노드)의 개수
    M : 간선의 개수
    R : 시작 정점 번호
    edges : (u, v) 형태의 간선 리스트 (무방향)

    반환값: 크기 N의 리스트 (1번 정점부터 N번 정점까지의 깊이)
           방문 불가능한 정점은 -1
    """

    # 1. 인접 리스트(Adjacency List) 생성
    graph = [[] for _ in range(N + 1)]  # 0번 인덱스는 사용하지 않음
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)  # 무방향 그래프이므로 양방향 추가

    # 2. 문제 조건: "인접 정점은 내림차순으로 방문"
    #    따라서 정렬을 미리 해둠
    for i in range(1, N + 1):
        graph[i].sort(reverse=True)

    # 3. 깊이 배열 초기화
    depth = [-1] * (N + 1)
    # depth[i] = 시작 정점 R에서 i번 정점까지의 깊이
    # 아직 방문하지 않은 노드는 -1로 유지

    # 4. DFS 함수 정의
    def dfs(node, d):
        """
        node : 현재 탐색 중인 노드
        d    : 현재 노드의 깊이
        """
        depth[node] = d  # 현재 노드의 깊이를 기록

        # 현재 노드에 연결된 인접 노드들 탐색
        for nxt in graph[node]:
            if depth[nxt] == -1:  # 아직 방문하지 않은 노드라면
                dfs(nxt, d + 1)  # 깊이를 하나 증가시켜 재귀 호출

    # 5. DFS 시작 (시작 정점 R에서 깊이 0)
    dfs(R, 0)

    # 6. 1번 정점부터 N번 정점까지의 깊이만 반환
    return depth[1:]


# -------------------------------
# 메인 실행 (백준 제출 시 동작)
# -------------------------------
if __name__ == "__main__":
    # 입력 처리
    N, M, R = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]

    # DFS 실행 및 결과 출력
    result = dfs_depths(N, M, R, edges)
    for d in result:
        print(d)

    # ---------------------------
    # 테스트 코드 (직접 실행 시만 동작)
    # ---------------------------
    cases = [
        {
            "name": "예제 입력 1",
            "N": 5,
            "M": 5,
            "R": 1,
            "edges": [(1, 4), (1, 2), (2, 3), (2, 4), (3, 4)],
            # DFS (내림차순):
            # 1 → 4 → 3 → 2  (5는 방문 불가)
            # 깊이 = [0, 3, 2, 1, -1]
            "expected": [0, 3, 2, 1, -1],
        },
        {
            "name": "선형 그래프",
            "N": 4,
            "M": 3,
            "R": 1,
            "edges": [(1, 2), (2, 3), (3, 4)],
            # DFS (내림차순, 사실 순서는 동일)
            # 1 → 2 → 3 → 4
            # 깊이 = [0, 1, 2, 3]
            "expected": [0, 1, 2, 3],
        },
    ]

    # 테스트 실행
    for i, tc in enumerate(cases, 1):
        actual = dfs_depths(tc["N"], tc["M"], tc["R"], tc["edges"])
        assert (
            actual == tc["expected"]
        ), f"[{i}] {tc['name']} 실패: expected={tc['expected']}, actual={actual}"
        print(f"[{i}] {tc['name']} 성공")
