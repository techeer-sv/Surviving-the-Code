def solve(n: int, k: list[int]) -> int:
    k.sort()
    score = 0
    for i in range(n):
        score = score + abs((i+1) - k[i])
    return score


def main():
    testcase = [
        {"input": (5, [1, 5, 3, 1, 2]), "expect": 3},
        {"input": (2, [2, 2]), "expect": 1},
        {"input": (3, [3, 3, 3]), "expect": 3},
        {"input": (4, [1, 2, 3, 4]), "expect": 0},
    ]

    for case in testcase:
        n, k = case["input"]
        expected_result = case["expect"]
        result = solve(n, k)

        print(f"case : {case['input']}")
        if result == expected_result:
            print(f" expect = {expected_result} 맞췄습니다.")
        else:
            print(f" expect = {expected_result} / result = {result} 서로 다릅니다.")


if __name__ == "__main__":
    main()
