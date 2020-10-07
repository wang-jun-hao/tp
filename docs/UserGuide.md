---
layout: page
title: User Guide
---
# MediBook User Guide: Everything you need to know about MediBook

## Table of Contents
+ [Introduction](#introduction)
+ [Quick Start](#quick-start)
+ [Features](#features)
    + [Viewing help : help](#help-command)
    + [Adding a patient: add](#add-command)
    + [Deleting a patient : delete](#delete-command)
    + [Listing all patients : list](#list-command)
    + [Exiting the program : exit](#exit-command)
    + [Saving the data](#saving)
+ [FAQ](#faq)
+ [Command Summary](#command-summary)

## Introduction
MediBook is a **desktop app for managing patient details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MediBook can get your patient management tasks done faster than traditional methods.

--------------------------------------------------------------------------------------------------------------------


## Quick start


1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest medibook.jar from https://github.com/AY2021S1-CS2103T-F13-3/tp/releases.
3. Copy the file to the folder you want to use as the home folder for your MediBook.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
6. Refer to the Features below for details on each command.

---------------------------------------------------------------------------------------------------------------

## Features


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add i/IC`, `IC` is a parameter which can be used as `add i/S91234567A`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [w/WEIGHT]` can be used as `n/John Doe w/60.5` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help` <a id="help-command"></a>

Shows a message explaning how to access the help page.

Format: `help`


### Adding a patient: `add` <a id="add-command"></a>

Adds a patient to the system.

Format: 

`add i/IC n/NAME d/DATE_OF_BIRTH p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`

Example:

`add i/S9123456A n/Divakar d/29-02-2000 p/91234567`

`add i/T0123456Q n/Divakar d/29-02-2000 p/91234567 e/divakarmal@medibook.com a/NUS, Kent Ridge Drive h/178 w/75 b/O+`

### Deleting a patient : `delete` <a id="delete-command"></a>

Deletes the specified patient from MediBook.

Format: `delete <IC of patient>`

Examples:
* `delete T0987654S` deletes the patient record with IC `T0987654S`


### Finding a patient by IC: `find` <a id="find-command"></a>

Finds patient records by their IC.

Format: `find <IC of patient>`

* The search is case-insensitive. e.g `S9123456A` will match `s9123456a`
* The full IC must match e.g `S912345` will not match `S9123456A`

Example: `find S9123456A` returns the patient record with IC S9123456A

### Listing all patients: `list` <a id="list-command"></a>

Shows a list of all patient's records in the system.

Format: `list`

### Exiting the program : `exit` <a id="exit-command"></a>

Exits the program.

Format: `exit`

### Saving the data <a id="saving"></a>

Patient records are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary
### Legend:
↵ = enter key

Action | Format, Examples
--------|------------------
**Add** | `add i/IC n/NAME d/DATE_OF_BIRTH p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE] ↵` <br> e.g.,<br>`add i/S9123456A n/Divakar d/29-02-2000 p/91234567` <br> `add i/T0123456Q n/Divakar d/29-02-2000 p/91234567 e/divakarmal@medibook.com a/NUS, Kent Ridge Drive h/178 w/75 b/O+`
**Delete** | `delete <IC> ↵`<br> e.g., `delete G1234567S`
**Find** | `find <IC> ↵`<br> e.g., `find G1234567S`
**List** | `list ↵`
**Exit** | `exit ↵`
**Help** | `help ↵`
