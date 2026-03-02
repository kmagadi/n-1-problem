@echo off
REM Spring Training Application - Quick Start Script
REM This script builds and runs the Spring Boot application

echo.
echo ========================================================
echo    Spring Training Application - Quick Start
echo ========================================================
echo.

REM Navigate to project directory
cd /d "%~dp0"

echo [1/3] Cleaning previous builds...
call mvnw.cmd clean -q
if errorlevel 1 (
    echo Error: Failed to clean project
    exit /b 1
)

echo [2/3] Building the application...
call mvnw.cmd package -q
if errorlevel 1 (
    echo Error: Failed to build project
    exit /b 1
)

echo [3/3] Starting the application...
echo.
echo ========================================================
echo    Application is starting...
echo ========================================================
echo.

call mvnw.cmd spring-boot:run


