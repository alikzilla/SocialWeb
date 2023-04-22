public abstract class Human {
        int id;
        int age;
        String name;
        String surname;
        protected Human() {
        }
        public Human(int id, String name, String surname, int age) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.age = age;
        }
        public abstract String toString();
        public abstract boolean checkPassword(String password);
        public abstract boolean setPassword(String password);
        public abstract int getId();
        public abstract String getName();
        public abstract String getSurname();
}
