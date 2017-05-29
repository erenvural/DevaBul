<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$followingUserName = $_POST['followingUserName'];
		$followedUserName = $_POST['followedUserName'];
        require_once('dbConnect.php');
        $sql = "INSERT INTO `id1529553_a7751875_test`.`user_connections` (`followingUserName`,`followedUserName`) VALUES ('$followingUserName','$followedUserName')";
		if(mysqli_query($con,$sql)){
			echo "Successfully Added";
		}else{
			echo "Could not added";
		}
	}else{
echo 'error';
}