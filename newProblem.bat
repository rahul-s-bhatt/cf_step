@echo off
if "%1"=="" (
  echo Usage: newProblem.bat ProblemName
  echo Example: newProblem.bat 189A-CutRibbon
  pause
  exit /b 1
)

set P=%1
set TARGET=problems\%P%

:: create problem folder structure
mkdir "%TARGET%\src" 2>nul

:: copy template Main.java
copy ".\templates\Main.java" "%TARGET%\src\Main.java" >nul

:: create blank input/output files
type nul > "%TARGET%\input.txt"
type nul > "%TARGET%\output.txt"

:: create run.bat inside problem folder (compile + run + pause)
(
  echo @echo off
  echo cd /d "%%~dp0"
  echo if not exist out mkdir out
  echo javac -d out src\*.java
  echo if errorlevel 1 echo Compilation failed ^&^& pause ^&^& exit /b 1
  echo java -cp out Main ^< input.txt
  echo.
  echo echo Press any key to close...
  echo pause
) > "%TARGET%\run.bat"

echo Created problem folder: %TARGET%
echo - src\Main.java (from template)
echo - input.txt and output.txt created
echo - run.bat created (double-click to build+run)
