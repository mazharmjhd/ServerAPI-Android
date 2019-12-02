<?php

$servername = "localhost";
$user = "sofyanla_budiluhur";
$pass = "Lolipop0)";
$dbname = "sofyanla_android";

// Create connection
$conn = new mysqli($servername, $user, $pass, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$username = $_POST['username'];
$password = $_POST['password'];

$data_users = mysqli_query($conn, "SELECT role FROM users where username = '$username' and password = '$password'");

$row = mysqli_fetch_array($data_users, MYSQLI_NUM);
$data = $row[0];
if ($data){
    echo $data;
}

$conn->close();
?>