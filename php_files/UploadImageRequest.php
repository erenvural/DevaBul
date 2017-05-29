 <?php
  if($_SERVER['REQUEST_METHOD']=='POST'){
  $image = $_POST['image'];
  $username = $_POST['username'];
  require_once('dbConnect.php');
  $sql = "DELETE FROM `id1529553_a7751875_test`.`images` WHERE `images`.`username`=''";
  mysqli_query($con,$sql);
  $sql = "DELETE FROM `id1529553_a7751875_test`.`images` WHERE `images`.`username`=' '";
  mysqli_query($con,$sql);
  $sql = "DELETE FROM `id1529553_a7751875_test`.`images` WHERE `images`.`username`=NULL";
  mysqli_query($con,$sql);
  $sql = "DELETE FROM `id1529553_a7751875_test`.`images` WHERE `images`.`username`='$username'";
  mysqli_query($con,$sql);
  $sql ="SELECT id FROM volleyupload ORDER BY id ASC";
  $res = mysqli_query($con,$sql);
  $id = 0;
  while($row = mysqli_fetch_array($res)){
  $id = $row['id'];
  }
  $path = "uploads/$username$id.jpg";
  $actualpath = "ftp://a7751875:e1r2e3n4@31.170.160.95/$path";
      $sql = "INSERT INTO `a7751875_test`.`images` (username, image_url) VALUES ('$username','$actualpath')";
  if(mysqli_query($con,$sql)){
  file_put_contents($path,base64_decode($image));
  echo "Successfully Uploaded";
  }
  mysqli_close($con);
  }else{
  echo "Error";
  }





