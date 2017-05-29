<?php
        $conn = mysqli_connect("*", "*", "*", "*");

    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM users WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $profile_status, $username, $email, $password, $birthday, $gender, $relatedDisease);

    $response = array();
    $response["success"] = true;

    while(mysqli_stmt_fetch($statement)){

        $response["profile_status"]= $profile_status;
        $response["username"] = $username;
        $response["email"] = $email;
        $response["password"] = $password;
        $response["birthday"] = $birthday;
        $response["gender"] = $gender;
        $response["relatedDisease"] = $relatedDisease;
    }

    echo json_encode($response);
    mysqli_stmt_close($statement);

	mysqli_close($con);
?>