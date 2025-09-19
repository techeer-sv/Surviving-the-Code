/**
 * 백준 2644번 - 촌수 계산 [dfs 알고리즘]
 *
 * [문제 요약]
 * - 부모 - 자식 사이는 1촌
 * - 이를 활용해 사람들 간 촌수를 계산
 * - 간선 하나당 1촌이라고 생각하면 된다
 *
 * [해결 방법]
 * - 결국 입력을 받으면서 그래프를 만들고, 그 그래프에서 서로를 찾아가는 횟수를 카운트 하면 된다.
 * - 이전에 ArrayList를 사용한 그래프를 다시 이용하자
 * - 이번에는 루프를 돌지않고 단순하게 시작 지점부터 도착지점까지의 간선 통과 횟수만 구하면 된다
 * - 그렇기에 visited를 다시 false로 돌릴 필요 X
 *
 * [시간 복잡도]
 *  - dfs 탐색 -> O(n+m) / n: 노드 개수, m: 간선 개수
 *  - 전체 시간 복잡도 -> O(n+m)
 */

import java.util.ArrayList;

public class Main {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int end;

    static int solve(int n, int[][] relations, int start, int target){
        // 그래프 초기화
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] r : relations) {
            int a = r[0] - 1;
            int b = r[1] - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        end = target - 1;
        visited = new boolean[n];
        return dfs(0, start - 1);
    }

    static boolean dfs(int count, int pos){
        if (pos == end){
            System.out.println(count);
            return true;
        }

        visited[pos] = true;

        for (int next : graph[pos]) {
            if (!visited[next]) {
                if (dfs(count+1, next)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] relations = {
                {1, 2}, {1, 3}, {2, 7}, {2, 8}, {2, 9}, {4, 5}, {4, 6}
        };
        int start = 7;
        int target = 3;

        // main에서는 테스트 돌리기만
        System.out.println("촌수 7 ↔ 3: " + solve(n, relations, start, target));
    }
}