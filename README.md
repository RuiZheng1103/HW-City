# HW - City


## Problem Statement
The application is aimed to determines if two cities are connected. Two cities are considere connected if there's a series of roads that can be traveled from one city to another.

List of roads is available in a file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

It will be deployed as a Spring Boot App and reture 'yes' or 'no' as a result for application

## Simple User Case
### City Data
city.txt content is: <br>
        Boston, New York<br>
        Philadelphia, Newark<br>
        Newark, Boston<br>
        Trenton, Albany<br>
### API Usage
http://localhost:8080/connected?origin=Boston&destination=Newark  - Should return yes<br>
http://localhost:8080/connected?origin=Boston&destination=Philadelphia - Should return yes<br>
http://localhost:8080/connected?origin=Philadelphia&destination=Albany - Should return no<br>
http://localhost:8080/connected?origin=Philadelphia&destination=Chicago - Should return no<br>


## Environments

This application is managed by Maven and based on Spring Boot 2 with JDK 1.8


## Application Usage
1. deploy application as Maven project locally
2. Run as Spring boot2 applction
3. Using Browser or Test Tool(Postman) to test this application


## Application Introduction
### Input Parameter 
'orgin' and 'destination' are cities 

### Java File Description
#### /controller/CitiesController.java
mapping http Requests. 

#### /pojo/Cities.java
class define for city object.

#### /service/CitiesService.java
implementation for checking connectability for different compontent.

#### /utils/DataReader.java
Loading Data from .txt file.

### Unit test
#### Test Controller
1. valid input <br>
  - both exist and connected <br>
  - both exist but not connected <br>
  - not connected<br>
2. invalid input<br>
  - single <br>
  - empty <br>
  
#### Test Service
1. valid input <br>
  - both exist and connected <br>
  - both exist but not connected <br>
  - not connected<br>
2. invalid input<br>
  - single <br>
  - empty <br>


#### Test FileRead
1. valid file
2. invaild file

## Result
### Empty
![image](https://github.com/RuiZheng1103/HW-City/blob/main/pic/empty.png)
### Good
![image](https://github.com/RuiZheng1103/HW-City/blob/main/pic/good.png)
### valid but no
![image](https://github.com/RuiZheng1103/HW-City/blob/main/pic/validno.png)
### valid but yes
![image](https://github.com/RuiZheng1103/HW-City/blob/main/pic/validyes.png)
### notvalid
![image](https://github.com/RuiZheng1103/HW-City/blob/main/pic/validno.png)


## Futher Improvement 

### Algorithm
1. Union-Find Algorithm
Realize O(1) Time Cost for HTTP request

### Datebase
1. using h2-database for tempory test
Realize O(1) Space Cost in Memory

2. using MySql for perm use
Reduce Space Cost in Memory

### Cache
1. implemented by Redis
improve performance when Application has multiple requests
