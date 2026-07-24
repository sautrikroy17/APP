import java.util.Scanner;
class sum{
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a,b;
        System.out.println("Enter two numbers");
        a=sc.nextInt();
        b=sc.nextInt();
        int sum=a+b;
        System.out.println("Sum of two numbers is: "+sum);
        sc.close();
    }
}