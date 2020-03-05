Set oShell = CreateObject ("Wscript.Shell")
Dim strArgs
strHomeFolder = oShell.ExpandEnvironmentStrings("%USERPROFILE%")
strArgs = "cmd /c "&strHomeFolder &"\SnightCoder_Data\Java_LightWeight_IDE\CreateDataFile.bat"
oShell.Run strArgs,0, false

