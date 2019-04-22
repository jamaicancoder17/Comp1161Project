//package contact;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
/** Represents a Name
  * @author Marlon Williams
  * @author 620119216

*/
public class Name implements Serializable
{
  //Attributes
  public String fName;
  public String lName;

  /** Creates a name with specified first name and last name
    * @param fName A string representing the first name of the Name
    * @param lName A string representing the last name of the Name
  */
  public Name(String fName, String lName)
  {
    this.fName = fName;
    this.lName = lName;
  }
  

  /** Gets the first name of the Name
    * @return A string representing the first name of the Name
  */
  public String getFirstName()
  {
    return this.fName;
  }

  /** Gets the last name of the Name
    * @return A string representing the last name of the Name
  */
  public String getLastName()
  {
    return this.lName;
  }

  /** Changes the last name of the Name
    * @param name A string representing the new surname that will be assigned
  */
  public void changeLastName(String name)
  {
    this.lName = name;
  }
  
}
