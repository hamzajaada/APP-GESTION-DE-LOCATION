-- Création de la base de données Socketapp
CREATE DATABASE IF NOT EXISTS Socketapp;

-- Utilisation de la base de données Socketapp
USE Socketapp;

-- Création de la table utilisateurs
CREATE TABLE IF NOT EXISTS utilisateurs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    ville VARCHAR(255),
    password VARCHAR(255) NOT NULL
);
