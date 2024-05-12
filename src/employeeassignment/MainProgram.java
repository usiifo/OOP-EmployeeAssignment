/*
* Name : Yousef Hisham Bogari 
* ID : 2136374
* Section : F1 
* Email : yabogari@stu.kau.edu.sa
* Assignment No.1 : Shopping Center Employee Management System 
* Strarting Date : 22/9/2022 
* End Date : 29/9/2022 
 */
package employeeassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {

    static ArrayList<Center> centers = new ArrayList<>(); // there're more than 1 centers so we will need an array 
    static int CenterCap;

    public static void main(String[] args) throws FileNotFoundException {
        File inInfo = new File("intialInformation.txt"); // informations file 
        File inCommands = new File("Commands.txt"); // commands file 
        File output = new File("output.txt"); // output file 
        PrintWriter pen = new PrintWriter(output); // print in the output file 
        // For check if "intialInformation.txt" file exists or not.
        if (!inInfo.exists()) {
            throw new FileNotFoundException("file is not exist");
        }
        // For check if "commands.txt" file exists or not.
        if (!inCommands.exists()) {
            throw new FileNotFoundException("file is not exist");
        }
        Scanner read = new Scanner(inInfo); // reading information from file 1 
        Scanner readF2 = new Scanner(inCommands); // reading commands from file 2 
        pen.println("                                                  Welcome to Shopping Center Employee Management System 				");
        pen.println("                                                  ---------------------------------------------------------------------------				");
        String Command = null;
        do {
            Command = readF2.next();
            if (Command.equalsIgnoreCase("STARTUP")) {
                int number_of_emplyees = read.nextInt();
                int number_of_centers = read.nextInt();
                int number_of_products = read.nextInt();
                StartUp(number_of_emplyees, number_of_centers, number_of_products, read);
            } else if (Command.equalsIgnoreCase("DISPLAY_ALL_CENTERS")) {
                DisplayCenter(pen);
            } else if (Command.equalsIgnoreCase("DISPLAY_PRODUCTS_FOR_EMPLOYEE")) {
                int EmployeeNo = Integer.parseInt(readF2.next());
                DisplayProducts(EmployeeNo, pen);
            } else if (Command.equalsIgnoreCase("NUM_OF_EMPLOYEES")) {
                NUM_STUDENT(pen, readF2);
            } else if (Command.equalsIgnoreCase("DISPLAY_BASED_ON_PRODUCT")) {
                Display_Based_on_Product(readF2, pen);
            } else if (Command.equalsIgnoreCase("CHANGE_TO_NULL_PRODUCT")) {
                pen.println("==========================================================================				");
                deleteProduct(readF2, pen);
            } else if (Command.equalsIgnoreCase("SWAP_BETWEEN_EMPLOYEES")) {
                pen.println("============================================================================				");
                swapEmployee(readF2, pen);
                pen.println();
                pen.println();
            }

        } while (!Command.equalsIgnoreCase("QUIT"));
        pen.println();
        pen.println();
        pen.println();
        pen.println("	============================			");
        pen.println("		Best Wishes 		");
        pen.println("	============================			");

        pen.flush();
        pen.close();
        read.close();
        readF2.close();
    }

    public static void StartUp(int number_of_emplyees, int number_of_centers, int number_of_products, Scanner read) {
        CenterCap = number_of_emplyees / number_of_centers;
        // This variable for eqaulity distribution.
        int remaining = number_of_emplyees;
        String[] CENTERS = new String[3];
        for (int i = 0; i < CENTERS.length; i++) {
            CENTERS[i] = read.next(); // reading all Centers names  
        }
        String[] products = new String[5];
        for (int i = 0; i < number_of_products; i++) {
            products[i] = read.next(); // reading all products names and storing it in an array 
        }
        for (int i = 0; i < CENTERS.length; i++) {
            Center center = new Center(CENTERS[i]); // creating an obj of center 
            centers.add(center); // adding the center created to the array we created 
            if (remaining >= CenterCap) {
                for (int j = 0; j < CenterCap; j++) { // each center has 5 employees which is the capacity 
                    int ID = Integer.parseInt(read.next());
                    String FName = read.next();
                    String LName = read.next();
                    Employee employee = new Employee(ID, FName, LName); // creating an obj & assign the data 
                    employee.setProduct(products[j]); // assign product to an employee 
                    center.addEmployee(employee); // add the created obj to the Linked list (center) 
                }
                remaining -= CenterCap; // decrement the remaining employees 
            } else { // If less than section capacity. 
                // Loop for read information of employee.
                for (int j = 0; j < remaining; j++) {
                    //This variable for read id employee.
                    int Id = Integer.parseInt(read.next());
                    //This variable for read first name of Employee employee.
                    String Fname = read.next();
                    //This variable for read lstt name of employee.
                    String Lname = read.next();
                    // Create object to add Employee information.
                    Employee employee = new Employee(Id, Fname, Lname);
                    // Add employee in center by addEmployee method.
                    centers.get(i).addEmployee(employee);
                    employee.setProduct(products[j]); // assign product to an employee 
                }
            }
        }
        for (int i = 0; i < CENTERS.length; i++) {
            centers.get(i).setCenterID(read.nextInt()); // assign an ID to specific center 
            String x = read.next(); // no need for the centers name, already stored
        }
    }

    public static void DisplayCenter(PrintWriter pen) {
        pen.println("                                                                         Information of Employees in Each Center 				");

        // Loop for print centers.
        for (int i = 0; i < centers.size(); i++) {
            pen.printf("%45s %s %s", "Employees in ", centers.get(i).getCenterName(), " Center " + "\n");
            pen.printf("%15s %20s %28s %n","ID" , "Name" , "Product");
            Employee helpPtr = centers.get(i).getHead();
            while (helpPtr != null) {
                pen.println(helpPtr.toString()); // print emplooyees info 
                helpPtr = helpPtr.getNext();
            }
            pen.println("                   ----------------------------------------------				");

        }
    }

    public static void DisplayProducts(int employee_number, PrintWriter pen) {
        Employee emp = null;
        boolean found = false;
        for (int i = 0; i < centers.size(); i++) {
            if (found == false) {
                emp = centers.get(i).searchEmployeeByID(employee_number);
                if (emp != null) {
                    pen.print("\t ''Employee: " + emp.getFname() + " " + emp.getlName() + "," + " is assigned to " + emp.getProduct() + " in " + centers.get(i).getCenterName() + " center ''");
                    found = true; // if found. stop the loop 
                    break;
                }
            }
        }
        if (found == false) { // if the employee not found 
            pen.println("\t No employee of this number is found");
            pen.println("----------------------------------------------------------------------------");
        }
        pen.println();
    }

    public static void NUM_STUDENT(PrintWriter pen, Scanner input) {
        // This variable for read specified Center.
        String CenterName = input.next();
        // Loop for all Center.
        for (int i = 0; i < centers.size(); i++) {
            // Center object for found target Center.
            Center targetCenter = centers.get(i);
            // Check for Center.
            if (targetCenter.getCenterName().equals(CenterName)) {
                // Print number of employee in center.
                pen.printf("%40s %n", "Number of employees in " + CenterName + " center: " + targetCenter.NumberOfemployee());
            }
        }
    }

    public static void Display_Based_on_Product(Scanner input, PrintWriter pen) {
        pen.println();
        pen.println("-------------------------------------------------------------------------------				");
        String product = input.next();
        pen.printf("%35s %s %n", "Employees for product ", product);
        pen.printf("%15s %20s %32s %n"," ID" ,"Name","Center");
        for (int i = 0; i < centers.size(); i++) {
            Employee HelpPtr = centers.get(i).getHead(); // get the head 
            while (HelpPtr != null) {
                if (HelpPtr.getProduct().equals(product)) { // check the product entered 
                    pen.printf("%s %28s %n",HelpPtr.DisplayBasedOnProduct(), centers.get(i).getCenterName()); //  print employee info 
                }
                HelpPtr = HelpPtr.getNext();
            }
        }
    }

    public static void deleteProduct(Scanner input, PrintWriter pen) {
        int idCenter = input.nextInt();
        // Loop for center list.
        for (int i = 0; i < centers.size(); i++) {
            // Employee object for get head.
            Employee HelpPtr = centers.get(i).getHead();
            // Check for id 
            while (HelpPtr != null) {
                if (HelpPtr.getEmpID() == idCenter) {
                    // Then delete employee.
                    HelpPtr.setProduct(null);
                    pen.println("\t \t Change Assigned Product to " + HelpPtr.getProduct());
                    pen.println("\t Products for " + HelpPtr.getFname() + " " + HelpPtr.getlName() + ": " + HelpPtr.getEmpID() + " has been changed to null");
                    pen.println();
                }
                HelpPtr = HelpPtr.getNext();
            }
        }

    }

    public static void swapEmployee(Scanner read, PrintWriter pen) {
        int ID1 = read.nextInt();
        int ID2 = read.nextInt();
        Employee emp1 = null;
        Employee emp2 = null;
        String cen1 = null;
        String cen2 = null;
        for (int i = 0; i < centers.size(); i++) {
            if (emp1 == null) {
                emp1 = centers.get(i).searchEmployeeByID(ID2); //  search employee with the second ID
                cen1 = centers.get(i).getCenterName();
            }
            if (emp2 == null) {
                emp2 = centers.get(i).searchEmployeeByID(ID1); // search employee with the first ID
                cen2 = centers.get(i).getCenterName();
            }
            if (emp1 != null && emp2 != null) { // if both found
                centers.get(i).swapEmployees(emp1, emp2); // swap 
                pen.println("                           Swap Centers Between Two Employees 				");
                pen.println(emp1.getFname() + " " + emp1.getlName() + " in " + cen2 + " is Swaped with " + emp2.getFname() + " " + emp2.getlName() + " in "+cen1);
            }
        }
        pen.println();
        pen.println("============================================================================				");
    }

}
