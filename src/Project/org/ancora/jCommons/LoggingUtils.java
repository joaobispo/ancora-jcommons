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

package org.ancora.jCommons;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import org.ancora.jCommons.Logging.ConsoleFormatter;


/**
 *
 * @author Joao Bispo
 */
public class LoggingUtils {

   /**
    * Removes current handlers from Root Logger and adds the given Handlers.
    * 
    * @param handlers
    */
   public static void setRootHandlers(Handler[] handlers) {
      Logger logger = Logger.getLogger("");

      // Remove all previous handlers
      Handler[] handlersTemp = logger.getHandlers();
      for(Handler handler : handlersTemp) {
         logger.removeHandler(handler);
      }

      // Add Handlers
      for(Handler handler : handlers) {
         logger.addHandler(handler);
      }
   }

   /**
    * @return a Console Hanlder formatted by ConsoleFormatter.
    */
   public static Handler buildConsoleHandler() {
         ConsoleHandler cHandler = new ConsoleHandler();
         cHandler.setFormatter(new ConsoleFormatter());

         return cHandler;
   }

   /**
    * Setups the root logger so that it only outputs to the console.
    */
   public static void setupConsoleOnly() {
      // Build ConsoleHandler
      Handler[] handlers = new Handler[1];
      handlers[0] = buildConsoleHandler();
      setRootHandlers(handlers);
   }

}
