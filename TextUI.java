import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
import java.util.*;

public class TextUI
{
	public void clearScreen()
	{
		for(int i = 0;i<125;i++)
		{
			System.out.println("");
		}
	}

	public void pauseScreen()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Press any key to continue...");
		String a = scan.nextLine();
	}

	public ArrayList<String> acceptLogin()
	{
		ArrayList<String> credentials = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		System.out.println("PLEASE ENTER YOUR CREDENTIALS\n\n\n\n");
		System.out.print("Please enter your Username: ");
		credentials.add(scan.nextLine());
		System.out.print("Please enter your Password: ");
		credentials.add(scan.nextLine());
		return credentials;
	}

	public void main()	
	{
		TextUI tU = new TextUI();
		int choice = 7;
		DataManager dM = new DataManager();
		Scanner scan = new Scanner(System.in);
		//if(dM.pullAddressBook.)
		AddressBook adBook = dM.pullAddressBook();
		int index,index1,index2,index3=0,index4=0;


		while (choice != 6)
		{
			tU.clearScreen();
			System.out.println("*************************************************");
			System.out.println("           A D D R E S S     B O O K             ");
			System.out.println("*************************************************");
			System.out.println("1 - Add Contact to the address book");
			System.out.println("2 - Search for/Display a contact");
			System.out.println("3 - Edit contact");
			System.out.println("4 - Display all contacts");
			System.out.println("5 - Delete a contact");
			System.out.println("6 - Exit");
			System.out.println("************************************************");
			System.out.println("Choose your option: ");
			try
			{
				choice = scan.nextInt();
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter an integer");
				choice = scan.nextInt();
			}
			
			tU.clearScreen();
			Gender g = Gender.MALE;
			switch (choice)
			{
				case 1:
					System.out.println("Enter Contact's First Name: ");
					String h = scan.nextLine();
					String fName = scan.nextLine();
					System.out.println("Enter Contact's Last Name: ");
					String lName = scan.nextLine();
					int gen = 3;
					while (gen != 1 || gen != 2)
					{
						System.out.println("Enter Gender of Contact (1 for Male, 2 for Female):");
						try
						{
							gen = scan.nextInt();
						}
						catch(NumberFormatException e)
						{
							System.out.println("Please enter an integer");
							gen = scan.nextInt();
						}
						
						if(gen == 1)
						{
							break;
						}
						if(gen == 2)
						{
							break;
						}
					
					}
					g = gen == 1 ? Gender.MALE : Gender.FEMALE;
					h = scan.nextLine();
					System.out.println("Enter Alias of Contact: ");
					String alias = scan.nextLine();
					System.out.println("Enter Contact Address (Use Semicolons (;) to separate the lines): ");
					String add = scan.nextLine();
					System.out.println("Enter Contact's Date of Birth (in the form yyyymmdd): ");
					long dob = scan.nextLong();
					adBook.makeContact(fName, lName, g, alias, add, dob);
					System.out.println("Added Successfully\n\n\n");
					this.pauseScreen();
					break;
				case 2:
					int searchChoice=0;
					while (searchChoice==0)
					{
						System.out.println("What would you like to search by?");
						System.out.println("1- By entry number");
						System.out.println("2 - By email address");
						h = scan.nextLine();
						System.out.print("Choose your option: ");
						try
						{
							searchChoice = scan.nextInt();
						}
						catch(NumberFormatException e)
						{
							System.out.println("Please enter an integer");
							searchChoice = scan.nextInt();
						}
						
						System.out.println("");
						switch(searchChoice)
						{
							case 1:
								System.out.print("Enter entry number: ");
								int num = scan.nextInt();
								System.out.println("");
								index1 = adBook.searchByEntry(num);
								adBook.printOne(index1);
								break;
							case 2:
								System.out.print("Enter email: ");
								String email = scan.nextLine();
								System.out.println("");
								index2 = adBook.searchByEmail(email);
								adBook.printOne(index2);
								break;
							default:
								System.out.println("Incorrect Option. Please Try Again.");
								break;
						}
					}
					this.pauseScreen();
					break;
				case 3:
					int searchChoice1=0;
					while (searchChoice1==0)
					{
						System.out.println("You will have to specify the contact first.");
						System.out.println("What would you like to search by?");
						System.out.println("1- By entry number");
						System.out.println("2 - By email address");
						h = scan.nextLine();
						System.out.print("Choose your option: ");
						searchChoice1 = scan.nextInt();
						System.out.println("");
						switch(searchChoice1)
						{
							case 1:
								System.out.print("Enter entry number: ");
								int num = scan.nextInt();
								System.out.println("");
								index3 = adBook.searchByEntry(num);
								break;
							case 2:
								System.out.print("Enter email: ");
								String email = scan.nextLine();
								System.out.println("");
								index3 = adBook.searchByEmail(email);
								break;
							default:
								System.out.println("Incorrect Option. Please Try Again.");
								break;
						}
					}
					int editChoice=0;
					
					if (index3>=0)
					{
						adBook.printOneMenu(index3);
						System.out.println("************************");
						System.out.println("This is the contact we found.");
						while (editChoice==0)
						{
							System.out.println("What would you like to edit?");
							System.out.println("1- Last name");
							System.out.println("2 - Alias");
							System.out.println("3- Address");
							System.out.println("4 - Add telephone number");
							System.out.println("5- Delete telephone number");
							System.out.println("6 - Add email address");
							System.out.println("7 - Delete email address");
							System.out.println("");
							System.out.print("Choose your option: ");
							editChoice = scan.nextInt();
							System.out.println("");
							h = scan.nextLine();
							Contact hold;
							AddressBook hold1;
							switch(editChoice)
							{
								case 1:
									System.out.print("Enter new last name: ");
									String newName = scan.nextLine();
									System.out.println("");
									hold1 = adBook;
									hold1.editLastName(newName,index3);
									adBook = hold1;
									break;

								case 2:
									System.out.print("Enter Alias: ");
									String alias1 = scan.nextLine();
									System.out.println("");
									hold1 = adBook;
									hold1.editAlias(alias1,index3);
									adBook = hold1;
									break;

								case 3:
									System.out.println("Enter address, separated by semicolons (;): ");
									String address = scan.nextLine();
									System.out.println("");
									hold1 = adBook;
									hold1.editAddress(address,index3);
									adBook = hold1;
									break;
									
								case 4:
									System.out.print("Enter phone to add: ");
									long tel = scan.nextLong();
									System.out.println("");
									System.out.print("Enter type (H- Home, W-Work, M- Mobile): ");
									char type = scan.next().charAt(0);
									System.out.println("");
									hold1 = adBook;
									hold1.addPhoneNum(type,tel,index3);
									adBook = hold1;
									break;
								
								case 5:
									adBook.getConList().get(index3).printPhones();
									System.out.print("Enter phone to delete: ");
									long tele = scan.nextLong();
									System.out.println("");
									hold1 = adBook;
									hold1.deletePhoneNum(tele,index3);
									adBook = hold1;
									break;
									
								case 6:
									System.out.print("Enter email to add: ");
									String email = scan.nextLine();
									System.out.println("");
									hold1 = adBook;
									hold1.updateEmail(email,index3);
									adBook = hold1;
									break;
									
								case 7:
									adBook.getConList().get(index3).printEmails();
									System.out.print("Enter email to delete: ");
									String email1 = scan.nextLine();
									System.out.println("");
									hold1 = adBook;
									hold1.deleteEmail(email1,index3);
									adBook = hold1;
									break;
									
								default:
									System.out.println("Incorrect Option. Please Try Again.");
									break;
							}
						}
					}
					else
					{
						System.out.println("The contact could not be found.");
					}
					this.pauseScreen();
					break;
				case 4:
					int displayChoice=0;
					while (displayChoice==0)
					{
						System.out.println("What would you like to sort by before you display?");
						System.out.println("1 - By entry number");
						System.out.println("2 - By name");
						h = scan.nextLine();
						System.out.println("");
						System.out.print("Choose your option: ");
						displayChoice = scan.nextInt();
						System.out.println("");
						switch(displayChoice)
						{
							case 1:
								adBook.displayByEntryNo();
								break;
							case 2:
								adBook.displayByName();
								break;
							default:
								System.out.println("Incorrect Option. Please Try Again.");
								break;
						}
						if(adBook.getConList().size() ==0)
						{
							System.out.println("No Enteries In The AddressBook");
						}
					}
					this.pauseScreen();
					break;
				case 5:
					int searchChoice2=0;
					while (searchChoice2==0)
					{
						System.out.println("You will have to specify the contact first.");
						System.out.println("What would you like to search by?");
						System.out.println("1 - By entry number");
						System.out.println("2 - By email address");
						h = scan.nextLine();
						System.out.println("");
						System.out.print("Choose your option: ");
						searchChoice2 = scan.nextInt();
						System.out.println("");
						//int index4;
						switch(searchChoice2)
						{
							case 1:
								System.out.print("Enter entry number: ");
								int num = scan.nextInt();
								System.out.println("");
								index4 = adBook.searchByEntry(num);
								break;
							case 2:
								System.out.print("Enter email: ");
								String email = scan.nextLine();
								System.out.println("");
								index4 = adBook.searchByEmail(email);
								break;
							default:
								System.out.println("Incorrect Option. Please Try Again.");
								break;
						}
					}
					int delChoice=0;
					
					if (index4>=0)
					{
						adBook.printOneMenu(index4);
						System.out.println("************************");
						System.out.println("This is the contact we found.");
						while (delChoice==0)
						{
							System.out.println("Are you sure you want to delete this contact?");
							System.out.println("1 - Yes");
							System.out.println("2 - No");
							System.out.print("Choose your option: ");
							delChoice = scan.nextInt();
							System.out.println("");
							switch(delChoice)
							{
								case 1:
									System.out.println("Contact will be deleted.");
									adBook.deleteContact(index4);
									break;
								case 2:
									System.out.println("Contact will not be deleted. ");
									break;
								default:
									System.out.println("Incorrect Option. Please Try Again.");
									break;
							}
						}
					}
					else
					{
						System.out.println("The contact could not be found.");
					}
					this.pauseScreen();
					break;
					
				case 6:
					int saveChoice=0;
					while (saveChoice==0)
					{
						System.out.println("Before you go, would you like to save the new changes?");
						System.out.println("1 - Yes");
						System.out.println("2 - No");
						System.out.print("Choose your option: ");
						saveChoice = scan.nextInt();
						switch(saveChoice)
						{
							case 1:
								System.out.println("Saving... ");
								dM.saveAddressBook(adBook);
								this.pauseScreen();
								break;
							case 2:
								System.out.println("Thank you! Come again soon!");
								this.pauseScreen();
								break;
							default:
								System.out.println("Incorrect Option. Please Try Again.");
								break;
						}
					}
					break;
				default:
					System.out.println("Please Enter another choice.\n");
					break;

			}
		}
	}
}
