<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $username = $_POST["username"];
 $relatedDisease = $_POST["relatedDisease"];
 require_once('dbConnect.php');
 $sql = "UPDATE  `id1529553_a7751875_test`.`users` SET  `relatedDisease` =  '$relatedDisease' WHERE  `users`.`username`='$username'";
 if(mysqli_query($con,$sql)){
 echo "Related Disease Changed";
 }else{
 echo "Could not Changed";
 }
 }else{
echo 'error';
}