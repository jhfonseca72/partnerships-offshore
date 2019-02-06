# Basic Event Management System

A simple distributed application running across multiple Docker containers.

## Description

Thanks for taking our coding challenge! We'd like for you to implement a basic event management system, from frontend to backend.

The features we'd like to develop are:

* Display events (event name, date, venue name, city, state) in a table
* Add events

## Prerequisites

* Java 8
* Docker
* Docker compose

## How to execute without Docker

### Backend

Execute the next command in this directory `backend`

`./gradlew bootRun`

Verify by entering the following url

`http://localhost:8080/events`

You should get a response like this:

`
[
  {
    "id": 1,
    "name": "Chicago White Sox vs. Chicago Cubs",
    "venue": {
      "id": 1,
      "name": "Wrigley",
      "city": "Wrigley",
      "state": "Wrigley"
    },
    "date": "2019-01-30T00:00"
  }
]
`

### Frontend

The first thing to do is install the necessary dependencies using the following command:

`npm install`

Execute the next command in this folder `frontend`

`npm run start`

Verify by entering the following url

`http://localhost:3000/`

## How to execute with Docker

You only need to run the file build.bat and automatically the images for each project will be build and executed

`./build.bat`

Verify by entering the following url

`http://localhost/`

### Database

The configuration was realized using flyway the scheme can be founded in the file `V1__init.sql` 

##Pending

* Add forms validations.

## Others

The technologies used in this project were the next:

### Backend

* Java 8
* Gradle
* Spring Boot 2.1.0.RELEASE
* Lombok
* H2 Memory DataBase
* Flyway

### Frontend

* React
* WebPack
* Babel 7
* Material UI

### Deploy

* Bat script
* Docker
* Docker Compose