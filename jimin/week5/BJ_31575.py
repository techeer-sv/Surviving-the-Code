# 도시가 M행 × N열의 격자로 주어짐
# 1 → 지나갈 수 있는 칸
# 0 → 건물이라 못 가는 칸
# 시작 위치: (0,0) (좌상단)
# 도착 위치: (M-1, N-1) (우하단)
# 이동은 오른쪽(→) 또는 아래쪽(↓) 으로만 가능
# 도착 가능하면 "Yes", 아니면 "No"
def solve_item(N, M, grid):
    # 방문 여부
    visited = [[False] * N for _ in range(M)]

    # 시작 위치 (항상 1이라고 보장됨)
    stack = [(0, 0)]
    visited[0][0] = True

    # DFS (스택 사용)
    while stack:
        x, y = stack.pop()

        # 도착지에 도달하면 True 반환
        if x == M - 1 and y == N - 1:
            return "Yes"

        # 이동 가능한 방향: 오른쪽, 아래
        for dx, dy in [(0, 1), (1, 0)]:
            nx, ny = x + dx, y + dy

            # 범위 안에 있고, 갈 수 있는 칸(1), 아직 방문 X
            if 0 <= nx < M and 0 <= ny < N:
                if grid[nx][ny] == 1 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    stack.append((nx, ny))

    # 끝까지 못 갔으면 No
    return "No"


def main():
    cases = [
        {
            "name": "예제 입력 1 (가능한 경우)",
            "N": 5,
            "M": 4,
            "grid": [
                [1, 1, 1, 1, 1],
                [0, 1, 0, 0, 1],
                [1, 0, 0, 0, 1],
                [0, 0, 0, 1, 1],
            ],
            "expected": "Yes",
        },
        {
            "name": "갈 수 없는 경우",
            "N": 3,
            "M": 3,
            "grid": [
                [1, 0, 0],
                [0, 0, 0],
                [0, 0, 1],
            ],
            "expected": "No",
        },
    ]

    for i, tc in enumerate(cases, 1):
        actual_result = solve_item(tc["N"], tc["M"], tc["grid"])
        assert actual_result == tc["expected"], (
            f"[{i}] {tc['name']} 실패: "
            f"expected={tc['expected']}, actual={actual_result}"
        )
        print(f"[{i}] {tc['name']} 성공")


if __name__ == "__main__":
    main()
