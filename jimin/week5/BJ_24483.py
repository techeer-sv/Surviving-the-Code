import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def dfs_order(N, M, R, edges):
    graph = [[] for _ in range(N + 1)]
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)

    for i in range(1, N + 1):
        graph[i].sort()  # 문제 조건: 인접 노드는 오름차순 방문

    visited = [0] * (N + 1)
    order = 1

    def dfs(node):
        nonlocal order
        visited[node] = order
        for nxt in graph[node]:
            if visited[nxt] == 0:
                order += 1
                dfs(nxt)

    dfs(R)
    return visited[1:]


def main():
    N, M, R = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    result = dfs_order(N, M, R, edges)
    print("\n".join(map(str, result)))


def run_tests():
    cases = [
        {
            "name": "예제 입력 1",
            "N": 5,
            "M": 5,
            "R": 1,
            "edges": [(1, 4), (1, 2), (2, 3), (2, 4), (3, 4)],
            "expected": [1, 4, 3, 2, 0],
        },
        {
            "name": "선형 그래프",
            "N": 4,
            "M": 3,
            "R": 1,
            "edges": [(1, 2), (2, 3), (3, 4)],
            "expected": [1, 2, 3, 4],
        },
    ]

    for i, tc in enumerate(cases, 1):
        actual = dfs_order(tc["N"], tc["M"], tc["R"], tc["edges"])
        assert (
            actual == tc["expected"]
        ), f"[{i}] {tc['name']} 실패: {actual} != {tc['expected']}"
        print(f"[{i}] {tc['name']} 성공")


if __name__ == "__main__":
    if sys.stdin.isatty():
        run_tests()
    else:
        main()
