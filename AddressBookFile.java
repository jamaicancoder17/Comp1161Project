import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import  java.io.Serializable;

public class AddressBookFile implements Serializable
{
	AddressBook adBook = new AddressBook();

	public AddressBookFile()
	{
	
	}
	
	private void createAddressBookData()
	{
		AddressBook adBook = new AddressBook();
		adBook.makeContact("Marlon","Williams", Gender.MALE,"Alex","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",14121999L);
		adBook.getConList().get(0).addEmail("marlon.kingson@gmail.com");
		adBook.getConList().get(0).addEmail("kingmarlon55@yahoo.com");
		adBook.makeContact("Eustace","Williams", Gender.MALE,"Stas","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",28081953L);
		adBook.getConList().get(1).addEmail("ewilliams@jrc.gov.jm");

		this.writeObjectToFile(adBook);
	}

	public void writeObjectToFile(AddressBook adBook)
	{
		try 
		{
            FileOutputStream fileOut = new FileOutputStream("AddressBookFile.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(adBook);
            objectOut.close();
            System.out.println("The Address Book was successfully written to a file");
 
        } 
        catch (IOException ex) 
        {
            //ex.printStackTrace();
        	System.out.println("Error3");
        }
	}

	public AddressBook readObjectFromFile()
	{
		//Object obj = new Object();
		//adBook = new AddressBook();
		try 
		 {
            FileInputStream fileIn = new FileInputStream("AddressBookFile.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            adBook = (AddressBook) objectIn.readObject();
            objectIn.close();
            return adBook;
 
        }
		 catch(FileNotFoundException f)
		 {
			//f.printStackTrace();
			 //System.out.println("Error");
			 return adBook;
		 }
		 catch (IOException ex) 
        {
            //ex.printStackTrace();
			 //System.out.println("Error1");
			 return adBook;
        }
         catch (ClassNotFoundException ex)
        {
        	//ex.printStackTrace();
        	 //System.out.println("Error2");
        	 return adBook;
        }

	}


	/**public static void main(String[] args)
	{
		AddressBookFile adbf = new AddressBookFile();
		AddressBook adBook = new AddressBook();
		adBook.makeContact("Marlon","Williams", Gender.MALE,"Alex","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",14121999L);
		Contact hold = adBook.getConList().get(0);
		adBook.removeContact(hold);
		Contact hold2;
		hold.addEmail("marlon.kingson@gmail.com");
		hold.addEmail("kingmarlon55@yahoo.com");
		adBook.makeContact("Eustace","Williams", Gender.MALE,"Stas","268 Queens Avenue;Magil Palms;Spanish Town;;Jamaica",28081953L);
		hold2 = adBook.getConList().get(0);
		adBook.removeContact(hold2);
		hold2.addEmail("ewilliams@jrc.gov.jm");




		adBook.addContact(hold);
		adBook.addContact(hold2);
		//adBook.displayByName();
		adbf.writeObjectToFile(adBook);

		AddressBook temp = new AddressBook();
		temp = (AddressBook) adbf.readObjectFromFile();
		temp.displayByName();
	}**/


}