package lcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LCS_length {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		对迭代器的练习
//		List<Object> listA = new ArrayList<>();
//		Collections.addAll(listA,'A','B','C');
//		Iterator<Object> m = listA.iterator();
//		while(m.hasNext()){
//			char a = (char)m.next();
//			System.out.print(a+",");
//		}
		
		char A[] = {'A','B','C','B','D','A','B'};
		char B[] = {'B','D','C','A','B','A'};
		int len1 = A.length;
		int len2 = B.length;
//		存储最长公共子序列的长度，如c【i】【j】表示序列A下标为i的序列与序列B下标为j的序列的最长公共子序列的值
		int c[][] = new int[len1+1][len2+1];
		char b[][] = new char[len1+1][len2+1];
		for(int i = 0;i<len1+1;i++){
//			表示序列A下标为i，与序列下标为0的最长公共子序列为0
			c[i][0] = 0;
		}
		for(int i =0;i<len2+1;i++){
			c[0][i] = 0;
		}
		for(int i=0;i<len1;i++){
			for(int j=0;j<len2;j++){
				if(A[i]==B[j]){
					c[i+1][j+1]=c[i][j]+1;
//					 数组存储‘-’表示，此位置的元素是最长公共子序列上的元素
					b[i][j]='-';
				}
				else if(c[i][j+1]>c[i+1][j]){
					c[i+1][j+1] = c[i][j+1];
//					当数组存储‘<'表示，则元素在 i-1，j位置上 	
					b[i][j]='<';
					
				}
				else{
//					当数组存储’<',表示，则元素在i，j-1位置上
					c[i+1][j+1] = c[i+1][j];
					b[i][j]='>';
				}
			}
		}
		System.out.println(c[len1][len2]);
//		输出最长公共子序列
		printLCS(b,A,len1-1,len2-1);
 	}
	
	static void printLCS(char[][] b,char [] A,int i,int j){
		if(i<0||j<0)
			return ;
		if(b[i][j]=='-'){
			printLCS(b, A, i-1, j-1);
			System.out.println(A[i]);
		}
		else if (b[i][j]=='<'){
			printLCS(b, A, i-1, j);
		}else {
			printLCS(b, A, i, j-1);
		}
	}

}













