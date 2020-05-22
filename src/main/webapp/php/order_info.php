
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="css/home_page_stye.css">
    <script src="js/home-page.js"></script>
</head>

<body>

    <!-- Nav Bar -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand" href="index.html">
                <img src="assets/Nuance9-Logo.png" alt="Nuance 9 Logo">
            </a>
            <div class="nav-bar-links-container">
                <div class="topnav" id="nav-bar-tabs">
                    <a href="index.html">Apparel</a>
                    <a href="about.html">About</a>
                </div>
            </div>

        </div>
    </nav>

    <div class="container">
        <div class="row">
            <?php
                $mysqli = new mysqli("localhost:8111", "user", "test123", "nuance9");
                $otable = "orders";
                $ctable = "customers";
                $credtable = "creditcards";
                
                $order = $mysqli->query("SELECT * WHERE orderid=MAX(orderid) FROM $otable");
                echo "<p> Order Number: $order['orderid'] </p>";
                echo "<p> Credit Card Number: $order['ccnum']</p>";
                echo "<p> Product ID: $order['pid']</p>";
                echo "<p> Quantity: $order['quantity']</p>";
                echo "<p> Order Date: $order['order_date']</p>";
                $customer = $mysqli->query("SELECT * WHERE $order['cid']=cid FROM $ctable");
                echo "<p> First Name: $customer['fname']</p>";
                echo "<p> Last Name: $customer['lname']</p>";
                echo "<p> Email: $customer['email']</p>";
                echo "<p> Phone: $customer['phone']</p>";
                echo "<p> Address: $customer['street_address']</p>";
                echo "<p> City: $customer['city']</p>";
                echo "<p> State: $customer['us_state']</p>";
                echo "<p> Zip Code: $customer['zip']</p>";
                ?>
        </div>
    </div>
</body>
</html>
                
            
