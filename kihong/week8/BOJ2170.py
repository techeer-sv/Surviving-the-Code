"""백준 2170 선 긋기"""


def solve(lines: list, n: int):
    lines.sort()
    results = []
    start = lines[0][0]
    end = lines[0][1]
    for x, y in lines[1:]:
        if start <= x and x <= end:
            if end < y:
                end = y
        elif start <= y and y <= end:
            if start > x:
                start = x
        elif start > x and y > end:
            end = y
            start = x
        else:
            results.append(abs(end - start))
            start = x
            end = y
    results.append(abs(end - start))
    return sum(results)


def main():
    import sys

    test_case = 1
    sys.stdin = open("kihong/week8/BOJ2170.txt", "r")

    for i in range(1, test_case + 1):
        n = int(input())
        lines = []
        for _ in range(n):
            x, y = map(int, input().split())
            lines.append([x, y])
        result = solve(lines, n)
        print(result)
        expect = int(input())
        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
