import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int teams = scanner.nextInt();
        int[] students = new int[2*teams];

        for(int i = 0; i<2*teams; i++){
            students[i] = scanner.nextInt();
        }
        Arrays.sort(students);
        //System.out.println("입력된 코딩 역량 배열: " + Arrays.toString(students));
        scanner.close();

        int[] part1 = new int[teams];
        int[] part2 = new int[teams];
        for(int i =0; i<teams; i++){
            part1[i]=students[i];
            part2[i]=students[teams+i];
        }
        //System.out.println("입력된 코딩 역량 배열: " + Arrays.toString(part1));
        //System.out.println("입력된 코딩 역량 배열: " + Arrays.toString(part2));
        int[] sum = new int[teams];
        for(int i =0; i<teams; i++){
            sum[i]=part1[i]+part2[part2.length -1 -i];
        }
        Arrays.sort(sum);
        System.out.println(sum[0]);
    }

}