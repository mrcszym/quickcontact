Work in progess.




Troubleshooting:

Windows:

"Web server failed to start. Port xxx was already in use."

    netstat -ano | findstr :xxx
    taskkill /PID <PID nr> /F

