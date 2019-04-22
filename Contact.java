import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

public class Contact extends Person implements Comparable<Contact>,Serializable
{
	/**Contact class. Inherits Person class.
		Alias of the contact alias of type String
		Address of the contact address of type Address
		Entry number num of type int
		Generated entry number entryNo of type static int
		Arraylist of emails emailLst of type String
		Arraylist of phones phoneLst of type Phone
	**/
	private String alias;
	private Address address;
	private int num;
	private static int entryNo=0;
	private ArrayList<String> emailLst = new ArrayList<String>();
	private ArrayList<Phone> phoneLst = new ArrayList<Phone>();
	
	/** Constructor for Contact. Generates entry number. Returns nothing.
	@param fName first name of contact, type String
	@param lName last name of contact, type String
	@param gender Gender of contact, type Gender
	@param dob date of birth, type long
	**/
	public Contact(String fName, String lName, Gender gender, long dob)
	{
		super(fName, lName, gender, dob);
		entryNo++;
		num= entryNo;
	}
	/**Constructor for Contact. Generates entry number. Returns nothing.
	 * 
	 * @param fName first name of contact, type String
	 * @param lName last name of contact, type String
	 * @param gender Gender of contact, type Gender
	 * @param alias Alias of contact, type String
	 * @param address Address of contact, type String[]
	 * @param dob Date of birth of contact, type long
	 */
	public Contact(String fName, String lName, Gender gender, String alias, String address, long dob)
	{
		super(fName, lName, gender, dob);
		entryNo++;
		num= entryNo;
		this.setAlias(alias);
		this.setAddress(address);
	}
	/**Constructor for dummy contact, for the purpose of binary searching for the entry number of a contact.
	 * Does not generate entry number. Returns nothing.
	 * @param entry entry number to set for contact.
	 */
	public Contact(int entry)
	{
		super("","",Gender.MALE,0);
		num = entry;
	}
	/** Getter for entry number. Accepts no parameters.
	@return num the entry number, type int
	**/
	public int getEntryNo()
	{
		return num;
	}
	/** Getter for age. Accepts no parameters.
	@return diff the integer difference in current date and the birth date
	**/
	public int getAge()
	{
		int yr = Calendar.getInstance().get(Calendar.YEAR);
		int mon = Calendar.getInstance().get(Calendar.MONTH);
		int da = Calendar.getInstance().get(Calendar.DATE);
		int birthYr = (int)(super.getDOB()/10000);
		int birthMon = (int)((super.getDOB()%10000)/100);
		int birthDay = (int)(super.getDOB()%100);
		int diff = yr -birthYr;
		if ((mon < birthMon) || (mon==birthMon && da <birthDay))
		{
			diff-=1;
		}
		return diff;
	}
	/** Getter for Name. Uses parent class to access first and last names
	@return name in the format "Last,First"
	**/
	public String getName()
	{
		return (super.sendName().getLastName() +","+ super.sendName().getFirstName());
	}
	/** Mutator for last name. 
	@param last name of contact, type String
	**/
	public void updateName(String last)
	{
		super.sendName().changeLastName(last);
	}
	/** Getter for alias.
	@return alias of contact, type String
	**/
	public String getAlias()
	{
		return alias;
	}
	/** Setter for alias.
	@param alias of contact, type String
	**/
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
	/** Setter for new alias, given the list of aliases.
	@param alias of contact, type String
	@param arr list of aliases, type List<String>
	**/
	public void newAlias(String alias, List<String> arr)
	{
		if (!arr.contains(alias))
		{
			this.setAlias(alias);
		}
		else
		{
			System.out.println("This alias already exists.");
		}
	}
	/** Getter for address.
	@return String array of sddresses
	**/
	public String[] getAddress()
	{
		return address.getAddress();
	}
	/** Setter for address.
	@param strAd String containing address lines separated by semicolons
	**/
	public void setAddress(String strAd)
	{
		address = new Address(strAd);
	}
	/** Mutator for email arraylist. Adds email to arraylist.
	@param email of type String
	**/
	public void addEmail(String email)
	{
		emailLst.add(email);
	}
	/** Mutator for email arraylist. Deletes email from arraylist.
	@param email of type String
	**/	
	public void deleteEmail(String email)
	{
		emailLst.remove(email);
	}
	/**Getter for email arraylist. Converts arraylist to array of strings.
	@return string list of emails
	**/
	public String[] getEmailList()
	{
		String[] strLst = Arrays.copyOf(emailLst.toArray(), emailLst.toArray().length, String[].class);
		return strLst;
	}
	/** Mutator for phone arraylist. Adds phone to arraylist.
	@param type of type char
	@param pNum number of type long
	**/
	public void addPhone(char type, long pNum)
	{
		if (phoneLst.size()<5)
		{
			Phone hold = new Phone(pNum,type);
			if(!this.phoneLst.contains(hold))
			{
				phoneLst.add(hold);	
			}
			else
			{
				System.out.println("Phone number is already in the list");		
			}
			
		}
		else
		{
			System.out.println("Phone list is already full.");
		}
	}
	/** Mutator for phone arraylist. Removes phone from arraylist.
	@param phone number of type long
	**/
	public void deletePhone(long phone)
	{
		int i = 0;
		for(i=0;i<phoneLst.size(); i++)
		{
			if (phoneLst.get(i).getNumber() == phone)
			{
				phoneLst.remove(i);
				break;
			}
		}	
	}
	/**Getter for phone arraylist. Converts arraylist to array of strings.
	@return string list of phones
	**/
	public String[] getPhoneList()
	{
		//Phone[] pLst = phoneLst.toArray(new Phone[0]);//, phoneLst.toArray().length, String[].class);
		String[] strLst = new String[phoneLst.size()];
		int count =0;
		for(Phone p: phoneLst)
		{
			strLst[count] = p.toString();
			count++;
		}	
		return strLst;
	}
	/**Prints the information for the contact. 
	 * If the signal is 1, it prints the name, gender, alias, address and date of birth of the contact
	 * If the signal is 0, it prints the entry number, name, gender and email addres(es) of the contact
	 * @param num the signal to let the function know what to print
	 **/
	public void displayDetails(int num)
	{
		String name = getName();//Using all predefined getters
		Gender gender = super.getGender();
		if (num==1)//full details to be displayed
		{
			String alias = getAlias();
			String address = this.address.toString();
			long dob = super.getDOB();
			
			System.out.println("Contact name: "+ name + "\nGender: "+ gender + "\nAlias: "
					+ alias + "\nAddress: " + address + "\nDate of Birth:" +dob);
		}
		if (num==0)//only some details to be displayed
		{
			int eNum = getEntryNo();
			String[] emailLst = getEmailList();
			String lst = "";
			for (String email: emailLst)
			{
				lst = lst+ email;
				lst = lst + "\n";
			}
			System.out.println("Entry Number:" + eNum+ "\nContact name: "+ name + "\nGender: "
			+ gender + "\nEmail Address(es): " + lst);
		}
	}
	
	public String returnDetails(int num)
	{
		String name = getName();//Using all predefined getters
		Gender gender = super.getGender();
		String detail="";
		if (num==1)//full details to be displayed
		{
			String alias = getAlias();
			String address = this.address.toString();
			long dob = super.getDOB();
			
			detail= ("Contact name: "+ name + "\nGender: "+ gender + "\nAlias: "
					+ alias + "\nAddress: " + address + "\nDate of Birth:" +dob);
		}
		if (num==0)//only some details to be displayed
		{
			int eNum = getEntryNo();
			String[] emailLst = getEmailList();
			String lst = "";
			for (String email: emailLst)
			{
				lst = lst+ email;
				lst = lst + "\n";
			}
			detail = ("Entry Number:" + eNum+ "\nContact name: "+ name + "\nGender: "
			+ gender + "\nEmail Address(es): " + lst);
		}
		return detail;
	}
	
	/**Natural compareTo function for the Contact class
	 * Uses comparable to sort contacts by entry number
	 * @param con2
	 * @return the difference between the numbers.
	 */
	public int compareTo(Contact con2)
	{
		return this.getEntryNo() - con2.getEntryNo();
	}
	
	/**Comparator class to sort contacts by name
	 * 
	 */
	public static Comparator<Contact> NameComparator= new Comparator<Contact>() 
    {
        @Override
        public int compare(Contact con1, Contact con2)
        {
            return con1.getName().compareTo(con2.getName());
        }
    };
    
    /**Comparator class to sort contacts by entry number
	 * 
	 */
    public static Comparator<Contact> EntryComparator= new Comparator<Contact>() 
    {
        @Override
        public int compare(Contact con1, Contact con2)
        {
        	return con1.getEntryNo() - con2.getEntryNo();
        }
    };

    public void printEmails()
	{
		String[] emails = getEmailList();
		System.out.println("Email Address(es): ");
		for (String email: emails)
		{
			System.out.println(email);
		}
	}
	
	public void printPhones()
	{
		String[] phones = getPhoneList();
		System.out.println("Phone Number(s): ");
		for (String phone: phones)
		{
			System.out.println(phone);
		}
	}

}