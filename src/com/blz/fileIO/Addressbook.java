package com.blz.fileIO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Addressbook {
    private static final ArrayList<Contacts> list = new ArrayList<Contacts>();
    /**
     * Read a file using io.FileReader
     * @throws IOException
     */
    public static void readFile() throws IOException {
        FileReader fr = new FileReader("addressbook.txt");
        int i = fr.read();
        while (i != -1){
            System.out.print((char) i);
            i = fr.read();
        }
        fr.close();
    }
    /**
     * Write a file using io.FileWriter
     * @throws IOException
     */
    public static void writeFile() throws IOException {
        try {
            FileWriter fw = new FileWriter("addressbook.txt", true);
            for (Contacts str : list) {
                fw.write("Firstname:: " + str.getFirstName());
                fw.write('\n');
                fw.write("Lastname:: " + str.getLastName());
                fw.write('\n');
                fw.write("Address:: " + str.getAddress());
                fw.write('\n');
                fw.write("City:: " + str.getCity());
                fw.write('\n');
                fw.write("State:: " + str.getState());
                fw.write('\n');
                fw.write("Zip:: " + str.getZip());
                fw.write('\n');
                fw.write("Phone number:: " + (long) str.getPhoneNumber());
                fw.write('\n');
                fw.write("Email:: " + str.getEmail());
                fw.write('\n');
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Call method to sort entry in contact by city
     */
    private void sortByCity() {
        List<Contacts> check = list.stream().sorted(Comparator.comparing(str -> str.getCity())).collect(Collectors.toList());
        ;
        check.forEach(str -> System.out.println(str.toString()));
    }

    /**
     * Call method to sort entry in contact by firstname
     */
    private void sortByFirstname() {
        List<Contacts> check = list.stream().sorted(Comparator.comparing(str -> str.getFirstName())).collect(Collectors.toList());
        ;
        check.forEach(str -> System.out.println(str.toString()));
    }

    /**
     * Call method to count entry in contact by city
     */
    private void getCountByCity() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter city to get count of entry in Contacts: ");
        String city = sc.nextLine();
        long check = list.stream().filter(i -> i.getCity().equals(city)).count();
        System.out.println("Count of contacts in address book by city is " + check);
    }

    /**
     * Call method to check entry in contact by searching state
     */
    private void searchPersonByState() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter state to check entry in Contacts: ");
        String state = sc.nextLine();
        Stream<Contacts> check = list.stream().filter(i -> i.getState().equals(state));
        check.forEach(str -> System.out.println(str.toString()));
    }

    /**
     * Call method to check entry in contact by searching city
     */
    private void searchPersonByCity() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter city to check entry in Contacts: ");
        String city = sc.nextLine();
        Stream<Contacts> check = list.stream().filter(i -> i.getCity().equals(city));
        check.forEach(str -> System.out.println(str.toString()));
    }

    /**
     * Call method to check duplicate entry
     */
    private void checkDuplicateEntry() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter firstname to Check duplicate entry Contact: ");
        String name = sc.nextLine();
        Stream<Contacts> check = list.stream().filter(i -> i.getFirstName().equals(name));
        check.forEach(str -> System.out.println(str.toString()));
    }

    /**
     * Call method to delete contact by searching firstname in contact list
     */
    private void deleteContact() throws ConcurrentModificationException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter firstname to Delete Contact: ");
        String name = sc.nextLine();
        try {
            for (Contacts search : list) {
                if (name.equalsIgnoreCase(search.getFirstName())) {
                    System.out.println("Entered name found in the Address Book, deleting contact");
                    list.remove(search);
                } else {
                    System.out.println("Entered name not found in the Address Book");
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Exception is " + e.toString());
        }
    }

    /**
     * Call method to edit the contact by searching firstname
     */
    private void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter firstname to edit contact: ");
        String name = sc.nextLine();
        for (Contacts search : list) {
            if (name.equalsIgnoreCase(search.getFirstName())) {
                System.out.println("Entered name found in the Contacts");
                System.out.println("Enter the updated first name");
                String firstName = sc.next();
                search.setFirstName(firstName);
                System.out.println("Enter the updated last name");
                String lastName = sc.next();
                search.setLastName(lastName);
                System.out.println("Enter the updated address");
                String address = sc.next();
                search.setAddress(address);
                System.out.println("Enter the updated city");
                String city = sc.next();
                search.setCity(city);
                System.out.println("Enter the updated state");
                String state = sc.next();
                search.setState(state);
                System.out.println("Enter the updated zipcode");
                int zip = sc.nextInt();
                search.setZip(zip);
                System.out.println("Enter the updated phoneNumber");
                long phoneNumber = sc.nextInt();
                search.setPhoneNumber(phoneNumber);
                System.out.println("Enter the updated emailID");
                String email = sc.next();
                search.setEmail(email);
                search.toString();
            } else {
                System.out.println("Entered name not found in the AddressBook");
            }
        }
    }

    /*
     * Call method to add contacts in ArrayList
     * Create object and add details in it and put it in that list
     */
    public void AddContactsDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("First Name = ");
        String firstName = sc.nextLine();
        System.out.println("Last Name = ");
        String lastName = sc.nextLine();
        System.out.println("Address = ");
        String address = sc.nextLine();
        System.out.println("City = ");
        String city = sc.nextLine();
        System.out.println("State = ");
        String state = sc.nextLine();
        System.out.println("Zip Code = ");
        int zip = sc.nextInt();
        System.out.println("Phone Number = ");
        long phoneNumber = sc.nextLong();
        System.out.println("Email = ");
        String email = sc.nextLine();
        Contacts person = new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
        list.add(person);
        person.toString();
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        HashMap<String, AddressBook> addressBooks = new HashMap<>();
        AddressBook book1 = new AddressBook();
        AddressBook book2 = new AddressBook();
        AddressBook book3 = new AddressBook();
        addressBooks.put("AddressBook1", book1);
        addressBooks.put("AddressBook2", book2);
        addressBooks.put("AddressBook3", book3);
        System.out.println("Choose Address Book");
        System.out.println("1. AddressBook 1");
        System.out.println("2. AddressBook 2");
        System.out.println("3. AddressBook 3");
        int chooseAddressBook = sc.nextInt();
        System.out.println("Choose What to do in this Address Book");
        while (chooseAddressBook >= 1) {
            System.out.println("1. Add Contacts");
            System.out.println("2. Edit Contacts");
            System.out.println("3. Delete Contacts");
            System.out.println("4. check duplicate entry");
            System.out.println("5. Search entry by city");
            System.out.println("6. Search entry by state");
            System.out.println("7. Get count by city");
            System.out.println("8. sort by firstname");
            System.out.println("9. sort by city");
            System.out.println("Enter Your Choice");
            int choice = sc.nextInt();
            switch (chooseAddressBook) {
                case 1:
                    if (choice == 1) {
                        book1.AddContactsDetails();
                        writeFile();
                        readFile();
                    } else if (choice == 2) {
                        book1.editContact();
                    } else if (choice == 3) {
                        book1.deleteContact();
                    } else if (choice == 4) {
                        book1.checkDuplicateEntry();
                    } else if (choice == 5) {
                        book1.searchPersonByCity();
                    } else if (choice == 6) {
                        book1.searchPersonByState();
                    } else if (choice == 7) {
                        book1.getCountByCity();
                    } else if (choice == 8) {
                        book1.sortByFirstname();
                    }
                    break;
                case 2:
                    if (choice == 1) {
                        book2.AddContactsDetails();
                    } else if (choice == 2) {
                        book2.editContact();
                    } else if (choice == 3) {
                        book2.deleteContact();
                    } else if (choice == 4) {
                        book2.checkDuplicateEntry();
                    } else if (choice == 5) {
                        book2.searchPersonByCity();
                    } else if (choice == 6) {
                        book2.searchPersonByState();
                    } else if (choice == 7) {
                        book2.getCountByCity();
                    }
                    break;
                case 3:
                    if (choice == 1) {
                        book3.AddContactsDetails();
                    } else if (choice == 2) {
                        book3.editContact();
                    } else if (choice == 3) {
                        book3.deleteContact();
                    } else if (choice == 4) {
                        book3.checkDuplicateEntry();
                    } else if (choice == 5) {
                        book3.searchPersonByCity();
                    } else if (choice == 6) {
                        book3.searchPersonByState();
                    } else if (choice == 7) {
                        book3.getCountByCity();
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.println("1. AddressBook 1");
            System.out.println("2. AddressBook 2");
            System.out.println("3. AddressBook 3");
            System.out.println("0. Exit");
            chooseAddressBook = sc.nextInt();
        }
        System.out.println("The Program End");
    }
}
