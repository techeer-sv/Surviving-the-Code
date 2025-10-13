"""백준 2212 센서"""


def solve(_list: list, n, k):
    # k가 n보다 크거나 같으면, 모든 센서를 각각 1개의 집중국으로 커버 가능.
    if k >= n:
        return 0

    _list.sort()
    # 인접한 센서 간의 거리를 계산하여, 각 간격이 최소화하게 함.
    gaps = []
    for i in range(1, n):
        gap = _list[i] - _list[i - 1]
        gaps.append(gap)

    gaps.sort(reverse=True)

    return sum(gaps[k - 1 :])


def main():
    import sys

    test_case = 2
    sys.stdin = open("kihong/week8/BOJ2212.txt", "r")

    for i in range(1, test_case + 1):
        n = int(input())
        k = int(input())
        order = list(map(int, input().split()))
        result = solve(order, n, k)
        print(result)
        expect = int(input())
        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
