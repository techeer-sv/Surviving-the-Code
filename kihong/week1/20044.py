import sys

MAX = sys.maxsize


def solve(n, students: list):
    groups = []
    result = MAX
    for _ in range(n):
        # 현재 남아있는 학생 중, 가장 작은 학생과 가장 큰 학생과 매칭
        min_num = min(students)
        max_num = max(students)
        group = [min_num, max_num]
        # 이후 그룹화한 학생은 제거
        students.remove(min_num)
        students.remove(max_num)
        # 결과 중 가장 작은 코딩 역량을 가진 팀을 색출
        groups.append(group)
        result = min(sum(group), result)
    return result


def main():
    test_case = [
        {"input": [2, [1, 7, 5, 8]], "expect": 9},
        {"input": [3, [1, 7, 3, 5, 9, 2]], "expect": 8},
    ]
    for case in test_case:
        result = solve(case["input"][0], case["input"][1])
        if result == case["expect"]:
            print(f"{case} 맞췄습니다.")


if __name__ == "__main__":
    main()
