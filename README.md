# RSA_Encryption_Decrpytion
## description
Encryption and decryption message by using RSA algorithms

#### Write data in csv (Excel) 
This method will help to avoid overwrite data in the file when run multiple iteration
```
File file = new File("exampleFilePath.csv");
BufferedWriter br;
FileWriter fr;
try {
  fr = new FileWriter(file,true);
  br = new BufferedWriter(fr);
  br.newLine();
			
  fr.write(String.format("%s,%.5f",test,time));
		
  br.close();
  fr.close();
  System.out.println("test!");
} catch (IOException e) {
  System.out.println("Error!");
}
```

#### Details
* Generating key pair forprivate and public key (1024 and 2048)
* Looping and time for each generating key pair process (time in nanoseconds)
* Encryption and decryption message with length of data (1024 and 2048)
* Using MessageDigest with SHA-1(algorithms) to digest message before encryption and decryption message with length of data (1024 and 2048)

#### Process flow
Message -> MessageDigest -> encrypt with private key 
-> decrypt with public key -> get messageDigest 
-> compare equal MessageDigest(from encrpyt) with  MessageDigest(from decrpyt)

###### Generate private and public key
```
KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
keyPairGen.initialize(1024);
KeyPair pair = keyPairGen.generateKeyPair();
PrivateKey privKey = pair.getPrivate();   
PublicKey publicKey = pair.getPublic(); 
```

###### MessageDigest class + ShA-1 algorithms
```
String message = "Hello";
MessageDigest md = MessageDigest.getInstance("SHA-1");
byte[] messageHash = md.digest(message.getBytes());
```    

###### Compare decrypted digest message with encrypted digest message
```
System.out.println(Arrays.equals(decryptedDigest, encryptedDigest));
```
