"""백준 2644
stack = [(현재 노드, count)]
while stack:
    현재 노드, count = stack.pop()
    if 찾고자 하는 노드 == 현재 노드:
        return count
    if 자식이 있을 경우:
        각 자식 노드 방문
        continue
    만약 부모를 방문하지 않았을 경우 부모 노드 방문
        continue
시간 복잡도는 O(n^2) 즉, O(n^2)이다. => while 문 내에 부모를
찾는 O(n)의 시간 복잡도가 있으므로 O(n)*O(n)의 시간 복잡도를 지님.

해당 문제는 DFS로 풀었기 때문에 다음과 같은 결과가 나왔다. 만약 BFS로
풀었다면 시간 복잡도는 O(n)이 나오게 될 것이다.
"""


def solve(n, find_person, find_person_2, relation: list):

    def dfs(find_person, find_person_2, relation, count):
        stack = []
        stack.append([find_person, count])
        visited = [False] * (n + 1)
        visited[find_person] = True
        while stack:
            # 현재 노드 반환
            current, count = stack.pop()

            # 만약 찾고자 하는 노드를 찾을 경우 return count
            if current == find_person_2:
                return count

            # 만약 자식이 있을 경우
            if relation[current]:
                for child in relation[current]:
                    if not visited[child]:
                        visited[child] = True
                        stack.append([child, count + 1])

            # 부모를 찾아서 append
            for idx in range(1, n + 1):
                if current in relation[idx] and not visited[idx]:
                    visited[idx] = True
                    stack.append([idx, count + 1])
                    break

    result = dfs(find_person, find_person_2, relation, 0)

    return result if result else -1


def main():
    cases = [
        {
            "n": 9,
            "find_person": 7,
            "find_person_2": 3,
            "relations_num": 7,
            "relation": [[], [2, 3], [7, 8, 9], [], [5, 6], [], [], [], [], []],
            "expected": 3,
        }
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(
            tc["n"],
            tc["find_person"],
            tc["find_person_2"],
            tc["relation"],
        )
        assert result == tc["expected"], print(
            f"Error : True= {tc["expected"]} / False = {result}"
        )

        print(f"{i} case 통과")


if __name__ == "__main__":
    main()
