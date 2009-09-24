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

package org.ancora.jCommons.DataManagement;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import org.ancora.jCommons.DataManagement.support.StringMap;


/**
 * Simple key-value database, backed-up by a Map of strings, with convenience
 * methods for insertion and retrival of various kinds of data.
 *
 * @author Joao Bispo
 */
public class DataMap implements ReadOnlyDataMap {
   /**
    * Creates an empty DataMap.
    */
   public DataMap(String name) {
      database = new StringMap(name);
      //constructorHelper(name, new HashMap<String, String>());
   }


   /**
    * Associates the specified value with the specified key in this datamap.
    * If the map previously contained a mapping for the key, a warning is issued
    * to the console and the old value is replaced by the specified value.
    *
    * @param key an enum.
    * @param value another string.
    */
   public void putString(Enum key, String value) {
      database.putString(enumString(key), value);
   }


  /**
   * Adds an array of strings to the StringMap.
   *
   * @param key
   * @param values
   */
   public void putStringArray(Enum key, String[] values) {
      database.putStringArray(enumString(key), values);
   }

   /**
    * Adds a Collection of strings to the StringMap.
    *
    * @param key
    * @param values
    */
   public void putStringCollection(Enum key, Collection<String> values) {
      database.putStringCollection(enumString(key), values);
   }

   /**
    * Loads values in properties into StringMap. Uses the same keys as the
    * properties object.
    *
    * @param properties
    */
   public void putProperties(Properties properties) {
      database.putProperties(properties);
   }

   /**
    * Merges the contents of a given DataMap with this DataMap.
    *
    * @param datamap
    */
   public void putDatamap(DataMap datamap) {
      database.putStringMap(datamap.database);
   }

   /**
    * Returns a string mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param key an enum.
    * @return the string mapped by the key. If no mapping is found, returns an
    * empty string.
    */
   public String getString(Enum key) {
      return database.getString(enumString(key));
   }


   /**
    * Returns the integer mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param eKey an enum.
    * @return a parsed int from the string mapped by the key. If no mapping is
    * found or the value could not be parsed, returns 0.
    */
   
   public int getInteger(Enum eKey) {
      return database.getInteger(enumString(eKey));
   }




   /**
    * Returns an array of strings mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param eKey an enum.
    * @return a parsed string array computed by splitting this string around
    * matches of the given regular expression, and trimming each resulting string.
    * If no mapping is found or the value could not be parsed,
    * returns an empty set.
    */
   
   public String[] getStringArray(Enum eKey) {
      return database.getStringArray(enumString(eKey));
   }


   /**
    * Returns an array of booleans mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param eKey an enum.
    * @return a parsed boolean array computed by splitting a string around
    * matches of the given regular expression, and trimming each resulting string
    * and converting it to a boolean.
    * If no mapping is found or the value could not be parsed,
    * returns an empty set.
    */
   
   public boolean[] getBooleanArray(Enum eKey) {
      return database.getBooleanArray(enumString(eKey));
   }


   /**
    * Returns an array of ints mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param eKey an enum.
    * @return a parsed int array computed by splitting this string around
    * matches of the given regular expression, trimming each resulting string and
    * parsing it for an Integer.
    * <br>If no mapping is found or the value could not be parsed,
    * returns an empty array.
    */
   
   public int[] getIntegerArray(Enum eKey) {
      return database.getIntegerArray(enumString(eKey));
   }


   /**
    * Returns a set of strings mapped to the given key
    *
    * <p><b>*Prints Info Messages*</b>
    *
    * @param eKey a string.
    * @return a parsed string set computed by splitting this string around
    * matches of the given regular expression.
    * If no mapping is found or the value could not be parsed,
    * returns an empty set.
    */
   
   public Set<String> getStringSet(Enum eKey) {
      return database.getStringSet(enumString(eKey));
   }


   public void setSeparator(String separator) {
      database.setSeparator(separator);
   }

   /**
    * Defines the string representation of an enum.
    *
    * @param key
    * @return
    */
   private String enumString(Enum key) {
      return key.name();
   }


   public String name() {
      return database.name();
   }

   @Override
   public String toString() {
      return database.toString();
   }



   /**
    * INSTANCE VARIABLES
    */
   private final StringMap database;
}
