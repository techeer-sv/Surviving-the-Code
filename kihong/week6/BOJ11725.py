def solve(_list, n):
    from collections import deque

    def bfs(_list, start):
        visited = [0] * (n + 1)
        q = deque([start])
        visited[start] = 1
        while q:
            current = q.popleft()
            for next in _list[current]:
                if not visited[next]:
                    visited[next] = current
                    q.append(next)
        return visited[2:]

    return bfs(_list, 1)


def main():
    cases = [
        {
            "n": 7,
            "_list": [
                [],
                [6, 4],
                [4],
                [6, 5],
                [1, 2, 7],
                [3],
                [1, 3],
                [4],
            ],
            "expected": [4, 6, 1, 3, 1, 4],
        }
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["_list"], tc["n"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
