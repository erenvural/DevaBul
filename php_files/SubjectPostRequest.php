<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$subjectDisease = $_POST['subjectDisease'];
        $subjectTitle = $_POST['subjectTitle'];
        $subjectContent = $_POST['subjectContent'];
        $subjectOwner = $_POST['subjectOwner'];
        $subjectDate = $_POST['subjectDate'];
		require_once('dbConnect.php');
        $sql = "INSERT INTO `id1529553_a7751875_test`.`subjects` (`subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES ('$subjectDisease','$subjectTitle','$subjectContent','$subjectOwner', '$subjectDate')";
		if(mysqli_query($con,$sql)){
			echo "Successfully Added";
			$sql="INSERT INTO  `id1529553_a7751875_test`.`subject_connections` (`subjectTitle` ,`username`)VALUES ('$subjectTitle',  '$subjectOwner')";
            mysqli_query($con,$sql);
		}else{
			echo "Could not added";
		}
	}else{
echo 'error';
}