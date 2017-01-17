package top5;

import top5.dao.implement.ClientDaoJdbc;
import top5.model.Client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;


public class APIKey {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        Date date = new Date();
        String dateString = date.toString();
        APIKey apiKey = new APIKey();
        String clientKey = apiKey.getMD5(dateString);
        System.out.println("Please write your brand name: ");
        Scanner sc = new Scanner(System.in);
        String i = sc.next();
        Client newClient = new Client(clientKey, i);
        ClientDaoJdbc saveClient = new ClientDaoJdbc();
        saveClient.addClient(newClient);
        System.out.println("Your API key is : " + clientKey);

    }

    /**
     *
     * @param input
     */

    public static String getMD5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
