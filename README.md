# DS
> For the Neo4j database, create a file "neo4j.properties"
>
> If you have set up a neo4j database remotely, just replace "localhost" with remote ip address
>
> Default username and password are all neo4j

```
url=jdbc:neo4j:http://localhost
user=yourusername
password=yourpassword
```
**The above is all I know about neo4j, I'm still confused with the syntax**

# 20/05/2021 First Meeting
- Discuss about the project
- Discuss about the implementation of Student and their Relationships
- Discuss about the graph database Neo4j
- Discuss about the implementation of events
- Assign work to each person
  - Toh, Niu -> event 3, 4, 6
  - Liu, Cheong -> event 1, 2, 5, neo4j db
- Discuss the time for next meeting - 27/05/2021

# 28/5 Second Meeting
+ yeyang & huiting: 1,2,5（Week 12一定要弄好） + parallel farming(不是Week 12)
+ toh & niu：3，4，6（Week 12一定要弄好）+ six degree（不是Week 12）

# Handbook
### E3 Lunch:
+ **How to use?**
  ```java
  E3Lunch VARIABLE1= new E3Lunch(YOUR STUDENT ARRAYLIST HERE);
  ArrayList<Student> VARIABLE2 = VARIABLE.findMaxSolution();
  ``` 
+ **How to test?**<br>
Uncomment line 56->60 and line 65->78, then run it.

