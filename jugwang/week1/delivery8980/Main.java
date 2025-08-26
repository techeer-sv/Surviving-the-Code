import java.util.PriorityQueue;

// 박스 클래스
class Box {
    public int fromVillage;
    public int toVillage;
    public int volume;

    public Box(int fromVillage, int toVillage, int volume) {
        this.fromVillage = fromVillage;
        this.toVillage = toVillage;
        this.volume = volume;
    }
}

public class Main {
    public static int solution(int numVillages, int maxVolume, int[][] boxes) {
        // 박스 큐
        PriorityQueue<Box> boxQue = new PriorityQueue<>(
                (b1, b2) -> {
                    if (b1.toVillage == b2.toVillage) {
                        return b1.fromVillage - b2.fromVillage;
                    }
                    return b1.toVillage - b2.toVillage;
                }
        );

        // 박스 추가
        for (int[] boxInfo : boxes) {
            boxQue.add(new Box(boxInfo[0], boxInfo[1], boxInfo[2]));
        }

        // 완료된 박스 수
        int finished = 0;
        // 구간별 박스 수
        int[] load = new int[numVillages + 1];

        while (!boxQue.isEmpty()) {
            Box box = boxQue.poll();

            // 이 박스를 실을 수 있는 최대 추가 적재량(구간 남은 용량의 최솟값)
            int minAvailable = maxVolume;
            for (int i = box.fromVillage; i < box.toVillage; i++) {
                minAvailable = Math.min(minAvailable, maxVolume - load[i]);
            }

            int cargo = Math.min(box.volume, minAvailable);
            if (cargo <= 0) continue;

            // 구간에 실제로 싣기
            for (int i = box.fromVillage; i < box.toVillage; i++) {
                load[i] += cargo;
            }
            finished += cargo;
        }
        return finished;
    }

    public static void main(String[] args) {
        // Example 1
        int numVillages1 = 4;
        int maxVolume1 = 40;
        int[][] boxes1 = {
                {3, 4, 20},
                {1, 2, 10},
                {1, 3, 20},
                {1, 4, 30},
                {2, 3, 10},
                {2, 4, 20}
        };
        int expected1 = 70;
        int result1 = solution(numVillages1, maxVolume1, boxes1);
        System.out.println("--- Test Case 1 ---");
        System.out.println("Expected: " + expected1 + ", Result: " + result1);
        System.out.println(result1 == expected1 ? ">>> SUCCESS" : ">>> FAILURE");
        System.out.println();

        // Example 2
        int numVillages2 = 6;
        int maxVolume2 = 60;
        int[][] boxes2 = {
                {1, 2, 30},
                {2, 5, 70},
                {5, 6, 60},
                {3, 4, 40},
                {1, 6, 40}
        };
        int expected2 = 150;
        int result2 = solution(numVillages2, maxVolume2, boxes2);
        System.out.println("--- Test Case 2 ---");
        System.out.println("Expected: " + expected2 + ", Result: " + result2);
        System.out.println(result2 == expected2 ? ">>> SUCCESS" : ">>> FAILURE");
    }
}
