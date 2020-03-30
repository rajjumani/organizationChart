Organization Chart

-> Description:
    There is a custom class Person to store the Person entity in Person.java and main class OrgChart in OrgChart.java which executes the entire program. Also there is seperate class OrgChartTest which will have all the unit testing for each function.

-> Assumptions:
    There are few assumptions which are made while creating this project as follows:

    1. There will 3 levels as follows but if you want to extend the system by adding new levels change the value of maxLevel and also add the title of the new level in the proper sequence in levelMap:
        Level 1 - Manager
        Level 2 - Team Lead
        Level 3 - Developer

    2. If total number of members level present are less than the requested level then it will print the members upto that level.

    3. There may be more than one person having the name as requested, in that case it will print all the members having the same name. Also if there are multiple member at the same level then it will have print all of them.

    4. The format of all the input will be same as it is give in the file like for person details it will have Address consisting of format Address, City, State and phone will be in 111-111-1111 format containing 10 digits.

    5. Every person will have a unique personID in integer format.

    6. For every entry in organization and team table there should be a person already existing.

    7. Input name member is considered to be at level 1 and all the members having level upto the given level in input after level 1 member are printed.

-> Methods and their functionalities:
    1. readPersonFile: It will read the contents of personal data from personal.txt file and stores it in the HashMap data structure to maintain the list of all the Person entity.

    2. readOrgFile: It will read title and organization of each employee from organization.txt file and associate it with each person.

    3. readTeamFile: It will read the team members of each person from team.txt file and link them with their manager.

    4. findPerson: This method will find the Person matching the input string like first name or last name or full name.

    5. findTeam: This method will find all the team members of the particular person till the specified level of person.

    6. display: This method will display all the information of the person matching the criteria.

-> Compilation and Execution:

    This program will be compiled and executed using the following command in CMD.
        dirName:                    absolute path to folder containing OrgChart class
        Change Directory:           cd <dirName>
        Compiling all the files:    javac -d ./ -cp jars/junit-4.13.jar *.java
        Executing the program:      java <dirName>.OrgChart <person name>

-> Testing: 

    This program can be tested using following commands in CMD.
        dirName:                                absolute path to folder containing OrgChartTest class
        Change Directory:                       cd <dirName>
        Compiling all the files with JUnit :    javac -d ./ -cp jars/junit-4.13.jar *.java
        Executing tests:                        java -cp ./;jars/junit-4.13.jar;jars/hamcrest-core-1.3.jar org.junit.runner.JUnitCore <dirName>.OrgChartTest