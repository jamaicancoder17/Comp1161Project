import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
public class UserLogin
{
	//Attributes
	String adminUser;
	String adminPass;
	int numTries = 3;

	public UserLogin(String fileName)
	{
		//this.createLoginData();
		
	}

	public ArrayList<String> readCredentials(String fileName)
	{
		ArrayList<String> credentials = new ArrayList<String>();
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String oneLine = "";
			while ((oneLine = br.readLine()) != null)
			{
				//Username is first, then password
				String[] strList = oneLine.split(";");
				credentials.add(strList[0]);
				credentials.add(strList[1]);
			}
			
		}
		catch(FileNotFoundException f)
		{
			System.out.println("Could not find file.");
		}
		catch(IOException e)
		{
			System.out.println("Could not read from file.");
		}
		return credentials;
	}

	public int assessLogin(String username, String password)//1.1 and 1.2
	{
		System.out.println(username);
		ArrayList<String> credentials = new ArrayList<String>();
		int flag = 3;
		int numTries=3;
		Scanner sc = new Scanner(System.in);
		credentials = this.readCredentials("loginInfo.txt");
		System.out.println(username);
		System.out.println(password);
		System.out.println(credentials.get(0));
		System.out.println(credentials.get(1));
		while ((!username.equals(credentials.get(0))) || (!password.equals(credentials.get(1))))
		{
			System.out.println("Enter the username:");
			username = sc.nextLine();
			System.out.println("Enter the password:");
			password = sc.nextLine();
			
			if ((username.equals(adminUser)) && (password.equals(adminPass)))
			{
				
				flag=1;
			}
			else
			{
				System.out.println("A C C E S S   D E N I E D:");
				
				numTries-=1;
					
				if (numTries==0)
				{
					System.out.println("INCORRECT LOGIN TOO MANY TIMES");
					//Exit here
					flag=2;
				}
			}
		}
		return flag;
	
	}

	private void createLoginData()
	{
		String fileName = "loginInfo.txt";
		try 
		{
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("username;password");

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) 
        {
            System.out.println("Error writing to file '"+ fileName + "'");
        }

	}

	public static void main(String[] args)
	{
		UserLogin ul = new UserLogin("loginInfo.txt");
		//ul.assessLogin();
	}
}