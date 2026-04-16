import java.util.Scanner;

/**
 * ============================================================
 *   SECURE AUTHENTICATION MODULE FOR OPERATING SYSTEMS
 *   A two-step CLI-based login system simulation
 *   Subject: Operating Systems | College Demo Project
 * ============================================================
 */
public class Main {

    // ─────────────────────────────────────────────
    // HARDCODED CREDENTIALS (can be replaced later
    // with a database or config file)
    // ─────────────────────────────────────────────
    // handle user authentication logic
    
    private static final String VALID_USERNAME = "palak";
    private static final String VALID_PASSWORD = "palak@123";
    private static final String VALID_PIN      = "2807";

    // Maximum number of failed login attempts before lockout
    private static final int MAX_ATTEMPTS = 3;

    // Tracks how many failed attempts have occurred
    private static int failedAttempts = 0;

    // Flag to indicate whether the account is locked
    private static boolean isLocked = false;

    // Scanner for reading user input from the terminal
    private static final Scanner scanner = new Scanner(System.in);

    // ─────────────────────────────────────────────
    // ENTRY POINT
    // ─────────────────────────────────────────────
    public static void main(String[] args) {
        displayBanner();          // Show welcome/system banner
        startLoginProcess();      // Begin the authentication flow
        scanner.close();          // Release scanner resource when done
    }

    // ─────────────────────────────────────────────
    // DISPLAY BANNER
    // Prints a styled OS-like system header to the CLI
    // ─────────────────────────────────────────────
    private static void displayBanner() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║         SECURE OS AUTHENTICATION MODULE      ║");
        System.out.println("║              Version 1.0 | CLI Mode          ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  [SYSTEM]  Boot sequence complete.");
        System.out.println("  [SYSTEM]  Authentication service started.");
        System.out.println("  [SYSTEM]  Please verify your identity to proceed.");
        System.out.println();
    }

    // ─────────────────────────────────────────────
    // START LOGIN PROCESS
    // Main loop: keeps prompting until success,
    // lockout, or user exhausts all attempts
    // ─────────────────────────────────────────────
    private static void startLoginProcess() {
        while (failedAttempts < MAX_ATTEMPTS) {

            // Check if account was locked in a previous iteration
            if (isLocked) {
                displayLockout();
                return;
            }

            System.out.println("──────────────────────────────────────────────");
            System.out.println("  SYSTEM LOGIN  [Attempt " + (failedAttempts + 1) + " of " + MAX_ATTEMPTS + "]");
            System.out.println("──────────────────────────────────────────────");

            // Step 1 – Collect and verify username + password
            String username = prompt("  Enter Username : ");
            String password = prompt("  Enter Password : ");

            log("Login attempt for user: \"" + username + "\"");
            simulate("  [AUTH]  Verifying credentials");

            if (verifyCredentials(username, password)) {
                // Credentials matched → move to Step 2
                System.out.println("  [AUTH]  Credentials accepted.");
                System.out.println();

                // Step 2 – PIN verification
                boolean pinResult = pinVerificationStep();

                if (pinResult) {
                    // Both steps passed → grant access
                    grantAccess(username);
                    return; // Exit the login loop on success
                } else {
                    // PIN failed → deny and count as a failed attempt
                    denyAccess();
                    failedAttempts++;
                }

            } else {
                // Wrong username or password
                failedAttempts++;
                int remaining = MAX_ATTEMPTS - failedAttempts;
                System.out.println("  [AUTH]  Invalid username or password.");
                log("Failed credential attempt #" + failedAttempts);

                if (remaining > 0) {
                    System.out.println("  [WARN]  " + remaining + " attempt(s) remaining.");
                } else {
                    // No attempts left → lock the account
                    isLocked = true;
                    displayLockout();
                    return;
                }
            }

            System.out.println();
        }

        // Reached if loop exits naturally (all attempts exhausted)
        if (!isLocked) {
            isLocked = true;
            displayLockout();
        }
    }

    // ─────────────────────────────────────────────
    // VERIFY CREDENTIALS
    // Checks username AND password together.
    // Returns true only if both match.
    // ─────────────────────────────────────────────
    private static boolean verifyCredentials(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    // ─────────────────────────────────────────────
    // PIN VERIFICATION STEP
    // Called after credentials are accepted.
    // Returns true if PIN is correct, false otherwise.
    // ─────────────────────────────────────────────
    private static boolean pinVerificationStep() {
        System.out.println("──────────────────────────────────────────────");
        System.out.println("  STEP 2 OF 2 — PIN VERIFICATION");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("  [INFO]  A 4-digit PIN is required to proceed.");
        System.out.println();

        String pin = prompt("  Enter PIN     : ");

        simulate("  [AUTH]  Verifying PIN");

        if (VALID_PIN.equals(pin)) {
            return true;
        } else {
            System.out.println("  [AUTH]  Incorrect PIN entered.");
            log("PIN verification failed for user: \"" + VALID_USERNAME + "\"");
            return false;
        }
    }

    // ─────────────────────────────────────────────
    // GRANT ACCESS
    // Displays a success message and simulates
    // loading the OS session for the authenticated user
    // ─────────────────────────────────────────────
    private static void grantAccess(String username) {
        System.out.println();
        simulate("  [AUTH]  Finalizing secure session");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║          ✔  ACCESS GRANTED                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  Welcome, " + username.toUpperCase() + "!");
        System.out.println("  Session started successfully.");
        System.out.println("  [LOG]   Authentication successful. Session active.");
        System.out.println();
        System.out.println("  $ OS SHELL READY — Loading environment...");
        System.out.println("  $ Type 'help' for available commands.");
        System.out.println();
        log("User \"" + username + "\" logged in successfully.");
    }

    // ─────────────────────────────────────────────
    // DENY ACCESS
    // Shown when PIN verification fails
    // ─────────────────────────────────────────────
    private static void denyAccess() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║          ✘  ACCESS DENIED                    ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  [SECURITY]  PIN mismatch. Authentication failed.");
    }

    // ─────────────────────────────────────────────
    // DISPLAY LOCKOUT
    // Triggered after MAX_ATTEMPTS failures.
    // Simulates OS-level account lockout.
    // ─────────────────────────────────────────────
    private static void displayLockout() {
        System.out.println();
        simulate("  [SECURITY]  Engaging lockout protocol");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      ⚠  SYSTEM LOCKED — ACCESS BLOCKED  ⚠   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  [SECURITY]  Maximum login attempts exceeded.");
        System.out.println("  [SECURITY]  Account has been temporarily locked.");
        System.out.println("  [SECURITY]  Contact your system administrator.");
        System.out.println("  [LOG]       Lockout triggered after "
                + failedAttempts + " failed attempt(s).");
        System.out.println();
        log("Account locked after " + failedAttempts + " failed attempts.");
    }

    // ─────────────────────────────────────────────
    // PROMPT
    // Utility: prints a label and reads user input.
    // Trims whitespace to avoid accidental spaces.
    // ─────────────────────────────────────────────
    private static String prompt(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    // ─────────────────────────────────────────────
    // SIMULATE
    // Prints a processing message with animated dots
    // and a short delay to mimic real system behavior
    // ─────────────────────────────────────────────
    private static void simulate(String message) {
        System.out.print(message);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(400); // Wait 400ms between each dot
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted state
            }
            System.out.print(".");
        }
        System.out.println(" Done.");
    }

    // ─────────────────────────────────────────────
    // LOG
    // Simple console-based logger.
    // In a real system, this would write to a log file.
    // ─────────────────────────────────────────────
    private static void log(String message) {
        System.out.println("  [LOG]   " + message);
    }
}
