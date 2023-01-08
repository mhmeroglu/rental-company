import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) throws IOException {
        BST tree = new BST();

        String fileName = "src\\example.txt";

        System.out.println("--------------- WELCOME TO RENTAL SYSTEM ---------------");
        LinkedList<String> list = readFile(fileName);

        //We called the necessary methods by reading the values in the list line by line.
        while (!list.isEmpty()) {
            if (list.getFirst().contains("Add_Captain")) {
                String arr[] = (list.getFirst().replace("  ", " ").split(" "));

                tree.addCaptain(new BST.Node(Integer.parseInt(arr[1]), arr[2], 0, "True"));
                System.out.println("Add Captain: Add a new captain record in the System");
                tree.printCaptain(Integer.parseInt(arr[1]));
                System.out.println("----------------------------------------------------------------");

            } else if (list.getFirst().contains("Is_Available")) {
                String arr[] = (list.getFirst().split(" "));

                tree.rentCar(Integer.parseInt(arr[1]));
            } else if (list.getFirst().contains("Display_captain")) {
                String arr[] = (list.getFirst().split(" "));
                System.out.print("Display Captain: ");
                tree.printCaptain(Integer.parseInt(arr[1]));
                System.out.println();
                System.out.println("----------------------------------------------------------------");
            } else if (list.getFirst().contains("Finish")) {
                String arr[] = (list.getFirst().split(" "));

                tree.finishRide(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
                System.out.println("----------------------------------------------------------------");
                ;
            } else if (list.getFirst().contains("Delete_captain")) {
                String arr[] = (list.getFirst().split(" "));

                tree.delete(Integer.parseInt(arr[1]));
                System.out.println("----------------------------------------------------------------");
            } else if (list.getFirst().contains("Display_all_captains")) {
                System.out.println("----------ALL CAPTAINS----------");
                tree.printAll();
            } else if (list.getFirst().contains("Quit")) {
                quit();
            } else {
                //do nothing
            }
            list.removeFirst();
        }
    }

    static void quit() {
//--------------------------------------------------------
// Summary:In this function we stopped the program.
// Precondition: -
// Postcondition: Program stopped.
//--------------------------------------------------------
        System.out.println("Thank you for using RENTAL, Good Bye!");
        System.exit(0);
    }

    static LinkedList<String> readFile(String path) throws IOException {
//--------------------------------------------------------
// Summary: In this function we read the files.
// Precondition: path is String for txt file names.
// Postcondition: txt file read and transferred to list.
//--------------------------------------------------------
        BufferedReader in = new BufferedReader(new FileReader(path));
        String str;

        LinkedList<String> list = new LinkedList<>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }
        return list;
    }
}