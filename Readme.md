# Bookstore API

This project is a Bookstore API built with Spring Boot. The main goals of this project are to:
- Learn to work with file uploads and downloads.
- Implement user authentication and authorization (normal users, authors, and admins).
- Integrate with NGINX for load balancing and reverse proxy.
- Use Redis for caching.


## Table of Contents
- [Requirements](#requirements)
- [Architecture](#architecture)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Technologies](#technologies)
- [Contributing](#contributing)
- [License](#license)


## Requirements
- Java 11+
- Maven
- Docker
- Docker Compose

## Architecture
The architecture consists of:
- **Spring Boot** application for the backend.
- **NGINX** as a reverse proxy and load balancer.
- **Redis** for caching.
- **MySQL** or **PostgreSQL** for the relational database.