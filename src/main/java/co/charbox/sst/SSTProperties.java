package co.charbox.sst;


public abstract class SSTProperties {
	
	public static final char DATA_CHAR = '#';
	public static final String DATA = DATA_CHAR + "";
	public static final String DATA_CHUNK_10 = DATA + DATA + DATA + DATA + DATA + DATA + DATA + DATA + DATA + DATA;
	public static final String DATA_CHUNK_100 = DATA_CHUNK_10 + DATA_CHUNK_10 + DATA_CHUNK_10 + DATA_CHUNK_10 + DATA_CHUNK_10
			 + DATA_CHUNK_10 + DATA_CHUNK_10 + DATA_CHUNK_10 + DATA_CHUNK_10 + DATA_CHUNK_10;
	public static final String DATA_CHUNK_1000 = DATA_CHUNK_100 + DATA_CHUNK_100 + DATA_CHUNK_100 + DATA_CHUNK_100 + DATA_CHUNK_100
			 + DATA_CHUNK_100 + DATA_CHUNK_100 + DATA_CHUNK_100 + DATA_CHUNK_100 + DATA_CHUNK_100;
	public static final String DATA_CHUNK_1024 = DATA_CHUNK_1000  + DATA_CHUNK_10 + DATA_CHUNK_10 + DATA + DATA + DATA + DATA;
	public static final String DATA_CHUNK_2000 = DATA_CHUNK_1000 + DATA_CHUNK_1000;
	public static final String DATA_CHUNK_10000 = DATA_CHUNK_1000 + DATA_CHUNK_1000 + DATA_CHUNK_1000 + DATA_CHUNK_1000 + DATA_CHUNK_1000
			 + DATA_CHUNK_1000 + DATA_CHUNK_1000 + DATA_CHUNK_1000 + DATA_CHUNK_1000 + DATA_CHUNK_1000;
	
	private static final byte[][] CHUNKS = new byte[200][];
	
	static {
		assert(DATA.length() == 1);
		assert(DATA_CHUNK_10.length() == 10);
		assert(DATA_CHUNK_100.length() == 100);
		assert(DATA_CHUNK_1000.length() == 1000);
		assert(DATA_CHUNK_1024.length() == 1024);
		assert(DATA_CHUNK_2000.length() == 2000);
		assert(DATA_CHUNK_10000.length() == 10000);
		
		for (int i=0;i<CHUNKS.length;i++) {
			CHUNKS[i] = new byte[2000];
			for (int j=0;j<CHUNKS[i].length;j++) {
				CHUNKS[i][j] = (byte) getRandomChar();
			}
		}
	}
	
	private static char getRandomChar() {
		int diff = 'A' - 'z';
		return (char)((Math.random() * diff) + 'A');
	}
	
	public static String getDefaultDataChunk() {
		return DATA_CHUNK_2000;
	}
	
	public static byte[] getRandomDataChunk() {
		return CHUNKS[(int)(Math.random() * CHUNKS.length)];
	}
	
	private SSTProperties() { }
}
