@echo off
setlocal
where gradle >nul 2>nul
if %ERRORLEVEL% EQU 0 (
  gradle %*
  exit /b %ERRORLEVEL%
)

echo Gradle bulunamadi. Lutfen Gradle 8.14.3+ kurup PATH'e ekleyin.
exit /b 1
