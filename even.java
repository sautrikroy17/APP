import java.util.Scanner;
class even{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter a number");
        n=sc.nextInt();
        if(n%2==0)
            System.out.println("Even number");
        else    
            System.out.println("Odd number");
        sc.close();
    }
}
        
    