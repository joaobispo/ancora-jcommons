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
 * Static methods for standard output of information and warning messages. 
 * Used instead of calls to System.out and System.err.
 *
 * @author Joao Bispo
 */
public class Print {

   /**
    * Prints the contents of "message" to the standard output,
    * prefixed by the string "Info:".
    *
    * @param message Message to be printed
    */
   public static void info(String message) {
      System.out.println(prefixInfo+message);
   }

   /**
    * Prints the contents of "message" to the standard output,
    * prefixed by the string "***".
    *
    * @param message Message to be printed
    */
   public static void warn(String message) {
      System.out.println(prefixWarn+message);
   }


   /**
    * Prints the contents of "message" to the standard output, prefixed by an
    * amount of whitespaces. Its purpose is to show additional information
    * after using "info" or "warn".
    */
   public static void more(String message) {
       System.out.println(tab+message);
   }

   // DEFINITIONS

   //Text that appears before a warning message.
   private static final String prefixWarn = "***";

   //Text that appears before an information message.
   private static final String prefixInfo = "Info:";

   // Whitespace for the beginning of paragraphs.
   private static final String tab = "    ";
}
