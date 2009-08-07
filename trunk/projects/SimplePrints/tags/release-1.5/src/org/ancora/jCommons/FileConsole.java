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

import java.io.File;

// TODO: Replace "\n" for

/**
 *
 * @author Joao Bispo
 */
public class FileConsole implements Console {

    public FileConsole(File outputFile) {
        int capacityEstimate = 256;

        this.outputFile = outputFile;
        this.builder = new StringBuilder(capacityEstimate);
    }



    public void info(String message) {
        builder.append(prefixInfo+message+NEWLINE);
    }

    public void warn(String message) {
       builder.append(prefixWarn+message+NEWLINE);

    }

    public void more(String message) {
        builder.append(tab+message+NEWLINE);
    }

    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Closes the console, writing the contents to a file.
     */
    public void close() {
        Disk disk = Disk.getDisk();
        disk.write(outputFile, builder.toString());
    }

   // INSTANCE VARIABLES
    //State
    private File outputFile;
    private StringBuilder builder;
    //Constants
   //Text that appears before a warning message.
   private final String prefixWarn = "***";
   //Text that appears before an information message.
   private final String prefixInfo = "Info:";
   // Whitespace for the beginning of paragraphs.
   private final String tab = "    ";
   // Newline
   private final String NEWLINE = System.getProperty("line.separator");



}
