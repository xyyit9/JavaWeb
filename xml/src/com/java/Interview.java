package com.java;

//所有打印题，都要用二维数组来做
public class Interview {
	public static void main(String[] args) {
		int num = 66;
		int height = num / 4 + 1;
		int width = num;

		int arr[][] = new int[height][width];

		int x = height - 1;
		int y = 0;
		boolean flag = true;
		for (int i = 1; i <= num; i++) {
			arr[x][y] = i;
			y++;

			if (flag) {
				x--;
			} else if (!flag) {
				x++;
			}

			if (x == 0) {
				flag = false;
			}
			if (x == height - 1) {
				flag = true;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0) {
					System.out.print("  ");
				} else {
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
	}

}