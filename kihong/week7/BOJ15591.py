"""백준 15591
각 농부의 질문마다 새로  BFS 알고리즘을 수행하여
동영상마다 몇 개의 동영상이 추천될 수 있는지 파악.
이후 기록
시간 복잡도는 O(qv)

"""


def solve(n, board, cost, video):
    import sys
    from collections import deque

    MAX_SIZE = sys.maxsize

    # bfs 알고리즘을 활용하여 간선의 정보를 업데이트하면서 조사
    def bfs(video):
        cnt = 0
        # 방문 배열 선언
        visited = [False] * (n + 1)
        # 첫 번째 배열은 -1로 초기화, 방문했음을 표현함과 동시에, 알고리즘을 고려하여 -1 선택
        visited[video] = -1

        weight = MAX_SIZE

        q = deque()
        q.append((video, weight))

        while q:
            video, weight = q.popleft()
            # 방문 가능한 모든 노드 조회
            for i in board[video]:
                if not visited[i[0]]:
                    # 간선 정보 업데이트
                    new_weight = min(weight, i[1])
                    # 만약 최소 USADO보다 클 경우에만 큐에 업데이트, 이전의 경우에는 굳이 정보를 기록할 필요가 없으므로 가지치기
                    if new_weight >= cost:
                        cnt += 1
                        visited[i[0]] = True
                        q.append([i[0], new_weight])
        return cnt

    return bfs(video)


def main():
    import sys

    sys.stdin = open("kihong/week7/BOJ15591.txt", "r")

    for i in range(1, 2):

        n, q = map(int, input().split())
        board = [[] for _ in range(n + 1)]
        for _ in range(n - 1):
            a, b, r = map(int, input().split())
            board[a].append([b, r])
            board[b].append([a, r])
        result = []
        for _ in range(q):
            cost, video = map(int, input().split())
            result.append(solve(n, board, cost, video))

        # 결과 값
        expect = list(map(int, input().split()))

        assert result == expect, (
            f"[{i}] 실패: " f"expected={expect} , actual_result={result} "
        )
        print(f"test [{i}] 성공")


if __name__ == "__main__":
    main()
