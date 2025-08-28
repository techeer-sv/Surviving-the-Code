# 백준 22864
# 시간 복잡도 O(1)
"""
기본 아이디어
1) for 24시간 동안:
        if 일할 경우 전체 피로치가 최대치를 넘길 경우:
            현재 피로치 -= 감소 피로치
            continue
        전체 일의 양 += 일
        전체 피로치 += 피로치
    return 최대 일의 양.
2) 시간 복잡도 : 각 주유소를 돌아보면서 계산을 하는 단순한 알고리즘이므로 O(1)이다.
"""


def solve(a, b, c, m):
    current_tired = 0
    current_work = 0
    for _ in range(24):
        if a + current_tired > m:
            current_tired -= c
            current_tired = 0 if current_tired < 0 else current_tired
            continue
        current_work += b
        current_tired += a
    return current_work


def main():
    cases = [
        {"name": "test case 1", "a": 5, "b": 3, "c": 2, "m": 10, "expected": 24},
        {"name": "test case 2", "a": 10, "b": 5, "c": 1, "m": 10, "expected": 15},
        {"name": "test case 3", "a": 11, "b": 5, "c": 1, "m": 10, "expected": 0},
    ]
    for i, tc in enumerate(cases, 1):
        actual_result = solve(tc["a"], tc["b"], tc["c"], tc["m"])
        assert actual_result == tc["expected"], (
            f"[{i}] {tc['name']} 실패: "
            f"expected={tc['expected']}, actual_result={actual_result}, "
        )
        print(f"[{i}] {tc['name']} 성공")


if __name__ == "__main__":
    main()
