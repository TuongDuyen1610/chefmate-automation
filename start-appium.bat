@echo off
cd /d %~dp0

echo ==== START APPIUM BACKGROUND ====

adb start-server

start "" /min cmd /c appium ^
--address 127.0.0.1 ^
--port 4723 ^
--base-path /wd/hub ^
--session-override ^
--log-level error

exit