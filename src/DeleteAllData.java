import java.util.Scanner;

public class DeleteAllData implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        System.out.println("Are you sure?\n" + "1.Continue\n2.Main Menu");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice==1){
            database.deleteAllData();
        } else user.menu(database, user);
    }
}
