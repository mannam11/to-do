
# TO-DO

Welcome to our ToDo Web Application! This application is designed to help you organize and manage your tasks effectively. Whether you're a busy professional, a student with assignments, or just someone looking to stay on top of their daily activities, our ToDo Web Application has got you covered.

With a clean and intuitive user interface, our application allows you to create, prioritize, and mark tasks as complete effortlessly.


## Features

#### User login & signup
- user authentication 
- Login & Logout functionality
#### Create tasks
- create title
- description
- Due date
- Level of priority( LOW, MEDIUM, HIGH )

#### Delete tasks
- Delete unwanted tasks

#### Progress Marking
- Mark a task as completed or inprogress

#### Task separation
- Explore inprogress and completed tasks through different tabs


## Run Locally


Clone the project

```bash
git clone https://github.com/mannam11/to-do.git
```

```bash
Install JDK (Recent version)
```
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```
- You don't to worry about creating the database tables. [spring.jpa.hibernate.ddl-auto=update] , This command will take care of creating tables and relations.

- #### You can use different database as you like other than this

```bash
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-mail-id
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```
- If you not able see App passwords in your gmail account,create 2-factor authentication and try. 
- Refer the below document how to create app-password
```bash
https://knowledge.workspace.google.com/kb/how-to-create-app-passwords-000009237
```





## Tech Stack

- Springboot + Thymeleaf
- Maven
- MySQL Workbench GUI
- HTML & CSS
- IntelliJ IDE for Developement



## Author

- [@manohar_mannam](https://github.com/mannam11)


## 🔗 Links 
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/manohar-mannam/)  

