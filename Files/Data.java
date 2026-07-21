import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Data {

    public static void main(String[] args) {

             // Console colors
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String YELLOW = "\u001B[33m";
    
    String newName;
    int newAge;
    boolean isMenuOpen = true;
    boolean ViewMenu;
    boolean SortMenu;
    boolean NameCheck;
    boolean isDuplicate;
    String Choice;
    String SortMethod;
    String SortOrder;

    Scanner Input = new Scanner(System.in);
    ArrayList<Person> People = new ArrayList<>();
     
    try { 
        File PeopleData = new File("PeopleData.txt");
    if (PeopleData.exists()) {
        Scanner DataReader = new Scanner(PeopleData);

        while (DataReader.hasNextLine()) {
            String Line = DataReader.nextLine();

            String[] PersonParts = Line.split(",");

            if (PersonParts.length == 3) {
                int LoadOrder = Integer.parseInt(PersonParts[0]);
                String LoadName = PersonParts[1];
                int LoadAge = Integer.parseInt(PersonParts[2]);

                People.add(new Person(LoadOrder, LoadName, LoadAge));
            }
        } DataReader.close();
    } else {
    Person Kai = new Person(1, "Kai", 19);
    People.add(Kai);
    Person Ryu = new Person(2, "Ryu", 20);
    People.add(Ryu);
    Person Moayad = new Person(3, "Saif", 19);
    People.add(Moayad);
    }
} catch (FileNotFoundException e) {System.out.println(RED + "No Save Found" + RESET);}


    while (isMenuOpen) {

    Person newPerson = new Person();

    System.out.println(YELLOW + "Choose" + RESET);
    System.out.println("1- Add Person");
    System.out.println("2- Show People");
    System.out.println("3- Quit");
    Choice = Input.nextLine();

    switch (Choice) {
        case "1":

            System.out.println(YELLOW + "Enter Name:" + RESET);
            newName = Input.nextLine();

            NameCheck = true;

            while (NameCheck) {

            if (newName.matches(".*\\d.*") || newName.trim().isEmpty()) {
            System.out.println(RED +"Enter A Valid Name" + RESET);
            newName = Input.nextLine(); continue;}

            isDuplicate = false;
            for (Person P: People) {
            if (P.name.equalsIgnoreCase(newName)) {
            isDuplicate = true; break;}
            }

            if (isDuplicate) { System.out.println(RED +"Name Already Exists" + RESET);
            newName = Input.nextLine();
            } else { newPerson.name = newName; NameCheck = false;} }

            System.out.println(YELLOW + "Enter Age" + RESET);
                  while (!Input.hasNextInt()) {System.out.println(RED + "Enter a Valid Age" + RESET); Input.next();}
                  newAge = Input.nextInt();
                  Input.nextLine();
                  newPerson.age = newAge;
      
            newPerson.InsertOrder = People.size() + 1;
            
            People.add(newPerson);

            People.sort((p1, p2) -> Integer.compare(p1.InsertOrder, p2.InsertOrder));

            try {
            PrintWriter SaveWriter = new PrintWriter(new FileWriter("PeopleData.txt"));
            for (Person P: People) {
                SaveWriter.println(P.InsertOrder + "," + P.name +"," + P.age);
            } 
            SaveWriter.close();
            System.out.println(GREEN + newPerson.name + YELLOW + " Saved Successfully" + RESET);
        } catch (IOException e) {System.out.println(RED + "ERROR! Something Went Wrong" + RESET);}

            break;
        case "2":

        if (People.isEmpty()) {
           System.out.println(RED + "Add a Person First!" + RESET);
        } else {

            ViewMenu = true;

            while (ViewMenu) {

            System.out.println("1- Sort By Order");
            System.out.println("2- Sort By Age");
            SortMethod = Input.nextLine();
           
            try {
                switch (SortMethod) {
                    case "1":
                    
                    SortMenu = true;

                    while (SortMenu) {

                    System.out.println("1- Ascending");
                    System.out.println("2- Descending");
                    SortOrder = Input.nextLine();
                    try {
                        switch (SortOrder) {
                            case "1":
                                People.sort((p1, p2) -> Integer.compare(p1.InsertOrder, p2.InsertOrder));
                                ViewMenu = false;
                                SortMenu = false;
                                break;

                            case "2":
                                People.sort((p1, p2) -> Integer.compare(p2.InsertOrder, p1.InsertOrder));
                                ViewMenu = false;
                                SortMenu = false;
                                break;
                        
                            default:
                                System.out.println(RED + "Enter A Valid Option" + RESET);
                                break;
                        }
                    } catch (Exception e) {System.out.println(RED + "Enter A Valid Option" + RESET);}
                }
                        break;

                    case "2":

                    SortMenu = true;

                    while (SortMenu) {

                    System.out.println("1- Ascending");
                    System.out.println("2- Descending");
                    SortOrder = Input.nextLine();
                    try {
                        switch (SortOrder) {
                            case "1":
                                People.sort((p1, p2) -> Integer.compare(p1.age, p2.age));
                                ViewMenu = false;
                                SortMenu = false;
                                break;

                            case "2":
                                People.sort((p1, p2) -> Integer.compare(p2.age, p1.age));
                                ViewMenu = false;
                                SortMenu = false;
                                break;
                        
                            default:
                                System.out.println(RED + "Enter A Valid Option" + RESET);
                                break;
                        }
                    } catch (Exception e) {System.out.println(RED + "Enter A Valid Option" + RESET);}
                }                 
                    break;

                    default:
                        System.out.println(RED + "Enter A Valid Option" + RESET);
                        break;
                }
            } catch (Exception e)   {System.out.println(RED + "Enter A Valid Option" + RESET);}
            
        }

    } for (Person P: People) P.call();
            break;

        case "3":
               
        People.sort((p1, p2) -> Integer.compare(p1.InsertOrder, p2.InsertOrder));
  
        try {
            PrintWriter QuitWriter = new PrintWriter(new FileWriter("PeopleData.txt"));
            for (Person P: People) {
                QuitWriter.println(P.InsertOrder + "," + P.name + "," + P.age);
            }
            QuitWriter.close();
             System.out.println(YELLOW + "Data Saved Successfully" + RESET);
        } catch (IOException e) {System.out.println(RED + "ERROR! Something Went Wrong" + RESET);}

        Input.close();
        System.out.println(YELLOW + "Goodbye" + RESET);
        isMenuOpen = false;
        break;

        default: 
        System.out.println(RED + "Use a Valid Option!" + RESET);
        break;
    }
 }

}
}