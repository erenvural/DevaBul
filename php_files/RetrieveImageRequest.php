<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT id, username, image_url FROM images";
        $response = array();
        $response["images"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["id"] = $row["id"];
               $tmp["username"] = $row["username"];
               $tmp["image_url"] = $row["image_url"];
               array_push($response["images"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>