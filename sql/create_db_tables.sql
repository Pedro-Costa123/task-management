create database task_management;

use task_management;

-- Table for Users
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Table for Tasks
CREATE TABLE tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(20) DEFAULT 'TO-DO',
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Insert Users
INSERT INTO users (username, email) VALUES
('john_doe', 'john.doe@example.com'),
('jane_smith', 'jane.smith@example.com'),
('bob_jackson', 'bob.jackson@example.com');

-- Insert Tasks
INSERT INTO tasks (title, description, due_date, status, user_id) VALUES
('Complete Project Proposal', 'Write a detailed proposal for the upcoming project.', '2023-12-01', 'TO-DO', 1),
('Design Database Schema', 'Create a database schema for the task management system.', '2023-12-05', 'IN PROGRESS', 1),
('Implement User Authentication', 'Integrate user authentication for the Spring Boot API.', '2023-12-10', 'TO-DO', 2),
('Review Codebase', 'Perform a code review for the latest changes in the codebase.', '2023-12-08', 'TO-DO', 3),
('Deploy API to Production', 'Prepare the API for production deployment and launch.', '2023-12-15', 'TO-DO', 3);
