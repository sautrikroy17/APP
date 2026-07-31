import java.util.Scanner;
class rectangle{
    float length;
    float breadth;
    void display(){
        float area=length*breadth;
        System.out.println("Area of rectangle= "+area);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        rectangle r= new rectangle();
        System.out.println("Enter length ");
        r.length=sc.nextFloat();
        System.out.println("Enter breadth");
        r.breadth=sc.nextFloat();
        r.display();
        sc.close();
    }
}