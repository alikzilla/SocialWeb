public class Person extends Human {
    int id;
    String name;
    String surname;
    int age;
    private String password;
    public Person(){

    }

    public Person(int id, String name,String surname,  int age){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public boolean setPassword(String password){
        int countUpper = 0;
        if(password.length() >= 8){
            for (int i = 0; i < password.length(); i++) {
                if(Character.isUpperCase(password.charAt(i)))
                    countUpper++;
                if(Character.isDigit(password.charAt(i)))
                    countUpper++;
            }
            if(countUpper >= 2) {
                this.password = password;
                return true;
            }
        }
        return false;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nSurname: " + this.surname + "\nAge: " + this.age;
    }
}
