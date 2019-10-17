package Final;
import java.security.*;

public class StartPK_original {

	public static void main(String[] args) throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		
	    keyPairGen.initialize(1024);
	      
	    KeyPair pair = keyPairGen.generateKeyPair();

	    PrivateKey privKey = pair.getPrivate();   

	    PublicKey publicKey = pair.getPublic(); 
	    System.out.println("Keys generated");
	}

}
