<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT id, commentSubject, commentOwner, commentContent, commentTotalLikes, commentDate FROM comments";
        $response = array();
        $response["comments"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["id"] = $row["id"];
               $tmp["commentSubject"] = $row["commentSubject"];
               $tmp["commentOwner"] = $row["commentOwner"];
               $tmp["commentContent"] = $row["commentContent"];
               $tmp["commentTotalLikes"] = $row["commentTotalLikes"];
               $tmp["commentDate"] = $row["commentDate"];
               array_push($response["comments"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>