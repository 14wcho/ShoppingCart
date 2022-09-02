package ProductManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import collections.shoppingcart.Product;

public class UI2 {

    private int ch = 0;
    private char choice;
    List<Product> products = new ArrayList<Product>();
    
    
    public UI2 () {
        menu();
    }
    
    public void startScreen () {
        System.out.println("1. AddProduct");
        System.out.println("2. ViewProduct");
        System.out.println("3. UpdateProduct");
        System.out.println("4. DeleteProduct");
        System.out.println("5. SearchProduct");
        System.out.println("0. Exit");
    }
    
    private int getUserInput() throws NumberFormatException {
        Scanner in = new Scanner (System.in);
        ch = Integer.parseInt(in.nextLine());
        return ch;
    }
    
    private void getUserProduct() throws NumberFormatException {
        Scanner in = new Scanner (System.in);
        System.out.println("Please enter ProductID");
        int pid = in.nextInt();
        
        System.out.println("Please enter ProductName");
        String nameString = in.next();
        
        System.out.println("Please enter ProductPrice");
        double price = in.nextDouble();
        System.out.println("Please enter ProductStock");
        int stock = in.nextInt();
        
        Product product = new Product(pid,nameString, price, stock); 
        this.products.add(product);
    }

    
    public void menu () {
        do {
            startScreen();
            getUserInput();
            
            switch (ch) {
                case 1: 
                	getUserProduct();
                    break;
                case 2:
                	displayStoreProducts();
                    break;
                case 3:
                	displayStoreProducts();
                	PickProduct();
                    break;
                case 4:
                	UserDeleteChoice();
                    break;
                case 5:
                	SearchProduct();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    
                    break;
            }
        } while (ch != 0);
    }

    public void PickProduct () {
    	Scanner in = new Scanner (System.in);
        System.out.println("Please type in ProductID");
        ch = Integer.parseInt(in.nextLine());
        Product changeproduct = getProductByProductID(ch);
        removeProductByPID(ch);
        System.out.println("A. Update Price");
        System.out.println("B. Update Stock");
        System.out.println("C. Exit");
        choice = in.nextLine().charAt(0);
        if(choice=='A' || choice=='a') {
        	System.out.println("Please enter Price");
            Double chDouble = Double.parseDouble(in.nextLine());
            changeproduct.price = chDouble;
        }
        if(choice=='B' || choice=='b') {
        	System.out.println("Please enter Stock");
            ch = Integer.parseInt(in.nextLine());
            changeproduct.stock = ch; 
        }
        if(choice=='C' || choice=='c'){
            return;
        }
        this.products.add(changeproduct);
    }

    public void removeProductByPID(int pid) {
        Product prod = getProductByProductID(pid);
        this.products.remove(prod);
    }
    
    private void UserDeleteChoice() {
    	Scanner in = new Scanner (System.in);
        System.out.println("Please type in ProductID");
        ch = Integer.parseInt(in.nextLine());
        removeProductByPID(ch);
    }
    
    private void SearchProduct() {
    	Scanner in = new Scanner (System.in);
        System.out.println("Please type in ProductID");
        ch = Integer.parseInt(in.nextLine());
        Product displayproduct = getProductByProductID(ch);
        System.out.println(displayproduct.pid + " " + displayproduct.name + " " + displayproduct.price + " " + displayproduct.stock);
    }
    private Product getProductByProductID(int pid) {
        Product product = null;
        //List<Product> products = new Products().getProducts();
        for (Product prod: products) {
            if (prod.getPid() == pid) {
                product = prod;
                break;
            }
        }
        return product;
    }


    private void displayStoreProducts() {
        for (Product prod: products) {
            System.out.println(
                    prod.getPid() + "- " +
                            prod.getName() + " " +
                            prod.getPrice() + " " +
                            prod.getStock()
            );
        }
    }
}