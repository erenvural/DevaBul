<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT subjectTitle, username FROM subject_connections";
        $response = array();
        $response["subject_connections"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["subjectTitle"] = $row["subjectTitle"];
               $tmp["username"] = $row["username"];
               array_push($response["subject_connections"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>