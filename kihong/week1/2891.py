#  2891 카약과 강풍 리뷰


import sys


def solution(n, s, r, damaged_teams, spare_teams):
    # 각 팀원 초기화
    def init_team(n, s, r):
        teams = [0] + [1] * n
        # 카야를 잃은 팀 초기화
        for i in damaged_teams:
            teams[i] = 0
        # 카야를 예비로 가져온 팀 초기화
        for i in spare_teams:
            teams[i] += 1
        return teams

    teams = init_team(n, s, r)

    # 참여를 하지 못한 팀
    result = 0

    for idx in range(1, n + 1):
        # 만약 현재 순서의 팀이 카야가 없다면
        if teams[idx] == 0:
            # 전에 있는 팀이 카야를 가지고 있는지 확인한다.
            if idx > 1 and teams[idx - 1] == 2:
                teams[idx] = 1
                teams[idx - 1] = 1
            # 전 팀에도 카야를 가지고 있는 지 확인한다.
            elif idx < n and teams[idx + 1] == 2:
                teams[idx] = 1
                teams[idx + 1] = 1
            else:
                result += 1
    return result


def main():
    # 로컬에서 여러 테스트 케이스를 처리하는 방법
    test_cases = [
        # (n, s, r, damaged_teams, spare_teams) 형식으로 테스트 케이스 추가
        # (5, 2, 1, [2, 4], [3]),
        # (5, 2, 3, [2, 4], [1, 3, 5]),
        (6, 1, 1, [6], [3]),
    ]

    for n, s, r, damaged_teams, spare_teams in test_cases:
        result = solution(n, s, r, damaged_teams, spare_teams)
        print(
            f"Test Case: n={n}, s={s}, r={r}, damaged={damaged_teams}, spare={spare_teams}"
        )
        print(f"Result: {result}\n")


if __name__ == "__main__":
    main()
