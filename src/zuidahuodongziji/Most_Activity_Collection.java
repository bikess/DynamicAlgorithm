package zuidahuodongziji;
// 此类是用动态规划的方式，来求解最大活动子集问题，当然可以不必用动态规划而采用贪心算法
public class Most_Activity_Collection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		所有活动已经按结束时间进行了排序
		Activity my[] = new Activity[12];
		my[1] = new Activity(1, 1, 4);
		my[2] = new Activity(2, 3, 5);
		my[3] = new Activity(3, 0, 6);
		my[4] = new Activity(4, 5, 7);
		my[5] = new Activity(5, 3, 9);
		my[6] = new Activity(6, 5, 9);
		my[7] = new Activity(7, 6, 10);
		my[8] = new Activity(8, 8, 11);
		my[9] = new Activity(9, 8, 12);
		my[10] = new Activity(10, 2,14);
		my[11] = new Activity(11, 12, 16);
		
//		数组才c[i][j]，用于记录活动ai结束之后，aj开始之前，的最大活动子集个数
		int n = my.length+1;
		int c[][] = new int[n][n];
		
		for(int i = 1;i<n;i++){
			c[i][i]=1;
			for(int j=1;j<n;j++){
				if(my[i].end>my[j].begin)
					c[i][j]=0;
			}
		}

 	}

}







