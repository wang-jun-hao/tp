---
layout: page
title: Wang Jun Hao's Project Portfolio Page
---

## Project: MediBook

MediBook is a **desktop medical records software** targeting **doctors and administrative staffs** in clinics or hospitals to 
help **manage patient details.** It is **optimized for use via a Command Line Interface** (CLI) while 
still having the benefits of a Graphical User Interface (GUI), implemented using JavaFX.

Main highlights of MediBook include:
* Ability to keep track of patients’ administrative and medical details
* Ability to store medical consultation notes for each patient
* Displaying of health metric charts such as `BMI`
* Login accounts for doctors for a more streamlined experience (e.g. auto-fill name and medical IC)

<br/>

**Given below are my contributions to the project.**

* **New Feature**: Added the ability to create/edit/delete medical notes for each patient
  * What it does: Allows users to create, edit and delete medical notes belonging to a patient, when logged in as a doctor
  and viewing a patient profile page. Autofills doctor's and patient's information.
  * Justification: This is a fundamental feature of a medical records software as it allows doctors to store text notes 
  following a consultation with a patient. By only allowing these commands when logged in and 
  viewing a patient profile page, the commands themselves can be streamlined as information about the doctor and patient 
  are automatically retrieved.
  * Highlights: This enhancement required the implementation of many underlying classes, such as `Doctor` and
  `MedicalNoteList`, and established a connection between these classes and `Patient`. It required an in-depth design analysis 
  to reduce coupling and avoid cyclic dependency. The autofilling feature required the implementation of `ModelContext` 
  to retrieve these information.

* **New Feature**: Doctor verification when editing or deleting medical notes
  * What it does: Restricts editing and deleting of medical notes to the doctor who authored them.
  * Justification: Medical notes are important sensitive information that affect treatment decisions. The integrity of
  medical notes should be protected. This feature prevents tampering of medical notes by other doctors.
  * Highlights: This feature interacts with the login and model context feature to check for account match.
  
* **New Feature**: Display of medical notes in patient profile page
  * What it does: 

* **New Feature**: Reverse chronological sorting of medical notes within list

* **New Feature**: Added support for `BMI` for patients
  * Highlights: Auto computation of a patient's `BMI` from `Height` and `Weight` fields.

* **New Feature**: Added support for `MCR` for doctors


  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed 
  by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the 
  app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth 
  analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wang-jun-hao)

* **Project management**:
  * Managed all releases `v1.2.1` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
