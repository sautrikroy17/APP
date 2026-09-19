interface BasicSecurity {
    void login(String username, String password);
    void logout();
}

interface AdvancedSecurity extends BasicSecurity {
    void fingerprintAuth(String fingerprintData);
    void faceRecognitionAuth(String faceScanData);
}

class BankingApplication implements AdvancedSecurity {
    private String user;
    private boolean isAuthenticated;

    @Override
    public void login(String username, String password) {
        this.user = username;
        this.isAuthenticated = true;
        System.out.println("Basic Login Successful for user: " + username);
    }

    @Override
    public void logout() {
        System.out.println("User " + user + " logged out successfully.\n");
        this.user = null;
        this.isAuthenticated = false;
    }

    @Override
    public void fingerprintAuth(String fingerprintData) {
        if (this.user == null) {
            this.user = "BiometricUser";
        }
        this.isAuthenticated = true;
        System.out.println("Fingerprint Authentication Successful: [" + fingerprintData + "]");
    }

    @Override
    public void faceRecognitionAuth(String faceScanData) {
        if (this.user == null) {
            this.user = "BiometricUser";
        }
        this.isAuthenticated = true;
        System.out.println("Face Recognition Authentication Successful: [" + faceScanData + "]");
    }
}

public class BankingSecurity {
    public static void main(String[] args) {
        BankingApplication app = new BankingApplication();

        System.out.println("--- Demonstrating Basic Security ---");
        app.login("alex_smith", "SecurePass#2026");
        app.logout();

        System.out.println("--- Demonstrating Advanced Security ---");
        app.fingerprintAuth("FINGERPRINT_HASH_8F7E6D");
        app.faceRecognitionAuth("FACE_3D_MESH_VERIFIED");
        app.logout();
    }
}
