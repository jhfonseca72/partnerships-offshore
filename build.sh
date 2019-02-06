#!/bin/sh

##############################################################################
##
##  VividSeats Interview
##
##############################################################################
echo "TODO: this process shoudl be automatized using a CI/CD integration tool"

cd backend
./gradlew build

## Folder frontend service
cd ../frontend

## Download dependencies
npm install

## Compiling frontend service
npm run build

## Executing docker-compose
docker-compose up -d