# Getting Started

Steps to build and run the project -
1. Build the project (mvn clean install)
   You will get VegetatianStats-0.0.1-SNAPSHOT.jar as result.

2. Run the war file using the below command
   java -jar VegetatianStats-0.0.1-SNAPSHOT.jar


Assumptions-
1. Vegetation value cannot be negative.
2. All users are authorized to perform Rest API
3. We have vegetation value for 1 field.

Notes-
API comply with the given requirement


Technologies Used : 
1. Spring Boot
2. Swagger2 : To display the API syntax(URL : http://localhost:8080/swagger-ui.html)
3. Embedded Apache Derby Server (To Store the data)
4. Spring Boot JPA : Apache Derby Client to retrive / save data.
5. Timer Task : To Clean the old records from DB

Logic : 
1. Only 1 table is created on DB and below is the DB Structure.
   a. occurenceDate
   b. vegetationMinValue
   c. vegetationMaxValue
   d. totalVegetation
   e. totalVegetationCount
   Each row for a day
 2. On receiving the POST /field-conditions request the below steps are performed :
   a. Validate the input parameter.
      i. Check if the date is older than 30 days
      ii. Check if the date is later than present date.
      iii. Check the supported date format.
      iv. Check if there is a entry in the DB for this date. If yes, get the entry, recalculate the new values based on the present data and update the entry in DB. 
       If not present, create a new record in the DB
      v. Synchronization is performed based on the date transaction so that the blocking happens only between the POST transactions for the same date.
  3. On receiving the GET /field-statistics,
     Get the entries for the previos 30 days, and calculate the response.
     
  4. Timer task is created, which executes once every day and deletes all older than 30 days records.  