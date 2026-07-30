import java.util.Scanner;
class prime{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n,c=0;
        System.out.println("Enter a number");
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            if(n%i==0){
                c++;
            }}
        
        if(c==2){
            System.out.println("Prime Number");}
        else{
            System.out.println("Not a prime number");}
            sc.close();
        }}
