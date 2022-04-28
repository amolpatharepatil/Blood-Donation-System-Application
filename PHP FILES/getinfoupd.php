 <?php  
 //  
 function get_data()  
 {  
      $connect = mysqli_connect("localhost","id17444673_admin123","Ani@12345678","id17444673_brs01");
$uid=$_POST["uid"];      
$query = "SELECT * FROM users WHERE uid='$uid'; ";
      $result = mysqli_query($connect, $query); 

      $my_data = array();  
      while($row = mysqli_fetch_array($result))  
      {  
           $my_data[] = array(  
		'uid'          =>     $row["uid"],
                'fname'          =>     $row["fname"], 
		'lname'          =>     $row["lname"],
		 'phno'          =>     $row["phno"],
		'emailid'          =>     $row["emailid"],
		'password'          =>     $row["password"],	
                'bloodgroup'          =>     $row["bloodgroup"], 
		'gender'          =>     $row["gender"],
		'state'          =>     $row["state"],			
		'district'          =>     $row["district"],
		'city'          =>     $row["city"],
		'pincode'          =>     $row["pincode"],
		 
		
		  
                
           );  
      }  
      return json_encode($my_data);  
 }  
 $contents = get_data();  
   
      echo $contents;  
 

 ?>