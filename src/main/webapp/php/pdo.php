<?php
    try{
        $servername = "localhost";
        $username = "user";
        $password = "test123";
        $pdo = new PDO('mysql:host = $servername; dbname=Nuance9', $username, $password);
        $pdo -> setAttribute(PDO:: ATTR_ERRMODE, PDO:: ERRMODE_EXCEPTION);
    }
    catch(PDOException $e)
    {
        echo $e-> getMessage();
    }
?>