# 최소값을 일단 price[0]으로 넣기
# price리스트의 원소를 하나씩 옮겨가면서
# 원소가 min값보다 클경우 min에다가 해당 거리를 곱해서 result에 넣어주기
# 작은 경우는 min값을 해당 원소로 교체해서 해당 거리르 곱해서 result에 넣어주기

# 시간복잡도 o(n)


def solve(n: int, dist: list, price: list) -> int:
    result = 0
    min_price = price[0]

    for i in range(n - 1):
        if price[i] < min_price:
            min_price = price[i]
        result += min_price * dist[i]

    return result


def main():
    testcase = [
        {
            "input": [4, [2, 3, 1], [5, 2, 4, 1]],
            "expect": 18,
        },
        {
            "input": [4, [3, 3, 4], [1, 1, 1, 1]],
            "expect": 10,
        },
    ]

    for case in testcase:
        n, dist, price = case["input"]
        expected_result = case["expect"]
        result = solve(n, dist, price)

        print(f"case : {case['input']}")
        print(
            f"expect = {expected_result} 맞췄습니다."
            if result == expected_result
            else f"expect = {expected_result} / result = {result} 서로 다릅니다."
        )


if __name__ == "__main__":
    main()
