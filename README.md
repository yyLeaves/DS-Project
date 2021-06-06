# <center>Data Structure Assignment</center>

## 20/05/2021 First Meeting
- Discuss about the project
- Discuss about the implementation of Student and their Relationships
- Discuss about the graph database Neo4j
- Discuss about the implementation of events
- Assign work to each person
  - Toh, Niu -> event 3, 4, 6
  - Liu, Cheong -> event 1, 2, 5, neo4j db
- Discuss the time for next meeting - 27/05/2021

## 28/05/2021 Second Meeting
- Update on each others' progress
- Discuss about extra features to implement
- Abandon the use of neo4j
- Discuss about the use of git repository
- Assign extra feature work & ddl of basic features to team members
  + yeyang & huiting: 1,2,5（Week 12一定要弄好） + parallel farming(不是Week 12)
  + toh & niu：3，4，6（Week 12一定要弄好）+ six degree（不是Week 12）
- Discuss the time for next meeting - 28/05/2021

## 04/06/2021 Third Meeting
- Discuss the difficultis in event 5&6
- Discuss the time for next meeting - 05/06/2021


## 05/06/2021 Fourth Meeting

# Handbook

### E0 Initialize:
+ **How to use?**
  ```java
  // to get the initialized Student ArrayList as said in the question
  ArrayList<Student> students = E0Init.init();
  ```

### E1 Teaching:
+ **How to use?**
  ```java
  ArrayList<Student> students = E0Init.init();
  E1Teaching teach = new E1Teaching(students);
  String s = teach.teachAStranger(1);
  // s describes this event
  ```

### E2 ChitChat:
+ **How to use?**
  ```java
  // the chit chat part depends on the teaching event
  // the teaching event will automatically call the chit chat method
  String s = E2ChitChat.chitChat(students, students.get(1), students.get(4));
  // s describes this event
  ```


### E3 Lunch:
+ **How to use?**
  ```java
  E3Lunch VARIABLE1= new E3Lunch(YOUR STUDENT ARRAYLIST HERE); // constructor
  ArrayList<Student> VARIABLE2 = VARIABLE1.receiver(STUDENT OBJECT YOU); // functional method
  System.out.println(VARIABLE1.getLunchList(VARIABLE2)); // printer
  ```
  Sample Output:
  ```
  --------------------------------------
  I can have lunch with Student 4,3.

  Student ID:4
  Lunch Period: 11:35-->12:09
  Student ID:3
  Lunch Period: 12:28-->13:00
  --------------------------------------
  ```
+ **How to test?**<br>
~~Uncomment line 73->77 and line 81->97, then run it.~~


### E6 Friendship:
+ **How to use?**
  ```java
  E6Friendship.callE6();
  ```
  Sample Output:
  ```
  Output: 11
  1. [1, 2]
  2. [2, 3]
  3. [2, 4]
  4. [3, 4]
  5. [1, 2, 3]
  6. [1, 2, 4]
  7. [2, 4, 3]
  8. [2, 3, 4]
  9. [3, 2, 4]
  10. [1, 2, 4, 3]
  11. [1, 2, 3, 4]
  ```
+ **How to test?**<br />
~~Uncomment line 72->75, then run it.~~
