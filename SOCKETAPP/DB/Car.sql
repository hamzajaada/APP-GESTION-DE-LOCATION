CREATE TABLE Car (
    id_car INT PRIMARY KEY AUTO_INCREMENT,
    Module VARCHAR(255) NOT NULL,
    Matricule VARCHAR(255) NOT NULL,
    Prix DECIMAL(10, 2) -- Utilisation de DECIMAL pour les prix, ajustez selon vos besoins
);
