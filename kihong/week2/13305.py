# 백준 13305
# 시간 복잡도 : O(n)
"""
기본 아이디어
1) for 현재 주유소 in 모든 주유소:
        if 현재 주유소 < 최저 주유소:
            최저 주유소 비용 = 현재 주유소 비용
        전체 비용 = 최저 주유소 비용 * 다음 주유소 거리
    return 전체 비용
2) 시간 복잡도 : 각 주유소를 돌아보면서 계산을 하는 단순한 알고리즘이므로 O(n)이다.
"""


def solve_item(n, load: list, gas: list):
    # 아이디어 : 각 주유소마다 조건에 따라, 가장 적게 쓸 수 있는 수단 선택.
    min_cost = gas[0]
    result = 0
    for idx in range(n - 1):
        if gas[idx] < min_cost:
            min_cost = gas[idx]
        result += min_cost * load[idx]
    return result


def main():
    cases = [
        {
            "name": "test case 1",
            "n": 4,
            "load": [2, 3, 1],
            "gas": [5, 2, 4, 1],
            "expected": 18,
        },
        {
            "name": "test case 2",
            "n": 4,
            "load": [3, 3, 4],
            "gas": [1, 1, 1, 1],
            "expected": 10,
        },
    ]
    for i, tc in enumerate(cases, 1):
        actual_result = solve_item(tc["n"], tc["load"], tc["gas"])
        assert actual_result == tc["expected"], (
            f"[{i}] {tc['name']} 실패: "
            f"expected={tc['expected']}, actual_result={actual_result}, "
        )
        print(f"[{i}] {tc['name']} 성공")


if __name__ == "__main__":
    main()
