import java.util.Scanner;
class palindrome{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n,c=0,r;
        System.out.println("Enter a number");
        n=sc.nextInt();
        for(int i=n;i>=1;i=i/10){
            r=i%10;
            c=c*10+r;
        }
        if(c==n)
            System.out.println("Palindrom number");
        else
            System.out.println("Not Palindrome");
        sc.close();

    }
}