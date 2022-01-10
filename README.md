# Pivot Access User Management System
This is simple application that will users to sign up and login users, with the ability to fill a form [names, tel, email, gender, age, etc.] and an administrator being able to view the individual entries and some aggregated data on the entries [pie chart of the participants by gender, etc.].

## Access application online

Deployed On Heroku [Pivot Access User Management System](https://pivot-access-users.herokuapp.com/)

## Access Application locally

    ## Before Getting Started Install

- `mvn 3.x.x`
- `JDK 11`
- `PostGreSQl 13.x.x`


    ## Getting started

Note: Make sure redis is running, and you have configured it.

```sh
$ git clone https://github.com/niomwungeri-fabrice/pa-users-api
$ cd pa-users-api
$ mvn spring-boot:run
```
    ## After cloning repository

- `Create a .env file and copy/paste the environment variables from the sample_env.txt file that's already existent in the root project directory.`
- `Update application.properties file with local database credentials and redis`

## API Documentation

[![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/11352687/UVXgKwhB)


## Available Functionalities

    ## Required EndPoints

| EndPoint                          | Functionality                                  |
|-----------------------------------|------------------------------------------------|
| `POST /v1/signup`                 | It allows you to create an account.            |
| `POST /v1/signin`                 | It allows you to login into the system.        |
| `POST /v1/complete/{userId}/form` | It allows to complete the user form.           |
| `GET /v1/count/gender`            | It allows to get statistics about about gender |
| `GET /v1/users`                   | It allows to get all users                     |



    ## Project built with:

- [`Java`](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
- [`Spring Boot`](https://spring.io/projects/spring-boot) The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform.
- [`Hibernate`](https://hibernate.org/) Hibernate ORM is an object–relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database.
- [`PostgreSql`](https://www.postgresql.org/) PostgreSQL, also known as Postgres, is a free and open-source relational database management system emphasizing extensibility and SQL compliance.
- [`JWT`](https://jwt.io/) JSON Web Token is a proposed Internet standard for creating data with optional signature and/or optional encryption whose payload holds JSON that asserts some number of claims. The tokens are signed either using a private secret or a public/private key.

## Reference:
- `PIVOT ACCESS`
