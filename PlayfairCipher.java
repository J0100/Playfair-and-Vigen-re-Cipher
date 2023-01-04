import java.util.ArrayList;

public class PlayfairCipher {

    public PlayfairCipher() {
    }

    // Generating key:
    public String GenerateKey(String keyword) {
        // replace J with I because we replaced I in the key matrix with the J
        keyword = keyword.replace("J", "I");
        String abc = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // no J
        // using stringbuffer to allow char type in a string array
        StringBuffer newKeyword = new StringBuffer();
        char c = 0;
        // check if there is no duplicate with the keyword entered
        for (int i = 0; i < keyword.length(); i++) {
            // point to the char at the index "i"
            c = keyword.charAt(i);
            // check if the "newKeyword" contain the letter pointed at
            if (!newKeyword.toString().contains("" + c)) {
                // if the pointed letter is not in the array then add the letter to the
                // neKeyword
                newKeyword.append(c);
                // remove the pointed letter from the abc string
                abc = abc.replace("" + c, "");
            }
        }
        // return the new keyword
        return newKeyword + abc;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public String EncryptMsg(String msg, String key) {
        // generate the matrix key
        char[][] finalKeyMatrix = GeneratekeyMatrix(key);
        // filter the message
        ArrayList<String> finalFilterdMsg = FilterMsg(msg);
        // return the Encruted message using the key matrix and the filterd message
        return Encrypt(finalKeyMatrix, finalFilterdMsg);
    }

    public String DecryptMsg(String msg, String key) {
        // generate the matrix key
        final char[][] finalKeyMatrix = GeneratekeyMatrix(key);
        // filter the message
        final ArrayList<String> ecryptedMsg = FilterMsg(msg);
        // return the Dencruted message using the key matrix and the filterd message
        return Decrypt(finalKeyMatrix, ecryptedMsg);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public char[][] GeneratekeyMatrix(String key) {
        // place the key into 5x5 matrix
        int c = 0;
        char[][] matrixKey = new char[5][5];
        for (int i = 0; i < matrixKey.length; i++) {
            for (int j = 0; j < matrixKey[0].length; j++) {
                matrixKey[i][j] = key.charAt(c);
                c++;
            }
        }
        // return the 5x5 matrix
        return matrixKey;
    }

    private int[] FindCharInkeyMatrix(char[][] keyMatrix, char searched) {
        // search for the character in the 5x5 matrix
        for (int i = 0; i < keyMatrix.length; i++) {
            for (int j = 0; j < keyMatrix[0].length; j++) {
                if (keyMatrix[i][j] == searched) {
                    // if found return the loction (column and row number)
                    return new int[] { i, j };
                }
            }
        }
        // if not return null
        return null;
    }

    private ArrayList<String> FilterMsg(String msg) {
        msg = msg.replace("J", "I");
        // msg is divided into endexes each has 2 charecters
        // if the last devided charecter is 1 then add x to make it 2
        if (msg.length() % 2 == 1) {
            msg += "X";
        }
        // store the devided msg in msgList
        ArrayList<String> msgList = new ArrayList<>();
        for (int i = 0; i < msg.length(); i += 2) {
            msgList.add(msg.substring(i, i + 2));
        }
        return msgList;
    }

    private String Encrypt(char[][] finalKeyMatrix, ArrayList<String> finalFilterdMsg) {
        StringBuffer outputEecryptedMsg = new StringBuffer();

        for (String s : finalFilterdMsg) {
            // point to the first charecter in the 2 letters
            int[] charPosA = FindCharInkeyMatrix(finalKeyMatrix, s.charAt(0));
            // point to the second charecter in the 2 letters

            int[] charPosB = FindCharInkeyMatrix(finalKeyMatrix, s.charAt(1));
            // If both the letters are in the same column: Take the letter below each one
            // (going back to the top if at the bottom).
            if (charPosA[0] == charPosB[0]) {
                outputEecryptedMsg.append(finalKeyMatrix[charPosA[0]][(charPosA[1] + 1) % finalKeyMatrix[0].length]);
                outputEecryptedMsg.append(finalKeyMatrix[charPosB[0]][(charPosB[1] + 1) % finalKeyMatrix[0].length]);
            }
            // If both the letters are in the same row: Take the letter to the right of each
            // one (going back to the leftmost if at the rightmost position).
            else if (charPosA[1] == charPosB[1]) {
                outputEecryptedMsg.append(finalKeyMatrix[(charPosA[0] + 1) % finalKeyMatrix.length][charPosA[1]]);
                outputEecryptedMsg.append(finalKeyMatrix[(charPosB[0] + 1) % finalKeyMatrix.length][charPosB[1]]);
            }
            // If neither of the above rules is true: Form a rectangle with the two letters
            // and take the letters on the horizontal opposite corner of the rectangle.
            else {
                outputEecryptedMsg.append(finalKeyMatrix[charPosA[0]][charPosB[1]]);
                outputEecryptedMsg.append(finalKeyMatrix[charPosB[0]][charPosA[1]]);
            }
        }
        return outputEecryptedMsg.toString();
    }

    private String Decrypt(char[][] finalKeyMatrix, ArrayList<String> ecryptedMsg) {
        StringBuilder outputDecryptedMsg = new StringBuilder();

        for (String s : ecryptedMsg) {
            // point to the first charecter in the 2 letters
            int[] charPosA = FindCharInkeyMatrix(finalKeyMatrix, s.charAt(0));
            // point to the second charecter in the 2 letters
            int[] charPosB = FindCharInkeyMatrix(finalKeyMatrix, s.charAt(1));

            // If both the letters are in the same column: Take the letter above each one
            // (going back to the bottom if at the top).
            if (charPosA[0] == charPosB[0]) {
                outputDecryptedMsg.append(finalKeyMatrix[charPosA[0]][(charPosA[1] + 4) % finalKeyMatrix[0].length]);
                outputDecryptedMsg.append(finalKeyMatrix[charPosB[0]][(charPosB[1] + 4) % finalKeyMatrix[0].length]);
            }
            // If both the letters are in the same row: Take the letter to the left of each
            // one (going back to the rightmost if at the leftmost position).
            else if (charPosA[1] == charPosB[1]) {
                outputDecryptedMsg.append(finalKeyMatrix[(charPosA[0] + 4) % finalKeyMatrix.length][charPosA[1]]);
                outputDecryptedMsg.append(finalKeyMatrix[(charPosB[0] + 4) % finalKeyMatrix.length][charPosB[1]]);
            }
            // If neither of the above rules is true: Form a rectangle with the two letters
            // and take the letters on the horizontal opposite corner of the rectangle.
            else {
                outputDecryptedMsg.append(finalKeyMatrix[charPosA[0]][charPosB[1]]);
                outputDecryptedMsg.append(finalKeyMatrix[charPosB[0]][charPosA[1]]);
            }
        }
        return outputDecryptedMsg.toString();
    }

}