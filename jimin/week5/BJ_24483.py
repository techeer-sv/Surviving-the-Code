import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def dfs(node, d, graph, depth, visit_order, order):
    depth[node] = d
    visit_order[node] = order[0]
    order[0] += 1

    for nxt in graph[node]:
        if depth[nxt] == -1:
            dfs(nxt, d + 1, graph, depth, visit_order, order)


def run_dfs(N, M, R, edges):
    graph = [[] for _ in range(N + 1)]
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
    for i in range(1, N + 1):
        graph[i].sort()

    depth = [-1] * (N + 1)
    visit_order = [0] * (N + 1)
    order = [1]

    dfs(R, 0, graph, depth, visit_order, order)

    result = 0
    for i in range(1, N + 1):
        result += depth[i] * visit_order[i]
    return result


def main():
    N, M, R = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(M)]
    print(run_dfs(N, M, R, edges))


def run_tests():
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


if __name__ == "__main__":
    if sys.stdin.isatty():
        # 터미널 실행 시: 테스트 모드
        run_tests()
    else:
        # 백준/CI 실행 시: 실제 입력 모드
        main()
