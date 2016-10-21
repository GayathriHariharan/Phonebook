import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBookExample {

	static int choice;
	static String phoneNumberAsString;
	static String name;
	static String name2;
	static Long phoneNumber;

	static Scanner scan = new Scanner(System.in);
	public static TreeMap<Long, String> contact = new TreeMap<Long, String>();

	public static void main(String[] args) {

		do {
			try {
				displayChoice();
				choice = scan.nextInt();
				switchChoice(choice);
			} catch (InputMismatchException e) {
				System.out.println("Please provide any input ranging from 1 to 6 ");
				choice = 0;
				scan.next();
			}
		} while (choice != 6);

	}

	public static void displayChoice() {

		System.out.println();
		System.out.println("1.Add");
		System.out.println("2.Update");
		System.out.println("3.Delete");
		System.out.println("4.Display all contacts");
		System.out.println("5.Search");
		System.out.println("6.Exit");
		System.out.println("Please enter your choice");

	}

	public static void switchChoice(int choice) {
		switch (choice) {
		case 1:
			add();
			break;
		case 2:
			update();
			break;
		case 3:
			delete();
			break;
		case 4:
			displayAllContact();
			break;
		case 5:
			search();
			break;
		case 6:
			System.exit(0);
		default:
			System.out.println("Please provide any input ranging from 1 to 6");
			break;

		}
	}

	public static void add() {

		try {

		name=	 getName();

			
		} catch (NullPointerException npe) {
			System.out.println("Name cannot be empty..Please enter valid input");
			name = scan.next();
		}
		try {
			System.out.println("Please enter phone number");
			phoneNumberAsString = scan.next();
			phoneNumber = Long.parseLong(phoneNumberAsString);

		} catch (NumberFormatException e) {
			System.out.println("Please enter only numbers");
			phoneNumberAsString = scan.next();
			phoneNumber = Long.parseLong(phoneNumberAsString);

		}
		contact.put(phoneNumber, name);
		System.out.println("Contact successfully added");

	}

	public static void update() {

		if (!(contact.isEmpty())) {
			
			name= getNameOne();
			phoneNumber=getPhoneNumberFromMap(name);
			
			contact.remove(phoneNumber);
			
					System.out.println("Enter New Phone number");
					try {
						phoneNumber = scan.nextLong();
					} catch (NumberFormatException nfe) {
						System.out.println("Enter only Numbers");
						phoneNumber = scan.nextLong();
					}
					contact.put(phoneNumber, name);
					System.out.println(name + "'s phone number is: " + phoneNumber);
			
		} else {
			System.out.println("The Phone book is empty please add any contact");
		}
	}

	public static void delete() {
		if (!(contact.isEmpty())) {
			name=getNameOne();
			phoneNumber=getPhoneNumberFromMap(name);
			
					contact.remove(phoneNumber);
					System.out.println("Contact deleted successfully");

		} else {
			System.out.println("The Phone book is empty please add any contact");
		}

	}

	public static void displayAllContact() {

		if (!(contact.isEmpty())) {
			for (Entry<Long, String> entry : contact.entrySet()) {

				System.out.println("Name        : " + entry.getValue());
				System.out.println("Phone number: " + entry.getKey());
			}
		} else {
			System.out.println("There is no entry in phone book!! Please add a contact");
		}

	}

	public static void search() {
		if (!(contact.isEmpty())) {
			name=getNameOne();
			phoneNumber=getPhoneNumberFromMap(name);
					System.out.println(name + " phone number is: " + phoneNumber);
	
		} else {
			System.out.println("The Phone book is empty please add any contact");
		}
	}

	public static String getName() {
		System.out.print("Please enter Name:");
		name2=scan.next();
		checkName(name2);
		return name2;
	}

	public static void checkName(String name2) {

		if (!contact.isEmpty()) {
			for (Entry<Long, String> entry : contact.entrySet()) {
				if (name2.equals(entry.getValue())) {
					System.out.println("The Name already exist.. Please enter any new name");
					getName();
				}
			}
			
		}

	}
	public static String getNameOne(){
		
		System.out.println("Please enter the name");
		name=scan.next();
		checkNameOne(name);
		return name;
	}
	public static void checkNameOne(String name){
	if(!(contact.containsValue(name))){
		System.out.println("The name doesnot  exists in phone book. please enter another name");
		getNameOne();
	}
	
}
	public static Long getPhoneNumberFromMap(String name){
		
		for (Entry<Long, String> entry : contact.entrySet()) {
			String name1 = entry.getValue();
			if (name.equals(name1)) {
				phoneNumber = entry.getKey();
			}
		}
		
		return phoneNumber;
	}
}
