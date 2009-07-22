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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Static methods for quick and simple manipulation of files, folders and other 
 * input/output related operations.
 *
 * @author Joao Bispo
 */
public class Io {

    /**
     * Given a string representing a filepath to a folder, returns a File
     * object representing the folder. It follows the contiditions:
     * <p>If the folder exists, a File with its path is returned;
     * <br>If the folder doesn't exist, it will be created, along with all
     * the necessary folders;
     * <br>If the folder couldn't be created, null is returned;
     * <br>If the File exists, but doesn't represent a folder, null is returned;
     *
     * <p>If a File object is returned, it is guaranteed that the folder exists.
     *
     * @param folderpath a string representing a filepath to a folder.
     * @return if folder exists, a File object representing the folder.
     * Null otherwise.
     */
    public static File safeFolder(String folderpath) {
        // EXPLAIN: The check for null String was commented out because if the
        // null is not caught here, the application will fail when we try to
        // initiallize a File object with a null argument. This way, we can
        // quickly found where the null argument was used.
        /*
        if(folderpath == null) {
            Print.warn("safeFolder: input String is null.");
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
            Print.warn("safeFolder: path \""+folderpath+"\" exists, but " +
                    "doesn't represent a folder.");
            return null;
        }

        // Try to create folder.
        final boolean folderCreated = folder.mkdirs();
        if(folderCreated) {
            Print.info("Created folder \""+folder.getAbsolutePath()+"\".");
            return folder;
        }

        // Couldn't create folder
        Print.warn("safeFolder: path \""+folderpath+"\" doesn't exist and " +
                "couldn't be created.");
        return null;
    }

    
    /**
     * Given a String representing a filepath to a File, returns a File
     * object representing the file. It follows the contiditions:
     * <p>If the file exists, a File with its path is returned;
     * <br>If the file doesn't exist, null is returned
     * <br>If the File exists, but doesn't represent a file, null is returned;
     * 
     * @param filepath a string representing a filepath to a file.
     * @return if the file exists, a File object representing the file.
     * Null otherwise.
     */
    public static File safeFile(String filepath) {
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
            Print.warn("safeFile: path \""+filepath+"\" exists, but " +
                    "doesn't represent a file.");
            return null;
        }

        // File doesn't exist
        Print.warn("safeFile: path \""+filepath+"\" doesn't exist.");
        return null;
    }

    /**
     * Given a File representing a folder, and a String representing a 
     * filepath to a File, returns a File object representing a file inside the
     * folder. It follows the contiditions:
     * <p>If the file inside the folder exists, a File with its path is
     * returned;
     * <br>If the folder doesn't exist, null is returned;
     * <br>If the file inside the folder doesn't exist, null is returned
     * <br>If the complete filepath exists, but doesn't represent a file, null
     * is returned;
     * 
     * @param folder File representing a folder.
     * @param filename name of a file inside the folder.
     * @return if the file exists, a File object representing the file.
     * Null otherwise.
     */
    public static File safeFile(File folder, String filename) {
        // Check if the folder exists
        final boolean folderExists = folder.exists();
        if(!folderExists) {
            Print.warn("safeFile: path to folder \""+folder.getAbsolutePath()+
                    "\" doesn't exist.");
        }

        // Check if the folder is valid
        final boolean isFolderValid = folder.isDirectory();
        if(!isFolderValid) {
            Print.warn("safeFile: path to folder \""+folder.getAbsolutePath()+
                    "\" exists, but isn't a folder.");
            return null;
        }

        // Build File
        String filepath = folder.getAbsolutePath() + filename;
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
    public static String read(File file) {

        final boolean fileExists = file.exists();
        if (!fileExists) {
            Print.warn("read: path \"" + file.getAbsolutePath() + "\" doesn't " + "exist.");
            return "";
        }

        final boolean isFileValid = file.isFile();
        if (!isFileValid) {
            Print.warn("read: path \"" + file.getAbsolutePath() + "\" exists, " + "but isn't a file.");
            return "";
        }


        final long fileSize = file.length();
        final StringBuilder stringBuilder = new StringBuilder((int) fileSize);
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);
            final BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read first character
            int intChar = (char)bufferedReader.read();

            while(intChar != -1) {
                char character = (char) intChar;
                stringBuilder.append(character);
                intChar = bufferedReader.read();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Io.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Io.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Io.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Print.info("File size:"+fileSize);
        Print.info("Builder size:"+stringBuilder.length());
        return stringBuilder.toString();
    }

    
}