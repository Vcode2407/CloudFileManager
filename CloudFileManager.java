import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class CloudFileManager {
    private static final String CLOUD_DIR = "cloud_storage/"; // Simulated cloud folder

    public static void main(String[] args) {
        // Create cloud directory if it doesn’t exist
        File dir = new File(CLOUD_DIR);
        if (!dir.exists()) dir.mkdir();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCloud File Manager");
            System.out.println("1. Upload File");
            System.out.println("2. Download File");
            System.out.println("3. List Files");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1: uploadFile(scanner); break;
                case 2: downloadFile(scanner); break;
                case 3: listFiles(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void uploadFile(Scanner scanner) {
        System.out.print("Enter file path to upload: ");
        String sourcePath = scanner.nextLine();
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("File not found!");
            return;
        }
        try {
            Files.copy(sourceFile.toPath(), Paths.get(CLOUD_DIR + sourceFile.getName()),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File uploaded successfully!");
        } catch (IOException e) {
            System.out.println("Upload failed: " + e.getMessage());
        }
    }

    private static void downloadFile(Scanner scanner) {
        System.out.print("Enter file name to download: ");
        String fileName = scanner.nextLine();
        File sourceFile = new File(CLOUD_DIR + fileName);
        if (!sourceFile.exists()) {
            System.out.println("File not found in cloud!");
            return;
        }
        try {
            Files.copy(sourceFile.toPath(), Paths.get("downloaded_" + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded as downloaded_" + fileName);
        } catch (IOException e) {
            System.out.println("Download failed: " + e.getMessage());
        }
    }

    private static void listFiles() {
        File dir = new File(CLOUD_DIR);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files in cloud storage.");
            return;
        }
        System.out.println("Files in cloud:");
        for (File file : files) {
            System.out.println("- " + file.getName());
        }
    }
}