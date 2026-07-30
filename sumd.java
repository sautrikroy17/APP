import java.util.Scanner;
class sumd{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n,c=0,r;
        System.out.println("Enter a number");
        n=sc.nextInt();
        for(int i=n;i>=1;i=i/10){
            r=i%10;
            c=c+r;
        }
        System.out.println("Sum of digits= "+c);
        sc.close();
    }
}