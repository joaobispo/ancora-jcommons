/*
 *  Copyright 2009 Abstract Maze.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package org.ancora.jCommons.DataManagement.support;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

/**
 * Simple database, backed-up by a Map of strings, with convenience methods for
 * insertion and retrival of data.
 *
 * @author Joao Bispo
 */
public class StringMap {

   /**
    * Creates an empty StringMap.
    */
   public StringMap(String name) {
      database = new StringTable(name);
      separator = DEFAULT_SEPARATOR;
      this.console = Logger.getLogger(StringMap.class.getName());
   }

  /**
   * Adds an array of strings to the StringMap.
   *
   * <p> Transform the array into the internal format of string array:
   * <br> string <separator> string ...
   * <br> A default <separator> is internally defined by the StringMap, but can be
   * changed.
   *
   *
   * @param key
   * @param values
   */
   
   public void putStringArray(String key, String[] values) {
      // Transform the array into the internal format of string array:
      // string <separator> string ...

      int capacity = 200;
      StringBuilder builder = new StringBuilder(capacity);

      boolean firstValue = true;
      boolean foundSeparator = false;
      for(String value : values) {
         // Check for each string if it contains the currently used separator.
         if(value.contains(separator)) {
            console.warning("Value \""+value+"\"of StringArray" +
                    " contains the sequence \""+separator+"\", which is being used " +
                    "internally as a separator.");
            console.info("\t- Please, set other separator for the DataMap \""+
                    database.getDatabaseName()+"\"");
            foundSeparator = true;
            break;
         }

         if(firstValue) {
            builder.append(value);
         }
         else {
            builder.append(separator);
            builder.append(value);
         }
      }

      if(!foundSeparator) {
         database.putString(key, builder.toString());
      }
   }

   /**
    * Adds a Collection of strings to the StringMap.
    *
    * @param key
    * @param values
    */
   public void putStringCollection(String key, Collection<String> values) {
      putStringArray(key, values.toArray(new String[values.size()]));
   }

   /**
    * Loads values in properties into StringMap. Uses the same keys as the
    * properties object.
    *
    * @param properties
    */
   public void putProperties(Properties properties) {
      for(String key : properties.stringPropertyNames()) {
         database.putString(key, properties.getProperty(key));
      }
   }

   /**
    * Merges the contents of a given StringMap with this StringMap.
    *
    * @param stringmap
    */
   public void putStringMap(StringMap stringmap) {
      StringTable table = stringmap.database;
      for(String key : table.keySet()) {
         database.putString(key, table.getString(key));
      }
   }

   /**
    * Adds a string to the StringMap.
    *
    * @param key
    * @param value
    */
   public void putString(String key, String value) {
      database.putString(key, value);
   }

   /**
    * Returns a String mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key a string.
    * @return the string mapped by the key. If no mapping is found, returns an
    * empty string.
    */   
   public String getString(String key) {
      return database.getString(key);
   }

   /**
    * Returns the integer mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key a string.
    * @return a parsed int from the string mapped by the key. If no mapping is
    * found or the value could not be parsed, returns 0.
    */  
   public int getInteger(String key) {
         String result = database.getString(key);
         if(result == null) {
            console.info(notFoundMessage(key));
            console.info("\tReturning 0.");
            return 0;
         }
         else {
            return parseInt(result, key);
         }
   }


   /**
    * Returns an array of strings mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key a string.
    * @return a parsed string array computed by splitting this string around
    * matches of the given regular expression, and trimming each resulting string.
    * If no mapping is found or the value could not be parsed,
    * returns an empty set.
    */
   public String[] getStringArray(String key) {
      String regex = separator;
      String result = database.getString(key);
      if (result == null) {
         console.info(notFoundMessage(key));
         console.info("\tReturning empty ArrayList<String>.");
         return new String[0];
      } else {
         return parseStringArray(result, regex, key);

      }
   }


   /**
    * Returns an array of booleans mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key a String.
    * @return a parsed boolean array computed by splitting a string around
    * matches of the given regular expression, and trimming each resulting string
    * and converting it to a boolean.
    * If no mapping is found or the value could not be parsed,
    * returns an empty set.
    */
   
   public boolean[] getBooleanArray(String key) {
      // Split the string in smaller strings
      String[] stringBools = getStringArray(key);

      // Parse strings
      boolean[] bools = new boolean[stringBools.length];
      for (int i = 0; i < stringBools.length; i++) {
         bools[i] = Boolean.parseBoolean(stringBools[i]);
      }

      return bools;
   }


   /**
    * Returns an array of integers mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key a string.
    * @return a parsed int array computed by splitting this string around
    * matches of the given regular expression, trimming each resulting string and
    * parsing it for an Integer.
    * <br>If no mapping is found or the value could not be parsed,
    * returns an empty array.
    */
   
   public int[] getIntegerArray(String key) {
      // Split the string in smaller strings
      String[] stringInts = getStringArray(key);

      // Parse strings
      int[] ints = new int[stringInts.length];
      for (int i = 0; i < stringInts.length; i++) {
         ints[i] = parseInt(stringInts[i], key);
      }

      return ints;
   }


   /**
    * Returns a set of strings mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key a string.
    * @return a parsed string set computed by splitting this string around
    * matches of the given regular expression.
    * If no mapping is found or the value could not be parsed,
    * returns an empty set.
    */
   
   public Set<String> getStringSet(String key) {
      Set<String> setValue = new HashSet<String>();
      String[] strings = getStringArray(key);
      // Add strings
      for (String string : strings) {
         setValue.add(string);
      }

      return setValue;
   }


   /**
    * Multiple Values are stored inside a single String, separated by the
    * contents of "separator".
    *
    * <p>The value of the default separator is defined in DEFAULT_SEPARATOR.
    *
    * @param separator a string.
    */
   public void setSeparator(String separator) {
      this.separator = separator;
   }

   /**
    * Multiple lists of strings can be store inside a single String, separated
    * by the contents of "terminator".
    *
    * <p>The value of the default terminator is defined in DEFAULT_TERMINATOR.
    *
    * @param terminator
    */
   public void setTerminator(String terminator) {
      this.terminator = terminator;
   }

   private String notFoundMessage(String key) {
      return "Key \""+key+"\" not found in database \""+database.getDatabaseName()+"\".";
   }

   /**
    * Given a String, tries to parse it into a integer. If an exception occours,
    * 0 is returned.
    *
    * @param result
    * @param key
    * @return
    */
   
   private int parseInt(String result, String key) {
      int intResult = 0;
      try {
         intResult = Integer.parseInt(result);
      } catch (NumberFormatException e) {
         console.info("Key \"" + key + "\" in database \"" + database.getDatabaseName() + "\" is not an Integer.");
         console.info("\tValue - \"" + result + "\".");
      //console.printInfo("Value of Property \""+key+"\" is not an Integer (\""+result+"\")");
      } finally {
         return intResult;
      }
   }

   /**
    * Splits a text string into smaller pieces.
    *
    * @param text
    * @param separator
    * @param key
    * @return
    */
   
   private String[] parseStringArray(String text, String separator, String key) {
      String[] strings = new String[0];
      try {
         // Split the string in smaller strings
         strings = text.split(separator);
         // Trim strings
         for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
         }
      } catch (PatternSyntaxException e) {
         console.info("While trying to parse the value of Property \"" + key + "\" in database \"" + database.getDatabaseName() + "\" an error happened.");
         console.info("\tRegular Expression \"" + separator + "\" has syntax errors. Error message:");
         System.out.println(e.getMessage());
      } finally {
         return strings;
      }
   }

   public String name() {
      return database.getDatabaseName();
   }

   @Override
   public String toString() {
      return database.toString();
   }



   /**
    * INSTANCE VARIABLES
    */
   private StringTable database;
   private String separator;
   private String terminator;

   public final String DEFAULT_SEPARATOR = ",";
   public final String DEFAULT_TERMINATOR = ";";

   // For output of messages
   private final Logger console;
}
