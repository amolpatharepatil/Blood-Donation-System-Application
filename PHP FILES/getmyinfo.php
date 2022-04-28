 <?php  
 //  
 function get_data()  
 {  
      $connect = mysqli_connect("localhost","id17444673_admin123","Ani@12345678","id17444673_brs01");  
      $query = $_POST["query"];
      $result = mysqli_query($connect, $query); 

      $my_data = array();  
      while($row = mysqli_fetch_array($result))  
      {  
           $my_data[] = array(  
                'fname'          =>     $row["fname"], 
		'lname'          =>     $row["lname"], 
                'bloodgroup'          =>     $row["bloodgroup"],  
		
		  
                
           );  
      }  
      return json_encode($my_data);  
 }  
 $contents = get_data();  
   
      echo $contents;  
 

 ?>