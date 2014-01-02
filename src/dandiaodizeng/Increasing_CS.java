package dandiaodizeng;

// 求n个数的序列的最长单调递增子序列，要求时间复杂度为o（n*n），如减小时间复杂度，在查找的时候利用折半查找
public class Increasing_CS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 10;
		int input[] = {5,7,9,3,4,5,1,6,2,7};
		
//		采用动态规划方法。
//		len存储的是到input（i）截止的最长递增长度
		int len;
		
//		max 存储的是到input（i）截止的最长递增长度为s的末尾的最小值
		int min[]= new int [n];
		
//		辅助数组，用来最终输出单调递增子序列
		int b[] = new int[n];
		
		for(int i=0;i<n;i++)
			min[i]=Integer.MIN_VALUE;
//		长度为1的序列的最小值为input【0】存储在min【1】中
		min[1]=input[0];
		len=1;
		b[0]=1;
		for(int i=1;i<n;i++){
//			input[i]比到i-1的所有最长递增序列中末尾的最小值大
			if(input[i]>min[len]){
				len++;
//				min[i]存储递增长度为i的末尾的最小值
				min[len]=input[i];
//				b中存储的是到i为止的最长递增子序列的长度
				b[i]=len;
			}
//			input[i]等于到i-1的所有最长递增序列中末尾的最小值不变
			if(input[i]==min[len]){
				min[len]=input[i];
			}
//			input[i]比到i-1的所有最长递增序列中末尾的最小值还小，那么到input[i]的最长递增序列长度为s-1的末尾的最大值就可能改变
//			 此时遍历所有最长递增子序列长度为s-1.....1,知道满足input【i】比长度为s'的末尾最小值大，
			int j=len;
			while(input[i]<=min[j]&&j>=1){
					j--;
			}
			if(j!=0)
			{
				min[j+1]=input[i];
				b[i]=j+1;
			}
			else
			{
				min[1]=input[i];
				b[i]=j+1;
			}
			
		}
		System.out.println(len);
		
		
		
//		输出单调递增子序列
		print_cs(input,b,len,n-1);
	}

	private static void print_cs(int[] input, int[] b, int len, int start) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		// TODO Auto-generated method stub
		int isLIS = 0;
		if(len==0||start<0)
			return;
		while(b[start]!=len)
		{
			start--;	
		}
		isLIS=1;
		print_cs(input, b, len-1, start-1);
		if(isLIS==1){
			System.out.print(input[start]+" ");
		}
	}

}
