package traverse;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.text.Segment;
public class GraphicTraverseUtil {
	private int vertexSize = Graph.productGraph().getVertexSize();//顶点数量
	private int [] vertexs = Graph.productGraph().getVertexs();//顶点数组
	private int[][]  matrix = Graph.productGraph().getMatrix();
	private static final int MAX_WEIGHT = 1000;
	private boolean [] isVisited;
	private ArrayList<Integer> al = new ArrayList<>();
	public GraphicTraverseUtil(){
		isVisited = new boolean[Graph.productGraph().getVertexSize()];
	}
	
	
	/**
	 * 获取某个顶点的第一个邻接点
	 */
	public int getFirstNeighbor(int index){
		for(int j = 0;j<vertexSize;j++){
			if(matrix[index][j]>0&&matrix[index][j]<MAX_WEIGHT){
				return j;
			}
		}
		return -1;
	}
	
	/**
	 * 根据前一个邻接点的下标来取得下一个邻接点
	 * @param v1表示要找的顶点
	 * @param v2 表示该顶点相对于哪个邻接点去获取下一个邻接点
	 */
	public int getNextNeighbor(int v,int index){
		for(int j = index+1;j<vertexSize;j++){
			if(matrix[v][j]>0&&matrix[v][j]<MAX_WEIGHT){
				return j;
			}
		}
		return -1;
	}
	
	/**
	 * 图的深度优先遍历算法
	 */
	public void doDepthFirstSearch(int i, int[][] matrix, boolean flag) {
		if (flag) {
			depthFirstSearch(i);
		} else {
			isVisited[i] = true;
			int w = getFirstNeighbor(i);//
			while (w != -1) {
				if (!isVisited[w]) {
					// 需要遍历该顶点
					al.add(w);
					doDepthFirstSearch(w, matrix, false);
				}
				w = getNextNeighbor(i, w);// 第一个相对于w的邻接点
			}
		}
	}
	public int[] depthFirstSearch(int j) {
		// TODO Auto-generated method stub
		
		int partner [] = new int[DistributeInvoke.segment];
		for(int j2=0;j2<DistributeInvoke.segment;j2++){
			partner[j2] = (j-1)*DistributeInvoke.segment+1+j2;
			
		}
		doDepthFirstSearch(j, matrix, false);
		al.add(j);
		Object[] temp = al.toArray();
		int [] ret = new int [DistributeInvoke.segment];
		for(int i2=0;i2<temp.length;i2++){
			for(int i=0;i<DistributeInvoke.segment;i++){
				if((int)temp[i2]==partner[i]&&partner[i]!=0){
					System.out.println("已遍历"+partner[i]);
				}
			}
		}
		return ret;
	}

	/**
	 * 获取某个顶点的出度
	 * @return
	 */
	public int getOutDegree(int index){
		int degree = 0;
		for(int j = 0;j<matrix[index].length;j++){
			int weight = matrix[index][j];
			if(weight!=0&&weight!=MAX_WEIGHT){
				degree++;
			}
		}
		return degree;
	}
	public static void main(String[] args) {
		new GraphicTraverseUtil().depthFirstSearch(3);
	}
}
