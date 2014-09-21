
public class MD5Data 
{
	int data[][];
	byte message[];
	int blockCount;
	final int blockSizeBites = 512;
	final int blockSizeBytes = 64;
	final int blockOnBlockCount = 16;
	final int blockOnBlockSizeBites = 32;
	final int blockOnBlockSizeBytes = 4;
	
	public MD5Data(byte []message, int blockCount) 
	{
		this.message = message;
		this.blockCount = blockCount;
		//System.out.println(blockCount);
		data = new int[blockCount][blockOnBlockCount];
		for(int i=0;i<blockCount;i++)
		{
			for(int j = 0; j < blockSizeBytes;j=j+4)
			{
				byte tmp[] = new byte[4];
				for(int z=0;z<4;z++)
				{
					tmp[z] = message[i*64+(j+z)];
				}
				
				data[i][j/4] = byteArrayToInt(tmp);
				
			}
			
		}
		
	}
	public int getWord(int blockId,int wordId)
	{
		return data[blockId][wordId];
	}
	public int getBlockCount() 
	{
		return blockCount;
	}
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<blockCount;i++)
		{
			for(int j=0; j < blockSizeBytes;j=j+4)
			{
				sb.append("Data["+i+"]["+j/4+"] = "+data[i][j/4]+" Bytes = ");
				for(int z=0; z<4 ; z++)
				{
					 sb.append(message[i*64+j+z]+" ");
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	private final int byteArrayToInt(byte [] b) {
        return (b[3] << 24)
                + ((b[2] & 0xFF) << 16)
                + ((b[1] & 0xFF) << 8)
                + (b[0] & 0xFF);
	}

}
