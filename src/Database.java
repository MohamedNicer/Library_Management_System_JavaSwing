import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Database {
    private ArrayList<User> users;
    private ArrayList<String> usernames;
    private ArrayList<Book> books;
    private ArrayList<String> booktitles;
    private ArrayList<Order> orders;
    private ArrayList<Borrowing> borrowings;
    private File usersfile = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data/Users");
    private File booksfile = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data/Books");
    private File ordersfile = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data/Orders");
    private File borrowingsfile =  new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data/Borrowings");

    private File folder = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data");

    public Database() {
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!usersfile.exists()) {
            try {
                usersfile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!booksfile.exists()) {
            try {
                booksfile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!ordersfile.exists()) {
            try {
                ordersfile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!borrowingsfile.exists()) {
            try {
                borrowingsfile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        users = new ArrayList<User>();
        usernames = new ArrayList<String>();
        books = new ArrayList<Book>();
        booktitles = new ArrayList<String>();
        orders = new ArrayList<Order>();
        borrowings = new ArrayList<Borrowing>();
        getOrders();
        getUsers();
        getBooks();
        getBorrowings();
    }

    public void AddUser(User user) {
        users.add(user);
        usernames.add(user.getName());
        saveUsers();
    }

    public int login(String phonenumber, String email) {
        int n = -1;
        for (User s : users) {
            if (s.getPhonenumber().matches(phonenumber) && s.getEmail().matches(email)) {
                n = users.indexOf(s);
                //System.out.println(n);
                break;
            }
        }
        //System.out.println(n);
        return n;
    }

    public User getUser(int n) {
        return users.get(n);
    }

    public void AddBook(Book book) {
        books.add(book);
        booktitles.add(book.getTitle());
        saveBooks();
    }

    private void getUsers() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(usersfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text.append(str);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.toString().matches("") || (!text.isEmpty())) {
            String[] s1 = text.toString().split("<NewUser/>");
            for (String s2 : s1) {
                String[] s3 = s2.split("<N/>");
                if (s3[3].matches("Admin")) {
                    User user = new Admin(s3[0], s3[1], s3[2]);
                    users.add(user);
                    usernames.add(user.getName());
                } else {
                    User user = new Customer(s3[0], s3[1], s3[2]);
                    users.add(user);
                    usernames.add(user.getName());
                }
            }
        }
    }

    public void saveUsers() {
        StringBuilder text1 = new StringBuilder();
        for (User user : users) {
            text1.append(user.toString()).append("<NewUser/>\n");
        }
        try {
            PrintWriter printWriter = new PrintWriter(usersfile);
            printWriter.print(text1);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void saveBooks() {
        StringBuilder text1 = new StringBuilder();
        for (Book book : books) {
            text1.append(book.toString2()).append("<NewBook/>\n");
        }
        try {
            PrintWriter printWriter = new PrintWriter(booksfile);
            printWriter.print(text1);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBooks() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(booksfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text.append(str);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.toString().matches("") || (!text.isEmpty())) {
            String[] s1 = text.toString().split("<NewBook/>");
            for (String s2 : s1) {
                Book book = parseBook(s2);
                books.add(book);
                booktitles.add(book.getTitle());
            }
        }
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public Book parseBook(String str) {
        String[] strings = str.split("<N/>");
        Book book = new Book();
        book.setTitle(strings[0]);
        book.setAuthor(strings[1]);
        book.setPublisher(strings[2]);
        book.setAddress(strings[3]);
        book.setQty(Integer.parseInt(strings[4]));
        book.setPrice(Double.parseDouble(strings[5]));
        book.setBorrowcopies(Integer.parseInt(strings[6]));
        return book;
    }

    public int getBook(String booktitle) {
        int index = -1;
        for (Book book : books) {
            if (book.getTitle().matches(booktitle)) {
                index=books.indexOf(book);
            }
        }
        return index;
    }
    public Book getBook(int index){
        return books.get(index);
    }
    public void deleteBook(int i ){
        books.remove(i);
        booktitles.remove(i);
        saveBooks();
    }
    public void deleteAllData(){
        if (usersfile.exists()) {
            try {
                usersfile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (booksfile.exists()) {
            try {
                booksfile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ordersfile.exists()) {
            try {
                ordersfile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (borrowingsfile.exists()) {
            try {
                borrowingsfile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void addOrder(Order order, Book book, int bookindex){
        orders.add(order);
        books.add(bookindex,book);
        saveOrders();
        saveBooks();
    }
    public void saveOrders() {
        StringBuilder text1 = new StringBuilder();
        for (Order order : orders) {
            text1.append(order.toString2()).append("<NewOrder/>\n");
        }
        try {
            PrintWriter printWriter = new PrintWriter(ordersfile);
            printWriter.print(text1);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    private void getOrders() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ordersfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text.append(str);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.toString().matches("") || (!text.isEmpty())) {
            String[] s1 = text.toString().split("<NewOrder/>");
            for (String s2 : s1) {
                Order order = parseOrder(s2);
                orders.add(order);
                }
            }
        }
    public Order parseOrder(String str) {
        String[] strings = str.split("<N/>");
        Order order = new Order(getUserByName(strings[0]), books.get(getBook(strings[1])),
                        Integer.parseInt(strings[2]),Double.parseDouble(strings[3]));
        return order;
    }
    public User getUserByName(String name){
        User user = new Customer("");
        for(User u: users){
            if(u.getName().matches(name)) {
                user = u;
                break;
            }
        }
        return user;
    }
    public boolean userExists(String name){
        boolean f = false;
        for(User u: users){
            if(u.getName().toLowerCase().matches(name.toLowerCase())) {
                f = true;
                break;
            }
        }
        return f;
    }
    public ArrayList<Order> getAllOrders(){
        return orders;
    }
    public void saveBorrowings() {
        StringBuilder text1 = new StringBuilder();
        for (Borrowing borrowing : borrowings) {
            text1.append(borrowing.toString2()).append("<NewBorrowing/>\n");
        }
        try {
            PrintWriter printWriter = new PrintWriter(borrowingsfile);
            printWriter.print(text1);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    private void getBorrowings(){
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(borrowingsfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text.append(str);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.toString().matches("") || (!text.isEmpty())) {
            String[] s1 = text.toString().split("<NewBorrowing/>");
            for (String s2 : s1) {
                Borrowing borrowing = parseBorrowing(s2);
                borrowings.add(borrowing);
            }
        }
    }
    private Borrowing parseBorrowing(String str){
        String[] strings = str.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(strings[0],formatter);
        LocalDate finish = LocalDate.parse(strings[1], formatter);
        User user = getUserByName(strings[3]);
        Book book = getBook(getBook(strings[4]));
        Borrowing borrowing = new Borrowing(start,finish,user,book);
        return borrowing;
    }
    public void borrowBook(Borrowing borrowing, Book book, int index){
        borrowings.add(borrowing);
        books.set(index,book);
        saveBorrowings();
        saveBooks();
    }
    public ArrayList<Borrowing> getAllBorrowings(){
        return borrowings;
    }
    public void returnBook(Borrowing borrowing, Book book, int index){
        borrowings.remove(borrowing);
        books.set(index,book);
        saveBorrowings();
        saveBooks();
    }
}


