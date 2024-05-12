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

public class Center {

    private String centerName;
    private int centerID;
    private Employee head;

    public Center() {
        head = null;
    }

    public Center(String centerName) {
        this.centerName = centerName;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String getCenterName() {
        return centerName;
    }

    public int getCenterID() {
        return centerID;
    }

    public Employee getHead() {
        return head;
    }

    public int NumberOfemployee() {
        // Counter for number of employee. 
        int numEmployee = 0;
        // employee object for center.
        Employee helpPtr = this.head;
        // Check if center is empty or not.
        if (isEmpty()) {
            // Return Counter.
            return numEmployee;
        }
        // Loop for count employee.
        while (helpPtr != null) {
            // If LLnode not equal null, then increment counter.
            numEmployee++;
            // For get next node of employee.
            helpPtr = helpPtr.getNext();
        } // End loop.
// return counter.
        return numEmployee;
    }

    public void addEmployee(Employee p) {
        if (isEmpty()) {
            // If true , return employee.
            this.head = p;
            return;
        }
        // employee object for store LLnode;
        Employee helptr = head;
        // Loop for check next node is not null.
        while (helptr.getNext() != null) {
            // If not null , then go to the next node.
            helptr = helptr.getNext();
        } // End loop.
        // Set node.
        helptr.setNext(p);
    }

    public Employee searchEmployeeByID(int ID) {
        // Check if LLnode is empty or not.
        if (isEmpty()) {
            // If empty return null. 
            return null;
        }
        // employee object for LLnode.
        Employee helptr = this.head;
        // employee object for retun employee if found it.
        Employee employee = null;
        // Loop for if employee not equal to null.
        while (helptr != null) {
            // If  LLnode id equal to id. 
            if (helptr.getEmpID() == ID) {
                //Then employee equal to this LLnode.
                employee = helptr;
            }
            // For got to next node.
            helptr = helptr.getNext();
        }
        // Return found employee.
        return employee;
    }

    public void deleteEmployeeByID(int id) {
        // Ckeck for id. 
        if (head != null && head.getEmpID() == id) {
            // Go to next.
            head = head.getNext();
            return;
        }
        // employee object for head.
        Employee helptr = head;
        // Loop for check the helptr not null.
        while (helptr.getNext() != null) {
            // Check for id.
            if (helptr.getNext().getEmpID() == id) {
                // set next.
                helptr.setNext(helptr.getNext().getNext());
                return;
            }
            // Go to next.
            helptr = helptr.getNext();
        }

    }

    public void swapEmployees(Employee Emp1, Employee Emp2) {

        // swap first name
        String temp = Emp2.getFname();// temporary object for swapping 
        Emp2.setFname(Emp1.getFname());
        Emp1.setFname(temp);
        // swap last name
        String temporary=Emp2.getlName(); // temporary object
        Emp2.setlName(Emp1.getlName());
        Emp1.setlName(temporary);
        // swap ID 
        int TEMP= Emp2.getEmpID(); // temporary object 
        Emp2.setEmpID(Emp1.getEmpID());
        Emp1.setEmpID(TEMP);
    }

}
