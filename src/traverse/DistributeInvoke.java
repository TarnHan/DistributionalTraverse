package traverse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DistributeInvoke {
	//总结点数
	private int matrixLength;
	//每组的结点数
	public static int segment;
	//每组邻接矩阵
	private int[][] tempCount;
	
	int temp3[][];
	
	int temp4[];
	//查询缓存 
	ArrayList<Integer> list = new ArrayList<>();
	//查询缓存
	HashMap<Integer, Integer> mergMap = new HashMap<>();
	//分组数，最后一组的余数
	
	private int partCount,partCountLeft;
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void getSegmention(int segment){
		this.segment = segment;
	}
	
	/**
	 * 对所有结点进行分组
	 * @param graph
	 * @return int型数组
	 */ 
	public int[][] distribute(Graph graph){
		
		int temp5=0;
		for(int k=0;k<graph.getVertexSize();k++){
			if(graph.getMatrix()[0][k]>0&&graph.getMatrix()[0][k]<2){
				temp5++;
			}
		}
		matrixLength = 	graph.getVertexSize();
		segment = matrixLength/temp5; 
		
		
		if(segment>matrixLength||segment<=0)
			segment=matrixLength;
		
		
		partCount = matrixLength/segment;
		if((matrixLength-1)/2<segment&&segment<matrixLength){
			partCount = 2;
		}
		
		
		partCountLeft = (matrixLength-1)%segment;
		
		tempCount = new int [partCount][segment];
		
		System.out.println("partCount:"+partCount);
		System.out.println("matrixLength:"+matrixLength);
		
		temp4 = new int [partCount];
		
		int temp = 1;
		
		for(int i=0;i<partCount;i++){
			for(int j=0;j<segment;j++){
				tempCount[i][j]=temp;
				temp++;
				if(temp>=matrixLength){
					System.out.println("分组结束,分组结果为:");
					for(int i2=0;i2<tempCount.length;i2++){
						for(int j2=0;j2<tempCount[0].length;j2++){
							System.out.print(tempCount[i2][j2]);
						}
						System.out.println("为一组");
						
					}
					return tempCount;					
				}	
			}
		}
		System.out.println("分组结束,分组结果为:");
		for(int i=0;i<tempCount.length;i++){
			for(int j=0;j<tempCount[0].length;j++){
				System.out.print(tempCount[i][j]);
			}
			System.out.println();
		}
		return tempCount;
		
	}
	
	
	/**
	 * 对所有组深度优先执行顺序进行消除强连通与排序
	 * @return:执行顺序
	 */
	public void orderSort(Graph graph){
		System.out.println("开始排序");
		int temp2 = 0;
		for(int i=1;i<=partCount;i++){
			int temp = detectWeakContact(i, graph);
			if(temp!=-1){
				temp2 = detectWeakContact(temp, graph);
				if(temp2==i){
					System.out.println(i+"组与"+temp+"组为强连接");
					mergMap.put(i, temp);
					list.add(temp);
						}
				else{
					if(temp2!=-1){
						System.out.println(i+"组与"+temp+"组为弱连接");
						changePosition(i-1,temp-1);	
						list.add(temp2);
					}
					
					}
				}
			if(list.contains(i)){
				continue;
			}
		}
		System.out.println("由此得出遍历顺序:");
		for(int i=0;i<partCount;i++){
			System.out.print(tempCount[i][segment-1]/segment!=0?tempCount[i][segment-1]/segment:partCount);
			temp4[i] = tempCount[i][segment-1]/segment!=0?tempCount[i][segment-1]/segment:partCount;
		}
		
	}
	/**
	 * 对弱连接的两组进行交换顺序
	 * @param i
	 * @param temp2
	 */
	private void changePosition(int i, int temp2) {
		// TODO Auto-generated method stub
		int[] temp;
		temp = tempCount[i];
		tempCount[i] = tempCount[temp2];
		tempCount[temp2] = temp;
	}

	/**
	 * @param:数组编号
	 * 对分类后的组进行判断是否有弱连通
	 * 因为只有从左向右的连接会对顺序有影响，所以无需检测从右向左的连接
	 * @return:若有 则返回与之相连的组数，若无，则返回-1
	 */
	public int detectWeakContact(int num,Graph graph){
		for(int i=1;i<=partCount;i++){
			for(int j=i+1;j<matrixLength;j++){
				if(graph.getMatrix()[i+(num-1)*segment][j]>0&&graph.getMatrix()[i+(num-1)*segment][j]<1000){
					int teamNum ;
					
					if(j%segment==0&&j/segment!=num){
						return j/segment;
					}
					else if((j+1)%segment==0&&(j+1)/segment!=num){
						return (j+1)/segment;
					}	
					else if((j+2)%segment==0&&(j+2)/segment!=num){
						return (j+2)/segment;}
					
					else if((j+3)%segment==0&&(j+3)/segment!=num){
						return (j+3)/segment;}
					
					else if((j+4)%segment==0&&(j+4)/segment!=num){
						return (j+4)/segment;}
					
					
					else if((j+5)/segment!=num)
						return (j+5)/segment;
				}
				
			}
		}
		return -1;
	}
	
	/**
	 * @param:数组编号
	 * 对分类后的组进行判断是否有强连通
	 * @return:若有 则返回与之相连的组数，若无，则返回-1
	 * 由于本算法中未涉及单独判定强连通的方法，所以未实现
	 */
	public int detectStrongContact(int num){
		return 0;
	}
	
	/**
	 *根据每组序号生成要遍历的子邻接矩阵 
	 * @param num
	 * @return 子邻接矩阵
	 */
	public int[][] productSonMatrix(int num,Graph graph){
		int[][] orginMatrix = graph.getMatrix();
		int[][] sonMatrix = new int[partCount][partCount];
		int[][] sonMatrixBackup = new int[partCount-1][partCount-1];
		for(int i=0;i<partCount;i++){
			for(int j=0;j<partCount;j++){
				if((num-1)*segment+i+1<graph.getVertexSize()&&(num-1)*segment+j+1<graph.getVertexSize()){
					sonMatrix[i][j] = orginMatrix[(num-1)*segment+i+1][(num-1)*segment+j+1];
				}
				else{
					for(int i2=0;i2<segment-1;i2++){
						for(int j2=0;j2<segment-1;j2++){
							if((num-1)*segment+i2+1<matrixLength-1&&(num-1)*segment+j2+1<matrixLength-1)
							sonMatrixBackup[i2][j2] = orginMatrix[(num-1)*segment+i2+1][(num-1)*segment+j2+1];
						}
					}
					return sonMatrixBackup;
				}
			}
		}
		return sonMatrix;
	}
	
	/**
	 * 进行深度优先遍历
	 */
	public void traverse(){
		
		
		Graph graph = Graph.productGraph();
		
		tempCount = distribute(graph);
		
		orderSort(graph);
		System.out.println();
		System.out.println("已遍历0");
		for(int i=0;i<partCount;i++){
			temp3= productSonMatrix(temp4[i], graph);
			new GraphicTraverseUtil().depthFirstSearch(temp4[i]);
		}

		
	}
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		new  DistributeInvoke().traverse();
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("耗时:"+(float)((endTime-startTime)/ 1000F)+"s");
		
		
	}
}
