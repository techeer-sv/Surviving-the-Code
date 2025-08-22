import java.util.Scanner;
import java.util.Arrays;
//import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int [] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        //System.out.println("입력된 소팅값: " + Arrays.toString(arr));
        int [] real = new int[n];
        for(int i=0;i<n; i++){
            real[i]+=i+1;
        }
        int [] sum = new int[n];
        for(int i=0; i<n; i++){
            sum[i] = Math.abs(real[i] - arr[i]);
        }
        long all=0;
        for(int i=0; i<n; i++){
            all += sum[i];
        }
        System.out.println(all);
    }
}
