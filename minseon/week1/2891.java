import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] infor = new int[3];
        for(int i=0; i<3; i++){
            infor[i] = scanner.nextInt();
        }
        int [] def = new int[infor[0]];
        for(int i=0; i<def.length; i++){
            def[i]=1;
        }
        //System.out.println("입력된 기본값: " + Arrays.toString(def));

        int [] broken = new int[infor[1]];
        for(int i=0; i<broken.length; i++){
            broken[i]=scanner.nextInt();
            def[broken[i]-1]-=1;
        }
        //System.out.println("입력된 부서진: " + Arrays.toString(broken));
        //System.out.println("입력된 기본값: " + Arrays.toString(def));

        int [] room = new int[infor[2]];
        for (int i=0; i<room.length; i++){
            room[i]=scanner.nextInt();
            def[room[i]-1]+=1;
        }
        //System.out.println("입력된 여유: " + Arrays.toString(room));
        //System.out.println("입력된 기본값: " + Arrays.toString(def));

        int count=0;
        for(int i = 0; i<def.length; i++){
            if(def[i]==0){
                if(i>0 && def[i-1]==2){
                    def[i-1]--;
                    def[i]++;
                }
                else if(i<def.length-1 && def[i+1]==2){
                    def[i+1]--;
                    def[i]++;
                }
                else {
                    count++;
                }
            }
        }
        //System.out.println("결과 기본값: " + Arrays.toString(def));
        System.out.println(count);
    }
}
