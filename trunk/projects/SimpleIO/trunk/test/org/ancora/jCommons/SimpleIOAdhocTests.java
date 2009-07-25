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


/**
 * Adhoc tests for the classes in SimpleIO project.
 *
 * @author Joao
 */
public class SimpleIOAdhocTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //testSafeFolder();
        //testSafeFile();
        testRead();
    }

    private static void testSafeFolder() {
        // Program should fail with a NullPointerException
        //Io.safeFolder(null);
        //Io.safeFolder("C:/autoexec.bat");
    }

    private static void testSafeFile() {
        File folder;
        String filename;

        folder = new File("C:/");
        //folder = new File("C:/autoexec.bat");

        filename = "";
        //filename = "autoexec.bat";
        
        //Io.safeFile(folder, filename);
    }

    private static void testRead() {
        String filepath;

        filepath = "C:/autoexec.bat";
        //filepath = "C:/bootmgr";
        //filepath = "C:/updatedatfix.log";
        //filepath = "C:/java/updatedatfix-test.log";

        //File file = Io.safeFile(filepath);
        //System.out.println(Io.read(file));
    }

}
