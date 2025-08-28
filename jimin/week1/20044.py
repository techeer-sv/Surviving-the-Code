def solve(N: int, students: list[int]) -> int:
    students.sort()
    score = []
    for i in range(N):
        score.append(students[i] + students[2 * N - i - 1])
    return min(score)


def main():
    testcase = [
        # 예시 1: 짝을 지어 최소합 찾기
        {"input": (2, [1, 7, 5, 9]), "expect": 10},
        # 예시 2: 모두 같은 값
        {"input": (3, [5, 5, 5, 5, 5, 5]), "expect": 10},
        # 예시 3: 큰 값과 작은 값이 섞인 경우
        {"input": (3, [1, 2, 3, 10, 11, 12]), "expect": 13},
        # 예시 4: 최소값이 앞뒤 짝에서 나오는 경우
        {"input": (4, [1, 2, 3, 4, 5, 6, 7, 8]), "expect": 9},
        # 예시 5: 학생 수가 1일 때
        {"input": (1, [100, 200]), "expect": 300},
    ]

    for case in testcase:
        N, students = case["input"]
        expected = case["expect"]
        result = solve(N, students)

        print(f"case : {case['input']}")
        if result == expected:
            print(f" expect = {expected} 맞췄습니다.")
        else:
            print(f" expect = {expected} / result = {result} 서로 다릅니다.")


if __name__ == "__main__":
    main()
