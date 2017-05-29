# DevaBul
Deva-Bul is a Android mobile application that patients to exchange information in disease-dedicated forums. The application also will provide map benefits. Users can find their location and addresses of health institutions (Hospitals, Pharmacies etc.) .    
Used Technologies: Java, Gradle, MySQL, PHP, Volley Library, Google APIs(Maps, Places)
#########################################################################################################################################

Changes to do for the right work of the project
_________________________________________________________________________________________________________________________________________
In Php files and other necessary MySQL connections codes you have to write your webhost properties instead of '*'. 
[$conn = mysqli_connect("*", "*", "*", "*")]
For example: $conn = mysqli_connect("HOSTNAME", "USERNAME", "PASSWORD", "DATABASE_NAME"
__________________________________________________________________________________________________________________________________________
In AndroidManifest file;
...
android:name="com.google.android.maps.v2.API_KEY"
android:value="*"/>  You have to write api code take from https://developers.google.com
...
__________________________________________________________________________________________________________________________________________
In Activities;
You have to write path of your php file instead of '*'. [String url ="*/LoginStatusChange.php"]
For example: String url ="www.webhost.com/LoginStatusChange.php"
