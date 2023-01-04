import java.util.ArrayList;

public class VingenereCipher {

    public VingenereCipher() {
    }

    // vigenere cipherFF
    public char[] GenerateKey(String msg, String key) {
        // char convertedMsg[] = msg.toCharArray();
        char newKey[] = new char[msg.length()];
        for (int i = 0, j = 0; i < msg.length(); ++i, ++j) {
            if (j == key.length()) {
                j = 0;
            }
            newKey[i] = key.charAt(j);
        }
        return newKey;
    }

    public char[] Encrypt(String msg, char[] Key) {
        char convertedMsg[] = msg.toCharArray();
        char encryptedMsg[] = new char[msg.length()];
        for (int i = 0; i < msg.length(); ++i)
            // converting in range 0-25
            encryptedMsg[i] = (char) (((convertedMsg[i] + Key[i]) % 26)
                    // convert into alphabets(ASCII)
                    + 'A');
        return encryptedMsg;

    }

    public char[] Dencrypt(String msg, char[] encryptedMsg, char[] Key) {
        char decryptedMsg[] = new char[msg.length()];
        for (int i = 0; i < msg.length(); ++i)
            // converting in range 0-25
            decryptedMsg[i] = (char) ((((encryptedMsg[i] - Key[i]) + 26) % 26)
                    // convert into alphabets(ASCII)
                    + 'A');
        return decryptedMsg;
    }
}
