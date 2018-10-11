package in.codingninjas.expensemanager;

/**
 * Created by manishakhattar on 12/02/17.
 */

public class Expense {


    String title;
    double price;
    int quantity;
    String date;
    String category;

    Expense(String title, String category, int quantity){
        this.title = title;
        this.category = category;
        this.quantity = quantity;
        this.date = "";

    }

}
