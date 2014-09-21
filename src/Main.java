import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main {

	static int A = 0x67452301;
	static int B = 0xefcdab89;
	static int C = 0x98badcfe;
	static int D = 0x10325476;
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException 
	{
		String s="kgdfgfdubamd5"; 
	    byte msg[] = s.getBytes();
	    int msgBitLenght = msg.length*8;
	    byte byteArrayLenght[] = longToByteArray((long)msgBitLenght);  
	    int blockCount = msgBitLenght/448 + 1 ;
	    int newMsgBiteLenght = blockCount*512;	    
	    byte newMsg[] = new byte[newMsgBiteLenght/8];
	    for(int i=0;i<(blockCount*512)/8;)
	    {
	    	if(i<msg.length)
	    	{
	    		newMsg[i] = msg[i];
	    		i++;
	    	}
	    	else
	    	{
	    		if(i == msg.length)
	    		{
	    			newMsg[i] = (byte)128;
	    			i++;
	    		}
	    		else
	    		{
	    			if(i >= ((blockCount*512)-64)/8)
	    			{
	    				for(int z=0;z<8;z++)
	    				{
	    					newMsg[i] = byteArrayLenght[z];
	    					i++;
	    				}
	    			}
	    			else
	    			{
	    				newMsg[i] = 0;
	    				i++;
	    			}
	    			
	    		}
	    	}
	    }
	   MD5Data md = new MD5Data(newMsg, blockCount);
	    int AA = 0;
	    int BB = 0;
	    int CC = 0;
	    int DD = 0;
	    for(int q=0; q<md.getBlockCount(); q++)
	    {
	    	
	    
	    		AA = A;
	    		BB = B;
	    		CC = C;
	    		DD = D;
	    	
	    		/* Round 1 */
	            AA = FF (AA, BB, CC, DD, md.getWord(q, 0),   7, 0xd76aa478); /* 1 */
	            DD = FF (DD, AA, BB, CC, md.getWord(q, 1),  12, 0xe8c7b756); /* 2 */
	            CC = FF (CC, DD, AA, BB, md.getWord(q, 2),  17, 0x242070db); /* 3 */
	            BB = FF (BB, CC, DD, AA, md.getWord(q, 3),  22, 0xc1bdceee); /* 4 */
	            AA = FF (AA, BB, CC, DD, md.getWord(q, 4),   7, 0xf57c0faf); /* 5 */
	            DD = FF (DD, AA, BB, CC, md.getWord(q, 5),  12, 0x4787c62a); /* 6 */
	            CC = FF (CC, DD, AA, BB, md.getWord(q, 6),  17, 0xa8304613); /* 7 */
	            BB = FF (BB, CC, DD, AA, md.getWord(q, 7),  22, 0xfd469501); /* 8 */
	            AA = FF (AA, BB, CC, DD, md.getWord(q, 8),   7, 0x698098d8); /* 9 */
	            DD = FF (DD, AA, BB, CC, md.getWord(q, 9),  12, 0x8b44f7af); /* 10 */
	            CC = FF (CC, DD, AA, BB, md.getWord(q, 10),  17, 0xffff5bb1); /* 11 */
	            BB = FF (BB, CC, DD, AA, md.getWord(q, 11),  22, 0x895cd7be); /* 12 */
	            AA = FF (AA, BB, CC, DD, md.getWord(q, 12),   7, 0x6b901122); /* 13 */
	            DD = FF (DD, AA, BB, CC, md.getWord(q, 13),  12, 0xfd987193); /* 14 */
	            CC = FF (CC, DD, AA, BB, md.getWord(q, 14),  17, 0xa679438e); /* 15 */
	            BB = FF (BB, CC, DD, AA, md.getWord(q, 15),  22, 0x49b40821); /* 16 */
	     
	            /* Round 2 */
	            AA = GG (AA, BB, CC, DD, md.getWord(q, 1),   5, 0xf61e2562); /* 17 */
	            DD = GG (DD, AA, BB, CC, md.getWord(q, 6),   9, 0xc040b340); /* 18 */
	            CC = GG (CC, DD, AA, BB, md.getWord(q, 11),  14, 0x265e5a51); /* 19 */
	            BB = GG (BB, CC, DD, AA, md.getWord(q, 0),  20, 0xe9b6c7aa); /* 20 */
	            AA = GG (AA, BB, CC, DD, md.getWord(q, 5),   5, 0xd62f105d); /* 21 */
	            DD = GG (DD, AA, BB, CC, md.getWord(q, 10),   9, 0x02441453); /* 22 */
	            CC = GG (CC, DD, AA, BB, md.getWord(q, 15),  14, 0xd8a1e681); /* 23 */
	            BB = GG (BB, CC, DD, AA, md.getWord(q, 4),  20, 0xe7d3fbc8); /* 24 */
	            AA = GG (AA, BB, CC, DD, md.getWord(q, 9),   5, 0x21e1cde6); /* 25 */
	            DD = GG (DD, AA, BB, CC, md.getWord(q, 14),   9, 0xc33707d6); /* 26 */
	            CC = GG (CC, DD, AA, BB, md.getWord(q, 3),  14, 0xf4d50d87); /* 27 */
	            BB = GG (BB, CC, DD, AA, md.getWord(q, 8),  20, 0x455a14ed); /* 28 */
	            AA = GG (AA, BB, CC, DD, md.getWord(q, 13),   5, 0xa9e3e905); /* 29 */
	            DD = GG (DD, AA, BB, CC, md.getWord(q, 2),   9, 0xfcefa3f8); /* 30 */
	            CC = GG (CC, DD, AA, BB, md.getWord(q, 7),  14, 0x676f02d9); /* 31 */
	            BB = GG (BB, CC, DD, AA, md.getWord(q, 12),  20, 0x8d2a4c8a); /* 32 */
	            
	            /* Round 3 */
	            AA = HH (AA, BB, CC, DD, md.getWord(q, 5),   4, 0xfffa3942); /* 33 */
	            DD = HH (DD, AA, BB, CC, md.getWord(q, 8),  11, 0x8771f681); /* 34 */
	            CC = HH (CC, DD, AA, BB, md.getWord(q, 11),  16, 0x6d9d6122); /* 35 */
	            BB = HH (BB, CC, DD, AA, md.getWord(q, 14),  23, 0xfde5380c); /* 36 */
	            AA = HH (AA, BB, CC, DD, md.getWord(q, 1),   4, 0xa4beea44); /* 37 */
	            DD = HH (DD, AA, BB, CC, md.getWord(q, 4),  11, 0x4bdecfa9); /* 38 */
	            CC = HH (CC, DD, AA, BB, md.getWord(q, 7),  16, 0xf6bb4b60); /* 39 */
	            BB = HH (BB, CC, DD, AA, md.getWord(q, 10),  23, 0xbebfbc70); /* 40 */
	            AA = HH (AA, BB, CC, DD, md.getWord(q, 13),   4, 0x289b7ec6); /* 41 */
	            DD = HH (DD, AA, BB, CC, md.getWord(q, 0),  11, 0xeaa127fa); /* 42 */
	            CC = HH (CC, DD, AA, BB, md.getWord(q, 3),  16, 0xd4ef3085); /* 43 */
	            BB = HH (BB, CC, DD, AA, md.getWord(q, 6),  23, 0x04881d05); /* 44 */
	            AA = HH (AA, BB, CC, DD, md.getWord(q, 9),   4, 0xd9d4d039); /* 45 */
	            DD = HH (DD, AA, BB, CC, md.getWord(q, 12),  11, 0xe6db99e5); /* 46 */
	            CC = HH (CC, DD, AA, BB, md.getWord(q, 15),  16, 0x1fa27cf8); /* 47 */
	            BB = HH (BB, CC, DD, AA, md.getWord(q, 2),  23, 0xc4ac5665); /* 48 */

	            /* Round 4 */
	            AA = II (AA, BB, CC, DD, md.getWord(q, 0),   6, 0xf4292244); /* 49 */
	            DD = II (DD, AA, BB, CC, md.getWord(q, 7),  10, 0x432aff97); /* 50 */
	            CC = II (CC, DD, AA, BB, md.getWord(q, 14),  15, 0xab9423a7); /* 51 */
	            BB = II (BB, CC, DD, AA, md.getWord(q, 5),  21, 0xfc93a039); /* 52 */
	            AA = II (AA, BB, CC, DD, md.getWord(q, 12),   6, 0x655b59c3); /* 53 */
	            DD = II (DD, AA, BB, CC, md.getWord(q, 3),  10, 0x8f0ccc92); /* 54 */
	            CC = II (CC, DD, AA, BB, md.getWord(q, 10),  15, 0xffeff47d); /* 55 */
	            BB = II (BB, CC, DD, AA, md.getWord(q, 1),  21, 0x85845dd1); /* 56 */
	            AA = II (AA, BB, CC, DD, md.getWord(q, 8),   6, 0x6fa87e4f); /* 57 */
	            DD = II (DD, AA, BB, CC, md.getWord(q, 15),  10, 0xfe2ce6e0); /* 58 */
	            CC = II (CC, DD, AA, BB, md.getWord(q, 6),  15, 0xa3014314); /* 59 */
	            BB = II (BB, CC, DD, AA, md.getWord(q, 13),  21, 0x4e0811a1); /* 60 */
	            AA = II (AA, BB, CC, DD, md.getWord(q, 4),   6, 0xf7537e82); /* 61 */
	            DD = II (DD, AA, BB, CC, md.getWord(q, 11),  10, 0xbd3af235); /* 62 */
	            CC = II (CC, DD, AA, BB, md.getWord(q, 2),  15, 0x2ad7d2bb); /* 63 */
	            BB = II (BB, CC, DD, AA, md.getWord(q, 9),  21, 0xeb86d391); /* 64 */

	    		A += AA;
	    		B += BB;
	    		C += CC;
	    		D += DD;
	    	
	    }
	    int [] tab = {A,B,C,D};
	    byte fin[] = encode(tab,16);
	    System.out.println(toHex(fin));
	    
	    MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(s.getBytes(),0,s.length());
	    System.out.println(new BigInteger(1,m.digest()).toString(16));
	}
	private static byte[] encode(int input[], int len){
        byte[] out = new byte[len];
        int i, j;
        for (i = j = 0; j  < len; i++, j += 4) {
            out[j] = (byte) (input[i] & 0xff);
            out[j + 1] = (byte) ((input[i] >>> 8) & 0xff);
            out[j + 2] = (byte) ((input[i] >>> 16) & 0xff);
            out[j + 3] = (byte) ((input[i] >>> 24) & 0xff);
        }
        return out;
    }
	private static String toHex(byte hash[]){
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for (byte element: hash) {
            int intVal = element & 0xff;
            if (intVal < 0x10){
                // append a zero before a one digit hex
                // number to make it two digits.
                buf.append("0");
            }
            buf.append(Integer.toHexString(intVal));
        }
        return buf.toString();
    }
	public static final byte[] longToByteArray(long l) 
	{
		byte[] out = new byte[8];
        out[0] = (byte) (l & 0xff);
        out[1] = (byte) ((l >>> 8) & 0xff);
        out[2] = (byte) ((l >>> 16) & 0xff);
        out[3] = (byte) ((l >>> 24) & 0xff);
        out[4] = (byte) ((l >>> 32) & 0xff);
        out[5] = (byte) ((l >>> 40) & 0xff);
        out[6] = (byte) ((l >>> 48) & 0xff);
        out[7] = (byte) ((l >>> 56) & 0xff);
        return out;
	}
	public static final long byteArrayToLong(byte [] b) {
		return (
				b[7] << 56)
                + ((b[6] & 0xFF) << 48)
                + ((b[5] & 0xFF) << 40)
                + ((b[4] & 0xFF) << 32)
                + ((b[3] & 0xFF) << 24)
                + ((b[2] & 0xFF) << 16)
                + ((b[1] & 0xFF) << 8)
                + (b[0] & 0xFF);
	
	}
	
    private static int FF (int a, int b, int c, int d, int x, int s, int ac) {
        a += ((b & c) | (~b & d));
        a += x;
        a += ac;
        //return rotateLeft(a, s) + b;
        a = (a << s) | (a >>> (32 - s));
        return a + b;
    }
 
    private static int GG (int a, int b, int c, int d, int x, int s, int ac) {
        a += ((b & d) | (c & ~d));
        a += x;
        a += ac;
        //return rotateLeft(a, s) + b;
        a = (a << s) | (a >>> (32 - s));
        return a + b;
    }
 
    private static int HH (int a, int b, int c, int d, int x, int s, int ac) {
        a += (b ^ c ^ d);
        a += x;
        a += ac;
        //return rotateLeft(a, s) + b;
        a = (a << s) | (a >>> (32 - s));
        return a + b;
    }
 
    private static int II (int a, int b, int c, int d, int x, int s, int ac) {
        a += (c ^ (b | ~d));
        a += x;
        a += ac;
        //return rotateLeft(a, s) + b;
        a = (a << s) | (a >>> (32 - s));
        return a + b;
    }

}
