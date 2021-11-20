-- Create Database
CREATE DATABASE UserSession;

-- Create User
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'testpwd';

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON UserSession.* TO 'admin'@'localhost';

-- mysql -u admin -p UserSession
