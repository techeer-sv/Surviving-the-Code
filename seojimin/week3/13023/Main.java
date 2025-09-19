/**
 * 백준 13023번 - ABCDE [dfs 알고리즘]
 * <p>
 * [문제 요약]
 * - 알고리듬 캠프에 N명 참가
 * - 사람들은 0번부터 N-1번까지 번호가 매겨져 있고, 일부는 친구
 * - 다음과 같은 친구 관계를 가진 사람 A,B,C,D,E의 존재 여부를 알아보자
 * - A와 B는 친구
 * - B와 C는 친구
 * - C와 D는 친구
 * - D와 E는 친구
 *
 * [해결 방법]
 * - 1차적으로 2차원 배열로 시도했을 때 모든 노드를 반복 확인해야 하기에 시간 초과 발생 -> 인접 리스트 사용하도록 수정
 * - ArrayList를 사용할 때 각 요소를 new ArrayList<>()로 초기화 해 주어야 함
 * - 각 요소를 하나의 사람으로 보고, 요소별 연결된 연관관계를 ArrayList에 저장
 * - ArrayList의 각 요소를 돌면서 dfs 진행
 * - dfs에선 해당 사람의 연결된 사람으로 count를 높이며 타고 들어감
 * - count가 4가 되는 순간 true를 반환해 최종적으로 1 출력
 * - 이때 방문할 때 visited를 true로 바꾸고, count가 4가 되지 않아 false를 반환하게 될 때 visited도 다시 false로 되돌린다
 *
 * [시간 복잡도]
 * - 입력 받기(친구 관계 저장) -> O(M)
 * - 모든 노드에서 DFS 호출 -> O(N * k^4)
 *      - N: 노드 수(사람 수)
 *      - k: 평균 친구 수
 *      - DFS 깊이 제한이 4이므로 한 DFS 호출당 k^4번 경로 탐색
 * - 전체 시간 복잡도 = O(N * k^4)
 * - 인접 행렬 사용 시: O(N^5) → 시간 초과 가능
 * - 인접 리스트 사용 시: O(N * k^4) → 충분히 통과 가능
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {


    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int m;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int friend1 = Integer.parseInt(st.nextToken());
            int friend2 = Integer.parseInt(st.nextToken());
            graph[friend1].add(friend2);
            graph[friend2].add(friend1);
        }

        for (int i = 0; i < n; i++) {
            if(dfs(0, i)){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    static boolean dfs(int count, int x) {
        if (count == 4) {
            return true;
        }

        visited[x] = true;

        for (int next : graph[x]) {
            if (!visited[next]){
                if (dfs(count+1, next)) return true;
            }
        }

        visited[x] = false;
        return false;
    }
}



