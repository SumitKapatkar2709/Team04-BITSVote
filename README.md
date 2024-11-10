# Bitsvote

BITSVote is a web-based voting application designed to enable secure and efficient voting for BITS Pilani students. The system is divided into a frontend built with modern web technologies and a backend powered by Spring Boot.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)


## Features
- Secure Google-based authentication (for BITS emails only)
- Role-based access for students, admins, and organizers
- Real-time voting results with dynamic charts
- Admin dashboard to manage elections and candidates
- Responsive UI for both desktop and mobile users

## Tech Stack
### Frontend
- **Angular 18**
- HTML5, CSS3, TypeScript
- Bootstrap / Material UI (for styling)

### Backend
- **Spring Boot 3.4.4**
- Java 17
- MySQL (or any other relational database)
- Spring Data JPA
- Spring Security (with Google OAuth2)

## Installation

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 18.2.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
