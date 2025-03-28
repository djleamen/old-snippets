package datastructures.TreeLab;

// Description: This class represents an employee in a company. 

import java.util.LinkedList;

/**
 * Represents an employee in a company.
 */
public class Employee {

    private final String name;
    private double salary;
    private String position;
    private Employee supervisor;
    private final LinkedList<Employee> employees;

    /**
     * Constructs an employee with the specified name, salary, and position.
     *
     * @param name     the name of the employee
     * @param salary   the salary of the employee
     * @param position the position of the employee
     */
    public Employee(String name, double salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.supervisor = null;
        this.employees = new LinkedList<>();
    }

    /**
     * Gets the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the salary of the employee.
     *
     * @return the salary of the employee
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets the position of the employee.
     *
     * @return the position of the employee
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gets the supervisor of the employee.
     *
     * @return the supervisor of the employee
     */
    public Employee getSupervisor() {
        return supervisor;
    }

    /**
     * Gets the list of employees supervised by this employee.
     *
     * @return the list of employees supervised by this employee
     */
    public LinkedList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Sets the salary of the employee.
     *
     * @param salary the new salary of the employee
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Sets the position of the employee.
     *
     * @param position the new position of the employee
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Sets the supervisor of the employee.
     *
     * @param supervisor the new supervisor of the employee
     */
    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * Hires a new employee under this employee's supervision.
     *
     * @param employee the employee to be hired
     */
    public void hireEmployee(Employee employee) {
        // set the new employee to supervisor
        employee.setSupervisor(this);
        // add the employee to the list of employees
        employees.add(employee);
    }

    /**
     * Calculates the total number of employees supervised by this employee.
     * (Hint: count the nodes in your tree from this point on - DFS)
     *
     * @return the total number of employees supervised by this employee
     */
    public int numOfEmployees() {
        int count = employees.size(); 
        for (Employee employee : employees) {
            // recursively count employees in each subtree
            count += employee.numOfEmployees();
        }
        return count;
    }

    /**
     * Prints the employees supervised by this employee. Display it nicely with
     * correct formatting. The depth will help you space it out.
     * (Hint: you want to visit all employees in a specific order)
     *
     * Example:
     * A
     *      B
     *      C
     *          D
     *          E
     *      F
     *
     * @param depth the depth of the employee in the hierarchy
     */
    public void printEmployees(int depth) {
         // print employee with indentation based on company depth
         for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        // using string formatting for salary
        System.out.println(getPosition() + ", " + getName() + " ($" + String.format("%.2f", getSalary()) + ")");
        // recursively print each employee, increasing the depth
        for (Employee employee : employees) {
            employee.printEmployees(depth + 1);
        }
    }

    /**
     * Simulates the employee leaving the company and promoting the necessary
     * replacements.
     */
    public void leaveCompany() {
        if (this.getSupervisor() == null) {
            System.out.println("The CEO cannot leave the company!");
            return;
        }
        // if this employee has no subordinates remove them from their supervisor's list
        if (this.employees.isEmpty()) {
            this.supervisor.getEmployees().remove(this);
        } else {
            // otherwise promote the most senior subordinate
            promoteSeniorEmployee(this);
        }
    }

    /**
     * Promotes the senior-most employee in the hierarchy under the specified supervisor.
     * This method recursively promotes the first employee found in the supervisor's direct reports,
     * adjusting their position, supervisor, and salary, and reassigning their subordinates to the promoted employee.
     * If the promoted employee has subordinates, the method recursively promotes them as well.
     *
     * The order of seniority MUST be maintained. Seniority is noted by position on a team (i.e. the index of their
     * position in the employees list). So when someone is promoted, they should join the end of the supervisor's list,
     * not take the place of the person they replace. Also, when someone gets promoted over their peers, their peers
     * should be added to their list of employees before their newly promoted replacement.
     *
     * Example: Bob supervises Chris, Danielle, and Eric; Chris supervises Frances and Gerry; the structure would be:
     *      Bob
     *          Chris
     *              Frances
     *              Gary
     *          Danielle
     *          Eric
     *
     *  But after Bob leaves, and we apply the promotions, it would be:
     *      Chris
     *          Danielle
     *          Eric
     *          Frances
     *              Gary
     *
     * @param employee The supervisor whose senior-most employee is to be promoted.
     */
    public void promoteSeniorEmployee(Employee employee) {
        // base case: no one to promote
        if (employee.getEmployees().isEmpty()) {
            return;
        }
        // the first employee in the list is the most senior
        Employee promotedEmployee = employee.getEmployees().removeFirst();
        // hold onto prev position in a string
        promotedEmployee.setPosition(employee.getPosition());

        // updateSubordinatePositions(promotedEmployee, promotedEmployee.getPosition()); (testing helper?) ignore...

        promotedEmployee.setSalary(employee.getSalary() + 10000); 
        promotedEmployee.setSupervisor(employee.getSupervisor());
        // if the supervisor exists, replace the current employee with the promoted one
        if (employee.getSupervisor() != null) {
            LinkedList<Employee> supervisorEmployees = employee.getSupervisor().getEmployees();
            supervisorEmployees.add(promotedEmployee);
            supervisorEmployees.remove(employee);
        }
        // transfer the subordinates of the current employee to the promoted employee
        promotedEmployee.getEmployees().addAll(employee.getEmployees());
        // update the supervisor for each transferred subordinate
        for (Employee subordinate : promotedEmployee.getEmployees()) {
            subordinate.setSupervisor(promotedEmployee);
        }
    }

    // helper? solves test 5... doesn't work for multi-chain (6)
    /*
    private void updateSubordinatePositions(Employee promotedEmployee, String newPosition) {
        for (Employee subordinate : promotedEmployee.getEmployees()) {
            if (subordinate.getPosition().equals("Intern") && newPosition.equals("Employee")) {
                subordinate.setPosition("Employee");
            }
            updateSubordinatePositions(subordinate, subordinate.getPosition());
        }
    }
        */
}
