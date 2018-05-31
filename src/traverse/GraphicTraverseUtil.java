package traverse;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.text.Segment;
public class GraphicTraverseUtil {
	private int vertexSize = Graph.productGraph().getVertexSize();//��������
	private int [] vertexs = Graph.productGraph().getVertexs();//��������
	private int[][]  matrix = Graph.productGraph().getMatrix();
	private static final int MAX_WEIGHT = 1000;
	private boolean [] isVisited;
	private ArrayList<Integer> al = new ArrayList<>();
	public GraphicTraverseUtil(){
		isVisited = new boolean[Graph.productGraph().getVertexSize()];
	}
	
	
	/**
	 * ��ȡĳ������ĵ�һ���ڽӵ�
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
	 * ����ǰһ���ڽӵ���±���ȡ����һ���ڽӵ�
	 * @param v1��ʾҪ�ҵĶ���
	 * @param v2 ��ʾ�ö���������ĸ��ڽӵ�ȥ��ȡ��һ���ڽӵ�
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
	 * ͼ��������ȱ����㷨
	 */
	public void doDepthFirstSearch(int i, int[][] matrix, boolean flag) {
		if (flag) {
			depthFirstSearch(i);
		} else {
			isVisited[i] = true;
			int w = getFirstNeighbor(i);//
			while (w != -1) {
				if (!isVisited[w]) {
					// ��Ҫ�����ö���
					al.add(w);
					doDepthFirstSearch(w, matrix, false);
				}
				w = getNextNeighbor(i, w);// ��һ�������w���ڽӵ�
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
					System.out.println("�ѱ���"+partner[i]);
				}
			}
		}
		return ret;
	}

	/**
	 * ��ȡĳ������ĳ���
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
