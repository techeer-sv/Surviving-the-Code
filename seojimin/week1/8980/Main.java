/**
 * 백준8980번 - 택배 [그리디 알고리즘]
 *
 * [문제 요약]
 * - 왼쪽에서부터 오른족으로 1번부터 차례대로 번호가 붙여진 마을
 * - 마을에 있는 물건 배송을 위한 트럭 존재, 트럭 본부는 1번 마을 왼쪽
 * - 트럭은 본부에서 출발해 1번 마을부터 마지막 마을까지 물건 배송
 * - 지나간 마을은 돌아가지 않고, 박스를 트럭에 실으면 배송 마을에서만 내림
 * - 박스들 중 일부만 배송할 수도 있음
 * - 트럭에 박스를 담을 수 있는 한계 용량 존재, 최대한 많은 박스들을 배송하고 싶다
 * -
 *
 * [해결 방법]
 *      [잘못된 해결 방법]
 * - 첫번째 마을에서 배송하게 오름차순 정렬
 * - 가까운 마을로 배송되는 택배 우선 싣기
 * - 남은 용량만큼 다른 택배 있으면 싣기
 *
 *      [올바른 해결 방법]
 * - 도착지가 가까운 순서로 오름차순 정렬
 * - 만약 도착지가 동일하면 출발지로 오름차순 정렬
 * - 구간별로 적재 가능 용량 상태 관리(int[] capacity)
 * - 도착지까지 가는 구간 (출발지 ~  도착지)동안 적재할 수 있는 최소 용량 만큼만 적재 가능
 * - 최소 적재 가능 용량과 배송 택배 양 중 더 적은 것을 트럭에 적재
 * - 적재된 양 만큼 해당 구간(출발지 ~  도착지)들의 적재 가능 용량 감소 업데이트
 * - 적재한 양 만큼 결과 값 합산
 *
 * [시간 복잡도]
 * - 택배 정렬 -> 택배 개수 m, O(m log m)
 * - 택배 적재 시 구간 최소 용량 확인 -> O(m * n), 택배 개수 m, 구간 확인 최악의 경우 n
 * - 구간 용량 감소 -> O(m * n), 택배 개수 m, 구간 길이 최악의 경우 n
 * - 전체 시간 복잡도 -> O(m log m) + O(m * n) -> O(m * n)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 마을 수

        int c = Integer.parseInt(st.nextToken()); // 트럭 용량

        // 택배 배송 정보 개수 m
        int m = Integer.parseInt(br.readLine());
        List<int[]> parcels = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int send = Integer.parseInt(st.nextToken());
            int receive = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            parcels.add(new int[]{send, receive, amount});
        }

        // 도착지 오름차순 정렬, 도착지가 같으면 출발지 오름차순 정렬
        parcels.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int result = 0;

        int[] capacity = new int[n]; // 인덱스 별(마을 별) 용량
        Arrays.fill(capacity,c); // 초기값 최대 용량으로 세팅

        for (int[] p : parcels) {
            int send = p[0];
            int receive = p[1];
            int amount = p[2];

            // send~receive 사이 구간에서 가장 작은 잔여 용량 찾기
            // 그 용량만큼만 적재할 수 있기 때문
            int minRemain = Integer.MAX_VALUE;
            for (int i = send; i < receive; i++) {
                minRemain = Math.min(minRemain, capacity[i]);
            }

            // 찾아낸 적재 가능 용량만큼 구간들에 적재
            int canLoad = Math.min(minRemain, amount); // 적재 가능 용량과 택배 수 중 더 적은 수 만큼 적재 가능
            for (int i = send; i < receive; i++) {
                capacity[i] = capacity[i] - canLoad;
            }

            result += canLoad;
        }

        System.out.println(result);
    }
}