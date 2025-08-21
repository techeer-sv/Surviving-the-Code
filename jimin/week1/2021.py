import sys
input = sys.stdin.readline

n = int(input())
k = [int(input()) for _ in range(n)]

k.sort()

score =0
for i in range(n):
    score = score + abs((i+1) - k[i])

print(score)