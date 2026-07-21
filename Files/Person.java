public class Person {

    String name;
    int age;
    int InsertOrder;

    public Person() {

    }
    
    public Person( int InsertOrder, String name, int age) {

        this.InsertOrder = InsertOrder;
        this.name = name;
        this.age = age;

    }

    public void call(){

  final String GREEN = "\u001B[32m";
  final String RESET = "\u001B[0m";

    System.out.println(GREEN + InsertOrder + ". " + name + " | " + age + RESET); 
     
    }
}
