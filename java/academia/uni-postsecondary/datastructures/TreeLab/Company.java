/**
 * Represents a company with employees and a CEO.
 */
public class Company {

    private final String name;
    private Employee ceo;

    /**
     * Constructs a company with the specified name and CEO.
     *
     * @param name the name of the company
     * @param ceo  the CEO of the company
     */
    public Company(String name, Employee ceo) {
        this.name = name;
        this.ceo = ceo;
    }

    /**
     * Gets the name of the company.
     *
     * @return the name of the company
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the CEO of the company.
     *
     * @return the CEO of the company
     */
    public Employee getCEO() {
        return ceo;
    }

    /**
     * Sets the CEO of the company.
     *
     * @param ceo the new CEO of the company
     */
    public void setCEO(Employee ceo) {
        this.ceo = ceo;
    }

    /**
     * Calculates the total number of employees in the company.
     *
     * @return the total number of employees in the company
     */
    public int getPopulation() {
        return ceo.numOfEmployees();
    }

    /**
     * Prints the company roster, including the CEO and all employees.
     */
    public void printCompany() {
        System.out.println(getName() + " Company Roster: (" + getPopulation() + " employees)");
        getCEO().printEmployees(0);
        System.out.println();
    }
}
