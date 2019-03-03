cd classes
rmdir /s/q com
cd ..\src
javac -d ..\classes -classpath ..\..\..\classes;..\lib\*;. com\thinking\machines\inventory\*.java
cd..
