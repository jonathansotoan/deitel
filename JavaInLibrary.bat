@echo off

set projectPath=%1
set filePath=%2
set fileName=%3
set fileBaseName=%4
set tempFile=%TEMP%\JavaInLibrary-temp.txt

set relativePath=%filePath%
set relativePath=%relativePath:\=/%
set projectPath=%projectPath:\=/%

setlocal enabledelayedexpansion
call set relativePath=%%relativePath:!projectPath!=!!%%

if %relativePath:~0,1% == / (set relativePath=%relativePath:~1%)
if %relativePath:~-1% NEQ / (set relativePath=%relativePath%/)

cd %projectPath%
echo ^> javac %relativePath%%fileName%
javac %relativePath%%fileName% 2> %tempFile%

set compiledClassPath=%relativePath:/=.%

set /p javacErrors= < %tempFile%
for %%? IN (%tempFile%) DO (
	set /A javacErrorsLength=%%~z? - 2
)

if %javacErrorsLength% GTR 0 (
	more %tempFile%
	del %tempFile%
	goto fin
)

echo ^> java %compiledClassPath%%fileBaseName%
java %compiledClassPath%%fileBaseName%

:fin
endlocal
