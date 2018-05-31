package traverse;



public class Graph {
	private int vertexSize;//顶点数量
	private int [] vertexs;//顶点数组
	private int[][]  matrix;
	private static final int MAX_WEIGHT = 1000;
	private boolean [] isVisited;
	public Graph(int vertextSize){
		this.vertexSize = vertextSize;
		matrix = new int[vertextSize][vertextSize];
		vertexs = new int[vertextSize];
		for(int i = 0;i<vertextSize;i++){
			vertexs[i] = i;
		}
		isVisited = new boolean[vertextSize];
	}
	public int[][] getMatrix(){
		return matrix;
	}
	
	public int getVertexSize(){
		return vertexSize;
	}
	
	
	
	public int[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(int[] vertexs) {
		this.vertexs = vertexs;
	}

	public static Graph productGraph(){
		Graph graph2 = new Graph(29);
		int[] a1 = new int[] { 0, 1, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a2 = new int[] { 2, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a3 = new int[] { 2, 2, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a4 = new int[] { 2, 2, 2, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1 };
		int[] a5 = new int[] { 2, 2, 2, 2, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a6 = new int[] { 2, 2, 2, 2, 2, 0, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a7 = new int[] { 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2 };
		int[] a8 = new int[] { 2, 2, 2, 2, 2, 1, 2, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a9 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a10 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a11 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a12 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a13 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a14 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a15 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2 };
		int[] a16 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2 };
		int[] a17 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a18 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a19 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a20 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a21 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[] a22 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2 };
		int[] a23 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2 };
		int[] a24 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2 };
		int[] a25 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2 };
		int[] a26 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2 };
		int[] a27 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2 };
		int[] a28 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2 };
		int[] a29 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0 };
//		graph.matrix[0] = a1;
		graph2.matrix[0] = a1;
		graph2.matrix[1] = a2;
		graph2.matrix[2] = a3;
		graph2.matrix[3] = a4;
		graph2.matrix[4] = a5;
		graph2.matrix[5] = a6;
		graph2.matrix[6] = a7;
		graph2.matrix[7] = a8;
		graph2.matrix[8] = a9;
		graph2.matrix[9] = a10;
		graph2.matrix[10] = a11;
		graph2.matrix[11] = a12;
		graph2.matrix[12] = a13;
		graph2.matrix[13] = a14;
		graph2.matrix[14] = a15;
		graph2.matrix[15] = a16;
		graph2.matrix[16] = a17;
		graph2.matrix[17] = a18;
		graph2.matrix[18] = a19;
		graph2.matrix[19] = a20;
		graph2.matrix[20] = a21;
		graph2.matrix[21] = a22;
		graph2.matrix[22] = a23;
		graph2.matrix[23] = a24;
		graph2.matrix[24] = a25;
		graph2.matrix[25] = a26;
		graph2.matrix[26] = a27;
		graph2.matrix[27] = a28;
		graph2.matrix[28] = a29;
		return graph2;
		
	}
	
		

}
