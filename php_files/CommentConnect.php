<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT commentId, commentVoter FROM comment_connections";
        $response = array();
        $response["comment_connections"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["commentId"] = $row["commentId"];
               $tmp["commentVoter"] = $row["commentVoter"];
               array_push($response["comment_connections"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>