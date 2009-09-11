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

import org.ancora.jCommons.Disk;
import java.io.File;

/**
 *
 * @author Joao Bispo
 */
public class DiskAdhocTests {

   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //testSafeFile();
        //testSafeFile2Args();
        //testRead();
        testWrite();

    }

   private static void testSafeFile() {
      Disk disk = Disk.getDisk();

      String filepath = "C:/newFile.txt";

      File file = disk.safeFile(filepath);
      System.out.println("Path: "+file.getAbsolutePath());
      
   }

   private static void testSafeFile2Args() {
      Disk disk = Disk.getDisk();

      String filepath = "C:/newFile.txt";
      String folderpath = "C:/newFolder/newFolder2";

      File folder = new File(folderpath);

      File file = disk.safeFile(folder, filepath);
      System.out.println("Path: "+file.getAbsolutePath());
   }

    private static void testRead() {
        Disk disk = Disk.getDisk();
        String filepath;
        File file;

        //filepath = "C:/newFile.txt";
        //filepath = "C:/fileC:/file";
        //filepath = "C:/autoexec.bat";
        filepath = "C:/swlist.reg";

        //file = disk.safeFile(filepath);
        file = new File(filepath);

        //file.delete();

        String contents = disk.read(file);

        System.out.println("Contents:");
        System.out.println("\""+contents+"\"");
    }

    private static void testWrite() {
        Disk disk = Disk.getDisk();
        String filepath;
        File file;
        String contents;

        //filepath = "C:/newFile.txt";
        //filepath = "C:/fileC:/file";
        filepath = "C:/test.txt";

        file = disk.safeFile(filepath);
        //file = new File(filepath);

        //file.delete();
        /*
        int numChars = 100000;
        StringBuilder builder = new StringBuilder(numChars);
        for(int i=0; i<numChars; i++) {
            builder.append("a");
        }
*/

        contents = "1234";
        //contents = builder.toString();

        disk.write(file, contents);
        
        //System.out.println("Contents:");
        //System.out.println("\""+contents+"\"");
    }
}
