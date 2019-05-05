//package contact;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
/** Represents an Address
  *@author Marlon Williams
  *@author 620119216
*/
public class Address implements Serializable
{

  String country;
  String[] address;

/** Creates an Address 
  *@param address The address as a comma separated address as input
*/
  public Address(String address)
  {
    String[] hold = address.split(";");
    ArrayList<String> h = new ArrayList<String>();
    for(int i=0;i<hold.length;i++)
    {
      if(hold[i].length()!=0)
      {
        h.add(hold[i]);
      }
      
    }
    this.address = h.toArray(new String[0]);
    this.country = this.address[this.address.length-1];
  }

  /** Returns the country of the address
    * @return A string representing the country of the address
  */
  public String getCountry()
  {
    return this.country;
  }
  
  /** Returns the address 
    *@return A string array representing the address
  */
  public String[] getAddress()
  {
    return this.address;
  }

  /**
  */
  public String toString()
  {
    String add = "";
    for(String line:this.address)
    {
      if(line.length()!= 0)
      {
        add += line+ "\n";//Presenting the address in new lines
      }
      
    }
  
    return add;
  }
}
