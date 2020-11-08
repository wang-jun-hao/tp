---
layout: page
title: Foo Jing Yi's Project Portfolio Page
---

## Project: MediBook

MediBook is a desktop application used for managing patient records, targeted at doctors and administrative staff working at clinics and hospitals.
This app is optimised for use via a Command Line Interface (CLI) whilst still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, MediBook can help you manage your patient records faster than traditional GUI apps. 
This allows you to reduce administrative overhead in your clinic or hospital, thereby also reducing waiting time for your patients.
The user interacts with MediBook using a CLI, and receives feedback from the app through the GUI. MediBook is written in Java, with its GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added a patient profile as a GUI element.
  * What it does: allows the user to view all of a specified patient's information in one window as a patient profile. 
  * Justification: This feature allows us to display only summarised information in the main window of the app so users can see an interface that is less cluttered, and only view more information when required.
  * Highlights: This enhancement required extensive research and experimentation in JavaFX. 
  It was challenging to finalise the sizing of each sub-element of the patient profile since JavaFX elements were reactive to each other.
  Object-oriented programming (OOP) was used in designing the sub-elements as separate classes before containing them in the main `PatientProfile` class to achieve the desired GUI.

* **New Feature**: Added medical details (allergy, condition and treatment) as patient fields

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=foojingyi)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

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
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

