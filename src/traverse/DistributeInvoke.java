package traverse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DistributeInvoke {
	//�ܽ����
	private int matrixLength;
	//ÿ��Ľ����
	public static int segment;
	//ÿ���ڽӾ���
	private int[][] tempCount;
	
	int temp3[][];
	
	int temp4[];
	//��ѯ���� 
	ArrayList<Integer> list = new ArrayList<>();
	//��ѯ����
	HashMap<Integer, Integer> mergMap = new HashMap<>();
	//�����������һ�������
	
	private int partCount,partCountLeft;
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void getSegmention(int segment){
		this.segment = segment;
	}
	
	/**
	 * �����н����з���
	 * @param graph
	 * @return int������
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
					System.out.println("�������,������Ϊ:");
					for(int i2=0;i2<tempCount.length;i2++){
						for(int j2=0;j2<tempCount[0].length;j2++){
							System.out.print(tempCount[i2][j2]);
						}
						System.out.println("Ϊһ��");
						
					}
					return tempCount;					
				}	
			}
		}
		System.out.println("�������,������Ϊ:");
		for(int i=0;i<tempCount.length;i++){
			for(int j=0;j<tempCount[0].length;j++){
				System.out.print(tempCount[i][j]);
			}
			System.out.println();
		}
		return tempCount;
		
	}
	
	
	/**
	 * ���������������ִ��˳���������ǿ��ͨ������
	 * @return:ִ��˳��
	 */
	public void orderSort(Graph graph){
		System.out.println("��ʼ����");
		int temp2 = 0;
		for(int i=1;i<=partCount;i++){
			int temp = detectWeakContact(i, graph);
			if(temp!=-1){
				temp2 = detectWeakContact(temp, graph);
				if(temp2==i){
					System.out.println(i+"����"+temp+"��Ϊǿ����");
					mergMap.put(i, temp);
					list.add(temp);
						}
				else{
					if(temp2!=-1){
						System.out.println(i+"����"+temp+"��Ϊ������");
						changePosition(i-1,temp-1);	
						list.add(temp2);
					}
					
					}
				}
			if(list.contains(i)){
				continue;
			}
		}
		System.out.println("�ɴ˵ó�����˳��:");
		for(int i=0;i<partCount;i++){
			System.out.print(tempCount[i][segment-1]/segment!=0?tempCount[i][segment-1]/segment:partCount);
			temp4[i] = tempCount[i][segment-1]/segment!=0?tempCount[i][segment-1]/segment:partCount;
		}
		
	}
	/**
	 * �������ӵ�������н���˳��
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
	 * @param:������
	 * �Է�����������ж��Ƿ�������ͨ
	 * ��Ϊֻ�д������ҵ����ӻ��˳����Ӱ�죬����������������������
	 * @return:���� �򷵻���֮���������������ޣ��򷵻�-1
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
	 * @param:������
	 * �Է�����������ж��Ƿ���ǿ��ͨ
	 * @return:���� �򷵻���֮���������������ޣ��򷵻�-1
	 * ���ڱ��㷨��δ�漰�����ж�ǿ��ͨ�ķ���������δʵ��
	 */
	public int detectStrongContact(int num){
		return 0;
	}
	
	/**
	 *����ÿ���������Ҫ���������ڽӾ��� 
	 * @param num
	 * @return ���ڽӾ���
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
	 * ����������ȱ���
	 */
	public void traverse(){
		
		
		Graph graph = Graph.productGraph();
		
		tempCount = distribute(graph);
		
		orderSort(graph);
		System.out.println();
		System.out.println("�ѱ���0");
		for(int i=0;i<partCount;i++){
			temp3= productSonMatrix(temp4[i], graph);
			new GraphicTraverseUtil().depthFirstSearch(temp4[i]);
		}

		
	}
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		new  DistributeInvoke().traverse();
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("��ʱ:"+(float)((endTime-startTime)/ 1000F)+"s");
		
		
	}
}
