N = int(input())
students = list(map(int, input().split()))

students.sort()
score = []

for i in range(N):
    score.append(students[i]+students[2*N-i-1])

print(min(score))