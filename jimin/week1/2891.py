N, S, R = map(int, input().split())
brokenTeam = set(map(int, input().split()))
onemoreTeam = set(map(int, input().split()))

# 1. 자기 여분을 자기 자신이 쓰는 경우 제거
both = brokenTeam & onemoreTeam
brokenTeam -= both
onemoreTeam -= both

answer = 0
for team in brokenTeam:
    if team - 1 in onemoreTeam:  # 왼쪽 팀이 빌려줄 수 있으면
        onemoreTeam.remove(team - 1)
    elif team + 1 in onemoreTeam:  # 오른쪽 팀이 빌려줄 수 있으면
        onemoreTeam.remove(team + 1)
    else:  # 못 빌림
        answer += 1

print(answer)
