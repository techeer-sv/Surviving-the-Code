"""백준 11581 문제

문제 아이디어
세 가지의 상태로 나누고, 각 노드별로 자식을 확인하면서 결국 사이클이
만들어지는 경우를 확인하고 문제를 풂.

시간 복잡도는 각 노드 확인 O(n), 모든 간석 확인 O(e) 이므로
시간 복잡도는 O(n)이다.
"""


def solve(graph, n):

    def dfs(start, graph):
        stack = [start]
        # 0: unvisited, 1: visiting, 2: visited
        visited = [0] * (n + 1)
        while stack:
            current_node = stack[-1]

            # 현재 노드를 처음 방문하는 경우
            if visited[current_node] == 0:
                visited[current_node] = 1  # 'visiting' 상태로 변경

            next_node = None

            # 현재 노드의 인접 노드들을 순회
            for neighbor in graph[current_node]:
                if visited[neighbor] == 1:
                    return True
                elif visited[neighbor] == 0:
                    # 아직 방문하지 않은 노드가 있다면 스택에 추가하고 다음 반복으로 넘어감
                    next_node = neighbor
                    break

            if next_node:
                stack.append(next_node)
            elif next_node == n:
                continue
            else:
                # 더 이상 갈 곳이 없는 경우 (백트래킹)
                # 현재 노드를 'visited' 상태로 변경하고 스택에서 pop
                visited[current_node] = 2
                stack.pop()
        return False

    return "CYCLE" if dfs(1, graph) else "NO CYCLE"


def main():
    cases = [
        {
            "n": 3,
            "graph": [[], (2, 3), [3], []],
            "expected": "NO CYCLE",
        },
        {
            "n": 3,
            "graph": [[], [2, 3], [1, 3], []],
            "expected": "CYCLE",
        },
        {
            "n": 3,
            "graph": [[], (2, 3, 2), [3], []],
            "expected": "NO CYCLE",
        },
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["graph"], tc["n"])
        assert tc["expected"] == result, (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
