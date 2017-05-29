<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$followingUserName = $_POST['followingUserName'];
		$followedUserName = $_POST['followedUserName'];
        require_once('dbConnect.php');
        $sql = "DELETE FROM `id1529553_a7751875_test`.`user_connections` WHERE `user_connections`.`followingUserName`='$followingUserName' AND `user_connections`.`followedUserName`='$followedUserName'";
		if(mysqli_query($con,$sql)){
			echo "Successfully Deleted";
		}else{
			echo "Could not deleted";
		}
	}else{
echo 'error';
}