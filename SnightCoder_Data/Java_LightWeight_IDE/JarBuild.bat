@echo off

set /p snight_project_path=<%UserProfile%\SnightCoder_Data\Java_LightWeight_IDE\ProjectPath.txt
set /p snight_project_mainclass_name=<%snight_project_path%\MainClass.txt

cd /d %snight_project_path%/
cd DataFile/Class
jar cvfe ../Jar/Output.jar %snight_project_mainclass_name% *.class

start ..\Jar\