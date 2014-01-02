package juzhenliancheng;

//此程序利用动态规划，求解矩阵连成问题的最优解问题，利用动态规划可以得到一个矩阵相乘顺序，使的乘法的代价最小
public class Matrix_Multiply {

	/**
	 * @param args
	 */
	/*
	 * 矩阵输入规模
	 * A1          A2        A3		A4      A5        A6
	 * 30*35     35*15		15*5	5*10  10*20 	20*25
	 */
	
//	定义输入的矩阵
	static int n=6;// 输入的矩阵规模
	static Matrix[] input = new Matrix[n+1]; 
	
	
//	设置二维数组m存储从i，到j的最小代价，如m[i][j]表示从矩阵Ai到Aj连乘的最小代价
	static int m[][] = new int[n+1][n+1];
	
//	设置二维数组s存储从i，到j的最小切割点，如s[i][j]表示矩阵Ai到Aj连乘最小代价是最有的切割点
	static int s[][] = new int [n+1][n+1];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//输入矩阵的值
		Matrix a1 = new Matrix();
		a1.s_num = 1;
		a1.r = 30;
		a1.l = 35;
		input[1]=a1;
		
		Matrix a2 = new Matrix();
		a2.s_num = 2;
		a2.r = 35;
		a2.l = 15;
		input[2]=a2;
		
		Matrix a3 = new Matrix();
		a3.s_num = 3;
		a3.r = 15;
		a3.l = 5;
		input[3]=a3;
		
		Matrix a4 = new Matrix();
		a4.s_num = 4;
		a4.r = 5;
		a4.l = 10;
		input[4]=a4;
		
		Matrix a5 = new Matrix();
		a5.s_num = 5;
		a5.r = 10;
		a5.l = 20;
		input[5]=a5;
		
		Matrix a6 = new Matrix();
		a6.s_num = 6;
		a6.r = 20;
		a6.l = 25;
		input[6]=a6;
		
//		求得最优解的代价，采用自底向上的方法
		matrixChainOrder(input);
		
//		求得最优解的代价，采用自顶向下 并且包含备忘录的方式
		int max = matrixChainOrderMemo(input);
		
		System.out.println("最优的连乘代价为："+m[2][5]);
		
		System.out.println("最优的连乘代价max为："+max);
//		求得最优求解顺序，即矩阵连乘的最优乘法顺序。
		optimalParens(2,5);
	}

//	 采取自顶向下的方式解决最优解问题
	static int matrixChainOrderMemo(Matrix[] input) {
		// TODO Auto-generated method stub
		int mf[][]= new int[n+1][n+1];
		for(int i =0;i<=n;i++){
			for(int j=i;j<=n;j++){
				mf[i][j] = Integer.MAX_VALUE;
			}
		}
		return lookUP_Chain(mf,2,5); 
	}

	static int lookUP_Chain(int[][] mf,int a,int b) {
		// TODO Auto-generated method stub
		if(mf[a][b]<Integer.MAX_VALUE)
			return mf[a][b];
		if(a==b){
			mf[a][b] = 0;
		}
		else{
			for(int i=a;i<=b-1;i++){
				int q = lookUP_Chain(mf,a, i)+lookUP_Chain(mf,i+1, b)+input[a].r*input[i].l*input[b].l;
				if(q<mf[a][b])
					mf[a][b]  = q;
			}
		}
		return mf[a][b];
		
	}

	//		得到从i到j连乘的最优计算顺序
	 static void optimalParens(int i ,int j) {
		// TODO Auto-generated method stub
		if(i==j){
			System.out.print("A"+i+" ");
		}
		else
		{
			optimalParens(i, s[i][j]);
			optimalParens(s[i][j]+1, j);
		}
	}


	static void matrixChainOrder(Matrix[] input) {
		// TODO Auto-generated method stub
		for(int i =0;i<=n;i++){
			m[i][i] = 0;
		}
//		第一重循环，求得规模链长度分别为 2，3，4....n个矩阵的最优解
		for(int i=2;i<=n;i++)
		{
//			第二重循环，求得规模长度为i的，任意规模长度为i的最优解，
//			这两重循环实际上就是确定了矩阵连乘所有的n的平方个子问题的最优解，而且确定方式是自底向上。
			for(int j=1;j<=n-i+1;j++){
				int e = j+i-1;
				m[j][e]=Integer.MAX_VALUE;
				
//				第三重循环，寻找规模长度为i的某个矩阵连乘，在求得最优解的时候，切割点是什么
				for(int k=j;k<=e-1;k++){
//					算出某个子问题即某个矩阵连乘的最小代价，等于分割成的两个矩阵的代价加上这两个矩阵合并的代价。
					int q = m[j][k]+m[k+1][e]+input[j].r*input[k].l*input[e].l;
					if(q<m[j][e]){
//						记录从Ai连乘到Aj的最优代价
						m[j][e] = q;
//						记录从Ai连乘到Aj的最优分界点。
						s[j][e] = k;
					}
				}
			}
		}
	}

}
