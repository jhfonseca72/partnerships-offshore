@echo off

@echo on
@rem TODO: this process shoudl be automatized using a CI/CD integration tool

rem Folder backend service
cd backend

@rem Compiling the backend project
call ./gradlew.bat build

@rem Folder frontend service
cd ..\frontend

rem Compiling frontend service
call npm run build

rem Executing docker-compose
docker-compose up --detach

@echo off