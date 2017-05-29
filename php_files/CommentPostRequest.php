<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$commentSubject = $_POST['commentSubject'];
        $commentOwner = $_POST['commentOwner'];
        $commentContent = $_POST['commentContent'];
        $commentDate = $_POST['commentDate'];

        require_once('dbConnect.php');
        $sql = "INSERT INTO `id1529553_a7751875_test`.`comments` (`commentSubject`, `commentOwner`, `commentContent`,`commentTotalLikes`, `commentDate`) VALUES ('$commentSubject','$commentOwner','$commentContent', '0', '$commentDate')";

		if(mysqli_query($con,$sql)){
			echo "Successfully Added";
		}else{
			echo "Could not added";
		}
	}else{
echo 'error';
}