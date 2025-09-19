/**
 * 백준2891번 - 카약과 강풍 [그리디 알고리즘]
 *
 * [문제 요약]
 * - 카약 대회에 N개의 팀 출전
 * - 강풍으로 인 일부 카약 S개 파손
 * - 여분을 가지고 있는 R개의 팀
 * - 여분을 빌려 줄 수 있으나 이는 양옆의 인접한 팀에 한해서만 가능
 * - 카약을 빌린다고 할때 출발하지 못하는 팀이 최소가 되는 값
 *
 * [해결 방법]
 * - 파손되지 않은 카약은 우선 출전 가능
 * - 파손된 카약은 0으로, 여분을 보유한 카약은 2로 설정 (디폴트는 1)
 * - 루프를 돌며, 현재 2인 팀은 다음 팀의 파손 여부를 체크해 나눠줄지 결정
 * - 루프를 돌며, 현재 0인 팀은 다음 팅의 여분 보유 여부를 체크해 뺏어올지 결정
 * - 최종적으로 출전 불가 팀 카운트
 *
 * [시간 복잡도]
 * - 카약 분배 루프 -> O(N)
 * - 결과 찾기 루프 -> O(N)
 * - 전체 시간 복잡도 -> O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOExecption {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        // 첫 번째 줄 입력
        st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 전체 카약 수
        int s = Integer.parseInt(st.nextToken()); // 파손 카약 수
        int r = Integer.parseInt(st.nextToken()); // 여분 카약 보유 수

        // 두 번째 줄 입력
        int[] kayak = new int[n];
        Arrays.fill(kayak, 1); // 기본 카약 개수 1개로 설정
        st= new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()){
            kayak[Integer.parseInt(st.nextToken()) - 1] = 0; // 카약이 파손된 팀은 보유 카약 0으로 설정
        }

        // 세 번째 줄 입력
        st= new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()){
            kayak[Integer.parseInt(st.nextToken()) - 1]++; // 여분의 카약을 보유한 팀은 카약의 수 1 증가
        }

        for (int i = 0; i < n-1; i++) {
            // 만약 여분의 카약을 갖고 있다면
            if (kayak[i] == 2){
                // 다음 팀 카약 파손 여부를 확인해, 파손되었다면 넘겨주자
                if (kayak[i+1] == 0){
                    kayak[i+1]++;
                    kayak[i]--;
                }
                continue;
            }

            // 카약이 파손된 팀 일때
            if (kayak[i] == 0){
                // 만약 오른쪽에 있는(1이 더 큰) 팀에 여분의 카약이 있다면 가져오자
                if (kayak[i+1] == 2){
                    kayak[i]++;
                    kayak[i+1]--;
                }
            }
        }

        // 출전 불가 팀 체크
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (kayak[i] == 0) result++;
        }

        System.out.println(result);
    }

}