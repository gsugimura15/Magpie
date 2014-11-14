// Internet isn't working well, adding this comment to change the code so that I can try and reupload. 
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
import java.util.Random; // Importing "random" for use later.
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
   String lastPhrase = "";// Stores last phrase inputted
   
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
  if (findKeyword(statement,"no") >= 0) // Replaced the statement.indexOf with findKeyword(statement,) and I replaced all of the instances of statement.index of....not just this one
  {
   response = "Why so negative?";
  }
  else if (statement.trim().length() == 0) // If the user doesn't say anything, this trims off the spaces and asks the user to say something
    {
      response = "Uhh...you gonna say something?";
    }
  
  // Takes "is" moves it to the front and adds "why" to make it a question. 
  else if (findKeyword(statement, "is", 0) >= 0) // Looks for "is"
    {
      int psn = findKeyword(statement, "is", 0);// Takes "is"
      String restOfStatement = statement.substring(psn + 3).trim(); // Gets rest of statement
      String beginningOfStatement = statement.substring(0, psn);// Gets beginning of statement
      response = "Why is " + beginningOfStatement + restOfStatement + "?"; // Combines for a response
    }
  
 // Takes "you are" and changes it to "why am I" and moves it to the front to make it a question. 
  else if (findKeyword(statement, "you are", 0) >=0) // Looks for "you are"
  {
    int psn = findKeyword(statement, "you are",0); // Takes "you are"
    String restOfStatement = statement.substring(psn + 7).trim(); // Gets rest of statement
    response = "Why am I " + restOfStatement + "?"; // Combines for a response 
  }
  // Takes "I am" and changes it to "why are you" and moves it to the front to make it a question. 
  else if (findKeyword(statement, "I am",0) >=0)
  {
    int psn = findKeyword(statement, "I am",0);
    String restOfStatement = statement.substring(psn + 5).trim();
    response = "Why are you " + restOfStatement + "?";
  }     
  // Takes "are" and adds "why" and moves them to the front to make it a question. 
  else if (findKeyword(statement, "are", 0) >=0) // Looks for just "are" without the "you"
  {
    int psn = findKeyword(statement, "are",0); // Takes "are"
    String beginningOfStatement = statement.substring(0, psn); // Gets beginning of statement
    String restOfStatement = statement.substring(psn + 4).trim(); // Gets end of statement
    response = "Why are " + beginningOfStatement + restOfStatement + "?"; //Combines for a response
  }
  
  // Past tense -ed verbs
  // Single vowel plus consonnt = add the last consonant and then -ed...opposite = drop redundant consonant and -ed
  // -lled verbs drop -led
  // rest drop -ed
  // Except the infinitives that end in "e" then I believe you just drop the -d from -ed. 
  
  
  else if (findKeyword(statement,"mother") >= 0
    || findKeyword(statement,"father") >= 0
    || findKeyword(statement,"sister") >= 0
    || findKeyword(statement,"brother") >= 0)
  {
   response = "Tell me more about your family.";
  }
  else if (findKeyword(statement,"Mr. Kiang") >= 0 // The statement for the computer to recognize that someone is talking about Mr. Kiang or Mr. Landgraf
    || findKeyword(statement,"Mr. Landgraf") >= 0)
  {
   response = "He's probably the best looking teacher at Punahou"; // The response statement about our teachers
  }
  else if (findKeyword(statement,"Dog") >= 0 // The statement for the computer to recognize that someone is talking about a dog or a cat
    || findKeyword(statement,"Cat") >= 0)
  {
   response = "Pets are great! Personally I'm a dog person but tell me more about your pets."; // The response statement about pets
  }
  else if (findKeyword(statement,"Food") >= 0) // New keyword and answer about food
  {
   response = "Speaking of food, I'm really hungry!"; 
  }
  else if (findKeyword(statement,"Music") >= 0) // New keyword and answer about music
  {
   response = "I love music, who's your favorite artist right now?"; 
  }
  else if (findKeyword(statement,"Minecraft") >= 0) // New keyword and answer about minecraft
  {
   response = "I haven't played that in awhile...I bought it real cheap during Alpha and I used to play it a lot but not so much anymore"; 
  }
   else if (findKeyword(statement, "I want", 0) >= 0)// If input has "I want" in it, uses transform method to give reply
  {
   response = wantStatement(statement);
  }  
   else
   {
   int psn = findKeyword(statement, "I", 0);// If input has I something you in it, uses transform method to give reply

   if (psn >= 0// If position >=0 and the statement contains you
     && findKeyword(statement, "you", psn) >= 0)
   {
    response = meStatement(statement);
   }  
  else
  {
   response = getRandomResponse();
  }
   }
  lastPhrase = statement;
  return response;
 }

 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
 
 
 
  /**
  * Search for one word in phrase. The search is not case
  * sensitive. This method will check that the given goal
  * is not a substring of a longer string (so, for
  * example, "I know" does not contain "no").
  * 
  * @param statement
  *            the string to search
  * @param goal
  *            the string to search for
  * @param startPos
  *            the character of the string to begin the
  *            search at
  * @return the index of the first occurrence of goal in
  *         statement or -1 if it's not found
  */
 private int findKeyword(String statement, String goal,
   int startPos)
 {
  String phrase = statement.trim();
  // The only change to incorporate the startPos is in
  // the line below
  int psn = phrase.toLowerCase().indexOf(
    goal.toLowerCase(), startPos);

  // Refinement--make sure the goal isn't part of a
  // word
  while (psn >= 0)
  {
   // Find the string of length 1 before and after
   // the word
   String before = " ", after = " ";
   if (psn > 0)
   {
    before = phrase.substring(psn - 1, psn)
      .toLowerCase();
   }
   if (psn + goal.length() < phrase.length())
   {
    after = phrase.substring(
      psn + goal.length(),
      psn + goal.length() + 1)
      .toLowerCase();
   }

   // If before and after aren't letters, we've
   // found the word
   if (((before.compareTo("a") < 0) || (before
     .compareTo("z") > 0)) // before is not a
           // letter
     && ((after.compareTo("a") < 0) || (after
       .compareTo("z") > 0)))
   {
    return psn;
   }

   // The last position didn't work, so let's find
   // the next, if there is one.
   psn = phrase.indexOf(goal.toLowerCase(),
     psn + 1);

  }

  return -1;
 }
  
  private String wantStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  int psn = findKeyword (statement, "I want", 0);// Looks for "I want"
  String restOfStatement = statement.substring(psn + 7).trim();// The rest of the statement is statement - the first 7 characters
  return "Would you really be happy if you had " + restOfStatement + "?";// Returns the rest 
 }
   private String meStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  
  int psnOfI = findKeyword (statement, "I", 0);// Looks for position of I
  int psnOfMe = findKeyword (statement, "you", psnOfI + 1);// Looks for the position of you only if it is past I
  
  String restOfStatement = statement.substring(psnOfI + 1, psnOfMe).trim();// Trims everything between
  return "Why do you " + restOfStatement + " me?";// Returns statement
 }

// A time when this might not work well is if you said something like "I want you." The result doesn't really make sense. Maybe put an exception into the code if something like 
// this were to pop up. 
   
   
 /**
  * Search for one word in phrase. The search is not case
  * sensitive. This method will check that the given goal
  * is not a substring of a longer string (so, for
  * example, "I know" does not contain "no"). The search
  * begins at the beginning of the string.
  * 
  * @param statement
  *            the string to search
  * @param goal
  *            the string to search for
  * @return the index of the first occurrence of goal in
  *         statement or -1 if it's not found
  */
 private int findKeyword(String statement, String goal)
 {
  return findKeyword(statement, goal, 0);
 }
 
 
 
  /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
 private String getRandomResponse () // Replaced old method
 {
  Random r = new Random (); // Uses "random" 
  return randomResponses [r.nextInt(randomResponses.length)];
 }
 
 private String [] randomResponses = {"Interesting, tell me more",
   "Hmmm.",
   "Do you really think so?",
   "You don't say.",
   "Dude, are you serious?",
   "Come again?",
   "I see...",
   "Really?",
   "SAY WHAT NOW?!",
   "Can you repeat that?",
   "I don't think I understand...",  // Responses
 };
 
}

// The responses are prioritized in order of the coding. So for instance, no is first, family is second, teachers are third, and so on. So when typing out a sentence, no matter what the order of the words
// ... in the sentences, what will come out first is what has been coding first. 

// What happens what a keyword is in another word is that the code just searches for certain letters in a certain order. So for example "no" is in "kNOwledge" so the code goes with the "no" statement
// And then it's exactly the same as above. The code prioritizes whatever has been coded first. 


 
