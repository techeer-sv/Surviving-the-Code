"""백준 10867 중복 빼고 정렬하기"""


def solve(_list: list, n):
    set_list = set(_list)
    return sorted(set_list)


def main():
    import sys

    test_case = 1
    sys.stdin = open("kihong/week8/BOJ10867.txt", "r")

    for i in range(1, test_case + 1):
        n = int(input())
        order = list(map(int, input().split()))
        result = solve(order, n)
        expect = list(map(int, input().split()))

        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
