//package contact;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
/** Represents a Person
  * @author Marlon Williams
  * @author 620119216
*/
public class Person implements Serializable
{
  /** Represents the gender of the person
  */
  public Name name;
  public Gender gen;
  public long dateOfBirth; 

  /** Creates a person with specified name,gender and date of birth
    * @param fName A string representing the first name of the person
    * @param lName A string representing the last name of the person
    * @param gen An attribute of the gender enumeration
    * @param dateOfBirth A long integer representing the date of birth
  */
  public Person(String fName, String lName, Gender gen, long dateOfBirth)
  {
    this.name = new Name(fName,lName);
    this.gen = gen;
    this.dateOfBirth = dateOfBirth;
  }

  /** Gets the name of the person
    * @return A string representing the name of the person
  */
  public String getName()
  {
    return this.name.getFirstName() + " " + this.name.getLastName();
  }

  /** Gets the gender of the person
    * @return An attribute of the enumeration Gender that represents the gender of a person
  */
  public Gender getGender()
  {
    return gen;
  }

  /** Gets the date of birth of the person
    * @return A long integer representing the date of birth of the person
  */
  public long getDOB()
  {
    return this.dateOfBirth;
  }

  /** Getter for the name as an object of type Name.  Accepts no parameters.
	@return name Name of the person
	**/
	public Name sendName()
	{
		return name;
	}

  public void changeLastName(String name)
  {
    this.name.changeLastName(name);
  }

}
