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

import java.util.Set;

/**
 * Interface for an immutable DataMap (only has read-only operations).
 *
 * @author Joao Bispo
 */
public interface ReadOnlyDataMap {

   public boolean[] getBooleanArray(Enum eKey);
   public int getInteger(Enum eKey);
   public int[] getIntegerArray(Enum eKey);
   public String getString(Enum key);
   public String[] getStringArray(Enum key);
   public Set<String> getStringSet(Enum key);
   public String name();
   
}
