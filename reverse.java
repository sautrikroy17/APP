import java.util.Scanner;
class reverse{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n,r,c=0;
        System.out.println("Enter a number");
        n=sc.nextInt();
        for(int i=n;i>=1;i=i/10){
            r=i%10;
            c=c*10+r;
    }
    System.out.println("Reversed Number= "+c);
    sc.close();
}
}