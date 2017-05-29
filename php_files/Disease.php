<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT id, diseaseName, diseaseType, diseaseDescription FROM diseases";
        $response = array();
        $response["diseases"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["id"] = $row["id"];
               $tmp["diseaseName"] = $row["diseaseName"];
               $tmp["diseaseType"] = $row["diseaseType"];
               $tmp["diseaseDescription"] = $row["diseaseDescription"];
               array_push($response["diseases"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>