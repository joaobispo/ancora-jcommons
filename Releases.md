# JavaCommons 0.2 (Snapshot 20090924) #

## Dependencies ##

None

## Changed ##

  * Disk class is now an utility class with static methods.

## New ##

  * LoggingUtils class with static methods for outputing information. Uses Java's Logger.
  * DataManagement package with class DataMap for storing data during execution of program.

## Removed ##

  * Console class and related classes. Subtituted by class LoggingUtils and package Logging.

# JavaCommons 0.1 (Snapshot 20090911) #

## Dependencies ##

None

## New ##

  * Merged SimplePrints and SimpleIO into JavaCommons project.

# SimplePrints 1.5 (Snapshot 20090807-2) #

## Dependencies ##

SimpleIO-0.1

## New ##

  * Interface Console now supports methods print and close.

# SimplePrints 1.4 (Snapshot 20090807) #

## Dependencies ##

SimpleIO-0.1

## New ##

  * Class FileConsole, for writing to a file.


# SimpleIO 0.1 (Snapshot 20090729) #

## Dependencies ##

SimplePrints-1.3


## New ##

Disk class with methods:

read;
write;
append;
safeFile;
safeFolder;


# SimplePrints 1.3 (Snapshot 20090725) #

## Dependencies ##

None

## Changed ##

  * Interface Console and Class DefaultConsole are now in package "org.ancora.jCommons"

## Removed ##

  * Class Print.


# SimplePrints 1.2 (Snapshot 20090723) #

## Dependencies ##

None

## Changed ##

  * Class Print is now in package "org.ancora.jCommons.console"

## New ##

  * Interface Console.
  * Class DefaultConsole.


# SimplePrints 1.1 (Snapshot 20090722) #

## Dependencies ##

None

## Changes ##

  * Now all methods report to System.out, instead of System.out or System.err ([ref](http://stackoverflow.com/questions/1049795/whats-wrong-with-using-system-err-in-java)).

## New ##

Class Print:
  * method "more".

## Removed ##

Class Print:

  * method "warnMore" e "infoMore".


# SimplePrints 1.0 (Snapshot 20090721) #

## Dependencies ##

None

## New ##

  * Class Print with static methods for standard output of information and warning messages, to replace calls to System.out and System.err.