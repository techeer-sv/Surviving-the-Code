

public class Main {
    public static void main (String[] args){
        // CI/CD 환경에서는 테스트 실행, 실제 입력이 필요한 경우는 주석 처리
        runTests();
        
        // 실제 백준 제출용 코드 (CI/CD에서는 주석 처리)
        /*
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        int work = 0;
        int tired = 0;
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        */
    }
    
    // ===== 테스트 코드 =====
    public static void runTests() {
        System.out.println("\n=== 백준 22864번 문제 테스트 시작 ===\n");
        
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
        testEdgeCases();
        testLargeNumbers();
        
        System.out.println("\n=== 모든 테스트 완료 ===");
    }
    
    private static void testCase1() {
        System.out.println("테스트 케이스 1: a=5, b=3, c=2, m=10");
        System.out.println("예상 결과: 일한 양 24");
        System.out.println("실행 결과:");
        
        int a = 5;
        int b = 3;
        int c = 2;
        int m = 10;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase2() {
        System.out.println("테스트 케이스 2: a=3, b=2, c=1, m=5");
        System.out.println("예상 결과: 일한 양 48");
        System.out.println("실행 결과:");
        
        int a = 3;
        int b = 2;
        int c = 1;
        int m = 5;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase3() {
        System.out.println("테스트 케이스 3: a=10, b=5, c=5, m=20");
        System.out.println("예상 결과: 일한 양 10");
        System.out.println("실행 결과:");
        
        int a = 10;
        int b = 5;
        int c = 5;
        int m = 20;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase4() {
        System.out.println("테스트 케이스 4: a=1, b=1, c=1, m=1");
        System.out.println("예상 결과: 일한 양 24");
        System.out.println("실행 결과:");
        
        int a = 1;
        int b = 1;
        int c = 1;
        int m = 1;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase5() {
        System.out.println("테스트 케이스 5: a=8, b=4, c=3, m=15");
        System.out.println("예상 결과: 일한 양 12");
        System.out.println("실행 결과:");
        
        int a = 8;
        int b = 4;
        int c = 3;
        int m = 15;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase6() {
        System.out.println("테스트 케이스 6: a=2, b=1, c=1, m=3");
        System.out.println("예상 결과: 일한 양 24");
        System.out.println("실행 결과:");
        
        int a = 2;
        int b = 1;
        int c = 1;
        int m = 3;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase7() {
        System.out.println("테스트 케이스 7: a=6, b=3, c=2, m=12");
        System.out.println("예상 결과: 일한 양 12");
        System.out.println("실행 결과:");
        
        int a = 6;
        int b = 3;
        int c = 2;
        int m = 12;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase8() {
        System.out.println("테스트 케이스 8: a=4, b=2, c=1, m=8");
        System.out.println("예상 결과: 일한 양 12");
        System.out.println("실행 결과:");
        
        int a = 4;
        int b = 2;
        int c = 1;
        int m = 8;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase9() {
        System.out.println("테스트 케이스 9: a=7, b=4, c=3, m=14");
        System.out.println("예상 결과: 일한 양 8");
        System.out.println("실행 결과:");
        
        int a = 7;
        int b = 4;
        int c = 3;
        int m = 14;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testCase10() {
        System.out.println("테스트 케이스 10: a=9, b=5, c=4, m=18");
        System.out.println("예상 결과: 일한 양 10");
        System.out.println("실행 결과:");
        
        int a = 9;
        int b = 5;
        int c = 4;
        int m = 18;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testEdgeCases() {
        System.out.println("테스트 케이스: 경계값 테스트");
        System.out.println("a=0, b=1, c=1, m=0");
        System.out.println("예상 결과: 일한 양 0");
        System.out.println("실행 결과:");
        
        int a = 0;
        int b = 1;
        int c = 1;
        int m = 0;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
    
    private static void testLargeNumbers() {
        System.out.println("테스트 케이스: 큰 수 테스트");
        System.out.println("a=100, b=50, c=25, m=200");
        System.out.println("실행 결과:");
        
        int a = 100;
        int b = 50;
        int c = 25;
        int m = 200;
        int work = 0;
        int tired = 0;
        
        for (int hour = 1; hour <= 24; hour++){
            if(tired + a <= m){
                work += b;
                tired += a;
            }
            else {
                tired = Math.max(0, tired - c);
            }
        }
        System.out.println(work);
        System.out.println("---");
    }
}
