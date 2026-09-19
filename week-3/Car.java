import java.util.Scanner;
class Car{
    String model;
    double price;
    Car(){
       model="Not Available";
       price=0;
    }
    Car(String model){
        this.model=model;
        this.price=0;
    }
    Car(String model,double price){
        this.model=model;
        this.price=price;
    }
    void display(){
        System.out.println("Model Name= "+model);
        System.out.println("Price is= "+price);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter model name: ");
        String model= sc.nextLine();
        System.out.println("Enter price: ");
        double price= sc.nextDouble();
        Car car=new Car(model,price);
        car.display();
        sc.close();
    }
}
