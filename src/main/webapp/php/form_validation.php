<?php
    require_once "pdo.php";
    $fnameError = $lnameError = $emailError = $pidError = $quantityError = $phoneError = $addressError = $stateError = $ccnumError = $cvvError = $expirationError = "";
    $fname = $lname= $email = $pid = $quantity= $phone = $address1 = $address2 = $state = $zip = $city = $shipping = $ccnum = $cvv = $expiration = "";

    //if ajax request is a post request
    if(isset($_POST["submit"])){
        //data is successfully added to database, redirect to confirmation page
        if(!validate_data()){

            //add customer data
            $sql = "INSERT INTO customers (fname, lname, email, phone, street_address, city, us_state, zip)
            VALUES (:fname, :lname, :email, :phone, :street_address, :city, :us_state, :zip)";
            $stmt = $pdo->prepare($sql);
            $stmt->execute(array(':fname'=>$fname, ':lname'=>$lname, ':email'=>$email, ':phone'=>$phone, ':street_address'=>$address1, ':city'=>$city, ':us_state'=>$state, ':zip'=>$zip));


            // //add credit card
            $cid = $pdo->lastInsertId();
            $sql = "INSERT INTO creditcards (cid, ccnum, cvv, expiration) VALUES (:cid, :ccnum, :cvv, :expiration)";
            $stmt = $pdo->prepare($sql);
            $stmt->execute(array(':cid'=>$cid, ':ccnum'=>$ccnum, ':cvv'=>$cvv, ':expiration'=>$expiration));

           

            $shipping_rate = 0.00;
            if($shipping == "overnight"){
                $shipping_rate = 14.99;
            }
            else if($shipping == "two-day"){
                $shipping_rate = 10.99;
            }
            else{
                $shipping_rate = 4.99;
            }

            $total = 0.00;

            $stmt = $pdo->prepare('SELECT pprice FROM products WHERE pid = :ID');
            $stmt->execute(array(':ID' => $pid));
            $row = $stmt->fetch(PDO::FETCH_ASSOC);
            $total = $row['pprice']*$quantity+$shipping_rate;
            echo "<p class = 'total'>Order Total: $total</p>";


            //add order
            $sql = "INSERT INTO orders (cid, ccnum, pid, quantity, order_date, total)
                    VALUES (:cid, :ccnum, :pid, :quantity, NOW(), :total)";
            $stmt = $pdo->prepare($sql);
            $stmt->execute(array(':cid' => $cid, ':ccnum' => $ccnum, ':pid' => $pid, ':quantity' => $quantity, ':total'=> $total));

            $pdo = null;


        }
        //there is bad data, errors will
       
    

    }

    //validate the data 
    function validate_data(){
        $bad_data = False;

        $GLOBALS['shipping'] = format_data($_POST["shipping"]);

        $GLOBALS['pid']= format_data($_POST["pid"]);
        if(empty($GLOBALS['pid'])){
            $bad_data = True;
            echo "<p class = 'error'>Bad data for product id</p>";
        }
        else{
            $stmt = $GLOBALS['pdo']->prepare('SELECT * FROM products WHERE pid = :ID');
            $stmt->execute(array('ID' => $GLOBALS['pid']));
            $row = $stmt->fetch(PDO::FETCH_ASSOC);
            if(!$row){
                $bad_data = True;
                echo "<p class = 'error'>Bad data for product id</p>";
            }
        }
       


        $GLOBALS['quantity'] = format_data($_POST["quantity"]);
        if(empty($GLOBALS['quantity']) or !is_numeric($GLOBALS['quantity'])){
            $bad_data = True;
            echo "<p class = 'error'>Bad data for quantity</p>";
        }

        //  //check first name data
        $GLOBALS['fname'] = format_data($_POST["fname"]);
        if (empty($GLOBALS['fname']) or !preg_match("/^[A-Za-z]+$/", $GLOBALS['fname'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for first name</p>";
        }

        // //check last name data
        $GLOBALS['lname'] = format_data($_POST["lname"]);
        if (empty($GLOBALS['lname']) or !preg_match("/^[A-Za-z]+$/", $GLOBALS['lname'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for last name field</p>";

        }

        $GLOBALS['email'] = format_data($_POST["email"]);
        if (!filter_var($GLOBALS['email'], FILTER_SANITIZE_EMAIL)) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for email field</p>";

        }

        $GLOBALS['phone'] = format_data($_POST["phone"]);
        if (empty($GLOBALS['phone']) or !preg_match("/^(\+1\s)?\d{3}-\d{3}-\d{4}$/", $GLOBALS['phone'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for phone field</p>";

        }

        $GLOBALS['address1'] = format_data($_POST["address1"]);
        if (empty($GLOBALS['address1']) or !preg_match("/^\s*\S+(?:\s+\S+){2}$/", $GLOBALS['address1'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for address field</p>";

        }



        $GLOBALS['city'] = format_data($_POST["city"]);
        if (empty($GLOBALS['city'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for city field</p>";
        }

        $GLOBALS['state'] = format_data($_POST["state"]);

        $GLOBALS['zip'] = format_data($_POST["zip"]);
        if (empty($GLOBALS['zip'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for zip field</p>";
        }
        

        $GLOBALS['ccnum'] = format_data($_POST["ccnum"]);
        if (!is_numeric($GLOBALS['ccnum']) or strlen($GLOBALS['ccnum']) != 16) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for credit card number field</p>";

        }

        $GLOBALS['cvv'] = format_data($_POST["cvv"]);
        if (!is_numeric($GLOBALS['cvv']) or strlen($GLOBALS['cvv']) != 3) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for cvv field</p>";

        }

        $GLOBALS['expiration'] = format_data($_POST["expiration"]);
        if (empty($GLOBALS['expiration']) or !preg_match("/^(0[1-9]|10|11|12)\/[0-9]{2}$/", $GLOBALS['expiration'])) {
            $bad_data = True;
            echo "<p class = 'error'>Bad data for expiration field</p>";

        }
        return $bad_data;
    }

    //formulate the data
    function format_data($data){
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

?>