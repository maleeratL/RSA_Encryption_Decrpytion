package RSA_KEY_DIGITAL_SIGNATURE;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

public class RSATest_SHA {
	public static final String ALGORITHM = "RSA";
	public static final String ALGORITHM_SHA = "SHA-1";
	public static final int ALGORITHM_BITS = 2048;
	public static final String FILE_PRIVATE = "..\\RSAEncryptionDecryption\\src\\RSA_KEY_DIGITAL_SIGNATURE\\private.txt";
	public static final String FILE_PUBLIC = "..\\RSAEncryptionDecryption\\src\\RSA_KEY_DIGITAL_SIGNATURE\\public.txt";
	
	//Generate key pair
	public static KeyPair keyPairRSA() {
		KeyPairGenerator generator = null;
		try {
			generator = KeyPairGenerator.getInstance(ALGORITHM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (generator != null) {
			generator.initialize(ALGORITHM_BITS);
			KeyPair keyPair = generator.genKeyPair(); //.genKeyPair(); or .generateKeyPair();
			return keyPair;
		}
		return null;
	}
	
	//Save private and public keys into seperate files
	public static void saveKey(KeyPair keyPair) {
		String fileBase = null;
		Key publicKey = keyPair.getPublic();
		Key privateKey = keyPair.getPrivate();
		
		try {
			FileOutputStream outPri = new FileOutputStream(FILE_PUBLIC);
			outPri.write(publicKey.getEncoded());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileOutputStream outPub = new FileOutputStream(FILE_PRIVATE);
			outPub.write(privateKey.getEncoded());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Get public key
	public static Key retrieveKeyPublic() throws Exception {
		byte[] bytes = Files.readAllBytes(Paths.get(FILE_PUBLIC));
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
		PublicKey pub = kf.generatePublic(ks);
	
		return pub;
	}
	
	//Get private key
	public static Key retrieveKeyPrivate() throws Exception {
		byte[] bytes = Files.readAllBytes(Paths.get(FILE_PRIVATE));
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
		PrivateKey pvt = kf.generatePrivate(ks);
	
		return pvt;
	}
	
	//Encrypt data
	public static byte[] encrypt(byte[] original, Key privateKey) {
		if (original != null && privateKey != null) {
			byte[] bs = original;
			byte[] encData = convert(bs, privateKey, Cipher.ENCRYPT_MODE);
			return encData;
		}
		return null;
	}
	
	//Decrypt data
	public static byte[] decrypt(byte[] encrypted, Key publicKey) {
		if (encrypted != null && publicKey != null) {
			byte[] decData = convert(encrypted, publicKey, Cipher.DECRYPT_MODE);
			return decData;
		}
		return null;
	}
	
	//Convert data to byte (Cipher)
	//Method to convert data from Encrypt and Decrypt
	private static byte[] convert(byte[] data, Key key, int mode) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(mode, key);
			byte[] newData = cipher.doFinal(data);
			return newData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] digestMessage(String text) throws Exception{
		MessageDigest md = MessageDigest.getInstance(ALGORITHM_SHA);
		byte[] messageHash = md.digest(text.getBytes());
		return messageHash;
	}
	
	public static void main(String[] args) throws Exception {
		String password = "password";
		KeyPair keyPair = keyPairRSA();
		saveKey(keyPair);
		byte[] digest = digestMessage(password);
//		Key publicKey = keyPair.getPublic();
//		Key privateKey = keyPair.getPrivate();
		Key publicKey = retrieveKeyPrivate();
		Key privateKey = retrieveKeyPublic();
		System.out.println("Encrypt Start");
		System.out.println("Original: " + password);
		byte[] encryptedDigest = encrypt(digest, privateKey);
		System.out.println("Encrypted: " + new String(encryptedDigest));
		System.out.println("Encrypt End");
		System.out.println();
		System.out.println("Decrypt Start");
		
		byte[] decryptedDigest = decrypt(encryptedDigest, publicKey);
//		byte[] newdecrypted = digestMessage(decrypted.toString());
		
	System.out.println("Decrypted: " + new String(decryptedDigest));
		System.out.println("Decrypted matches Original: " + Arrays.equals(decryptedDigest, digest));
		System.out.println("Decrypt End");
	}
}
