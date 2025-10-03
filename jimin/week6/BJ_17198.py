# B와 L 좌표 찾기.
# 맨해튼 거리를 구함:
# dist = |Bx - Lx| + |By - Ly|
# 이게 헛간 옆 ↔ 호수 옆 최소 거리.
# 그런데, 중간에 R이 있어서 최단 경로가 막히는 경우가 있어요.
# 즉, B와 L이 같은 행/열에 있고, 그 사이에 R이 있으면 돌아가야 해서 +2.
# 최종 답 = dist - 1 (소가 차지해야 할 칸 수).
# 단, 바위 때문에 우회해야 하면 dist + 1.

grid = [input().strip() for _ in range(10)]

# 좌표 찾기
for i in range(10):
    for j in range(10):
        if grid[i][j] == "B":
            bx, by = i, j
        elif grid[i][j] == "L":
            lx, ly = i, j
        elif grid[i][j] == "R":
            rx, ry = i, j

# 기본 맨해튼 거리
dist = abs(bx - lx) + abs(by - ly)

# 바위가 같은 행에 있으면서 B와 L 사이에 있을 때
if bx == lx == rx and min(by, ly) < ry < max(by, ly):
    print(dist + 1)
# 바위가 같은 열에 있으면서 B와 L 사이에 있을 때
elif by == ly == ry and min(bx, lx) < rx < max(bx, lx):
    print(dist + 1)
# 일반 경우
else:
    print(dist - 1)
