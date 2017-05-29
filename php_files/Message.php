<?php
        $conn = mysqli_connect("*", "*", "*", "*");
            $sql = "SELECT id, sender, receiver, messageContent, messageDate FROM messages";
            $response = array();
            $response["messages"] = array();
            $result = $conn->query($sql);
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }

            if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                   $tmp = array();
                   $tmp["id"] = $row["id"];
                   $tmp["sender"] = $row["sender"];
                   $tmp["receiver"] = $row["receiver"];
                   $tmp["messageContent"] = $row["messageContent"];
                   $tmp["messageDate"] = $row["messageDate"];
                   array_push($response["messages"], $tmp);
                }
            } else {
                echo "0 results";
            }
            header('Content-Type: application/json');
            echo json_encode($response);
            $conn->close();
?>