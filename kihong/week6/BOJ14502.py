n, m = map(int, input().split())
board = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, input().split())
    board[a].append(b)
    board[b].append(a)


def solve(board, n, m):
    from collections import deque

    visited = [False] * (n + 1)

    def bfs(board, start):
        q = deque()
        q.append(start)
        visited[start] = True
        while q:
            current = q.popleft()
            for next in board 
