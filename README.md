<h1>🔐 Secure Authentication Module (Java CLI)</h1>

A simple two-step authentication system built in Java, simulating an OS-level login process using the command line interface (CLI).

This project demonstrates core concepts of authentication, validation, and security mechanisms like account lockout after multiple failed attempts.

#version 1 update
📌 Features
✅ Username & Password authentication
🔢 Second-layer PIN verification (2FA-like system)
🔁 Maximum 3 login attempts
🔒 Automatic account lockout after failed attempts
📜 Console-based logging system
🎬 Simulated system behavior (loading effects, delays)
💻 Clean CLI interface with OS-style UI
🧠 How It Works

The authentication process follows this flow:

Program starts → Displays system banner
User enters:
Username
Password
If credentials are correct → Move to PIN verification
If PIN is correct → ✅ Access Granted
If incorrect:
Attempts increase
After 3 failures → 🔒 System Lockout
🔑 Default Credentials

⚠️ Hardcoded for demo purposes (can be modified in code)

Username: palak
Password: palak@123
PIN:      2807
🛠️ Technologies Used
Java (Core Java)
CLI (Command Line Interface)
OOP Concepts
Basic Threading (for delay simulation)
📂 Project Structure
Main.java

All logic is implemented in a single file:

Authentication flow
Input handling
Logging
Security checks

Source file:

▶️ How to Run
1. Compile
javac Main.java
2. Run
java Main
📸 Sample Output
SECURE OS AUTHENTICATION MODULE
[SYSTEM] Boot sequence complete.

Enter Username:
Enter Password:

[AUTH] Verifying credentials...
[AUTH] Credentials accepted.

STEP 2 — PIN VERIFICATION
Enter PIN:

✔ ACCESS GRANTED
Welcome, PALAK!
🚀 Future Improvements
🔐 Replace hardcoded credentials with a database
🧾 File-based logging system
🌐 GUI version (JavaFX / Swing)
🔑 Password hashing for better security
📱 OTP-based authentication
⚠️ Limitations
Credentials are stored in plain text
No encryption or real security layer
Designed for educational/demo purposes only
💡 Use Case

Perfect for:

OS / Java mini projects
Authentication system demos
Learning basic security logic
