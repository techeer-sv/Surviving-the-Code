/**
 * 백준 13305번 - 주유소 [그리드 알고리즘]
 *
 * [문제 요약]
 * - 제일 왼쪽 도시에서 제일 오른쪽의 도시로 자동차를 이용해 이동
 * - 인접한 두 도시 사이 도로들은 서로 길이가 다를 수 있음
 * - 도로의 단위는 km이며, 도로를 이동할 때 1km당 1L의 기름 사용
 * - 처음 출밣할 때는 기름이 없어 주유소에서 기름을 넣고 출발해야 함
 * - 도시별로 주유소 가격이 다르며 (리터 당 가격) 가격 단위는 원 사용
 * - 각 도시를 경유하며, 주유를 자율적으로 해서 최소 비용으로 제일 오른쪽 도시로 이동하는 비용 계산
 *
 * [해결 방법]
 * - 2차원 배열로, [0]에는 도시의 순서, [1]에는 기름값을 저장
 * - 기름값을 기준으로 오름차순 정렬, 기름값이 동일하면 도시 순서를 기준으로 오름차순 정렬
 * - 정렬된 배열을 루프를 돌며, 해당 도시에서 가장 오른쪽 끝 도시까지의 거리만큼 기름값 계산
 * - 계산된 거리들은 다 0으로 설정
 * - 루프 반복
 *
 * [시간 복잡도]
 * - 배열 정렬 -> O(n log n)
 * - 정렬된 도시 루프 -> O(n^2)
 * - 전체 시간 복잡도 -> O(n^2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 도시의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] distance = new int[n-1];
        for (int i = 0; i < n - 1; i++) {
            distance[i] = Integer.parseInt(st.nextToken()); // 도시간 거리 저장
        }

        st = new StringTokenizer(br.readLine());

        int[][] oilPrice = new int[n][2];
        for (int i = 0; i < n; i++) {
            oilPrice[i][0] = i; // 해당 기름값을 보유한 도시
            oilPrice[i][1] = Integer.parseInt(st.nextToken()); // 도시 별 기름값 저장
        }
        Arrays.sort(oilPrice, (o1, o2) -> {
            if (o1[1] == o2[1]){
                return o1[0] - o2[0]; // 만약 기름값이 같으면, 마을 순서로 오름차순 정렬
            }
            return o1[1] - o2[1]; // 기름값 오름차순 정렬
        });

        long won = 0; // 0원 부터 시작, 20억을 초과할 가능성이 있어 long타입 설정

        for (int[] city : oilPrice) {
            int cityNum = city[0]; // 해당 도시의 순서
            if (cityNum == n-1) continue; // 마지막 마을은 해당사항 없으므로 생략
            int cityOilPrice = city[1]; // 해당 도시의 기름값

            // 해당 도시에서부터 제일 오른쪽 도시까지 가는 거리만큼의 기름은 해당 도시에서 전부 구매
            for (int i = cityNum; i < n-1; i++) {
                won += (cityOilPrice * distance[i]);
                distance[i] = 0// 기름값이 지불된 거리는 이제 없는 거리로 가정
            }
        }

        System.out.println(won);
    }
}