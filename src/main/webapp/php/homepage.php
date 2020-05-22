<?php
    // require_once "hpdo.php";

    $sql = "CREATE DATABASE IF NOT EXISTS Nuance9";
    $pdo -> exec($sql);
    echo "Nuance9 database created";

    $sql = "CREATE TABLE IF NOT EXISTS products(
        pid INT(5) AUTO_INCREMENT,
        pname VARCHAR(30) NOT NULL,
        price INT(30) NOT NULL,
        brand VARCHAR(50) NOT NULL,
        homedesc VARCHAR(10) NOT NULL,
        productdesc VARCHAR(50) NOT NULL,
        quantity INT(30) NOT NULL,
        PRIMARY KEY (pid)
    )";
    $pdo->exec($sql);
    echo "customers table created";

    



?>