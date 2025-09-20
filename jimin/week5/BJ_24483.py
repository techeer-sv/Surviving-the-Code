import sys

sys.setrecursionlimit(10**6)
# DFS 깊이 제한 문제 방지용

input = sys.stdin.readline


# -------------------------------
# DFS 함수 정의
# -------------------------------
def dfs(node, d):
    """
    깊이 우선 탐색(DFS)
    node: 현재 방문 노드
    d: 시작 노드로부터의 깊이
    """
    global order, depth, visit_order, graph
    depth[node] = d
    visit_order[node] = order
    order += 1

    for nxt in graph[node]:
        if depth[nxt] == -1:  # 방문하지 않았다면
            dfs(nxt, d + 1)


# -------------------------------
# 공통 실행 함수
# -------------------------------
def run_dfs(N, M, R, edges):
    """
    DFS 로직을 실행하고 결과(Σ 깊이*방문순서)를 반환하는 함수
    메인 실행부와 테스트 코드 양쪽에서 재사용 가능
    """
    global graph, depth, visit_order, order
    graph = [[] for _ in range(N + 1)]
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
    for i in range(1, N + 1):
        graph[i].sort()  # 오름차순 방문 조건

    depth = [-1] * (N + 1)
    visit_order = [0] * (N + 1)
    order = 1

    dfs(R, 0)

    result = 0
    for i in range(1, N + 1):
        result += depth[i] * visit_order[i]
    return result


# -------------------------------
# 메인 실행부
# -------------------------------
if __name__ == "__main__":
    N, M, R = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    print(run_dfs(N, M, R, edges))

    # -------------------------------
    # 테스트 코드
    # -------------------------------
    cases = [
        {
            "name": "예제 입력 1",
            "N": 5,
            "M": 5,
            "R": 1,
            "edges": [(1, 4), (1, 2), (2, 3), (2, 4), (3, 4)],
            "expected": 20,
        },
        {
            "name": "선형 그래프",
            "N": 4,
            "M": 3,
            "R": 1,
            "edges": [(1, 2), (2, 3), (3, 4)],
            "expected": 20,
        },
    ]

    for i, tc in enumerate(cases, 1):
        actual = run_dfs(tc["N"], tc["M"], tc["R"], tc["edges"])
        assert (
            actual == tc["expected"]
        ), f"[{i}] {tc['name']} 실패: expected={tc['expected']}, actual={actual}"
        print(f"[{i}] {tc['name']} 성공")
