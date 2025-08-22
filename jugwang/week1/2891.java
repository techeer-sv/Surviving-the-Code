import java.util.Arrays;

// 팀 객체
class Team{
    private boolean spare;
    private boolean damage;

    Team(Boolean spare, boolean damage){
        this.spare = spare;
        this.damage = damage;
    }

    public boolean getSpare(){
        return spare;
    }

    public boolean getDamage(){
        return damage;
    }

    public void setSpare(boolean spare){
        this.spare = spare;
    }

    public void setDamage(boolean damage){
        this.damage = damage;
    }
}

public class Main {
    public static int solution(int n, int[] damagedTeams, int[] spareTeams) {
        Team[] team = new Team[n];
        for (int i = 0; i < n; i++) {
            team[i] = new Team(false, false);
        }

        // 팀 파손 설정
        for (int dam : damagedTeams) {
            team[dam-1].setDamage(true);
        }
        
        // 팀 여분 설정
        for (int spr : spareTeams) {
            team[spr-1].setSpare(true);
        }

        int retired = 0;

        // 여분 대여 확인
        for (int i = 0; i < n; i++) {
            int pre = (i - 1 + n) % n;
            int pro = (i + 1) % n;
            // 파손 팀 확인
            if (team[i].getDamage()) {
                // 본인 팀 여분 확인
                if (team[i].getSpare()) {
                    // 여분 사용
                    team[i].setSpare(false);
                    team[i].setDamage(false);
                    continue;
                }
                // 앞 팀 여분 확인
                else if (team[pre].getSpare() && !team[pre].getDamage() && i != 0) {
                    // 앞 팀 여분 사용
                    team[pre].setSpare(false);
                    team[i].setDamage(false);
                    continue;
                }
                // 뒷 팀 여분 확인
                else if (team[pro].getSpare() &&  !team[pro].getDamage() && i != n-1) {
                    // 뒷 팀 여분 사용
                    team[pro].setSpare(false);
                    team[i].setDamage(false);
                    continue;
                }
                // 여분 없으면 탈락
                retired++;
            }
        }
        return retired;
    }
    
    public static void main(String[] args) {
        // Test cases from kihong/week1/2891.py
        int[] nArr = {5, 5, 6};
        int[][] damagedArr = {{2, 4}, {2, 4}, {6}};
        int[][] reserveArr = {{3}, {1, 3, 5}, {3}};
        int[] expectedResults = {1, 0, 1};

        for (int i = 0; i < nArr.length; i++) {
            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("N: " + nArr[i]);
            System.out.println("Damaged: " + Arrays.toString(damagedArr[i]));
            System.out.println("Reserve: " + Arrays.toString(reserveArr[i]));
            int result = solution(nArr[i], damagedArr[i], reserveArr[i]);
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result == expectedResults[i] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}