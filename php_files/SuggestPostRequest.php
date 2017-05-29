<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$suggestName= $_POST['suggestName'];
        $suggestType = $_POST['suggestType'];
        $suggestDescription = $_POST['suggestDescription'];
        $suggestOwner = $_POST['suggestOwner'];
        $suggestDate= $_POST['suggestDate'];
		require_once('dbConnect.php');
        $sql = "INSERT INTO `id1529553_a7751875_test`.`suggestions` (`suggestName`, `suggestType`, `suggestDescription`,`suggestOwner`,`suggestDate`,`suggestConfirm`) VALUES ('$suggestName','$suggestType','$suggestDescription','$suggestOwner','$suggestDate','0')";
		if(mysqli_query($con,$sql)){
			echo "Successfully Added";
		}else{
			echo "Could not added";
		}
	}else{
echo 'error';
}