import java.util.Scanner;
class area{
    double area1,area2,area3;
    void Areaa(int s){
        area1=(s*s);
    }
    void Areaa(double l, double b){
        area2=(l*b);
    }
    void Areaa(double r){
        area3=(3.14)*r*r;
    }
    void display(){
        System.out.println("Area of square= "+area1);
        System.out.println("Area of rectangle= "+area2);
        System.out.println("Area of circle= "+area3);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Side");
        int side=sc.nextInt();
        System.out.println("Enter length and breadth");
        double l=sc.nextDouble();
        double b=sc.nextDouble();
        System.out.println("Enter radius= ");
        double r=sc.nextDouble();
        area a=new area();
        a.Areaa(side);
        a.Areaa(l,b);
        a.Areaa(r);
        a.display();
        sc.close();
    }
}
