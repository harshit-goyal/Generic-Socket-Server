cd classes
rd /s/q com
cd..
cd src
javac -d ..\classes -classpath ..\lib\*;. com\thinking\machines\chat\dl\*.java
cd..
