/*
 *  Copyright 2009 Ancora Research Group.
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

package org.ancora.jCommons;

/**
 * Default implementation of Console.
 * 
 * <p> This class is a stateless singleton, get an instance of this class
 * by invoking the method "getConsole".
 *
 * @author Joao Bispo
 */
public class DefaultConsole implements Console {

   private DefaultConsole() {
   }


   /**
    * Prints the contents of "message" to the standard output,
    * prefixed by the string "Info:".
    *
    * @param message Message to be printed
    */
   public void info(String message) {
      System.out.println(prefixInfo+message);
   }

   /**
    * Prints the contents of "message" to the standard output,
    * prefixed by the string "***".
    *
    * @param message Message to be printed
    */
   public void warn(String message) {
      System.out.println(prefixWarn+message);
   }


   /**
    * Prints the contents of "message" to the standard output, prefixed by an
    * amount of whitespaces. Its purpose is to show additional information
    * after using "info" or "warn".
    */
   public void more(String message) {
       System.out.println(tab+message);
   }



    public void print(String message) {
        System.out.println(message);
    }


    public void close() {
        return;
    }

   /**
    * Returns an instance of DefaultConsole.
    *
    * @return an instance of DefaultConsole
    */
   public static DefaultConsole getConsole() {
      return defaultConsole;
   }

   // DEFINITIONS
   //Text that appears before a warning message.
   private static final String prefixWarn = "***";
   //Text that appears before an information message.
   private static final String prefixInfo = "Info:";
   // Whitespace for the beginning of paragraphs.
   private static final String tab = "    ";
   // Unique Instance of this class.
   final static private DefaultConsole defaultConsole = new DefaultConsole();



}
