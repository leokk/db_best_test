# Test project

####Your goal is to create a Java application which answers the following questions: 
1) Does the route between two points (id-A and id-B) exist?
2) If the answer is yes, then calculate the minimal route length between id-A and id-B.


#Solution

- Used Maven as a project management tool
 - h2 database(v.1.4.200) to store information about water pipeline system and set of nodes
between which we need to find the route.
 - JavaFX was used to create the application's UI.  


### Detail about project structure
Algorithm package contains only one class which resolves Dijkstra's algorithm.

DB package contains parent and child database classes for quick change to different db

Utils contains classes for constants, alerts and custom file manager

### Class detail info

 - TaskPerformer is a service that manages files and database operations.  
 - Controller (and MainScene.fxml of course) contains everything about gui 
 
 ### Using info
 You need to specify position of Pipe, Route and Database files by clicking 
 appropriate button with file open dialog box. If some fields
 are not filled in, a dialog box appears with a corresponding message.
 If the file path is correct and if the files contain valid data the program 
 creates result.csv file in folder where contains route.csv. After that writes information from
 route.csv and pipe.scv to database tables. If something of above is wrong you will see
 a corresponding message.
 