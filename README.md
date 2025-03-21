# Student-Card-Management-System

## Student Card Management System Application

### What will the application do?
The student card is used by almost all universities. In addition to being
a proof of student status, the student card facilitates the 
daily life of students to a large extent. Student cards need to be managed 
and serviced by people, but it is not possible to do it all *manually*, 
so we need a student card *self-service* system. In this application, students can
**check their balance, add money, checking their card information, change password, 
and so on.**

### Who will use it?
If any university uses this software, all students of the university can use it.
It will make students' life more convenient.

### Why is this project of interest you?
As a student, I use my student card on a daily basis, so I hope there is a very 
useful application to improve the convenience of student card.  

## User Stories

- As a user, I want to be able to create an account
- As a user, I want to be able to log account
- As a user, I want to be able to delete account
- As a user, I want to be able to check the information of my student card
- As a user, I want to be able to check the balance of my student card
- As a user, I want to be able to add money to student card
- As a user, I want to change my the password of my card
- As a user, I want to be able to print accounts
- As a user, I want to be able to save changes to file
- As a user, I want to be able to load my changes from file


## Phase 4: Task 2
A representative sample of the events that occur when this program runs:<br>
These events include add account and remove account:<br>

[Wed Mar 30 17:55:47 PDT 2022
Added account:
name: A
ID: 1

, Wed Mar 30 17:55:47 PDT 2022
Added account:
name: B
ID: 2

, Wed Mar 30 17:55:47 PDT 2022
Added account:
name: C
ID: 3

, Wed Mar 30 17:55:55 PDT 2022
Added account:
name: D
ID: 4

, Wed Mar 30 17:56:06 PDT 2022
Removed account:
name: D
ID: 4

]


## Phase 4: Task 3
- The first change I want to make is to increase readability. In GUI, 
MangeGUI and SelfServiceGUI, I want to extract some codes from the 
constructor and then create separate methods for task and give them meaningful
names. For example, I want to create a method to initialize buttons.
<br>
<br>
- There is a change I have made before, I realize that set labels and
text fields in the method which initialize panel was not following the 
single responsibility principle. So I create method to set labels and
text fields.

## About  
This project was developed as part of **CPSC 210 (Software Construction)** at UBC. It is a Java-based Student Card Management System that allows students to manage their student cards through a self-service interface. Key features include creating and deleting accounts, checking balance and card info, changing passwords, and saving/loading data. The project also emphasizes software design principles, with improvements made to readability, modularity, and GUI structure throughout development.
