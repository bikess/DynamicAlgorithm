package zuiyousousuoshu;

// 此方法是，求解最优二叉搜索树的算法
public class Optimal_BST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 存储关键字的数组
		int keyword [] = {0,1,2,3,4,5};
//		 存储查找每个关键字对应的概率
		float keyP [] = new float [6];
		keyP[1]=(float) 0.15;
		keyP[2]=(float) 0.1;
		keyP[3]=(float) 0.05;
		keyP[4]=(float) 0.1;
		keyP[5]=(float) 0.2;
//		 存储查找每个非关键字对应的概率
		float keyQ[] = new float [6];
		keyQ[0]=(float) 0.05;
		keyQ[1]=(float) 0.1;
		keyQ[2]=(float) 0.05;
		keyQ[3]=(float) 0.05;
		keyQ[4]=(float) 0.05;
		keyQ[5]=(float) 0.1;
		
//		我们首先利用动态规划的思想，计算关键字1到关键字n的最优期望值，即最优搜索代价
		
//	 	设置数组，来存储关键字i到关键字j的最优期望值
		//关键字的个数
		int n = keyword.length-1;
		float e[][] = new float[n+2][n+1];
//		设置数组，来存储关键字i到关键字j的所有概率的值
		float w[][] = new float[n+2][n+1];
		
//		设置数组，来存储关键字i到关键字j的根
		int root[][] = new int[n+1][n+1];
		for(int i =1 ;i<=n+1;i++){
//			表示当j=i-1时候，有关键在i到关键字i-1组成的子树的最优期望值，由于此时没有关键字，只有伪关键字，期望值就是为关键字i-1的概率值
			e[i][i-1]=keyQ[i-1];
//			同时关键字j到关键字i的关键字与伪关键字的所有概率的和为
			w[i][i-1]=keyQ[i-1];
		}
		
//		下面再来计算当j>i-1时候的最优期望值
// 	第1,2层循环是，求取任何关键字i到关键字j的最优搜索树
//		其中第一层，是循环关键字i与关键字j之间的间隔分别为0,1,2,3，。。。n-1个关键字
		for(int l=1;l<=n;l++){
//			第2层，是当间隔为l的任意2个关键字之间的最优搜索树
			for(int i=1;i<=n-l+1;i++){
				int j = i+l-1;
				e[i][j]=Float.MAX_VALUE;
				w[i][j]=w[i][j-1]+keyP[j]+keyQ[j];
//				第三层循环，从i到j之间取一个关键字作为根，使的从关键字i到关键字j建立的搜索树代价最低
				for(int r=i;r<=j;r++){
					float t = e[i][r-1]+e[r+1][j]+w[i][j];
					if(t<e[i][j]){
						e[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
		System.out.println(e[1][n]);
	}

}
