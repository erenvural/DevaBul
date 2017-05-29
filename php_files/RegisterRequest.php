<?php
        $conn = mysqli_connect("*", "*", "*", "*");
    
    $username = $_POST["username"];
    $email = $_POST["email"];
  	$password = $_POST["password"];
      $birthday = $_POST["birthday"];
      $gender = $_POST["gender"];

      $statement = mysqli_prepare($con, "INSERT INTO users (username, email, password, birthday, gender) VALUES (?, ?, ?, ?, ?)");
      mysqli_stmt_bind_param($statement, "sssss", $username, $email, $password, $birthday, $gender);
      mysqli_stmt_execute($statement);

      $response = array();
      $response["success"] = true;

      echo json_encode($response);
  ?>