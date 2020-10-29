---
layout: page
title: User Guide
---
<h1>MediBook User Guide: Everything you need to know about MediBook</h1>
* Table of Contents
{:toc}

## 1. Introduction
MediBook is a **desktop app for managing patient details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MediBook can get your patient management tasks done faster than traditional methods.

--------------------------------------------------------------------------------------------------------------------


## 2. Quick start


1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest medibook.jar from [https://github.com/AY2021S1-CS2103T-F13-3/tp/releases](https://github.com/AY2021S1-CS2103T-F13-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your MediBook.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
6. Refer to the Features below for details on each command.

---------------------------------------------------------------------------------------------------------------

## 3. Features


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add i/IC`, `IC` is a parameter which can be used as `add i/S91234567A`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [w/WEIGHT]` can be used as `n/John Doe w/60.5` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### 3.1 Viewing help : `help`

Shows a message explaning how to access the help page.

####Format: 

`help`

### 3.2 Listing all patients: `list`

Shows a list of all patient's records in the system.

####Format: 

`list`

### 3.3 Adding a patient: `add`

Adds a patient to the system.

IC, Name, Date of Birth and Phone Number are compulsory fields while the rest are optional.

####Format: 

`add i/IC n/NAME d/DATE_OF_BIRTH p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`

####Examples:

`add i/S9123456A n/Divakar d/29-02-2000 p/91234567`

`add i/T0123456Q n/Divakar d/29-02-2000 p/91234567 e/divakarmal@medibook.com a/NUS, Kent Ridge Drive h/178 w/75 b/O+`

### 3.4 Editing a patient: `edit`

Edits the specified patient's information from MediBook. 

The `edit` command is also used to fill in unspecified fields.

####Format: 

`edit INDEX [i/IC] [n/NAME] [d/DATE OF BIRTH] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD TYPE][t/TAG]`

* `edit` on a field that already exists will update it from the previous value to the new value
* `edit` on an optional field that was not specified at the point of adding patient will fill the field with the given value

####Example:

`edit 1 n/Divakar` edits the name of patient with index `1` in the displayed list to `Divakar`

`edit 3 n/Divakar p/91111111 h/201` edits the name, phone number and height of patient with index `3` in the displayed list to `Divakar`, `91111111` and `201`cm  respectively.

### 3.5 Deleting a patient : `delete`

Deletes the specified patient from MediBook.

####Format: 

`delete INDEX`

####Example:
* `delete 1` deletes the patient with index `1` in the displayed list

### 3.6 Finding a patient by IC: `find`

Finds patient records by multiple fields and multiple keywords.

####Format: 

`find [i/IC] [n/NAME] [d/DATE_OF_BIRTH] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`

* At least one field must be specified for the command to be valid
* The search is case-insensitive. e.g `S9123456A` will match `s9123456a`
* The search will match substrings e.g `S912345` will match `S9123456A`
* Search will return all records that matches any of the keywords for each field e.g `n/Alice Bob` will return the records of all patients with name containing `Alice` or `Bob`

####Examples: 
* `find i/S9123456A` returns the patient record with IC `S9123456A`
* `find n/Billy Alice a/Clementi i/S99` returns the patient records with Name containing `Billy` or `Alice`, Address containing `Clementi` and IC containing `S99`

### 3.7 Accessing a patient's profile : `access`

Accesses a specified patient's profile in MediBook.

####Format: 

`access INDEX`

* Use the `list` command to return to the main list page
* Each patient's profile consists of 3 sections: Personal Details, Medical Details and Medical Notes.
    * Personal Details section (top left): Shows the personal details of the patient, including their Name, IC, Date of Birth, and Phone Number, as well as their Email, Address, Height, Weight, BMI and Blood Type if available.
    * Medical Details section (bottom left): Shows medical details as tags separated into 3 categories (to be implemented), namely: Allergies, (ongoing) Treatments and (preexisting) Conditions.
    * Medical Notes section (right): A scrollable panel showing the list of medical notes recorded for the specified patient.

### 3.8 Adding a medical note: `addnote`

Adds a medical note to a patient.

If you are a doctor, you can add a medical note to your patient when viewing his/her profile to keep track of consultations
with your patients. MediBook will automatically add the medical note to the patient displayed on your screen.

####Format: 

`addnote [d/DATE] c/CONTENT_OF_MEDICAL_NOTE`

* You have to be on a patient's profile page to add a medical note. You can do this by first `find`-ing the patient by IC
when on the main list and then `access`-ing the patient's index on the filtered list.
* You have to be logged in as a Doctor.
* MediBook will automatically set you as the author of the medical note.
* For your convenience, the date field can be omitted. MediBook will automatically select today's date.
* If you specify the date of the medical note, it cannot be in the future.

####Example:

Context: You are logged in as Dr John Doe (M06371K) and viewing the profile page of patient with IC 'S9123456A'

* `addnote c/Patient complains of stomach ache and headache. No signs of fever. Prescribed painkillers and probiotics.`

Adds a medical note that is dated today, by Dr John Doe (M06371K) with content "Patient complains of stomach ache and headache. 
No signs of fever. Prescribed painkillers and probiotics." to patient with IC 'S9123456A'.

### 3.9 Editing a medical note: `editnote`

Edits the date and/or content of an existing medical note belonging to a patient, authored by you.

If you are a doctor, you can edit the medical note belonging to your patient when viewing his/her profile. You may want to use this
feature to correct any typos or update incorrect dates.

####Format: 

`editnote INDEX [d/DATE] [c/CONTENT_OF_MEDICAL_NOTE]`

* You have to be on a patient's profile page to edit a medical note. You can do this by first `find`-ing the patient by IC
when on the main list and then `access`-ing the patient's index on the filtered list.
* You can only edit notes that are authored by you.
* `INDEX` refers to the index of the medical note displayed in the list of medical notes.
* If you change the date of the medical note, the new date cannot be in the future.

####Example:

Context: You are logged in as Dr John Doe (M06371K), viewing the profile page of patient with IC 'S9123456A' with 
the medical note at index 1 authored by you.

* `editnote 1 d/20-02-2018 c/Patient is having migraine.`

Updates the date of the medical note with index 1 in the displayed list of medical notes 
to '20-02-2018' and content to 'Patient is having migraine.'.

### 3.10 Deleting a medical note: `deletenote`

Deletes a medical note belonging to a patient, authored by you.

If you are a doctor, you can delete an existing medical note belonging to your patient when viewing his/her profile.

####Format: 

`deletenote INDEX`

* You have to be on a patient's profile page to delete a medical note. You can do this by first `find`-ing the patient by IC
when on the main list and then `access`-ing the patient's index on the filtered list.
* You can only delete notes that are authored by you.
* `INDEX` refers to the index of the medical note displayed in the list of medical notes.

####Example:

Context: You are logged in as Dr John Doe (M06371K), viewing the profile page of patient with IC 'S9123456A' with 
the medical note at index 1 authored by you.

* `deletenote 1`

Deletes the medical note with index 1 in the displayed list of medical notes belonging to patient with IC 'S9123456A'.

### 3.11 Exiting the program : `exit`

Exits the program.

####Format: `exit`

### 3.12 Saving the data

Patient records are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediBook home folder.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**List** | `list`
**Add** | `add i/IC n/NAME d/DATE_OF_BIRTH p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]` <br> e.g.,<br>`add i/S9123456A n/Divakar d/29-02-2000 p/91234567` <br> `add i/T0123456Q n/Divakar d/29-02-2000 p/91234567 e/divakarmal@medibook.com a/NUS, Kent Ridge Drive h/178 w/75 b/O+`
**Edit** | `edit INDEX [i/IC] [n/NAME] [d/DATE OF BIRTH] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD TYPE][t/TAG]` <br> e.g., <br> `edit 1 n/Divakar` <br> `edit 3 n/Divakar p/91111111 h/201`
**Delete** | `delete INDEX`<br> e.g., `delete 1`
**Find** | `find [i/IC] [n/NAME] [d/DATE_OF_BIRTH] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [h/HEIGHT] [w/WEIGHT] [b/BLOOD_TYPE]`<br> e.g., <br> `find i/G1234567S`<br>`find n/Jack i/T00 dob/2000 h/17 a/Changi`
**Access** | `access INDEX` <br> e.g., `access 1`
**Add Note** | `addnote [d/DATE] c/CONTENT_OF_MEDICAL_NOTE` <br> e.g. `addnote c/Patient is having fever. Prescribed panadol.`
**Edit Note** | `editnote INDEX [d/DATE] [c/CONTENT_OF_MEDICAL_NOTE]` <br> e.g. `editnote 1 d/20-02-2018 c/Patient is having migraine.`
**Delete Note** | `deletenote INDEX` <br> e.g. `deletenote 1`
**Exit** | `exit`
