def solve(N: int, S: int, R: int, brokenTeam: set[int], onemoreTeam: set[int]) -> int:
    # 내부에서만 사용할 복사본으로 작업
    brokenTeam = set(brokenTeam)
    onemoreTeam = set(onemoreTeam)

    # 자기 여분을 자기에게 쓰는 경우 제거
    both = brokenTeam & onemoreTeam
    brokenTeam -= both
    onemoreTeam -= both

    answer = 0
    # 결정적 결과를 위해 정렬 순회
    for team in sorted(brokenTeam):
        if team - 1 in onemoreTeam:
            onemoreTeam.remove(team - 1)
        elif team + 1 in onemoreTeam:
            onemoreTeam.remove(team + 1)
        else:
            answer += 1
    return answer


def main():
    testcase = [
        # 기본 예시
        {"input": (5, 2, 1, {2, 4}, {3}), "expect": 1},
        # 자기 여분을 자기에게 쓰는 팀이 있는 경우
        {"input": (5, 2, 2, {2, 4}, {1, 2}), "expect": 1},
        # 모두 빌릴 수 있는 경우
        {"input": (6, 2, 2, {2, 5}, {1, 3}), "expect": 0},
        # 한쪽에서만 빌릴 수 있는 경우
        {"input": (5, 1, 1, {3}, {2}), "expect": 0},
        # 전혀 못 빌리는 경우
        {"input": (5, 2, 0, {2, 4}, set()), "expect": 2},
        # 경계 팀 테스트
        {"input": (5, 2, 1, {1, 5}, {2}), "expect": 1},
        # 여분 팀이 여러 개지만 충돌 가능성 확인
        {"input": (7, 3, 3, {2, 4, 6}, {1, 3, 5}), "expect": 0},
    ]

    for case in testcase:
        N, S, R, brokenTeam, onemoreTeam = case["input"]
        expected = case["expect"]
        result = solve(N, S, R, brokenTeam, onemoreTeam)

        print(f"case : {case['input']}")
        if result == expected:
            print(f"expect = {expected} 맞췄습니다.")
        else:
            print(f"expect = {expected} / result = {result} 서로 다릅니다.")


if __name__ == "__main__":
    main()
