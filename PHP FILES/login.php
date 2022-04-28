<?php 
		$con=mysqli_connect("localhost","id17444673_admin123","Ani@12345678","id17444673_brs01");
	
		$username = $_POST["username"];
		$password = $_POST["password"];

		$sql = "SELECT * FROM users WHERE uid = '$username' AND password = '$password'";
		$result = mysqli_query($con,$sql);
		
		if($result->num_rows > 0){
			echo "logged in successfully" ;
		}else{
  			 echo "user not found";
}
?>