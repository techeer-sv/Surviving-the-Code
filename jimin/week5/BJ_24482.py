import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline  # 빠른 입력


def dfs_depths(N, M, R, edges):
    """
    N : 정점 개수
    M : 간선 개수
    R : 시작 정점
    edges : (u, v) 형태의 무방향 간선 리스트

    반환: 1번 ~ N번 정점의 깊이 리스트
          방문하지 못한 정점은 -1
    """
    graph = [[] for _ in range(N + 1)]
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)

    # 문제 조건: 인접 정점 내림차순 방문
    for i in range(1, N + 1):
        graph[i].sort(reverse=True)

    depth = [-1] * (N + 1)

    def dfs(node, d):
        depth[node] = d
        for nxt in graph[node]:
            if depth[nxt] == -1:
                dfs(nxt, d + 1)

    dfs(R, 0)
    return depth[1:]


def main():
    N, M, R = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    result = dfs_depths(N, M, R, edges)
    for d in result:
        print(d)


def run_tests():
    cases = [
        {
            "name": "예제 입력 1",
            "N": 5,
            "M": 5,
            "R": 1,
            "edges": [(1, 4), (1, 2), (2, 3), (2, 4), (3, 4)],
            "expected": [0, 3, 2, 1, -1],
        },
        {
            "name": "선형 그래프",
            "N": 4,
            "M": 3,
            "R": 1,
            "edges": [(1, 2), (2, 3), (3, 4)],
            "expected": [0, 1, 2, 3],
        },
    ]

    for i, tc in enumerate(cases, 1):
        actual = dfs_depths(tc["N"], tc["M"], tc["R"], tc["edges"])
        assert actual == tc["expected"], (
            f"[{i}] {tc['name']} 실패: expected={tc['expected']}, actual={actual}"
        )
        print(f"[{i}] {tc['name']} 성공")


if __name__ == "__main__":
    if sys.stdin.isatty():
        # 터미널에서 직접 실행할 때 → 테스트 모드
        run_tests()
    else:
        # 온라인 저지/CI 입력 모드
        main()
