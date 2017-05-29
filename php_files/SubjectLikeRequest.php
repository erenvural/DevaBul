<?php
  if($_SERVER['REQUEST_METHOD']=='POST'){
  $likeStatus = $_POST["likeStatus"];
  $subjectTitle = $_POST["subjectTitle"];
  $username = $_POST["username"];
  require_once('dbConnect.php');
  if($likeStatus=='Follow'){
  $sql="INSERT INTO  `id1529553_a7751875_test`.`subject_connections` (`subjectTitle` ,`username`)VALUES ('$subjectTitle',  '$username')";
  mysqli_query($con,$sql);
  }else{
  $sql="DELETE FROM `id1529553_a7751875_test`.`subject_connections` WHERE `subject_connections`.`username`='$username'";
  mysqli_query($con,$sql);
  }
  }else{
 echo 'error';
  }