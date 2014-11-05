/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
 /**
  * Get a default greeting  
  * @return a greeting
  */
 public String getGreeting()
 {
  return "Hello, let's talk.";
 }
 
 /**
  * Gives a response to a user statement
  * 
  * @param statement
  *            the user statement
  * @return a response based on the rules given
  */
 public String getResponse(String statement)
 {
  String response = "";
  if (statement.indexOf("no") >= 0)
  {
   response = "Why so negative?";
  }
  else if (statement.trim().length() == 0) // If the user doesn't say anything, this trims off the spaces and asks the user to say something
    {
      response = "Uhh...you gonna say something?";
    }
  else if (statement.indexOf("mother") >= 0
    || statement.indexOf("father") >= 0
    || statement.indexOf("sister") >= 0
    || statement.indexOf("brother") >= 0)
  {
   response = "Tell me more about your family.";
  }
  else if (statement.indexOf("Mr. Kiang") >= 0 // The statement for the computer to recognize that someone is talking about Mr. Kiang or Mr. Landgraf
    || statement.indexOf("Mr. Landgraf") >= 0)
  {
   response = "He's probably the best looking teacher at Punahou"; // The response statement about our teachers
  }
  else if (statement.indexOf("Dog") >= 0 // The statement for the computer to recognize that someone is talking about a dog or a cat
    || statement.indexOf("Cat") >= 0)
  {
   response = "Pets are great! Personally I'm a dog person but tell me more about your pets."; // The response statement about pets
  }
  else
  {
   response = getRandomResponse();
  }
  
  return response;
 }

 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
 private String getRandomResponse()
 {
  final int NUMBER_OF_RESPONSES = 6;
  double r = Math.random();
  int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
  String response = "";
  
  if (whichResponse == 0)
  {
   response = "Interesting, tell me more.";
  }
  else if (whichResponse == 1)
  {
   response = "Hmmm.";
  }
  else if (whichResponse == 2)
  {
   response = "Do you really think so?";
  }
  else if (whichResponse == 3)
  {
   response = "You don't say.";
  }
  else if (whichResponse == 4) // New response
  {
    response = "Really now?";
  }
  else if (whichResponse == 5) // New response
  {
    response = "I see...";
  }

  return response;
 }
}
