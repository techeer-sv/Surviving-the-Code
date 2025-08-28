import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int last = scanner.nextInt();
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
    }
    
    // ===== 테스트 코드 =====
    public static void runTests() {
        System.out.println("\n=== 백준 16953번 문제 테스트 시작 ===\n");
        
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
        testCase8();
        testCase9();
        testCase10();
        testImpossibleCase();
        testLargeNumbers();
        testEdgeCaseZero();
        testNegativeNumbers();
        
        System.out.println("\n=== 모든 테스트 완료 ===");
    }
    
    private static void testCase1() {
        System.out.println("테스트 케이스 1: 2 → 162");
        System.out.println("예상 결과: 연산 횟수 8");
        System.out.println("실행 결과:");
        
        int start = 2;
        int last = 162;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase2() {
        System.out.println("테스트 케이스 2: 4 → 42");
        System.out.println("예상 결과: 연산 횟수 5");
        System.out.println("실행 결과:");
        
        int start = 4;
        int last = 42;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase3() {
        System.out.println("테스트 케이스 3: 100 → 40021");
        System.out.println("예상 결과: 연산 횟수 10");
        System.out.println("실행 결과:");
        
        int start = 100;
        int last = 40021;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase4() {
        System.out.println("테스트 케이스 4: 1 → 1");
        System.out.println("예상 결과: 연산 횟수 1");
        System.out.println("실행 결과:");
        
        int start = 1;
        int last = 1;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase5() {
        System.out.println("테스트 케이스 5: 2 → 3");
        System.out.println("예상 결과: 변환 불가능 (-1)");
        System.out.println("실행 결과:");
        
        int start = 2;
        int last = 3;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase6() {
        System.out.println("테스트 케이스 6: 10 → 40");
        System.out.println("예상 결과: 연산 횟수 3");
        System.out.println("실행 결과:");
        
        int start = 10;
        int last = 40;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase7() {
        System.out.println("테스트 케이스 7: 5 → 101");
        System.out.println("예상 결과: 연산 횟수 6");
        System.out.println("실행 결과:");
        
        int start = 5;
        int last = 101;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase8() {
        System.out.println("테스트 케이스 8: 3 → 27");
        System.out.println("예상 결과: 연산 횟수 5");
        System.out.println("실행 결과:");
        
        int start = 3;
        int last = 27;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase9() {
        System.out.println("테스트 케이스 9: 7 → 77");
        System.out.println("예상 결과: 연산 횟수 5");
        System.out.println("실행 결과:");
        
        int start = 7;
        int last = 77;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testCase10() {
        System.out.println("테스트 케이스 10: 9 → 99");
        System.out.println("예상 결과: 연산 횟수 5");
        System.out.println("실행 결과:");
        
        int start = 9;
        int last = 99;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testImpossibleCase() {
        System.out.println("테스트 케이스: 2 → 7 (변환 불가능)");
        System.out.println("예상 결과: 변환 불가능 (-1)");
        System.out.println("실행 결과:");
        
        int start = 2;
        int last = 7;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testLargeNumbers() {
        System.out.println("테스트 케이스: 1 → 1000000000 (큰 수)");
        System.out.println("실행 결과:");
        
        int start = 1;
        int last = 1000000000;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testEdgeCaseZero() {
        System.out.println("테스트 케이스: 0 → 10 (경계값)");
        System.out.println("실행 결과:");
        
        int start = 0;
        int last = 10;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
    
    private static void testNegativeNumbers() {
        System.out.println("테스트 케이스: -5 → 10 (음수)");
        System.out.println("실행 결과:");
        
        int start = -5;
        int last = 10;
        System.out.println(start);
        System.out.println(last);

        int count = 1;
        while (last > start){
            if(last % 2 == 0){
                last = last / 2;
            }
            else if (last % 10 == 1){
                last = (last-1)/10;
            }
            else {
                break;
            }
            count++;
        }

        if(last==start){
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }
        System.out.println("---");
    }
}
