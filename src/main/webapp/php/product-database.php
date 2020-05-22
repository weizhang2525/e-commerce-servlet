<?php

    // Creates the connection
    try{
        $servername = "localhost";
        $username = "test";
        $password = "test1234";
        $database = "Products";

        $connection = mysqli_connect($servername,$username,$password, $database);

        if(!$connection){
            echo 'COULD NOT CONNECT';
            die('Could not connect:' . mysql_error());
        }

  

    }

    catch(PDOException $e)
    {
        echo $e-> getMessage();
    }
    

    // Checks to see if the database exists

    $exists = mysqli_query($connection, "select 1 from prodTest");

    if ($exists!==FALSE){
        echo "";
    }

    //If does not exists, 1. Creates the database 2.Adds all the products
    else{


        //Creates the database
        $sql = "CREATE TABLE IF NOT EXISTS prodTest(
            pid INT(12) AUTO_INCREMENT,
            pname VARCHAR(50) NOT NULL,
            price VARCHAR(50) NOT NULL,
            descr VARCHAR(250) NOT NULL,
            quantity INT(50) NOT NULL,
            srcOne VARCHAR(250) NOT NULL,
            srcTwo VARCHAR(250) NOT NULL,
            srcThree VARCHAR(250) NOT NULL,
            alt VARCHAR(30) NOT NULL,
            PRIMARY KEY (pid)
        )";

        if(mysqli_query($connection,$sql)){
            echo "The database has been created";
        
        }

        else{
            echo "Error in creating table";
        }

        mysqli_error($connection);
        
        
        
        
        //Inserts the information into the database
        
        $sql = "INSERT INTO prodTest(`pid`, `pname`, `price`, `descr`, `quantity`, `srcOne`, `srcTwo`, `srcThree`, `alt`) VALUES
            ('1','Amalgam Fleece+Snyth','$174.99', 'Warm multicolor fleece. The different colors represent the famous Nuance brand. Wash on cold. 40% cotton' ,'50','assets/Product-1-v0.jpg', 'assets/Product-1-v1.jpg','assets/Product-1-v2.jpg', 'clothes'),
            ('2','Retro Walkers','$89.99','Multi color runners. Designed in Italy, sold on Nuance 9. Shoe runs true to size','34','assets/Product-2-v0.jpg','assets/Product-2-v1.jpg','assets/Product-2-v2.jpg','clothes'),
            ('3','Deathlyhallow 3','$49.99','Cargo camo pants. The material is imported from South Africa. Wash on cool since it is 34% Polyester','72','assets/Product-3-v0.jpg','assets/Product-3-v1.jpg','assets/Product-3-v2.jpg','clothes'),
            ('4','Featuring Teddy Roosevelt','$19.99','Bear hat is a one size fits all hat. The material is safe for all types of washes','45','assets/Product-4-v0.jpg','assets/Product-4-v1.jpg','assets/Product-4-v2.jpg','clothes'),
            ('5','Graphic Design Is My Passion','$74.99','This multicolor shoe is designed by the Great Robert. It infuses different cultural influences from all around the world. Fits true to size.','45','assets/Product-5-v0.jpg','assets/Product-5-v1.jpg','assets/Product-5-v2.jpg','clothes'),
            ('6','P.A.N.T.S','$79.99','Multi patch is made of different colored patches. Size runs true to size, and is 50% cotten.','43','assets/Product-6-v0.png','assets/Product-6-v1.png','assets/Product-6-v2.jpg','clothes'),
            ('7','Subtle Arachnid','$39.99','Metal chain of a collection of rings and cubain chains. Can be cleaned easily and safe for water wear.','34','assets/Product-7-v0.jpg','assets/Product-7-v1.jpg','assets/Product-7-v2.jpg','clothes'),
            ('8','Tactical Cubeta','$29.99','Black bucket hat with long string. Flexiable and heat resistant. Material is 50% cotton and is machine wash safe.','43','assets/Product-8-v0.jpg','assets/Product-8-v1.jpg','assets/Product-8-v2.jpg','clothes'),
            ('9','The Vest','$54.99','Black wrap around best. 5 deep pockets making it easy to carry things, and easy to wash.','34','assets/Product-9-v0.jpg','assets/Product-9-v1.jpg','assets/Product-9-v2.jpg','clothes'),
            ('10','Samura','$59.99','Black/Red jacket. Created from influences of several animaes. Clean on cold and is washer safe.','34','assets/Product-10-v0.jpg','assets/Product-10-v1.jpg','assets/Product-10-v2.jpg','clothes'),
            ('11','Drifter','$64.99','Black/White/Red shoes. Material is everything on this shoe. Shoe is true to size.','42','assets/Product-11-v0.jpg','assets/Product-11-v1.jpg','assets/Product-11-v2.jpg','clothes'),
            ('12','Godzilla','$24.99','Black/Red/White short sleeve t shirt with race car design. 20% cotton and easy to wash.','34','assets/Product-12-v0.jpg','assets/Product-12-v1.jpg','assets/Product-12-v2.jpg','clothes')";

       


        //Checks to see if the information was properly inserted
        if(mysqli_query($connection, $sql)){
            echo "Records have been inserted successfully";
        }

        //Error checker. This thing saved my life. 
        else{
            echo "ERROR: Could not insert $sql.".
            mysqli_error($connection);
        }



        //Close connection
        mysqli_close($connection);

    }


?>