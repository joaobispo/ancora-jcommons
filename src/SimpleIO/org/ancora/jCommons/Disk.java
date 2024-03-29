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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Methods for quick and simple manipulation of files, folders and other
 * input/output related operations.
 * 
 * @author Joao Bispo
 */
public class Disk {

   /**
    * Create a Disk object.
    *
    * @param console A jCommons.console.Console, for message ouput formatting.
    */
   public Disk(Console console) {
      this.console = console;
   }


   /**
    * Creates a Disk with default formatting of messages.
    *
    * @return a default disk.
    */
    private static Disk defaultDisk() {
        Console defaultConsole = DefaultConsole.getConsole();
        return new Disk(defaultConsole);
    }

    /**
     * Returns a Disk with default formatting of messages.
     *
     * @return a Disk with default formatting of messages.
     */
    public static Disk getDisk() {
        return defaultDisk;
    }



    /**
     * Given a string representing a filepath to a folder, returns a File
     * object representing the folder. It follows the contiditions:
     * <p>If the folder exists, a File with its path is returned;
     * <br>If the folder doesn't exist, it will be created, along with all
     * the necessary folders;
     * <br>If the folder couldn't be created, null is returned;
     * <br>If the path represented by the folderpath exists, but doesn't
     * represent a folder, null is returned;
     *
     * <p>If a File object is returned, it is guaranteed that the folder exists.
     *
     * @param folderpath a string representing a filepath to a folder.
     * @return if folder exists, a File object representing the folder.
     * Null otherwise.
     */
    public File safeFolder(String folderpath) {
        // EXPLAIN: The check for null String was commented out because if the
        // null is not caught here, the application will fail when we try to
        // initiallize a File object with a null argument. This way, we can
        // quickly found where the null argument was used.
        /*
        if(folderpath == null) {
            console.warn("safeFolder: input String is null.");
            return null;
        }
         */

        // Variables
        final File folder = new File(folderpath);

        // Check if File is a folder
        final boolean isFolder = folder.isDirectory();
        if(isFolder) {
            return folder;
        }

        // Check if File exists. If true, is not a folder.
        final boolean folderExists = folder.exists();
        if(folderExists) {
            console.warn("safeFolder: path \""+folderpath+"\" exists, but " +
                    "doesn't represent a folder.");
            return null;
        }

        // Try to create folder.
        final boolean folderCreated = folder.mkdirs();
        if(folderCreated) {
            console.info("Folder created ("+folder.getAbsolutePath()+").");
            return folder;
        }

        // Couldn't create folder
        console.warn("safeFolder: path \""+folderpath+"\" doesn't exist and " +
                "couldn't be created.");
        return null;
    }


    /**
     * Given a File representing a folder, and a String representing the name
     * of another folder inside the previous folder, returns a File object
     * representing the complete folder. It follows the contiditions:
     * <p>If the folder inside the first folder exists, a File with its path is
     * returned;
     * <br>If the folder doesn't exist, an attempt is made to create the folder
     * and all the needed folders. If unsucessful, null is returned.
     * <br>If the complete folderpath exists, but doesn't represent a folder,
     * null is returned;
     *
     * @param folder File representing a folder.
     * @param foldername name of a file inside the folder.
     * @return if the file exists, a File object representing the file.
     * Null otherwise.
     */
    public File safeFolder(File folder, String foldername) {
       // Check if the folder exists and is a folder. If it doesn't, create it.
       final boolean isFolder = folder.isDirectory();
       if(!isFolder) {
          folder = safeFolder(folder.getAbsolutePath());
          // No need to return messages, safeFolder will indicate what error
          // occurred.
          if(folder == null) {
             return null;
          }
       }

        // Build the complete folder
        String folderpath = folder.getAbsolutePath() + File.separator +
                foldername;
        return safeFile(folderpath);
    }


    /**
     * Given a String representing a filepath to a File, returns a File
     * object representing the file. It follows the contiditions:
     * <p>If the file exists, a File with its path is returned;
     * <br>If the file doesn't exist, an attempt is made to create an empty
     * file. If unsucessful, null is returned.
     * <br>If the path represented by the obejct File exists, but doesn't
     * represent a file, null is returned;
     *
     * <p>If a File object is returned, it is guaranteed that the file exists.
     *
     * @param filepath a string representing a filepath to a file.
     * @return a File object representing the file. Null otherwise.
     */
    public File safeFile(String filepath) {
        // Variables
        final File file = new File(filepath);

        // Check if File is a file
        final boolean isFile = file.isFile();
        if(isFile) {
            return file;
        }

        // Check if File exists. If true, is not a file.
        final boolean fileExists = file.exists();
        if(fileExists) {
            console.warn("safeFile: path \""+filepath+"\" exists, but " +
                    "doesn't represent a file.");
            return null;
        }

       // File doesn't exist. Try to create it.
       try {
          final boolean fileCreated = file.createNewFile();
          if (fileCreated) {
             console.info("File created (" + file.getAbsolutePath() + ").");
             return file;
          } else {
             console.info("File could not be created (" + file.getAbsolutePath()
                     + ").");
             return null;
          }
       } catch (IOException ex) {
          console.warn("safeFile: IOException while trying to create file! " +
                  "(" + file.getAbsolutePath() + ")");
          Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);          
          return null;
       }

    }

    /**
     * Given a File representing a folder, and a String representing a
     * filepath to a File, returns a File object representing a file inside the
     * folder. It follows the contiditions:
     * <p>If the file inside the folder exists, a File with its path is
     * returned;
     * <br>If the folder doesn't exist, an attempt is made to create the folder
     * and all the needed folders. If unsucessful, null is returned.
     * <br>If the file inside the folder doesn't exist, an attempt is made to
     * create an empty file. If unsucessful, null is returned.
     * <br>If the complete filepath exists, but doesn't represent a file, null
     * is returned;
     *
     * @param folder File representing a folder.
     * @param filename name of a file inside the folder.
     * @return if the file exists, a File object representing the file.
     * Null otherwise.
     */
    public File safeFile(File folder, String filename) {
       // Check if the folder exists and is a folder. If it doesn't, create it.
       final boolean isFolder = folder.isDirectory();
       if(!isFolder) {
          folder = safeFolder(folder.getAbsolutePath());
          // No need to return messages, safeFolder will indicate what error
          // occurred.
          if(folder == null) {
             return null;
          }
       }

        // Build File
        String filepath = folder.getAbsolutePath() + File.separator +
                filename;
        return safeFile(filepath);
    }


    /**
     * Given a File representing a file, returns a String with the contents of
     * the file. It follows the contiditions:
     *
     * <p>If the File object doesn't exist or doesn't represent a file, an empty
     * string is returned;
     *
     * @param file File object representing a file.
     * @return contents of the file.
     */
    public String read(File file) {

        // Check if path exists
        final boolean fileExists = file.exists();        
        if (!fileExists) {
            console.warn("read: path \"" + file.getAbsolutePath() + "\" doesn't " + "exist.");
            return "";
        }

        // Path exists. Check if path is a file
        final boolean isFileValid = file.isFile();
        if (!isFileValid) {
            console.warn("read: path \"" + file.getAbsolutePath() + "\" exists, " + "but isn't a file.");
            return "";
        }
         
        // File exists. Check size
        final long fileSize = file.length();
        if(fileSize == 0) {
            console.info("Reading empty file ("+file.getAbsolutePath()+").");
            return "";
        }

        // File has size greater than 0. Create StringBuilder with size of file
        final StringBuilder stringBuilder = new StringBuilder((int) fileSize);

        // Try to open the file


         FileInputStream stream = null;
         InputStreamReader streamReader = null;
        // Try to read the contents of the file into the StringBuilder
        try {
            stream = new FileInputStream(file);
            streamReader = new InputStreamReader(stream, charSet);
            final BufferedReader bufferedReader = new BufferedReader(streamReader);

            // Read first character. It can't be cast to "char", otherwise the
            // -1 will be converted in a character.
            // First test for -1, then cast.
            int intChar = bufferedReader.read();
            while(intChar != -1) {
                char character = (char) intChar;
                stringBuilder.append(character);
                intChar = bufferedReader.read();
            }

        } catch (FileNotFoundException ex) {
            console.warn("read: FileNotFoundException while trying to read " +
                    "file! (" + file.getAbsolutePath() + ")");
            Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            console.warn("read: IOException while trying to read " +
                    "file! (" + file.getAbsolutePath() + ")");
            Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        
        finally {        
            try {
                streamReader.close();
            } catch (IOException ex) {
                console.warn("read: IOException while trying to close the " +
                        "streamReader! (" + file.getAbsolutePath() + ")");
                Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
             
        }
         

        // Checking if size of StringBuffer is equal to size of original file
        //final int builderSize = stringBuilder.length();
        //final boolean sameSize = fileSize == builderSize;
        //if(!sameSize) {
        //   console.info("Size of original file ("+fileSize+") is " +
        //           "different from size of loaded String ("+builderSize+").");
        //
        //}

        return stringBuilder.toString();
    }


    /**
     * Given a File representing an existing file and a String, writes the
     * contents of the String in the file, overwritting everything that was
     * previously in the file. It follows the contiditions:
     *
     * <p>If the File object doesn't exist or doesn't represent a file, nothing
     * is written;
     *
     * @param file File object representing an existing file.
     * @param contents The contents to write.
     */
    public void write(File file, String contents) {
        write(file, contents, false);
    }


    /**
     * Given a File representing an existing file and a String, writes the
     * contents of the String at the end of the file. 
     * It follows the contiditions:
     *
     * <p>If the File object doesn't exist or doesn't represent a file, nothing
     * is written;
     *
     * @param file File object representing an existing file.
     * @param contents The contents to write.
     */
    public void append(File file, String contents) {
        write(file, contents, true);
    }

    /**
     * Given a File representing an existing file and a String, writes the
     * contents of the String in the file.If the second argument is true,
     * then bytes will be written to the end of the file rather than
     * the beginning. It follows the contiditions:
     *
     * <p>If the File object doesn't exist or doesn't represent a file, nothing
     * is written;
     *
     * @param file File object representing an existing file.
     * @param contents The contents to write.
     * @param append if true, then bytes will be written to the end of the
     * file rather than the beginning.
     */
    private void write(File file, String contents, boolean append) {
        // Check if path exists
        final boolean fileExists = file.exists();
        if (!fileExists) {
            console.warn("write: path \"" + file.getAbsolutePath() + "\" " +
                    "doesn't " + "exist.");
            return;
        }

        // Path exists. Check if path is a file
        final boolean isFileValid = file.isFile();
        if (!isFileValid) {
            console.warn("read: path \"" + file.getAbsolutePath() + "\" " +
                    "exists, but isn't a file.");
            return;
        }

         FileOutputStream stream = null;
         OutputStreamWriter streamWriter = null;
        // File exists. Try to write it
        try {
            stream = new FileOutputStream(file, append);
            streamWriter = new OutputStreamWriter(stream, charSet);
            final BufferedWriter writer = new BufferedWriter(streamWriter);
            writer.write(contents, 0, contents.length());
            writer.close();
            // Inform about the operation
            if(append) {
               console.info("File appended ("+file.getAbsolutePath()+").");
            } else {
                console.info("File written ("+file.getAbsolutePath()+").");
            }

        } catch (IOException ex) {
            console.warn("write: IOException while trying to use the " +
                        "writers! (" + file.getAbsolutePath() + ")");
            Logger.getLogger(Disk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // INSTANCE VARIABLES
    // Message Output
    final private Console console;
    // Static Disk with default messages.
    final private static Disk defaultDisk = defaultDisk();

    // DEFINITIONS
    final String charSet = "UTF-8";

}
