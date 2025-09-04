# 체력이 m이 넘으면 0을 출력 0을 넘지않으면 다음으로 넘어감
# 24번 루프를 돌기
# 만약에 기존 체력에다가 a를 더했을 때 m을 넘게 되면 기존 체력에다가 c를 뺴줌
# 아니라면 기존체력에다가 a를 더해주고 한일에다가 b를 더해줌

# 시간 복잡도: O(1)


def solve(a: int, b: int, c: int, m: int) -> int:
    work = 0
    gage = 0

    if a > m:  # 아예 일 못하는 경우
        return 0

    for _ in range(24):
        if gage + a > m:  # 피로도 초과하면 휴식
            gage = max(0, gage - c)
        else:  # 일하기
            work += b
            gage += a

    return work


def main():
    testcase = [
        {"input": [5, 3, 2, 10], "expect": 24},
        {"input": [10, 5, 1, 10], "expect": 15},
        {"input": [11, 5, 1, 10], "expect": 0},
    ]

    for case in testcase:
        a, b, c, m = case["input"]
        expected_result = case["expect"]
        result = solve(a, b, c, m)

        print(f"case : {case['input']}")
        print(
            f"expect = {expected_result} 맞췄습니다."
            if result == expected_result
            else f"expect = {expected_result} / result = {result} 서로 다릅니다."
        )


if __name__ == "__main__":
    main()
