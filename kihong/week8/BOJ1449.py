"""백준 1449 수리공 실버"""


def solve(_list: list, k):
    board = [True] * 1001  # 배수관의 위치 배열

    _list.sort()

    cnt = 0  # 테이프 갯수

    for i in _list:
        board[i] = False  # 배수관이 뚫긴 곳 기록

    for i in _list:
        if not board[i]:  # 만약 뚫렸다면
            for idx in range(k):
                if (i + idx) < 1001:
                    board[i + idx] = True
            cnt += 1
    return cnt


def main():
    import sys

    test_case = 3
    sys.stdin = open("kihong/week8/BOJ1449.txt", "r")

    for i in range(1, test_case + 1):
        n, k = map(int, input().split())
        order = list(map(int, input().split()))
        result = solve(order, k)
        print(result)
        expect = int(input())
        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect}, actual_result={result}"
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
