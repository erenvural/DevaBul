<?php
  if($_SERVER['REQUEST_METHOD']=='POST'){
  $commentId= $_POST["commentId"];
  $commentVoter = $_POST["commentVoter"];
  require_once('dbConnect.php');
  $sql = "SELECT commentVoter FROM `id1529553_a7751875_test`.`comment_connections` WHERE`comment_connections`.`commentId`='$commentId' AND `comment_connections`.`commentVoter`='$commentVoter'";

  if($sql == NULL):
      $sql = "INSERT INTO `id1529553_a7751875_test`.`comment_connections` (`commentId` ,`commentVoter`) VALUES('$commentId', '$commentVoter')";
      mysqli_query($con,$sql);
   else:
     $sql = "DELETE FROM `id1529553_a7751875_test`.`comment_connections` WHERE `comment_connections`.`commentVoter`='$commentVoter'";
     mysqli_query($con,$sql);
  endif;
  $sql = "SELECT * FROM `a7751875_test`.`comment_connections` WHERE `comment_connections`.`commentId`='$commentId'";
  if ($result = mysqli_query($con,$sql)) {
      $row_cnt = $result->num_rows;
      $result->close();
  }
  $totalLike = $row_cnt;
  $sql = "UPDATE `a7751875_test`.`comments` SET  `commentTotalLikes` =  '$totalLike' WHERE  `comments`.`id` = '$commentId'";
  mysqli_query($con,$sql);
  }else{
 echo 'error';
  }