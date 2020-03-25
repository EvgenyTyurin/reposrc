mkdir src\main\java
unzip -d src\main\java *-sources.jar 
call mvn -f *.pom clean install
del /q "src\*"
FOR /D %%p IN ("src\*.*") DO rmdir "%%p" /s /q
rmdir src
del /q "target\*"
FOR /D %%p IN ("target\*.*") DO rmdir "%%p" /s /q
rmdir target