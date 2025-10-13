"""백준 18110 solved.ac
python 반올림 이슈가 있었음. round 함수는 짝수에 대해서 내림 결과가 다름.
heapq를 사용하려고 했으나, 구조적인 문제로 사용할 수가 없었음. 오히려 비 효율적인 문제라서.
"""

import math


def solve(_list: list, n):
    def round_half(n: int):
        return math.floor(n + 0.5)

    _list.sort()

    if n == 0:
        return 0

    start = round_half(n * 0.15)

    end = n - start
    cnt = n - (start * 2)

    mean_value = round_half(sum(_list[start:end]) / cnt)
    return mean_value


def main():
    import sys

    test_case = 3
    sys.stdin = open("kihong/week8/BOJ18110.txt", "r")

    for i in range(1, test_case + 1):
        n = int(input())
        listed = []
        for _ in range(n):
            listed.append(int(input()))
        result = solve(listed, n)
        expect = int(input())
        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
