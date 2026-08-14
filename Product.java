import java.util.*;
class Product{
    int productId;
    String productName;
    int price;
    int quantity;

Product(int productId, String productName, int price, int quantity) {
    this.productId = productId;
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
}
void totalPrice() {
    double discount=0;
    double total = price * quantity;
    if(total>5000){
        discount= total*10/100;
        total=total-discount;
    }
    else
    {
        discount=total*5/100;
        total=total-discount;
    }
    System.out.println("Discount on " + productName + " is: " + discount);
    System.out.println("Total Price of " + productName + " is: " + total);
}
public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    Product[] product = new Product[5];
    for(int i=0; i<5; i++) {
    System.out.println("Enter Product Id: ");
    int productId = sc.nextInt();
    System.out.println("Enter Product Name: ");
    String productName = sc.next();
    System.out.println("Enter Product Price: ");
    int price = sc.nextInt();
    System.out.println("Enter Product Quantity: ");
    int quantity = sc.nextInt();
    product[i] = new Product(productId, productName, price, quantity);
    product[i].totalPrice();
    }
    sc.close();
}
}

