// Import Gson
import com.google.gson.Gson;

// Define a simple Java class (POJO)
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class GsonExample {
    public static void main(String[] args) {
        // Create a new Person object
        Person person = new Person("Jimbo", 44);

        // Create a Gson instance
        Gson gson = new Gson();

        // Convert the Java object to a JSON string (serialization)
        String json = gson.toJson(person);
        System.out.println("Serialized JSON: " + json);

        // Convert the JSON string back to a Java object (deserialization)
        Person deserializedPerson = gson.fromJson(json, Person.class);
        System.out.println("Deserialized Person: " + deserializedPerson.name + ", " + deserializedPerson.age);
    }
}
