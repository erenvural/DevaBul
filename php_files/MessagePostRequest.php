<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$sender = $_POST['sender'];
        $receiver = $_POST['receiver'];
        $messageContent = $_POST['messageContent'];
        $messageDate = $_POST['messageDate'];
		require_once('dbConnect.php');
        $sql = "INSERT INTO `id1529553_a7751875_test`.`messages` (`sender`, `receiver`, `messageContent`,`messageDate`) VALUES ('$sender','$receiver','$messageContent','$messageDate')";
		if(mysqli_query($con,$sql)){
			echo "Successfully Added";
		}else{
			echo "Could not added";
		}
	}else{
echo 'error';
}