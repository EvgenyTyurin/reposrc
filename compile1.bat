cd %1
mkdir src\main\java
unzip -n -d src\main\java *-sources.jar 
call mvn -f *.pom -Dhttps.protocols=TLSv1.2 -DskipTests -Dmaven.javadoc.skip=true install
del /q "src\*"
FOR /D %%p IN ("src\*.*") DO rmdir "%%p" /s /q
rmdir src
del /q "target\*"
FOR /D %%p IN ("target\*.*") DO rmdir "%%p" /s /q
rmdir target