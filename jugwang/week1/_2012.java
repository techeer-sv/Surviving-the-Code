import java.util.Arrays;

class Student{
    private int ownRank;


    //불만도 계산
    public int calculate(int assiRank) {
        // 불만도 계산
        int complaint = Math.abs(assiRank - ownRank);
        return complaint;
    }

    public int getOwnRank() {
        return ownRank;
    }
    public Student setOwnRank(int ownRank) {
        this.ownRank = ownRank;
        return this;
    }
}

public class _2012 {
    public static long solution(int num, int[] ranks) {
        Student[] students = new Student[num];
        // 학생 수만큼 반복하며 학생 순위 입력받기
        for (int i = 0; i<num; i++){
            students[i] = new Student().setOwnRank(ranks[i]);
        }

        // 학생 순위 정렬
        Arrays.sort(students, (s1, s2) -> s1.getOwnRank() - s2.getOwnRank());

        // 불만도 합 계산
        long sumComplaint = 0;
        for (int j=0; j<num; j++) {
            sumComplaint += students[j].calculate(j+1);
        }
        return sumComplaint;
    }

    public static void main(String[] args) {
        // Test cases
        int[] nArr = {5, 2};
        int[][] ranksArr = {
            {1, 5, 3, 1, 2},
            {2, 2}
        };
        long[] expectedResults = {3, 1};

        for (int i = 0; i < nArr.length; i++) {
            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("N: " + nArr[i]);
            System.out.println("Ranks: " + Arrays.toString(ranksArr[i]));
            long result = solution(nArr[i], ranksArr[i]);
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result == expectedResults[i] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}