@echo off
echo ==== ADB RESET ====
adb kill-server
timeout /t 2 > nul
adb start-server
timeout /t 2 > nul
adb devices
echo ==== DONE ====
pause