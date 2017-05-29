<?php
        $conn = mysqli_connect("*", "*", "*", "*");
            $sql = "SELECT id, subjectDisease, subjectTitle, subjectContent, subjectOwner, subjectDate FROM subjects";
            $response = array();
            $response["subjects"] = array();
            $result = $conn->query($sql);
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }

            if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                   $tmp = array();
                   $tmp["id"] = $row["id"];
                   $tmp["subjectDisease"] = $row["subjectDisease"];
                   $tmp["subjectTitle"] = $row["subjectTitle"];
                   $tmp["subjectContent"] = $row["subjectContent"];
                   $tmp["subjectOwner"] = $row["subjectOwner"];
                   $tmp["subjectDate"] = $row["subjectDate"];
                   array_push($response["subjects"], $tmp);
                }
            } else {
                echo "0 results";
            }
            header('Content-Type: application/json');
            echo json_encode($response);
            $conn->close();
?>