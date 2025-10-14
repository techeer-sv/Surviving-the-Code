def solve(hour, min, goal_hour, goal_min):
    hour_board = [[False] * 60 for _ in range(24)]

    h_move = [0, 1]
    m_move = [1, 0]

    def bfs(hour, min, goal_hour, goal_min):
        q = deque()
        count = 0
        q.append((hour, min, count))
        hour_board[hour][min] = True
        while q:
            current_hour, current_min, count = q.popleft()
            if current_hour == goal_hour and current_min == goal_min:
                return count
            for i in range(2):
                n_min = (current_min + m_move[i]) % 60
                n_hour = (
                    (current_hour + h_move[i] + 1) % 24
                    if i == 0 and current_min + m_move[i] == 60
                    else (current_hour + h_move[i]) % 24
                )
                if not hour_board[n_hour][n_min]:
                    hour_board[n_hour][n_min] = count + 1
                    q.append((n_hour, n_min, count + 1))

    return bfs(hour, min, goal_hour, goal_min)


from collections import deque

hour, min = map(int, input().split(":"))
goal_hour, goal_min = map(int, input().split(":"))

print(solve(hour, min, goal_hour, goal_min))


def main():
    cases = [
        {
            "hour": 11,
            "min": 57,
            "goal_hour": 12,
            "goal_min": 0,
            "expected": 3,
        },
        {
            "hour": 9,
            "min": 9,
            "goal_hour": 21,
            "goal_min": 21,
            "expected": 24,
        },
        {
            "hour": 19,
            "min": 44,
            "goal_hour": 8,
            "goal_min": 50,
            "expected": 19,
        },
    ]
    for i, tc in enumerate(cases, 1):
        result = solve(tc["hour"], tc["min"], tc["goal_hour"], tc["goal_min"])
        assert result == tc["expected"], (
            f"[{i}] 실패: " f"expected={tc['expected']}, actual_result={result}, "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
