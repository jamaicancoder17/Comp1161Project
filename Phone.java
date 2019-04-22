//package contact;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
/** Represents a Phone
  * @author Marlon Williams
  * @author 620119216
*/
public class Phone implements Serializable
{
  
  public int areaCode;
  public int number;
  public char type; 

  /** Creates a Phone with specified number and type
    * @param number A long integer representing the 10 digit number of the phone
    * @param type A character representint the type of phone
  */
  public Phone(long number,char type)
  {
    String hold = Long.toString(number);
    this.areaCode = Integer.parseInt(hold.substring(0,3));
    this.number = Integer.parseInt(hold.substring(3,10));
    if (type == 'H' || type == 'M'|| type == 'W')
    {
      this.type = type;
    }
    else
    {
      System.out.println("Incorrect Type entered, please enter H for home, M for mobile or W for work\n");
      Scanner input = new Scanner(System.in);
      this.type = input.next().charAt(0);
    }
  }

  
  /** Gets the area code of the number
    * @return An integer representing the areacode
  */
  public int getAreaCode()
  {
    return this.areaCode;
  }


  /** Gets the number
    * @return A long integer in the 10 digit format
  */
  public long getNumber()
  {
    return Long.parseLong(Integer.toString(this.areaCode) + Integer.toString(this.number));
  }
  
  /** Gets the type of the phone
    * @return A character representing the type of phone 
  */
  public char getType()
  {
    return this.type;
  }

  /** Returns the information of the phone
    * @return A string formatted as (xxx)xxx-xxxx
  */
  public String toString()
  {
    return "("+ this.getAreaCode() + ") " + Integer.toString(this.number).substring(0,3) + "-" + Integer.toString(this.number).substring(3,7);
  }


}
