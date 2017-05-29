<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $username = $_POST["username"];
 $profile_status = $_POST["profile_status"];
 require_once('dbConnect.php');
 if ($profile_status=="Normal") {
         $sql = "UPDATE  `id1529553_a7751875_test`.`users` SET  `profile_status` =  'Hidden' WHERE  `users`.`username`='$username'";
      }else {
         $sql = "UPDATE  `id1529553_a7751875_test`.`users` SET  `profile_status` =  'Normal' WHERE  `users`.`username`='$username'";
 }
 if(mysqli_query($con,$sql)){
 echo "Status Changed";
 }else{
 echo "Could not Changed";
 }
 }else{
echo 'error';
}