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
 * Methods for standard output of information and warning messages.
 * Used instead of calls to System.out.
 *
 * @author Joao Bispo
 */
public interface Console {

   /**
    * Prints the contents of "message" to the standard output,
    * as an information message.
    *
    * @param message Message to be printed
    */
   public void info(String message);

   /**
    * Prints the contents of "message" to the standard output,
    * as a warning message.
    *
    * @param message Message to be printed
    */
   public void warn(String message);


   /**
    * Prints the contents of "message" to the standard output
    * as aditional information, after using "warn" or "info".
    */
   public void more(String message);

}
