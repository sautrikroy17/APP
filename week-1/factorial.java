import java.util.Scanner;
class factorial{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int f=1,n;
        System.out.println("Enter a number");
        n=sc.nextInt();
        for(int i=n;i>=1;i--){
            f=f*i;}
        System.out.println("Factorial of " +n +" = " +f);
    sc.close();
}
}