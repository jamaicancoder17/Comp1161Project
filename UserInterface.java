import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserInterface
{
	TextUI tU = new TextUI();
	DataManager dM = new DataManager();
	Login log = new Login();

	public void chooseInterface()
	{
		int option = 3;
		while((option != 1) || (option !=2))
		{
			this.tU.clearScreen();
			System.out.println("Press 1 to enter the TextUI, and 2 to enter the GUI");
			Scanner scan = new Scanner(System.in);
			option = scan.nextInt();
			tU.clearScreen();
			switch(option)
			{
				case 1:
					ArrayList<String> credentials = this.tU.acceptLogin();
					int result = this.dM.doLogin(credentials.get(0),credentials.get(1));

					if(result == 1)
					{
						System.out.println();
						tU.pauseScreen();
						this.tU.main();
						System.exit(0);
					}
					else
					{
						System.out.println();
						tU.pauseScreen();
						System.exit(0);
					}
					break;
				case 2:
					log.setVisible(true);
					break;
				default:
					System.out.println("Incorrect Option Selected, enter again");
					break;
			}
	
		}
		
	}

	public static void main(String[] args)
	{
		UserInterface uI = new UserInterface();
		uI.chooseInterface(); 
	}

}