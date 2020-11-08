---
layout: page
title: Wang Jun Hao's Project Portfolio Page
---

## Project: MediBook

MediBook is a **desktop medical records software** targeting **doctors and administrative staffs** in clinics or hospitals to 
help **manage patient details.** It is **optimized for use via a Command Line Interface** (CLI) while 
still having the benefits of a Graphical User Interface (GUI). The GUI is implemented using JavaFX.

Main highlights of MediBook include:
* Ability to keep track of patients’ administrative details, such as `IC`, `Name` etc, and medical details, like `Weight`, `BMI`, `Blood Type` etc. 
* Ability to store medical consultation notes for each patient
* Displaying of health metric charts such as `BMI`
* Login accounts for Doctors for a more streamlined experience (e.g. auto-fill name and medical IC)

<br/>

**Given below are my contributions to the project.**

* **New Feature**: Added the ability to create/edit/delete medical consultation notes for each patient
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Auto computation of a patient's `BMI` from `Height` and `Weight` fields

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
