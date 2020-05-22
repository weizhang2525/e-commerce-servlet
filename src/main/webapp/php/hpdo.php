<?php

    function getProducts(){

        $products = array();

        $servername = "localhost";
        $username = "test";
        $password = "test1234";
        
        try{
            
            $pdo = new PDO('mysql:host = $servername;dbname=Products', $username, $password);
            $pdo -> setAttribute(PDO:: ATTR_ERRMODE, PDO:: ERRMODE_EXCEPTION);
            echo "connection succesful";

            $sql = "SELECT * FROM `Product-info`";
            $data = $pdo->query($sql);

            
            foreach ($data as $row) {
                $append_array = array("id"=>$row["id"],
                "name"=>$row["name"],
                "price"=>$row["price"]
            
            );

            array_push($products,$append_array);
                
            
            }

            print_r($products);
            


            
        }
        catch(PDOException $e)
        {
            echo $e-> getMessage();
        }

    }
    // getting data



    getProducts();


?>