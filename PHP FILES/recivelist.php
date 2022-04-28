 <?php  
 //  
 function get_data()  
 {  
      $connect = mysqli_connect("localhost","id17444673_admin123","Ani@12345678","id17444673_brs01");
$mybg="";  
      $mybg = $_POST["mybg"];
$obg = $_POST["obg"];
$myuid = $_POST["myuid"];


$query="";
if(strcmp($mybg,'O+')!=0 or strcmp($mybg,'O+')!=0)
{
if(strcmp($mybg,'A+')==0)
{
$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='AB+' ) AND status='pending' AND ruid<>'$myuid'";
}
else if(strcmp($mybg,'A-')==0)
{
$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='AB-' ) AND status='pending' AND ruid<>'$myuid'";
}
else if(strcmp($mybg,'B+')==0)
{
$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='AB+' ) AND status='pending' AND ruid<>'$myuid'";
}
else if(strcmp($mybg,'B-')==0)
{
$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='AB-' ) AND status='pending' AND ruid<>'$myuid'";
}
else
{



$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE rbg='$mybg'  AND status='pending' AND ruid<>'$myuid'";


}
}
else
{
//if blood is O
if(strcmp($mybg,'O+')==0)
{

$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='A+' OR rbg='AB+' OR rbg='B+') AND status='pending' AND ruid<>'$myuid'";
}
else if(strcmp($mybg,'O-')==0)
{
$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='A-' OR rbg='AB-' OR rbg='B-') AND status='pending' AND ruid<>'$myuid'";
}
else
{

$query="SELECT srno,ruid,rname,rbg FROM tasks WHERE (rbg='$mybg' OR rbg='A-' OR rbg='AB-' OR rbg='B-') AND status='pending' AND ruid<>'$myuid'";

}

}

      $result = mysqli_query($connect, $query); 

      $task_data = array();  
      while($row = mysqli_fetch_array($result))  
      {  
           $task_data[] = array(  
                 'srno'          =>     (string)$row["srno"], 
		'ruid'          =>     $row["ruid"], 
                'rname'          =>     $row["rname"],  
		'rbg'          =>     $row["rbg"],  
		  
                
           );  
      }  
      return json_encode($task_data);  
 }  
 $contents = @get_data();  
   
      echo $contents;  
 

 ?>