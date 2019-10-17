package Final;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;

public class StartPKLoopFinalMilli {
	
	File file = new File("..\\EncryptionAndDecryption\\src\\Final\\FinalRecord1024.csv");
	BufferedWriter br;
	FileWriter fr;
	KeyPairGenerator keyPairGen;
	long starttime = System.currentTimeMillis();
 	long endtime = System.currentTimeMillis();
	
	public void writeRecord(String test, double time) {
		try {
			fr = new FileWriter(file,true);
			br = new BufferedWriter(fr);
			br.newLine();
			
			fr.write(String.format("%s,%.5f",test,time));
		
			br.close();
			fr.close();
			System.out.println("test!");
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Error!");
		}
		
	}
	
	public void keyGenerate(int lenght,int count) {
		try {
			for(int i=0; i<count; i++) {
				keyPairGen = KeyPairGenerator.getInstance("RSA");
				starttime = System.currentTimeMillis();
		    	keyPairGen.initialize(lenght);
			    KeyPair pair = keyPairGen.generateKeyPair();
			    PrivateKey privKey = pair.getPrivate();   
			    PublicKey publicKey = pair.getPublic(); 
			    endtime = System.currentTimeMillis();
			    this.writeRecord("Size "+count,(endtime - starttime));
			    System.out.println("Keys generated");
			}
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
			System.out.println("Error!");
		}
		
	}
	


	public static void main(String[] args)  {
		StartPKLoopFinalMilli test = new StartPKLoopFinalMilli();
//		KeyPairGenerator keyPairGen;

//		try {
//			keyPairGen = KeyPairGenerator.getInstance("RSA");
//			starttime = System.currentTimeMillis();
//	    	keyPairGen.initialize(1024);
//		    KeyPair pair = keyPairGen.generateKeyPair();
//		    PrivateKey privKey = pair.getPrivate();   
//		    PublicKey publicKey = pair.getPublic(); 
//		    endtime = System.currentTimeMillis();
//		    test.writeRecord("Size 50",(endtime - starttime));
//		    System.out.println("Keys generated");
//		} catch (NoSuchAlgorithmException e) {
//
//			e.printStackTrace();
//		}
	 	 
//	 	test.writeRecord("good", 100.10);
		
//		test.keyGenerate(2048,10);
//		test.keyGenerate(2048,20);
//		test.keyGenerate(2048,50);
//		test.keyGenerate(2048,100);
//		test.keyGenerate(2048,200);
//		test.keyGenerate(2048,500);
//		test.keyGenerate(2048,1000);
		
		test.keyGenerate(1024 ,10);
		test.keyGenerate(1024 ,20);
		test.keyGenerate(1024 ,50);
		test.keyGenerate(1024 ,100);
		test.keyGenerate(1024 ,200);
		test.keyGenerate(1024 ,500);
		test.keyGenerate(1024 ,1000);
	    	
	    
	}

}
