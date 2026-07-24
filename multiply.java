import java.util.Scanner;
class multiply{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.println("Enter a number from 1 to 10");
        n=sc.nextInt();
        for(int i=1;i<=10;i++){
            System.out.println(n+ " x "+ i+ " = " +(n*i));
            sc.close();
        }
    }
}