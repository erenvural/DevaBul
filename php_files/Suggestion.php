<?php
        $conn = mysqli_connect("*", "*", "*", "*");
        $sql = "SELECT id, suggestName, suggestType, suggestDescription, suggestOwner, suggestDate, suggestConfirm FROM disease_suggestions";
        $response = array();
        $response["suggestions"] = array();
        $result = $conn->query($sql);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
               $tmp = array();
               $tmp["id"] = $row["id"];
               $tmp["suggestName"] = $row["suggestName"];
               $tmp["suggestType"] = $row["suggestType"];
               $tmp["suggestDescription"] = $row["suggestDescription"];
               $tmp["suggestOwner"] = $row["suggestOwner"];
               $tmp["suggestDate"] = $row["suggestDate"];
               $tmp["suggestConfirm"] = $row["suggestConfirm"];
               array_push($response["suggestions"], $tmp);
            }
        } else {
            echo "0 results";
        }
        header('Content-Type: application/json');
        echo json_encode($response);
        $conn->close();
?>