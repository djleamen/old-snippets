package datastructures.TreeLab;

// Description: This class is used to test the Company class and its methods.

public class MyTechCompany {

    public static Employee alice;
    public static Employee bob;
    public static Employee carrie;
    public static Employee david;
    public static Employee eve;
    public static Employee frank;
    public static Employee grace;
    public static Employee harry;
    public static Employee ivy;
    public static Employee jack;
    public static Employee kate;
    public static Employee liam;
    public static Employee mia;
    public static Employee noah;
    public static Employee olivia;
    public static Employee peter;
    public static Employee ruby;
    public static Employee steve;
    public static Employee tiana;
    public static Employee ulrich;
    public static Employee alpha;
    public static Employee bravo;
    public static Employee charlie;
    public static Employee delta;
    public static Employee echo;
    public static Employee foxtrot;
    public static Employee golf;
    public static Employee hotel;

    public static void main(String[] args) {

        Company test = refreshCompany();
        test.printCompany();

        System.out.println("Test 1: Intern Alpha (no subordinates) leaves company");
        alpha.leaveCompany();
        test.printCompany();

        System.out.println("Test 2: Employee Mia (1 subordinate) leaves company, Foxtrot Promoted");
        mia.leaveCompany();
        test.printCompany();

        System.out.println("Test 3: Employee Liam (2 subordinates) leaves company, Delta promoted, Echo remains");
        liam.leaveCompany();
        test.printCompany();

        System.out.println("Refresh Company for Testing");
        test = refreshCompany();
        test.printCompany();

        System.out.println("Test 4: Employee Kate (3 subordinates) leaves company, Alpha promoted, Bravo and Charlie remain");
        kate.leaveCompany();
        test.printCompany();

        System.out.println("Test 5: Manager Grace leaves, testing multiple promotion levels (single chain)");
        grace.leaveCompany();
        test.printCompany();

        System.out.println("Test 6: Manager Frank leaves, testing multiple promotion levels (multi chain)");
        frank.leaveCompany();
        test.printCompany();

        System.out.println("Refresh Company for Testing");
        test = refreshCompany();
        test.printCompany();

        System.out.println("Test 7: Manager Eve leaves, testing multiple promotion levels (multi chain)");
        eve.leaveCompany();
        test.printCompany();

        System.out.println("Test 8: Director David leaves, testing additional promotion levels (single chain)");
        david.leaveCompany();
        test.printCompany();

        System.out.println("Test 9: Director Bob leaves, testing highest promotion levels (multi chain)");
        bob.leaveCompany();
        test.printCompany();

        System.out.println("Test 10: CEO tries to leave - not allowed.");
        alice.leaveCompany();
        test.printCompany();

    }

    public static Company refreshCompany() {
        alice = new Employee("Alice", 200000, "CEO");
        bob = new Employee("Bob", 150000, "Director");
        carrie = new Employee("Carrie", 150000, "Director");
        david = new Employee("David", 150000, "Director");
        eve = new Employee("Eve", 120000, "Manager");
        frank = new Employee("Frank", 120000, "Manager");
        grace = new Employee("Grace", 120000, "Manager");
        harry = new Employee("Harry", 120000, "Manager");
        ivy = new Employee("Ivy", 120000, "Manager");
        jack = new Employee("Jack", 120000, "Manager");
        kate = new Employee("Kate", 100000, "Employee");
        liam = new Employee("Liam", 100000, "Employee");
        mia = new Employee("Mia", 100000, "Employee");
        noah = new Employee("Noah", 100000, "Employee");
        olivia = new Employee("Olivia", 100000, "Employee");
        peter = new Employee("Peter", 100000, "Employee");
        ruby = new Employee("Ruby", 100000, "Employee");
        steve = new Employee("Steve", 100000, "Employee");
        tiana = new Employee("Tiana", 100000, "Employee");
        ulrich = new Employee("Ulrich", 100000, "Employee");
        alpha = new Employee("Alpha", 40000, "Intern");
        bravo = new Employee("Bravo", 40000, "Intern");
        charlie = new Employee("Charlie", 40000, "Intern");
        delta = new Employee("Delta", 40000, "Intern");
        echo = new Employee("Echo", 40000, "Intern");
        foxtrot = new Employee("Foxtrot", 40000, "Intern");
        golf = new Employee("Golf", 40000, "Intern");
        hotel = new Employee("Hotel", 40000, "Intern");

        Company company = new Company("Sample Company", alice);

        alice.hireEmployee(bob);
        alice.hireEmployee(carrie);
        alice.hireEmployee(david);
        bob.hireEmployee(eve);
        bob.hireEmployee(frank);
        bob.hireEmployee(grace);
        carrie.hireEmployee(harry);
        carrie.hireEmployee(ivy);
        david.hireEmployee(jack);
        eve.hireEmployee(kate);
        eve.hireEmployee(liam);
        eve.hireEmployee(mia);
        frank.hireEmployee(noah);
        frank.hireEmployee(olivia);
        grace.hireEmployee(peter);
        harry.hireEmployee(ruby);
        ivy.hireEmployee(steve);
        ivy.hireEmployee(tiana);
        jack.hireEmployee(ulrich);
        kate.hireEmployee(alpha);
        kate.hireEmployee(bravo);
        kate.hireEmployee(charlie);
        liam.hireEmployee(delta);
        liam.hireEmployee(echo);
        mia.hireEmployee(foxtrot);
        noah.hireEmployee(golf);
        peter.hireEmployee(hotel);
        return company;
    }
}
