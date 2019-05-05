import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
import java.util.*;

public class DataManager
{
	UserLogin ul;
	AddressBookFile adbf;

	public DataManager()
	{
		ul = new UserLogin("loginInfo.txt");
		adbf = new AddressBookFile();
	}

	public int doLogin(String username, String password)
	{
		int flag=2;
		int numTries = 3;
		Scanner sc = new Scanner(System.in);

		while (numTries>=0)
		{
			
			if(username.equals("username") && (password.equals("password")))
			{
				System.out.println("A C C E S S   G R A N T E D");
				flag = 1;
				return flag;
			}
			else
			{
				flag = 2;
				System.out.println("A C C E S S   D E N I E D");
				System.out.println("Incorrect Credentials Entered");
				numTries-=1;		
				if (numTries==0)
				{
					System.out.println("INCORRECT LOGIN TOO MANY TIMES");
					flag=2;
					return flag;
				}
				else
				{
					System.out.println("\n\n\n\n\n Enter your credentials again");
					System.out.println("Enter the username:");
					username = sc.nextLine();
					System.out.println("Enter the password:");
					password = sc.nextLine();
				
				}
			}
		}
		return flag;
		
	}

	public AddressBook pullAddressBook()
	{
		return (AddressBook)this.adbf.readObjectFromFile();
	}

	public void saveAddressBook(AddressBook adBook)
	{
		this.adbf.writeObjectToFile(adBook);
	}
	
	public String[] getInfo()
	{
		String[] info = {"username","password"};
		return info;
	}

}