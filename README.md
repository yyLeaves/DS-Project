# <center>Data Structure Assignment</center>
https://github.com/yyLeaves/DS-Project


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
  + yeyang & huiting: 1,2,5（Before Week 12） + parallel farming(After Week 12)
  + toh & niu：3，4，6（Before Week 12）+ six degree（After Week 12）
- Discuss the time for next meeting - 28/05/2021

## 04/06/2021 Third Meeting
- Discuss the difficultis in event 5&6
- Discuss the time for next meeting - 05/06/2021


## 05/06/2021 Fourth Meeting
- Discuss about the reports
- Assign work of reports to everyone

## 05/06/2021 Fourth Meeting
- Assign Test and debug tasks
- Discuss the time for next meeting, 10/06/2021

# Handbook

## Entrance

`DS-Project -> project_sociopath -> src -> com -> sociopath -> ui -> MainInterface`

## Possible Problems and Solution

If you are having an IOException, that is probably because your IDE cannot find `path.properties`.
The simplest fix is to go to `DS-Project -> project_sociopath -> src -> com -> sociopath -> util -> PathUtils`, change line 21 "path.properties" to the absolute path of 'DS-Project/path.properties'
