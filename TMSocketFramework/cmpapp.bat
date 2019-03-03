cls
cd classes
rd /s/q com
cd..
cd src
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\client\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\common\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\common\exceptions\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\common\interfaces\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\factory\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\annotations\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\util\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\util\exceptions\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\model\*.java

javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\server\event\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\server\event\interfaces\*.java
javac -d ..\classes -classpath ..\libs\*;. com\thinking\machines\socket\framework\server\*.java
cd..