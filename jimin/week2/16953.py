def solve(a: int, b: int) -> int:
    count = 1
    while a < b:
        if b % 2 > 0:  # 홀수
            if b % 10 == 1:  # 끝자리가 1이면
                b //= 10
                count += 1
            else:
                break
        else:  # 짝수
            b //= 2
            count += 1

    return count if b == a else -1


def main():
    testcase = [
        {"input": [2, 162], "expect": 5},
        {"input": [4, 42], "expect": -1},
        {"input": [100, 40021], "expect": 5},
    ]

    for case in testcase:
        a, b = case["input"]
        expected_result = case["expect"]
        result = solve(a, b)

        print(f"case : {case['input']}")
        print(
            f"expect = {expected_result} 맞췄습니다."
            if result == expected_result
            else f"expect = {expected_result} / result = {result} 서로 다릅니다."
        )


if __name__ == "__main__":
    main()
