#FlightPub Group 2 Project Template

A project template for development of the FlightPub app. 

## Tech Stack
- Stimulus.js
- Freemarker
- Spring 
- Hibernate/Spring JPA
- Mysql
- Docker

## Setup Instructions
1. Make sure jdk20 or above is installed
   - If you're on mac you can install using [homebrew](https://formulae.brew.sh/formula/openjdk)
   - Otherwise you can install from [Oracle](https://www.oracle.com/java/technologies/downloads/)
2. Clone this project to your local machine using `git clone`
3. Setup Docker
   - Instructions can be found [here](https://docs.docker.com/get-docker/)
   - Choose setup instructions for your OS
   - Download and install
   - Run Docker
4. Define environment variables
   - Create a .env file at the root of the project 
   - Add the following variables to the .env file. Assign your own values
   
        `MYSQL_DATABASE=flightpub`
        `MYSQL_USER=group2`
        `MYSQL_PASSWORD=pass`
        `MYSQL_ROOT_PASSWORD=pass`
        `MYSQL_PORT=3306`
    
5. Setup your database container
   - Open a terminal and navigate to your project root
   - Run `docker compose build`. This builds the container image
   - Run `docker compose up`. This creates container from the image and runs your spring application for the first time.
   - To stop the container use `docker compose stop`
   - To start the container again in the background use `docker compose start`
   - To fully remove the container run `docker compose down`. This will not delete your database data as it is mounted to a section of your local.

6. Setup MySQL Workbench 
   - Download it from [here](https://dev.mysql.com/downloads/workbench/)
   - Open the application
   - Click on the plus icon next to **MySQL Connections**
   - Give the connection a name
   - Set the username to be `root`
   - Set the password to be the same as the value for `MYSQL_ROOT_PASSWORD` in step 3
   - Set the hostname to be `localhost`
   - Set the port to be the same as `MYSQL_PORT`

7. Running your spring application 
   - Your spring app will start up when you run the command `docker compose up` for the first time
   - You will know its running as the terminal will spit out a bunch of spring related outputs including a big ascii banner
   - You can stop it any time by pressing `ctrl+c` whilst in the terminal that you ran the docker command in
   - If your database container is already running you can use the command `mvn spring-boot:run` to start up spring
   - The spring app is configured to include live reloading so you do not need to stop it between code changes

*More steps to be added soon*
