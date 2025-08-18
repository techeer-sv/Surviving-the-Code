def solve(n: int, students: list):
    # 결과 값
    result = 0
    students.sort()
    # 불만족도의 합을 계산
    for i in range(n):
        expect_rank = students[i]
        actual_rank = i + 1
        result += abs(actual_rank - expect_rank)
    return result


def main():
    testcase = [
        {"input": [5, [1, 5, 3, 1, 2]], "expect": 3},
        {"input": [2, [2, 2]], "expect": 1},
    ]

    for case in testcase:
        n, arr = case["input"]
        expected_result = case["expect"]
        result = solve(n, arr)

        print(f"case : {case['input']}")
        print(
            f"expect = {expected_result} 맞췄습니다."
            if result == expected_result
            else f"expect = {expected_result} / result = {result} 서로 다릅니다."
        )


if __name__ == "__main__":
    main()
