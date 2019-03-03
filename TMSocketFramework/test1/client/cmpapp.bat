cd classes
rmdir /s/q com
cd..
cd src
javac -d ..\classes -classpath ..\lib\*;..\..\..\classes;. com\thinking\machines\inventory\dl\interfaces\*.java
javac -d ..\classes -classpath ..\lib\*;..\..\..\classes;. com\thinking\machines\inventory\dl\exception\*.java
javac -d ..\classes -classpath ..\lib\*;..\..\..\classes;. com\thinking\machines\inventory\dl\*.java
cd..
