package in.codingninjas.expensemanager;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by manishakhattar on 12/02/17.
 */

@Table(name = "Expenses" )
public class Expense extends Model{

    // No more needed because active android has a id field
//    @Column(name = "expense_id")
//    int id;
    @Column(name = "title")
    String title;
    @Column(name = "price")
    double price;
    @Column(name = "quantity")
    double quantity;
    @Column(name = "date")
    long date;
    @Column(name = "category")
    String category;


    Expense(){
        super();
    }

    Expense(String title, String category, double price, double quantity, long date){

        this.title = title;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.date = date;

    }

}
