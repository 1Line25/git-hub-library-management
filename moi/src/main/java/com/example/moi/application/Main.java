package com.example.moi.application;

// Including Header Files.
import java.io.*;
import java.util.*;
import java.sql.*;

import com.example.moi.models.Person;
import com.example.moi.models.Library;
import com.example.moi.models.Book;
import com.example.moi.models.Borrower;
import com.example.moi.models.Staff;
import com.example.moi.models.Clerk;
import com.example.moi.models.Librarian;
import com.example.moi.models.Loan;

public class Main {
    // Clearing Required Area of Screen
    public static void clrscr() {
        for (int i = 0; i < 20; i++)
            System.out.println();
    }

    // Asking for Input as Choice
    public static int takeInput(int min, int max) {
        String choice;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter Choice: ");

            choice = input.next();

            try {
                int numChoice = Integer.parseInt(choice);
                if (numChoice > min && numChoice < max) {
                    return numChoice;
                } else {
                    System.out.println("\nInvalid Input. Please enter a number between " + (min + 1) + " and " + (max - 1) + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid Input. Please enter a number.");
            }
        }
    }

    // Functionalities of all Persons
    public static void allFunctionalities(Person person, int choice) throws IOException {
        Library lib = Library.getInstance();

        Scanner scanner = new Scanner(System.in);
        int input = 0;

        //Search Book
        if (choice == 1) {
            lib.searchForBooks();
        }

        //Do Hold Request
        else if (choice == 2) {
            ArrayList<Book> books = lib.searchForBooks();

            if (books != null) {
                input = takeInput(-1, books.size());
                if (input >= 0 && input < books.size()) {
                    Book b = books.get(input);

                    if ("Clerk".equals(person.getClass().getSimpleName()) || "Librarian".equals(person.getClass().getSimpleName())) {
                        Borrower bor = lib.findBorrower();

                        if (bor != null)
                            b.makeHoldRequest(bor);
                    } else if (person instanceof Borrower) {
                        b.makeHoldRequest((Borrower) person);
                    }
                } else {
                    System.out.println("\nInvalid book selection.");
                }
            }
        }

        //View borrower's personal information
        else if (choice == 3) {
            if ("Clerk".equals(person.getClass().getSimpleName()) || "Librarian".equals(person.getClass().getSimpleName())) {
                Borrower bor = lib.findBorrower();

                if (bor != null)
                    bor.printInfo();
            } else {
                person.printInfo();
            }
        }

        //Compute Fine of a Borrower
        else if (choice == 4) {
            if ("Clerk".equals(person.getClass().getSimpleName()) || "Librarian".equals(person.getClass().getSimpleName())) {
                Borrower bor = lib.findBorrower();

                if (bor != null) {
                    double totalFine = lib.computeFine2(bor);
                    System.out.println("\nYour Total Fine is : Rs " + totalFine);
                }
            } else if (person instanceof Borrower) {
                double totalFine = lib.computeFine2((Borrower) person);
                System.out.println("\nYour Total Fine is : Rs " + totalFine);
            }
        }

        //Check hold request queue of a book
        else if (choice == 5) {
            ArrayList<Book> books = lib.searchForBooks();

            if (books != null) {
                input = takeInput(-1, books.size());
                if (input >= 0 && input < books.size()) {
                    books.get(input).printHoldRequests();
                } else {
                    System.out.println("\nInvalid book selection.");
                }
            }
        }

        //Issue a Book
        else if (choice == 6) {
            ArrayList<Book> books = lib.searchForBooks();

            if (books != null) {
                input = takeInput(-1, books.size());
                if (input >= 0 && input < books.size()) {
                    Book b = books.get(input);

                    Borrower bor = lib.findBorrower();

                    if (bor != null && person instanceof Staff) {
                        b.issueBook(bor, (Staff) person);
                    } else if (bor == null) {
                        System.out.println("\nBorrower not found.");
                    } else {
                        System.out.println("\nOnly staff members can issue books.");
                    }
                } else {
                    System.out.println("\nInvalid book selection.");
                }
            }
        }

        //Return a Book
        else if (choice == 7) {
            Borrower bor = lib.findBorrower();

            if (bor != null) {
                bor.printBorrowedBooks();
                ArrayList<Loan> loans = bor.getBorrowedBooks();

                if (!loans.isEmpty()) {
                    input = takeInput(-1, loans.size());
                    if (input >= 0 && input < loans.size()) {
                        Loan l = loans.get(input);
                        if (person instanceof Staff) {
                            l.getBook().returnBook(bor, l, (Staff) person);
                        } else {
                            System.out.println("\nOnly staff members can return books.");
                        }
                    } else {
                        System.out.println("\nInvalid loan selection.");
                    }
                } else
                    System.out.println("\nThis borrower " + bor.getName() + " has no book to return.");
            } else {
                System.out.println("\nBorrower not found.");
            }
        }

        //Renew a Book
        else if (choice == 8) {
            Borrower bor = lib.findBorrower();

            if (bor != null) {
                bor.printBorrowedBooks();
                ArrayList<Loan> loans = bor.getBorrowedBooks();

                if (!loans.isEmpty()) {
                    input = takeInput(-1, loans.size());
                    if (input >= 0 && input < loans.size()) {
                        loans.get(input).renewIssuedBook(new java.util.Date());
                    } else {
                        System.out.println("\nInvalid loan selection.");
                    }
                } else
                    System.out.println("\nThis borrower " + bor.getName() + " has no issued book which can be renewed.");
            } else {
                System.out.println("\nBorrower not found.");
            }
        }

        //Add new Borrower
        else if (choice == 9) {
            lib.createPerson('b');
        }

        //Update Borrower's Personal Info
        else if (choice == 10) {
            Borrower bor = lib.findBorrower();

            if (bor != null)
                bor.updateBorrowerInfo();
            else
                System.out.println("\nBorrower not found.");
        }

        //Add new Book
        else if (choice == 11) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\nEnter Title:");
            String title = reader.readLine();

            System.out.println("\nEnter Subject:");
            String subject = reader.readLine();

            System.out.println("\nEnter Author:");
            String author = reader.readLine();

            lib.createBook(title, subject, author);
        }

        //Remove a Book
        else if (choice == 12) {
            ArrayList<Book> books = lib.searchForBooks();

            if (books != null) {
                input = takeInput(-1, books.size());
                if (input >= 0 && input < books.size()) {
                    lib.removeBookfromLibrary(books.get(input));
                } else {
                    System.out.println("\nInvalid book selection.");
                }
            }
        }

        //Change a Book's Info
        else if (choice == 13) {
            ArrayList<Book> books = lib.searchForBooks();

            if (books != null) {
                input = takeInput(-1, books.size());
                if (input >= 0 && input < books.size()) {
                    books.get(input).changeBookInfo();
                } else {
                    System.out.println("\nInvalid book selection.");
                }
            }
        }

        //View clerk's personal information
        else if (choice == 14) {
            Clerk clerk = lib.findClerk();

            if (clerk != null)
                clerk.printInfo();
            else
                System.out.println("\nClerk not found.");
        }

        // Functionality Performed.
        System.out.println("\nPress any key to continue..\n");
        scanner.next();
    }

    /*-------------------------------------MAIN---------------------------------------------------*/

    public static void main(String[] args) {
        Scanner admin = new Scanner(System.in);

        //-------------------INTERFACE---------------------------//

        Library lib = Library.getInstance();

        // Setting some by default information like name of library ,fine, deadline and limit of hold request
        lib.setFine(20);
        lib.setRequestExpiry(7);
        lib.setReturnDeadline(5);
        lib.setName("FAST Library");

        // Making connection with Database.
        Connection con = lib.makeConnection();

        if (con == null) // Oops can't connnect !
        {
            System.out.println("\nError connecting to Database. Exiting.");
            return;
        }

        try {

            lib.populateLibrary(con); // Populating Library with all Records

            boolean stop = false;
            while (!stop) {
                clrscr();

                // FRONT END //
                System.out.println("--------------------------------------------------------");
                System.out.println("\tWelcome to Library Management System");
                System.out.println("--------------------------------------------------------");

                System.out.println("Following Functionalities are available: \n");
                System.out.println("1- Login");
                System.out.println("2- Exit");
                System.out.println("3- Admininstrative Functions"); // Administration has access only

                System.out.println("-----------------------------------------\n");

                int choice = 0;

                choice = takeInput(0, 4);

                if (choice == 3) {
                    System.out.println("\nEnter Password: ");
                    String aPass = admin.next();

                    if (aPass.equals("lib")) {
                        while (true) // Way to Admin Portal
                        {
                            clrscr();

                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Admin's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");

                            System.out.println("1- Add Clerk");
                            System.out.println("2- Add Librarian");
                            System.out.println("3- View Issued Books History");
                            System.out.println("4- View All Books in Library");
                            System.out.println("5- Logout");

                            System.out.println("---------------------------------------------");

                            choice = takeInput(0, 6);

                            if (choice == 5)
                                break;

                            if (choice == 1)
                                lib.createPerson('c');
                            else if (choice == 2)
                                lib.createPerson('l');

                            else if (choice == 3)
                                lib.viewHistory();

                            else if (choice == 4)
                                lib.viewAllBooks();

                            System.out.println("\nPress any key to continue..\n");
                            admin.next();
                        }
                    } else
                        System.out.println("\nSorry! Wrong Password.");
                } else if (choice == 1) {
                    Person person = lib.login();

                    if (person == null) {
                        System.out.println("\nLogin failed. Please check your credentials.");
                        System.out.println("\nPress any key to continue..\n");
                        Scanner scanner = new Scanner(System.in);
                        scanner.next();
                    } else if (person instanceof Borrower) {
                        while (true) // Way to Borrower's Portal
                        {
                            clrscr();

                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Borrower's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Search a Book");
                            System.out.println("2- Place a Book on hold");
                            System.out.println("3- Check Personal Info of Borrower");
                            System.out.println("4- Check Total Fine of Borrower");
                            System.out.println("5- Check Hold Requests Queue of a Book");
                            System.out.println("6- Logout");
                            System.out.println("--------------------------------------------------------");

                            choice = takeInput(0, 7);

                            if (choice == 6)
                                break;

                            allFunctionalities(person, choice);
                        }
                    } else if (person instanceof Clerk) {
                        while (true) // Way to Clerk's Portal
                        {
                            clrscr();

                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Clerk's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Search a Book");
                            System.out.println("2- Place a Book on hold");
                            System.out.println("3- Check Personal Info of Borrower");
                            System.out.println("4- Check Total Fine of Borrower");
                            System.out.println("5- Check Hold Requests Queue of a Book");
                            System.out.println("6- Check out a Book");
                            System.out.println("7- Check in a Book");
                            System.out.println("8- Renew a Book");
                            System.out.println("9- Add a new Borrower");
                            System.out.println("10- Update a Borrower's Info");
                            System.out.println("11- Logout");
                            System.out.println("--------------------------------------------------------");

                            choice = takeInput(0, 12);

                            if (choice == 11)
                                break;

                            allFunctionalities(person, choice);
                        }
                    } else if (person instanceof Librarian) {
                        while (true) // Way to Librarian Portal
                        {
                            clrscr();

                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Librarian's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Search a Book");
                            System.out.println("2- Place a Book on hold");
                            System.out.println("3- Check Personal Info of Borrower");
                            System.out.println("4- Check Total Fine of Borrower");
                            System.out.println("5- Check Hold Requests Queue of a Book");
                            System.out.println("6- Check out a Book");
                            System.out.println("7- Check in a Book");
                            System.out.println("8- Renew a Book");
                            System.out.println("9- Add a new Borrower");
                            System.out.println("10- Update a Borrower's Info");
                            System.out.println("11- Add new Book");
                            System.out.println("12- Remove a Book");
                            System.out.println("13- Change a Book's Info");
                            System.out.println("14- Check Personal Info of Clerk");
                            System.out.println("15- Logout");
                            System.out.println("--------------------------------------------------------");

                            choice = takeInput(0, 16);

                            if (choice == 15)
                                break;

                            allFunctionalities(person, choice);
                        }
                    }

                } else
                    stop = true;

                System.out.println("\nPress any key to continue..\n");
                Scanner scanner = new Scanner(System.in);
                scanner.next();
            }

            //Loading back all the records in database
            lib.fillItBack(con);
        } catch (Exception e) {
            System.out.println("\nExiting...\n");
            e.printStackTrace(); // It's good practice to print the stack trace for debugging
        } // System Closed!

    } // Main Closed

} // Class closed.