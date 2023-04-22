import java.util.Scanner;

public class SocialNetwork {
    static Scanner in = new Scanner(System.in);
    static Person[] people = new Person[0];
    static Employee[] worker = new Employee[1];
    static int countPeople = 0;
    static boolean isLogin = false;
    static boolean isMenu = false;
    static int adminNum = 0;
    static int adminPos = -1;

    public static void main(String[] args) {
        String welcome = """
                WW    W    WW   EEEEEEE   LL        CCCCC    00000    MM     MM   EEEEEEE   !!!
                WW    W    WW   EE        LL       CC       00   00   MMM   MMM   EE        !!!
                WW    W    WW   EEEEE     LL       CC       00   00   MM M M MM   EEEEE     !!!
                 WW  W W  WW    EE        LL       CC       00   00   MM  M  MM   EE
                  WWW   WWW     EEEEEEE   LLLLLLL   CCCCC    00000    MM     MM   EEEEEEE   !!!""";
        System.out.println(welcome);
        menu();
    }
    public static void menu(){
        String menu = """                      
                      0: Exit
                      1: Registration
                      2: Login
                      3: Admin page""";

        int n;
        while (!isMenu) {
            System.out.println(menu);
            System.out.print("Enter: ");
            n = in.nextInt();
            if (n == 0) {
                System.out.println("Wasted");
                isMenu = true;
                break;
            } else if (n == 1) {
                addPerson();
            } else if (n == 2) {
                login();
            } else if (n == 3) {
                adminPage();
            } else if (n == 4) {
                int id = in.nextInt();
                findUser(id);
                System.out.println(people[id-1].toString());
            }
        }
    }
    public static void addPerson(){
        Person[] temp = new Person[people.length + 1];
        System.arraycopy(people, 0, temp, 0, people.length);
        System.out.print("Enter name: ");
        String name = in.next();
        System.out.print("Enter surname: ");
        String surname = in.next();
        System.out.print("Enter age: ");
        int age = in.nextInt();
        System.out.print("Enter password: ");
        String password = in.next();
        temp[temp.length - 1] = new Person(++countPeople, name, surname, age);
        while (!temp[temp.length - 1].setPassword(password)) {
            System.out.println("Your password is more or equal 8 characters or you're not entered uppercase letter. Try register again!");
            System.out.print("password: ");
            password = in.next();
        }
        if(people.length == 0 && adminNum < 1){
            System.out.println("Done");
            worker[0] = new Employee(countPeople, name, surname, age, password, 1);
            adminNum++;
        }
        // main_people = temp;
        people = new Person[people.length + 1];
        System.arraycopy(temp, 0, people, 0, temp.length);
        System.out.println("Successfully registered! Your id: " + countPeople);
    }

    public static void login() {
        while(!isLogin){
            System.out.println("\nLogging");
            int pos = -1;
            String panel = """
                0: Exit\s
                1: Change password
                2: Information
                3: Remove me""";
            System.out.print("Enter id: ");
            int id = in.nextInt(); // 1
            System.out.print("Enter password: ");
            String password = in.next();
            System.out.println(people.length);
            for (int i = 0; i < people.length; i++) {
                if (people[i].getId() == id && people[i].checkPassword(password)) {
                    pos = i;
                    break;
                }
            }
            if (pos == -1) {
                System.out.println("You're not login!");
                login();
            } else {
                System.out.println("Welcome " + people[pos].getName() + " " + people[pos].getSurname());
                isLogin = true;
                int n = -1;
                while (n != 0) {
                    System.out.println(panel);
                    n = in.nextInt();
                    if (n == 2) {
                        System.out.println("Name: " + people[pos].getName());
                        System.out.println("Surname: " + people[pos].getSurname());
                        System.out.println("Age: " + people[pos].getAge());
                    }
                    if (n == 1) {
                        System.out.println("Enter new password: ");
                        String pass = in.next();
                        while (!people[pos].setPassword(pass)) {
                            System.out.println("Your password is more or equal 8 characters or you're not entered uppercase letter. Try register again!");
                            pass = in.next();
                        }
                    }
                    if (n == 3) {
                        System.out.println("Do you agree this step? 1 - Yes. 2 - No");
                        int l = in.nextInt();
                        if (l == 1) {
                            System.out.println("Enter password for agree this step: ");
                            String s = in.next();
                            while (!people[pos].checkPassword(s)) {
                                System.out.println("Enter again: ");
                                s = in.next();
                            }
                            Person[] temp = new Person[people.length - 1]; // 3 - 1
                            // 0 1 2
                            System.arraycopy(people, 0, temp, 0, pos);
                            if (people.length - (pos + 1) >= 0)
                                System.arraycopy(people, pos + 1, temp, pos + 1 - 1, people.length - (pos + 1));
                            people = new Person[people.length - 1];
                            System.arraycopy(temp, 0, people, 0, temp.length);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static int  logging(){
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter password: ");
        String password = in.next();
        int pos = -1;
        for (int i = 0; i < worker.length; i++) {
            if(id == worker[i].getId() && worker[i].checkPassword(password))
                pos = i;
        }
        return pos;
    }
    public static void adminPage(){
        adminPos = logging();
        if(adminPos == -1)
            menu();
        else {
            while (true) {
                System.out.println(worker[adminPos].getName() + " " + worker[adminPos].getSurname() + " " + "Welcome to Admin Page ");
                System.out.println("Your status: " + worker[adminPos].getStatus());
                String panel = """
                            0. Exit.\s
                            1. Check people.
                            2. Update myPassword.\s""";
                System.out.print(panel + "\nEnter: ");
                int l = in.nextInt();
                if (l == 1) {
                    adminMenu();
                }
                if( l == 0)
                    break;
                if(l == 2){
                    System.out.println("Enter new password: ");
                    String password = in.next();
                    while(!worker[adminPos].checkPassword(password)){
                        System.out.println("Enter again! \nNew password: ");
                        password = in.next();
                    }
                    worker[adminPos].setPassword(password);
                }
            }
        }
    }
    public static void adminMenu() {
        System.out.println("All people: ");
        for (int i = 0; i < people.length; i++) {
            System.out.println((i+1) + ")" + people[i].getName() + " id: " + people[i].getId());
        }
        System.out.print("Enter id to more info: ");
        int id = in.nextInt();
        int pos = -1;
        for (int i = 0; i < people.length; i++) {
            if(people[i].getId() == id)
                pos = i;
        }
        if(pos == -1){
            System.out.println("Error!");
            adminPage();
        }
        System.out.println(people[pos]);
        System.out.println("Menu: \n0.Exit. \n1. Remove. \n2. Add to Admin List \n3. Set new password.");
        while(true){
            int n = in.nextInt();
            if(n == 0)
                break;
            if(n == 1){
                System.out.print("Enter id for verification: ");
                int idd = in.nextInt();
                if(worker[idd-1].getStatus().equals("SENIOR") && worker[pos].getStatus().equals("HEAD") || worker[idd-1].getStatus().equals("JUNIOR") && worker[pos].getStatus().equals("HEAD")){
                    System.out.println("you don't have enough rights!");
                }else if(worker[idd-1].getStatus().equals("HEAD")){
                    people = delete(pos);
                }
                break;
            }
            if(n == 2){
                addToNewAdmin(pos);
            }
            if(n == 3){
                System.out.println("Enter the new Password: ");
                String password = in.next();
                while (!people[pos].setPassword(password)) {
                    System.out.println("Try again! ");
                    password = in.next();
                }
            }
            menu();
        }
    }
    public static void addToNewAdmin(int pos){
        Employee[] temp = new Employee[worker.length + 1];
        for (int i = 0; i < worker.length; i++) {
            temp[i] = worker[i];
        }
        System.out.println("status: (1 - Head. 2 - Senior. 3 - Junior");
        int status = in.nextInt();
        if(status == 2 && !worker[adminPos].getStatus().equals("HEAD")){
            System.out.println("Only head can add senior status!");
            adminMenu();
        }
        else {
            temp[temp.length - 1] = new Employee(countPeople, people[pos].getName(), people[pos].getSurname(), people[pos].getAge(), people[pos].getPassword(), status);
            worker = temp;
            menu();
        }
    }
    public static Person findUser(int id){
        for (int i = 0; i < people.length; i++) {
            if(id == people[i].getId())
                return people[i];
        }
        return new Person();
    }
    public static Person[] delete(int pos){
        Person[] temp = new Person[people.length - 1]; // 3 - 1
        for (int i = 0; i < pos; i++) { // 0 1 2
            temp[i] = people[i];
        }
        for (int i = pos + 1; i < people.length; i++) {
            temp[i - 1] = people[i];
        }
        return temp;
    }
}