<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT followingUserName, followedUserName FROM user_connections";
        $response = array();
        $response["user_connections"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["followingUserName"] = $row["followingUserName"];
               $tmp["followedUserName"] = $row["followedUserName"];
               array_push($response["user_connections"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>