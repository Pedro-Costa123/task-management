create database task_management;

use task_management;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    id INT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE KEY userAuth_uni (id, authority),
    FOREIGN KEY (id) REFERENCES users(user_id)
);

CREATE TABLE tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(20) DEFAULT 'TO_DO',
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Insert Users
-- Insert Users with Bcrypt-hashed Passwords
-- 1234
INSERT INTO users (username, password, email, enabled) VALUES
('john_doe', '$2a$12$T/IGcTDpls0Fd9qu69pTN.7k0i4GTTowG6NCPGx6z3VEemklyRncu', 'john.doe@example.com', 1),
('jane_smith', '$2a$12$T/IGcTDpls0Fd9qu69pTN.7k0i4GTTowG6NCPGx6z3VEemklyRncu', 'jane.smith@example.com', 1),
('bob_jackson', '$2a$12$T/IGcTDpls0Fd9qu69pTN.7k0i4GTTowG6NCPGx6z3VEemklyRncu', 'bob.jackson@example.com', 1);


-- Insert Tasks
INSERT INTO tasks (title, description, due_date, status, user_id) VALUES
('Complete Project Proposal', 'Write a detailed proposal for the upcoming project.', '2023-12-01', 'TO_DO', 1),
('Design Database Schema', 'Create a database schema for the task management system.', '2023-12-05', 'IN_PROGRESS', 1),
('Implement User Authentication', 'Integrate user authentication for the Spring Boot API.', '2023-12-10', 'TO_DO', 2),
('Review Codebase', 'Perform a code review for the latest changes in the codebase.', '2023-12-08', 'TO_DO', 3),
('Deploy API to Production', 'Prepare the API for production deployment and launch.', '2023-12-15', 'TO_DO', 3);


INSERT INTO authorities VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_USER'),
(3, 'ROLE_USER');