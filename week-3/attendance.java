import java.util.Scanner;
class attendance{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int p=0;
        System.out.println("Enter attendance values");
        int[] att=new int[7];
        for(int i=0;i<7;i++){
            att[i]=sc.nextInt();
            if(att[i]==1)
                p++;
        }
        if(p>=5.25){
            System.out.println("Eligible");
        }
        else{
            System.out.println("Not Eligible");
        }
        sc.close();
    }}
    


        
    
