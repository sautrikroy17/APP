import java.util.Scanner;
class book{
    String title;
    int price;
    String author;
void display(){
    System.out.println("Title= "+title);
    System.out.println("Author "+author);
    System.out.println("Price "+price);
}
public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    book b=new book();
    System.out.println("Enter Title");
    b.title=sc.nextLine();
    System.out.println("Enter Author");
    b.author=sc.nextLine();
    System.out.println("Enter Price");
    b.price=sc.nextInt();
    b.display();
    sc.close();
}}