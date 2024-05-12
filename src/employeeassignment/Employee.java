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

public class Employee {

    private int EmpID;
    private String Fname;
    private String lName;
    private String product;
    private String phone;
    private int age;
    private int center;
    private Employee next = null;

    public Employee() {
        next = null;
    }

    public Employee(int EmpID, String Fname, String lName) {
        this.EmpID = EmpID;
        this.Fname = Fname;
        this.lName = lName;
    }

    public int getEmpID() {
        return EmpID;
    }

    public String getFname() {
        return Fname;
    }

    public String getlName() {
        return lName;
    }

    public String getProduct() {
        return product;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public int getCenter() {
        return center;
    }

    public Employee getNext() {
        return next;
    }

    public void setEmpID(int EmpID) {
        this.EmpID = EmpID;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("%15s %20s %1s %28s" ,convertToString(EmpID), Fname, lName , product);
    }

    public String DisplayBasedOnProduct() {
        return String.format("%15s %20s %1s", convertToString(EmpID) , Fname , lName);
    }

    public static String convertToString(int empID) { // method for printing the zeros before the number 
        if (empID <= 9) {
            return "00" + empID;
        } else if (empID >= 10) {
            return "0" + empID;
        }
        return "";
    }
}
