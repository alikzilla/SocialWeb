public class Employee extends Human {

    int status;
    private String password;

    public Employee() {

    }
    public Employee(int id, String name, String surname, int age, String password, int status) {
        super(id, name, surname, age);
        this.status = status;
        this.password = password;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public String getStatus() {
        if (status == 1) {
            return "HEAD";
        } else if (status == 2) {
            return "SENIOR";
        } else if (status == 3) {
            return "JUNIOR";
        } else {
            return "UNKNOWN";
        }
    }
    public String toString(){
        return super.name + " " + super.surname + " status: " + getStatus();
    }
    @Override
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public boolean setPassword(String password) {
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }
}