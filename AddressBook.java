import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

public class AddressBook implements Serializable
{
	private static final long serialVersionUID = 1L;

	//Attributes
	private ArrayList<Contact> conList = new ArrayList<Contact>();
	private ArrayList<String> aliasList = new ArrayList<String>();
	private ArrayList<Phone> phoneList = new ArrayList<Phone>();
	private ArrayList<String> emailList = new ArrayList<String>();

	//Constructor 
	public AddressBook()
	{

	}

	public ArrayList<Contact> getConList()
	{
		return this.conList;
	}
	public void setConList(ArrayList<Contact> conList)
	{
		this.conList = conList;
	}

	//Methods	
	public Contact makeContact(String fName, String lName, Gender gen, String alias, String address, long dob)
	{
		
		if(isValidAlias(alias))
		{
			this.aliasList.add(alias);	
			Contact con = new Contact(fName,lName,gen,alias,address,dob);
			addContact(con);
			return con;
		}
		else
		{
			this.aliasList.add("");
			Contact con = new Contact(fName,lName,gen,"",address,dob);
			addContact(con);
			return con;
		}
		
		
	}


	public Boolean isValidAlias(String alias)
	{
		if(aliasList.contains(alias))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public Boolean isValidPhone(Phone ph)
	{
		if(phoneList.contains(ph))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public Boolean isValidEmail(String email)
	{
		if(emailList.contains(email))
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	public void addContact(Contact con)
	{
		this.conList.add(con);
	}

	public void removeContact(Contact con)
	{
		this.conList.remove(con);
	}

	public int searchByEntry(int num)//4.2
	{
		return Collections.binarySearch(conList, new Contact(num), Contact.EntryComparator);
	}

	public void printOneByEntry(int num)
	{
		int ind = searchByEntry(num);
		if (ind<0)
		{
			System.out.println("The contact could not be found.");
		}
		else
		{
			conList.get(ind).displayDetails(1);
		}
	}

	public int searchByEmail(String email)//4.3
	{
		for(int i=0; i<conList.size(); i++)
		{
			//if (conLst.get(i).getEmailList().contains(email))//if email is in the list of emails
			if (Arrays.stream(conList.get(i).getEmailList()).anyMatch(t -> t.equals(email)))
			{
				return i;//returns index of contact
			}
		}
		return -1;
	}
	/**Method to print the contact that was searched for using the email
	 * 
	 * @param email email to search for. Type String
	 */
	public void printOneByEmail(String email)
	{
		int ind = searchByEmail(email);
		if (ind==-1)
		{
			System.out.println("The contact could not be found.");
		}
		else
		{
			conList.get(ind).displayDetails(1);
		}
	}

	public void displayByEntryNo()
	{
		Collections.sort(conList);
		for(Contact c:conList)
		{
			c.displayDetails(0);
		}
	}

	public void displayByName()
	{
		Collections.sort(conList, Contact.NameComparator);
		for(Contact c:conList)
		{
			c.displayDetails(0);
		}
	}

	public void deleteByEntryNo(int num)
	{
		int ind = searchByEntry(num);
		if (ind<0)
		{
			System.out.println("The contact could not be found.");
		}
		else
		{
			conList.remove(ind);
		}
	}

	public void editLastName(String lName,int position)
	{
		this.conList.get(position).updateName(lName);
	}

	public void editAlias(String alias,int position)
	{
		if(isValidAlias(alias))
		{
			this.conList.get(position).setAlias(alias);
		}
		else
		{
			System.out.println("Alias already exists");
		}
		
	}

	public void editAddress(String address, int position)
	{
		this.conList.get(position).setAddress(address);
	}

	public void addPhoneNum(char type, long pNum, int position)
	{
		if(isValidPhone(new Phone(pNum,type)))
		{
			this.conList.get(position).addPhone(type,pNum);
		}
		else
		{
			System.out.println("Phone Number already exists");
		}
		
	}

	public void deletePhoneNum(long pNum, int position)
	{
		this.conList.get(position).deletePhone(pNum);
	}

	public void printOne(int ind)
	{
		if (ind==-1)
		{
			System.out.println("The contact could not be found.");
		}
		else
		{
			this.conList.get(ind).displayDetails(1);
		}
	}

	public void printOneMenu(int ind)
	{
		this.conList.get(ind).displayDetails(1);
	}

	public void updateEmail(String email,int index)
	{	
		if(isValidEmail(email))
		{
			this.conList.get(index).addEmail(email);	
		}
		else
		{
			System.out.println("Email already exists");
		}
		
	}

	public void deleteEmail(String email,int index)
	{
		this.conList.get(index).deleteEmail(email);
	}

	public void deleteContact(int num)
	{
		//int ind = searchByEntry(num);
		if (num<0)
		{
			System.out.println("The contact could not be found.");
		}
		else
		{
			conList.remove(num);
		}
	}
	
	public String returnStr(int ind)
	{
		return this.conList.get(ind).returnDetails(1);
	}
	
	public String returnByEntryNo()
	{
		Collections.sort(conList);
		String arr= "";
		for(Contact c:conList)
		{
			arr= arr + c.returnDetails(0);
			arr = arr + "\n";
		}
		return arr;
	}

	public String returnByName()
	{
		Collections.sort(conList, Contact.NameComparator);
		String arr= "";
		for(Contact c:conList)
		{
			arr= arr + c.returnDetails(0);
			arr = arr + "\n";
		}
		return arr;
	}

	public static void main(String[] args)
	{	
		AddressBook adBook = new AddressBook();
		// FOUR Contacts
		adBook.makeContact("Marlon","Williams", Gender.MALE,"Alex","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",14121999L);
		adBook.conList.get(0).addEmail("marlon.kingson@gmail.com");
		adBook.conList.get(0).addEmail("kingmarlon55@yahoo.com");
		adBook.makeContact("Eustace","Williams", Gender.MALE,"Stas","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",28081953L);
		adBook.conList.get(1).addEmail("ewilliams@jrc.gov.jm");
		adBook.makeContact("Sheron","Williams", Gender.FEMALE,"Diane","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",25081963L);
		adBook.conList.get(2).addEmail("dianecam25@yahoo.com");
		adBook.makeContact("John","Doe", Gender.MALE,"Alex","518 Anywhere Ln;Anywhere Town;Anywhere City;;USA",01012000L);

		adBook.printOneByEntry(1);
		adBook.printOneByEntry(2);
		adBook.printOneByEntry(3);
		adBook.printOneByEntry(4);

		System.out.println("-----------------------------------------------------------------");

		adBook.printOneByEmail("marlon.kingson@gmail.com");

		System.out.println("-----------------------------------------------------------------");

		adBook.displayByName();

		System.out.println("-----------------------------------------------------------------");

		adBook.displayByEntryNo();

		System.out.println("-----------------------------------------------------------------");

		adBook.deleteByEntryNo(4);

		adBook.displayByName();

		System.out.println("----------------------------------------------------------------");

		adBook.editLastName("Campbell", 2);
		adBook.printOneByEntry(3);

		System.out.println("----------------------------------------------------------------");

		adBook.editAlias("LDon",0);
		adBook.printOneByEntry(1);

		System.out.println("----------------------------------------------------------------");

		adBook.editAlias("Stas",0);
		adBook.printOneByEntry(1);	

		System.out.println("----------------------------------------------------------------");









	}

}

