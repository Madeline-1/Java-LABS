public class Person {
    String lastName;
    String firstName;
    int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != Person.class) return false;

        Person other = (Person) obj;
        if (!lastName.equals(other.lastName)) return false;
        if (!firstName.equals(other.firstName)) return false;
        if (age != other.age) return false;
        return true;
    }

    public int hashCode() {
        return lastName.hashCode() + firstName.hashCode() + age;
    }

    public static void main(String[] args) {
        Person first = new Person("Шевченко", "Тарас", 20);

        String json = "{\"lastName\":\"" + first.lastName
                + "\",\"firstName\":\"" + first.firstName
                + "\",\"age\":" + first.age + "}";

        String[] parts = json.split("\"");
        String ageText = parts[10].replace(":", "");
        ageText = ageText.replace("}", "");

        Person second = new Person(parts[3], parts[7], Integer.parseInt(ageText));

        System.out.println(json);
        System.out.println(first.equals(second)); // true
    }
}
