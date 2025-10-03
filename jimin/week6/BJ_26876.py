# 시간을 hh:mm → 총 분(minutes) 으로 변환
# 예: 11:57 = 11*60 + 57 = 717분
# 현재 시각에서 목표 시각까지의 차이를 구함
#이렇게 얻은 차이(분) 를, A(+1분), B(+60분)를 이용해 최소 버튼 수로 맞추기
#d//60 → B버튼으로 채우는 횟수, d%60 → A버튼으로 채우는 횟수
def to_minutes(t):
    h, m = map(int, t.split(":"))
    return h * 60 + m

cur = input().strip()      # 현재 시각
target = input().strip()   # 목표 시각

cur_min = to_minutes(cur)
target_min = to_minutes(target)

# 차이 계산
diff = target_min - cur_min
if diff < 0:   # 음수면 다음날로 넘어가야 함
    diff += 1440

# 버튼 최소 횟수
ans = diff // 60 + diff % 60
print(ans)
