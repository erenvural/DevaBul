<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT id, profile_status, username, email, birthday, gender, relatedDisease FROM users";
        $response = array();
        $response["users"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["id"] = $row["id"];
               $tmp["profile_status"] = $row["profile_status"];
               $tmp["username"] = $row["username"];
               $tmp["email"] = $row["email"];
               $tmp["birthday"] = $row["birthday"];
               $tmp["gender"] = $row["gender"];
               $tmp["relatedDisease"] = $row["relatedDisease"];
               array_push($response["users"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>