<?php
    require_once "pdo.php";

    $sql = "CREATE DATABASE IF NOT EXISTS Nuance9";
    $pdo -> exec($sql);
    echo "Nuance9 database created";

    $sql = "CREATE TABLE IF NOT EXISTS customers(
        cid INT(5) AUTO_INCREMENT,
        fname VARCHAR(30) NOT NULL,
        lname VARCHAR(30) NOT NULL,
        email VARCHAR(50) NOT NULL,
        phone VARCHAR(10) NOT NULL,
        street_address VARCHAR(50) NOT NULL,
        city VARCHAR(30) NOT NULL,
        us_state VARCHAR(30) NOT NULL,
        zip VARCHAR(30) NOT NULL,
        PRIMARY KEY (cid)
    )";
    $pdo->exec($sql);


    $sql = "CREATE TABLE IF NOT EXISTS creditcards(
        cid INT(5) NOT NULL,
        ccnum VARCHAR(16) NOT NULL,
        cvv VARCHAR(4) NOT NULL,
        expiration VARCHAR(5) NOT NULL,
        FOREIGN KEY (cid) REFERENCES customers(cid)
    )";
    $pdo->exec($sql);

     $sql = "CREATE TABLE IF NOT EXISTS products(
        pid VARCHAR(5) PRIMARY KEY,
        pname VARCHAR(50) NOT NULL,
        pprice FLOAT(10) NOT NULL
    )";
    $pdo->exec($sql);

    $sql = "CREATE TABLE IF NOT EXISTS orders(
        orderid INT(5) AUTO_INCREMENT PRIMARY KEY,
        cid INT(5) NOT NULL,
        ccnum VARCHAR(16) NOT NULL,
        pid VARCHAR(14) NOT NULL,
        quantity INT(2) NOT NULL,
        order_date DATETIME NOT NULL,
        total FLOAT(30) NOT NULL,
        FOREIGN KEY (cid) REFERENCES customers(cid),
        FOREIGN KEY (pid) REFERENCES products(pid)
    )";
    $pdo->exec($sql);

   

   $sql = "INSERT INTO products (pid, pname, pprice) VALUES
        ('1A','Amalgam Fleece+Snyth','174.99'),
        ('2R','Retro Walkers','89.99'),
        ('3D','Deathlyhallow 3','49.99'),
        ('4F','Featuring Teddy Roosevelt', '19.99'),
        ('5G','Graphic Design Is My Passion','74.99'),
        ('6P','P.A.N.T.S','79.99'),
        ('7S','Subtle Arachnid','39.99'),
        ('8T','Tactical Cubeta','29.99'),
        ('9T','The Vest','54.99'),
        ('10S','Samura','59.99'),
        ('11D','Drifter','64.99'),
        ('12G','Godzilla','24.99')";
    $pdo->exec($sql);
?>