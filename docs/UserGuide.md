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
    + [Adding a medical note : note](#note-command)
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

IC, Name, Date of Birth and Phone Number are compulsory fields while the rest are optional.

Format: 

`add i/IC n/NAME d/DATE_OF_BIRTH p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`

Example:

`add i/S9123456A n/Divakar d/29-02-2000 p/91234567`

`add i/T0123456Q n/Divakar d/29-02-2000 p/91234567 e/divakarmal@medibook.com a/NUS, Kent Ridge Drive h/178 w/75 b/O+`

### Deleting a patient : `delete` <a id="delete-command"></a>

Deletes the specified patient from MediBook.

Format: `delete <index>`

Examples:
* `delete 1` deletes the patient with index `1` in the displayed list

### Accessing a patient's profile : `access` <a id="access-command"></a>

Accesses the specified patient in MediBook.

Format: `access <index>`

* Use the `list` command to return to the main list page

### Finding a patient by IC: `find` <a id="find-command"></a>

Finds patient records by their fields.


Format: `find [i/IC] [n/NAME] [d/DATE_OF_BIRTH] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`

* At least one field must be specified for the command to be valid
* The search is case-insensitive. e.g `S9123456A` will match `s9123456a`
* The search will match substrings e.g `S912345` will match `S9123456A`
* Search will return all records that matches any of the keywords for each field e.g `n/Alice Bob` will return the records of all patients with name containing `Alice` or `Bob`

Examples: 
* `find i/S9123456A` returns the patient record with IC `S9123456A`
* `find n/Billy a/Clementi i/S99` returns the patient records with Name containing `Billy`, Address containing `Clementi` and IC containing `S99`

### Listing all patients: `list` <a id="list-command"></a>

Shows a list of all patient's records in the system.

Format: `list`

### Adding a medical note: `note` <a id="note-command"></a>

Adds a medical note to a patient.

As a doctor, you can add a medical note to a patient when viewing his/her profile. MediBook will automatically add the 
to the patient displayed on the screen.

Format: `note [d/DATE] n/NAME_OF_DOCTOR c/CONTENT_OF_MEDICAL_NOTE`

* You have to be on a patient's profile page to add a medical note. This is done by first `find`-ing the patient by IC
when on the main list and then `access`-ing the patient's index on the filtered list.
* For your convenience, the date field can be omitted. MediBook will automatically select today's date.
* If you specify the date of the medical note, it has to be in the past or today.

Examples:

Context: while viewing the profile page of patient with IC `S9123456A`

* `note n/Dr John Doe c/Patient complains of stomach ache and headache. No signs of fever. Prescribed painkillers and probiotics.`

Adds a medical note that is dated today, by Dr John Doe with content "Patient complains of stomach ache and headache. 
No signs of fever. Prescribed painkillers and probiotics." to patient with IC S9123456A

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

Action | Format, Examples
--------|------------------

**Add** | `add i/IC n/NAME d/DATE_OF_BIRTH p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]` <br> e.g.,<br>`add i/S9123456A n/Divakar d/29-02-2000 p/91234567` <br> `add i/T0123456Q n/Divakar d/29-02-2000 p/91234567 e/divakarmal@medibook.com a/NUS, Kent Ridge Drive h/178 w/75 b/O+`
**Access** | `access <index>` <br> e.g., `access 1`
**Delete** | `delete <index>`<br> e.g., `delete 1`
**Find** | `find [i/IC] [n/NAME] [d/DATE_OF_BIRTH] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`<br> e.g., <br> `find i/G1234567S`<br>`find n/Jack i/T00 dob/2000 h/17 a/Changi`
**List** | `list`
**Note** | `note [d/DATE] n/NAME_OF_DOCTOR c/CONTENT` <br> e.g. `note n/Dr John c/Patient is having fever. Prescribed panadol.`
**Exit** | `exit`
**Help** | `help`
