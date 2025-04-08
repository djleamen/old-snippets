// Description: A class representing a contact list that allows for multiple contact methods 
// (e.g., phone numbers) per name.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a contact list that allows for multiple contact methods (e.g., phone numbers) per name.
 */
public class ContactList {
    private final Map<String, List<ContactMethod>> contacts;

    /*
     *  BELOW ARE THE METHODS FOR PART 1
     *
     */

    /**
     * Constructor to initialize the contact list.
     */
    public ContactList() {
        contacts = new HashMap<>();
    }

    /**
     * Adds a contact method (e.g., phone number) to the contact list for the given name.
     *
     * @param name   The name of the contact.
     * @param method The type of contact method (e.g., "phone", "email").
     * @param value  The value of the contact method (e.g., the actual phone number or email address).
     */
    public void addContactMethod(String name, String method, String value) {
        contacts.putIfAbsent(name, new ArrayList<>());
        contacts.get(name).add(new ContactMethod(method, value));
    }

    /**
     * Gets the number of contacts in the contact list.
     *
     * @return The number of contacts.
     */
    public int getSize() {
        return contacts.size();
    }

    /**
     * Prints the contact list in the following format, where X is the total number of contacts:
     *
     * All Contacts: (X)
     *
     * Contact name:
     *     contact method: value
     *     ...
     *
     * ...
     */
    public void printContacts() {
        System.out.println("All Contacts: (" + getSize() + ")");
        for (String name : contacts.keySet()) {
            System.out.println(name + ":");
            for (ContactMethod method : contacts.get(name)) {
                System.out.println("    " + method);
            }
        }
    }

    /**
     * Retrieves the list of contact methods for the given name.
     *
     * @param name The name of the contact.
     * @return The list of contact methods, or null if the contact does not exist.
     */
    public List<ContactMethod> getContactMethods(String name) {
        return contacts.getOrDefault(name, null);
    }

    /*
     *  BELOW ARE THE METHODS FOR PART 2
     *
     */


    /**
     * Checks if the contact list contains a contact with the given name.
     *
     * @param name The name to check.
     * @return True if the contact list contains the name, false otherwise.
     */
    public boolean containsContact(String name) {
        return contacts.containsKey(name);
    }

    /**
     * Removes a specific contact method (e.g., phone number) from the contact list for the given name.
     *
     * @param name   The name of the contact.
     * @param method The type of contact method to remove.
     * @param value  The value of the contact method to remove.
     */
    public void removeContactMethod(String name, String method, String value) {
        if (!contacts.containsKey(name)) {
            System.out.println("No contact information for " + name + "was found to remove.");
            return;
        }
        List<ContactMethod> methods = contacts.get(name);
        methods.removeIf(m -> m.getMethod().equals(method) && m.getValue().equals(value));
        if (methods.isEmpty()) {
            contacts.remove(name);
        }
    }

    /**
     * A class representing a single contact method.
     */
    public static class ContactMethod {
        private final String method;
        private final String value;

        /**
         * Constructs a contact method with the specified method and value.
         *
         * @param method The type of contact method (e.g., "phone", "email").
         * @param value  The value of the contact method (e.g., the actual phone number or email address).
         */
        public ContactMethod(String method, String value) {
            this.method = method;
            this.value = value;
        }

        /**
         * Gets the contact method.
         *
         * @return The contact method.
         */
        public String getMethod() {
            return method;
        }

        /**
         * Gets the contact value.
         *
         * @return The contact value.
         */
        public String getValue() {
            return value;
        }

        /**
         * Returns a string representation of the contact method.
         *
         * @return The string representation of the contact method.
         */
        @Override
        public String toString() {
            return method + ": " + value;
        }
    }

    /**
     * Main method to test the ContactList class.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        ContactList contactList = new ContactList();

        // Add contacts
        contactList.addContactMethod("Bob", "cell phone", "987-654-3210");
        contactList.addContactMethod("Bob", "work phone", "1-888-234-8797 ext 667");
        contactList.addContactMethod("Alice", "cell phone", "123-456-7890");
        contactList.addContactMethod("Alice", "email", "alice@example.com");
        contactList.addContactMethod("Alice", "instagram", "@AlicesAdventures");
        contactList.addContactMethod("Charlie", "cell phone", "555-555-5555");

        // Print all contacts
        contactList.printContacts();

        // Get contact methods for a specific contact
        System.out.println("\nAll Contact Info for Bob:");
        System.out.println(contactList.getContactMethods("Bob"));

        // Remove specific contact methods
        System.out.println("\nRemoving Bob and Charlie's cell phone numbers:");
        contactList.removeContactMethod("Bob", "cell phone", "987-654-3210");
        contactList.removeContactMethod("Charlie", "cell phone", "555-555-5555");

        // Print updated contacts
        contactList.printContacts();

        // Check if contacts exist
        System.out.println("\nDo we have contact info for Bob? " + (contactList.containsContact("Bob") ? "Yes" : "No"));
        System.out.println("Do we have contact info for Charlie? " + (contactList.containsContact("Charlie") ? "Yes" : "No"));

        // Attempt to remove a non-existing contact method
        System.out.println("\nRemove Bob's instagram:");
        contactList.removeContactMethod("Bob", "instagram", "@AlicesAdventures");

        System.out.println("\nRemove Dana's cell phone:");
        contactList.removeContactMethod("Dana", "cell phone", "unknown");

        // Print final contacts
        contactList.printContacts();
    }
}
