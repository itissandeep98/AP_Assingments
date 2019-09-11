import java.util.*;
interface User {
    public static final String name="NONE";
    public void search_category(String cat);
    public void display();
    public int display_reward();
    public void display_list();
}

class Merchant implements User{
    private String name;
    private double income;
    private int slot;
    private List<Item> Item_list;

    Merchant(String name){
        this.name=name;
        this.income=0;
        this.slot=0;
        this.Item_list=new ArrayList<Item>();
    }

    public void setSlot(int slot) {
    	this.slot = slot;
    }
    public String getName() {
        return this.name;
    }

    public double getIncome() {
    	return this.income;
    }
    public void setIncome(double income) {
    	this.income = income;
    }
    public void add_item(Item it){
        for(Item i : this.Item_list){
            if(i.getName().equals(it.getName())){
                System.out.println("error in adding (duplicate element found)");
                return;
            }
        }
        this.Item_list.add(it);
        it.setSeller(this);
    }

    public boolean check_id(int id){
        for(Item i : this.Item_list){
            if(i.getUnique_id()==id){
                return true;
            }
        }
        return false;
    }
    public void update_item(int id,int price,int quantity){
        for(Item i : this.Item_list){
            if(i.getUnique_id()==id){
                i.setPrice(price);
                i.setQuantity(quantity);
                i.display();
                break;
            }
        }
    }
    public void update_item(int uid,String offer){
        for(Item i : this.Item_list){
            if(i.getUnique_id()==uid){
                i.setOffer(offer);
                break;
            }
        }
    }
    public void display_item(int uid){
        for(Item i : this.Item_list){
            if(i.getUnique_id()==uid){
                i.display();
                break;
            }
        }
    }
    @Override
    public void search_category(String category){
        for(Item i : this.Item_list){
            if(i.getCategory().equals(category)){
                i.display();
            }
        }
    }
    @Override
    public void display(){
        System.out.println(this.name+" "+(0.005*this.income));

    }
    @Override
    public void display_list(){
        for(Item i: this.Item_list){
            i.display();
        }
    }
    @Override
    public int display_reward(){
        return this.slot;
    }

}

class Customer implements User{
    private String name;
    private int main_money;
    private int reward_money;
    private int number_of_orders;
    private List<Item> history_of_orders;
    public List<Item> cart;

    Customer(String name){
        this.name=name;
        this.number_of_orders=0;
        this.main_money=100;
        this.reward_money=0;
        history_of_orders=new ArrayList<Item>();
        cart=new ArrayList<Item>();
    }

    public int getReward_money() {
    	return this.reward_money;
    }
    public int getNumber_of_orders() {
    	return this.number_of_orders;
    }
    public void setNumber_of_orders(int number_of_orders) {
    	this.number_of_orders = number_of_orders;
    }
    public int getMain_money() {
    	return this.main_money;
    }
    public void setMain_money(int main_money) {
    	this.main_money = main_money;
    }
    public void setReward_money(int reward_money) {
    	this.reward_money = reward_money;
    }
    public String getName() {
    	return this.name;
    }
    public void update_cart(Item it){
        cart.add(it);
    }
    public void add_history(Item it){
        this.history_of_orders.add(it);
    }
    @Override
    public void search_category(String cat){
        for(Item i: cart){
            if(i.getCategory().equals(cat)){
                i.display();
            }
        }
    }
    @Override
    public void display(){
        System.out.println(this.name+" "+this.number_of_orders);
    }
    @Override
    public void display_list(){
        for(Item i: this.history_of_orders){
            System.out.println("Bought item "+ i.getName()+" quantity: "+i.getDemand_quantity()+" for Rs "+i.getPrice()+" from Merchant "+i.getSeller().getName());
            i.display();
        }
    }
    @Override
    public int display_reward(){
        return this.reward_money;
    }

}

class Item{
    private int unique_id;
    private double price;
    private int quantity;
    private String name;
    private String offer;
    private String category;
    private Merchant seller;
    private int demand_quantity;

    Item(int unique_id, String name,int price,int quantity,String category){
        this.unique_id=unique_id;
        this.name=name;
        this.offer="NONE";
        this.category=category;
        this.price=price;
        this.quantity=quantity;
        this.demand_quantity=0;
    }

    public String getOffer() {
        return this.offer;
    }
    public void setOffer(String offer) {
        this.offer = offer;
    }
    public Merchant getSeller() {
        return this.seller;
    }
    public void setSeller(Merchant seller) {
        this.seller = seller;
    }
    public int getDemand_quantity() {
    	return this.demand_quantity;
    }
    public void setDemand_quantity(int demand_quantity) {
    	this.demand_quantity = demand_quantity;
    }
    public String getCategory() {
    	return this.category;
    }
    public int getUnique_id() {
        return this.unique_id;
    }
    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }
    public double getPrice() {
    	return this.price;
    }
    public void setPrice(double price) {
    	this.price = price;
    }
    public int getQuantity() {
    	return this.quantity;
    }
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
    public String getName() {
    	return this.name;
    }
    public void display(){
        System.out.println(this.unique_id+" "+this.name+" "+this.price+" "+this.quantity+" "+this.category+" "+this.offer);
    }
}

public class Assignment2{
    public static List<String> categories=new ArrayList<String>();
    public static List<Item> All_items = new ArrayList<Item>();
    public static final Scanner scan=new Scanner(System.in);
    public static List<Merchant> merchants=new ArrayList<Merchant>();
    public static List<Customer> customers=new ArrayList<Customer>();
    public static float balance=0;
    public static int unique_id=1;

    static void display_categories(){
        for(int i=0;i<categories.size();i++){
            System.out.println((i+1)+" "+categories.get(i));
        }
    }
    static void update_category(String cat){
        for(String i: categories){
            if(i.equals(cat)){
                return;
            }
        }
        categories.add(cat);
    }

    public static int update_history(Item it,Customer c){
        int offer_quantity=0;
        double price=it.getPrice();
        if(it.getOffer().equals("buy one get one")){
            offer_quantity= it.getQuantity();
        }
        if(it.getOffer().equals("25% off")){
            price=price*0.75;
        }
        if(it.getQuantity()<offer_quantity+it.getDemand_quantity()){
            System.out.println("Not enough quantity available");
            return -1;
        }
        else if(it.getDemand_quantity()*price>c.getMain_money()+c.getReward_money()){
            System.out.println("Not enough money available");
            return -1;
        }

        c.setNumber_of_orders(c.getNumber_of_orders()+1);
        if(c.getNumber_of_orders()%10==0){
            c.setReward_money(c.getReward_money()+10);
        }
        if(price*it.getDemand_quantity()>c.getMain_money()){
            c.setReward_money((int)(c.display_reward()- (price*it.getDemand_quantity()-c.getMain_money())));
            c.setMain_money(0);
        }
        else{
            c.setMain_money((int)(c.getMain_money()-price*it.getDemand_quantity()));
        }

        c.add_history(it);
        balance+=price*it.getDemand_quantity()*0.01;
        it.getSeller().setIncome(it.getSeller().getIncome()+price*it.getDemand_quantity()*0.99);
        it.getSeller().setSlot((int)(it.getSeller().getIncome()/99));
        System.out.println("Item successfully bought");
        return 0;
    }
    public static void clear_Cart(Customer c){
        while (c.cart.size()!=0) {
            System.out.println("clearing "+c.cart.get(0).getName());
            if(update_history(c.cart.get(0),c)==-1){
                return;
            }
            c.cart.remove(0);
        }
    }

    static void merchant(Merchant m){
        int n,price,quantity,id,option;
        String name,category;
        Item it;
        while(true){
            System.out.println("Welcome "+m.getName());
            System.out.println("Merchant Menu");
            System.out.println("1) Add item");
            System.out.println("2) Edit item");
            System.out.println("3) Search by category");
            System.out.println("4) Add offer");
            System.out.println("5) Rewards won");
            System.out.println("6) Exit");
            n=scan.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Enter item details");
                    System.out.println("item name");
                    name =scan.next();
                    System.out.println("item price");
                    price=scan.nextInt();
                    System.out.println("item quantity");
                    quantity=scan.nextInt();
                    System.out.println("item category");
                    category=scan.next();
                    it=new Item(unique_id, name,price,quantity,category);
                    it.display();
                    unique_id++;
                    All_items.add(it);
                    m.add_item(it);
                    update_category(category);
                    break;
                case 2:
                    System.out.println("Choose item by code");
                    m.display_list();
                    id=scan.nextInt();
                    if(m.check_id(id)){
                        System.out.println("Enter edit details");
                        System.out.println("item price");
                        price=scan.nextInt();
                        System.out.println("item quantity");
                        quantity=scan.nextInt();
                        m.update_item(id,price,quantity);
                    }
                    else{
                        System.out.println("can't find item with that id");
                    }
                    break;
                case 3:
                    if(categories.size()>0){
                        System.out.println("choose a category");
                        display_categories();
                        id=scan.nextInt()-1;
                        m.search_category(categories.get(id));
                    }
                    else{
                        System.out.println("Number of categories are 0");
                    }
                    break;
                case 4:
                    System.out.println("choose item by code");
                    m.display_list();
                    id=scan.nextInt();
                    System.out.println("choose offer");
                    System.out.println("1) buy one get one");
                    System.out.println("2) 25% off");
                    option =scan.nextInt();
                    if(option==1)
                        m.update_item(id, "buy one get one");
                    else{
                        m.update_item(id, "25% off");
                    }
                    m.display_item(id);
                    break;
                case 5:
                    System.out.println("Number of slots awarded = "+ m.display_reward());
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    static void customer(Customer c){
        int n,quantity,id,option;
        Item it;
        while(true){
            System.out.println("Welcome "+c.getName());
            System.out.println("Customer Menu");
            System.out.println("1) Search item");
            System.out.println("2) Checkout cart");
            System.out.println("3) Reward won");
            System.out.println("4) Print latest orders");
            System.out.println("5) Exit");
            n=scan.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Choose a category");
                    display_categories();
                    option=scan.nextInt()-1;
                    System.out.println("choose item by code");
                    List<Item> temp=new ArrayList<Item>();
                    for(Item i : All_items){
                        if(i.getCategory().equals(categories.get(option))){
                            i.display();
                            temp.add(i);
                        }
                    }
                    System.out.println("Enter item code");
                    id=scan.nextInt();
                    it=temp.get(0);
                    for(Item i: temp){
                        if(i.getUnique_id()==id){
                            it=i;
                            break;
                        }
                    }
                    System.out.println("enter item quantity");
                    quantity=scan.nextInt();
                    System.out.println("choose method of transaction");
                    System.out.println("1) Buy item");
                    System.out.println("2) Add item to cart");
                    System.out.println("3) Exit");
                    option=scan.nextInt();
                    it.setDemand_quantity(quantity);
                    if(option==3){
                        break;
                    }
                    else if(option==1){
                        it.setDemand_quantity(quantity);
                        update_history(it,c);
                    }
                    else{
                        c.update_cart(it);
                    }
                    break;
                case 2:
                    clear_Cart(c);
                    break;
                case 3:
                    System.out.println("Reward won="+c.display_reward());
                    break;
                case 4:
                    c.display_list();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        int n,option;
        String s;
        merchants.add(new Merchant("Jack"));
        merchants.add(new Merchant("John"));
        merchants.add(new Merchant("James"));
        merchants.add(new Merchant("Jeff"));
        merchants.add(new Merchant("Joseph"));
        customers.add(new Customer("Ali"));
        customers.add(new Customer("Nobby"));
        customers.add(new Customer("Bruno"));
        customers.add(new Customer("Borat"));
        customers.add(new Customer("Aladeen"));

        while(true){
            System.out.println("Welcome to Mercury");
            System.out.println("1) Enter as Merchant");
            System.out.println("2) Enter as Customer");
            System.out.println("3) See user details");
            System.out.println("4) Company account balance");
            System.out.println("5) Exit");
            n=scan.nextInt();
            if(n==5){
                break;
            }
            else if(n==1){
                System.out.println("Choose merchant");
                for(int i=0;i<merchants.size();i++){
                    System.out.println((i+1)+" "+merchants.get(i).getName());
                }
                option=scan.nextInt()-1;
                merchant(merchants.get(option));
            }
            else if(n==2){
                System.out.println("choose customer");
                for(int i=0;i<customers.size();i++){
                    System.out.println((i+1)+" "+customers.get(i).getName());
                }
                option=scan.nextInt()-1;
                customer(customers.get(option));
            }
            else if(n==3){
                for(int i=0;i<merchants.size();i++){
                    System.out.println((i+1)+" "+merchants.get(i).getName());
                }
                for(int i=0;i<customers.size();i++){
                    System.out.println((i+merchants.size())+" "+customers.get(i).getName());
                }
                System.out.println("Enter M or C followed by serial number");
                s=scan.next();
                if(s.equals("M")){
                    option=scan.nextInt();
                    merchants.get(option).display();
                }
                else{
                    option=scan.nextInt()-merchants.size();
                    customers.get(option).display();
                }
            }
            else if(n==4){
                System.out.println("Company's account balance = "+balance);
            }
        }
    }
}