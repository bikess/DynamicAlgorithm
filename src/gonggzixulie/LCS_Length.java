package gonggzixulie;

// 动态规划求解最长的公共子序列问题
public class LCS_Length {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char a[]={'A','B','C','B','D','A','B'};
		char b[]={'B','D','C','A','B','A'};
		int m=a.length;
		int n=b.length;
		
		int len[][] = new int[m+1][n+1];//len数组存储的是a（i）与a（j）的最长公共子序列的长度，例如len[0][1]
//		表示的是 A与B，D的最长公共子序列
		
//		帮助构造最优解，即得到最长公共子序列
		char p[][] = new char[m+1][n+1];
		for(int i =0;i<=m;i++){
			len[i][0]=0;
		}
		for(int i =0;i<=n;i++){
			len[0][i]=0;
		}
		
//	 	计算两个队列最长公共子序列的长度，并记录构造LCS序列的字符
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
//				若两个字符是相同的，则直接利用i-1与j-1的子问题求解
				if(a[i-1]==b[j-1]){
					len[i][j]=len[i-1][j-1]+1;
					p[i][j]='-';
				}
				else if(len[i-1][j]>=len[i][j-1]){
					len[i][j] = len[i-1][j];
					p[i][j]='<';
				}else{
					len[i][j]=len[i][j-1];
					p[i][j]='>';				
					}
			}
		}
		System.out.println(len[m][n]+" ");
		
		
//		构造LCS
		Print_LCS(p,a,a.length,b.length);
	}
	
//	构造LCS的递归方法
	static void Print_LCS(char[][] p, char[] a, int i, int j) {
		// TODO Auto-generated method stub
		if(i==0||j==0)
			return;
//		若未-，表明此位置的元素两个队列是公共的。
		if(p[i][j]=='-'){
			Print_LCS(p, a, i-1, j-1);
			System.out.print(a[i-1]+",");
		}
//		若为<,表明此位置的元素不是公共的，而且公共元素在a（i-1）与b（j）的公共队列上
		else if(p[i][j]=='<'){
			Print_LCS(p, a, i-1, j);
		}
//		若为>,表明此位置的元素不是公共的，而且公共元素在a（i）与b（j-1）的公共队列上
		else if(p[i][j]=='>'){
			Print_LCS(p, a, i, j-1);
		}
	}

}
