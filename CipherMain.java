import java.util.ArrayList;

public class CipherMain {

    public static void main(String[] args) {
        // call vigenere class
        VingenereCipher Vingenere = new VingenereCipher();
        // call playfair class
        PlayfairCipher Playfair = new PlayfairCipher();

        String msg = "Ghaida";
        String key = "keyword";

        // display plaintext
        System.out.println("Plain Text : " + msg + "\n");

        // convetring input to UpperCase
        // replace any charecter that isn't a letter with null
        msg = msg.toUpperCase().replaceAll("[^A-Z]+", "");
        key = key.toUpperCase().replaceAll("[^A-Z]+", "");

        // Generate vigenere key
        char[] vKey = Vingenere.GenerateKey(msg, key);
        // Encrypt using vigenere
        char[] vEncryptMsg = Vingenere.Encrypt(msg, vKey);
        // Generate Playfair key
        String pKey = Playfair.GenerateKey(String.valueOf(vKey));
        // Encrypt again using Playfair
        String pEncryptedMsg = Playfair.EncryptMsg(String.valueOf(vEncryptMsg), pKey);
        // Decrypt using Playfair
        String pDecryptedMsg = Playfair.DecryptMsg(pEncryptedMsg, pKey);
        // Decrypt using vigenere
        char[] vDecryptMsg = Vingenere.Dencrypt(msg, pDecryptedMsg.toCharArray(), vKey);

        // display Generated key
        System.out.println("Vingenere Key: " + String.valueOf(vKey) + "\n");
        System.out.println("Playfair Key: " + pKey + "\n");
        // display Cipher text
        System.out.println("Cipher text: " + pEncryptedMsg + "\n");
        // display Decrypted Cipher text
        System.out.println("Decrypted Cipher text : " + String.valueOf(vDecryptMsg) + "\n");

    }
}