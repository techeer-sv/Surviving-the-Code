/**
 * 백준 11581번 - 구호 물자 [dfs 알고리즘]
 *
 * [문제 요약]
 * - 강력한 폭풍 인천 강타
 * - 인천에 구호물자 보내려 하는데, 인천의 모든 길은 교차로와 도로만으로 이뤄짐
 * - 한 교차로와다른 교차로는 일반통행 도로로 연결
 * - 한 교차로가 여러 교차로와 연결될 수 있음
 * - 도로에 한 번 진입하면 교차로에 도착할 때 까지 도로 탈주 불가
 * - 이미 지나간 교차로를 다시 방문해야하는 경우가 있는지 없는지 판단하는 프로그램 작성
 * - 결국 1번 교차로에서 N번 교차로로 가는 동안 이미 지나간 교차로를 방문하는 경우가 발생할 가능성이 있다면 CYCLE
 *
 * [해결 방법]
 * - 일단 이미 지나간 곳 재방문 안한다는 점에서 방문 기록
 * - 그리고 단방향이기 때문에, 그래프를 사용하면서 하나의 노드에만 add()
 * - dfs를 활용해 순환이 있는지를 판단해 출력
 *
 * [시간 복잡도]
 * - 각 노드 확인 -> O(n)
 * - 모든 간선 확인 -> O(m)
 * - 전체 시간 복잡도 -> O(n + m)
 */

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    static String solve(int n, String[] inputLines){
        // 그래프 초기화
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 연결 상태 세팅
        for (int i = 0; i < n - 1; i++) {
            String[] parts = inputLines[i].split(" ");
            int m = Integer.parseInt(parts[0]);
            for (int j = 1; j <= m; j++) {
                graph[i].add(Integer.parseInt(parts[j]) - 1); // 0-based
            }
        }

        visited = new boolean[n];
        return dfs(0) ? "CYCLE" : "NO CYCLE";
    }

    static boolean dfs(int node){
        if (visited[node]) {
            return true;
        }
        visited[node] = true;

        for (int next : graph[node]) {
            if (dfs(next)) return true;
        }

        visited[node] = false;

        return false;
    }

    public static void main(String[] args) {
        String[] test1 = {
                "2 2 3",
                "1 3"
        };

        String[] test2 = {
                "2 2 3",
                "1 3"
        };

        System.out.println("Test 1: " + solve(3, test1));
        System.out.println("Test 2: " + solve(3, test2));
    }
}

