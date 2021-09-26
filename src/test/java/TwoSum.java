import java.nio.charset.StandardCharsets;

public class TwoSum {

	public static void main(String[] args) {
		// testTwoSum();
		// testMidNumber();
		testMaxLength();
	}

	private static void testMaxLength() {
		// int result = MaxLength.maxLength("abcabcab");
		// System.out.println("result = " + result);
		// result = MaxLength.maxLength("abcdbefgh");
		// System.out.println("result = " + result);
		// result = MaxLength.maxLength("bbbbb");
		// System.out.println("result = " + result);
		// result = MaxLength.maxLength("pwwkew");
		// System.out.println("result = " + result);
		// result = MaxLength.maxLength("");
		// System.out.println("result = " + result);
		int result = MaxLength.maxLength("aabaab!bb");
		// int result = MaxLength.maxLength("aabbccde");
		System.out.println("result = " + result);
	}

	static class MaxLength {
		public static int maxLength(String str) {
			char[] chars = new char[str.length()];
			str.getChars(0, str.length(), chars, 0);
			int length = 0;
			int max = 0;
			int pos = 0;
			for (int i = 0; i < chars.length; i++) {
				// System.out.println("pos = " + pos);
				boolean duplicate = false;
				char aChar = chars[i];
				int j = pos;
				while (j < i) {
					char temp = chars[j];
					if (temp == aChar) {
						duplicate = true;
						if (j == i -1){
							pos = i;
						} else {
							pos = j;
							length = length - pos;
						}
						break;
					}
					j++;
				}
				if (duplicate == false) {
					length++;
					if (max < length) {
						max = length;
					}
				}
			}
			return max;
		}
	}

	private static void testMidNumber() {

		// double retVal = new MidNumber().findMedianSortedArrays(new int[] {1, 3}, new int[] {2});
		// System.out.println("retVal = " + retVal);

		double retVal = new MidNumber().findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4});
		System.out.println("retVal = " + retVal);

		// new MidNumber().findMedianSortedArrays(new int[]{1,3}, new int[]{2});
	}

	static class MidNumber {
		public double findMedianSortedArrays(int[] nums1, int[] nums2) {
			int nl1 = nums1.length;
			int nl2 = nums2.length;
			if (nl1 == 0 && nl2 > 0) {
				if (nl2 % 2 == 1) {
					return nums2[nl2];
				} else {
					return (nums2[nl2] + nums2[nl2 - 1]) / 2.0;
				}
			}
			if (nl2 == 0 && nl1 > 0) {
				if (nl1 % 2 == 1) {
					return nums1[nl1];
				} else {
					return (nums1[nl1] + nums1[nl1 - 1]) / 2.0;
				}
			}

			if (nl1 > 0 && nl2 > 0) {
				int mid = (nl1 + nl2) / 2;
				int midSub = 0;
				boolean odd = false;
				if (mid % 2 == 1) {
					midSub = mid / 2;
					odd = true;
				}
				if (mid % 2 == 0) {
					midSub = mid / 2;
					odd = false;
				}
				int n1 = nums1[0];
				int n2 = nums2[0];
				if (n1 >= n2) {
					int i1 = 0, i2 = 0;
					while (i1 + i2 != mid) {
						if (nums1[i1] >= nums2[i2]) {
							if (i2 < nl2) {
								i2++;
							} else {
								i1++;
							}

						} else {
							if (i1 < nl1) {
								i1++;
							} else {
								i2++;
							}
						}
					}
					if (odd) {
						if (i1 > i2) {
							return nums1[i1];
						} else {
							return nums2[i2];
						}
					} else {
						if (i1 > i2 && i1 - 1 == i2) {
							return (nums1[i1] + nums2[i2]) / 2.0;
						}
						if (i1 > i2 && i1 - 1 != i2) {
							return (nums1[i1] + nums1[i1 - 1]) / 2.0;
						}
						if (i1 < i2 && i2 - 1 == i1) {
							return (nums1[i2] + nums2[i1]) / 2.0;
						}
						if (i1 < i2 && i1 - 1 != i2) {
							return (nums1[i2] + nums1[i2 - 1]) / 2.0;
						}
					}

				}
				if (n1 < n2) {
					int i1 = 0, i2 = 0;
					while (i1 + i2 != mid) {
						if (nums1[i1] < nums2[i2]) {
							if (i1 < nl1) {
								i1++;
							} else {
								i2++;
							}

						} else {
							if (i2 < nl2) {
								i2++;
							} else {
								i1++;
							}
						}
					}
					if (odd) {
						if (i1 > i2) {
							return nums1[i1];
						} else {
							return nums2[i2];
						}
					} else {
						if (i1 == i2) {
							return nums2[i2 - 1];
						}
						if (i1 > i2 && i1 - 1 == i2) {
							return (nums1[i1] + nums2[i2]) / 2.0;
						}
						if (i1 > i2 && i1 - 1 != i2) {
							return (nums1[i1] + nums1[i1 - 1]) / 2.0;
						}
						if (i1 < i2 && i2 - 1 == i1) {
							return (nums1[i2] + nums2[i1]) / 2.0;
						}
						if (i1 < i2 && i1 - 1 != i2) {
							return (nums1[i2] + nums1[i2 - 1]) / 2.0;
						}
					}
				}
			}
			return 0.0;
		}
	}

	private static void testTwoSum() {
		Solution solution = new Solution();
		// ListNode l1 = new ListNode(0);
		// ListNode l2 = new ListNode(0);
		// // 2 4 3
		ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		// 5 6 4
		ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

		// 9 9 9 9 9 9 9
		// ListNode l1 = new ListNode(9,
		// 	new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
		// // 9 9 9 9
		// ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));

		ListNode listNode = solution.addTwoNumbers(l1, l2);
		for (ListNode h = listNode; h != null; h = h.next) {
			System.out.print(h.val + " ");
		}
		System.out.println();
	}

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 */
	static class Solution {

		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			ListNode head1 = l1;
			ListNode head2 = l2;
			ListNode result = null;
			ListNode head = null;
			ListNode newNode = null;
			boolean addOne = false;
			int sumVal = 0;
			int newVal = 0;
			while (head1 != null || head2 != null) {
				int val1 = head1 == null ? 0 : head1.val;
				int val2 = head2 == null ? 0 : head2.val;

				if (addOne == false) {
					sumVal = val1 + val2;
				} else {
					sumVal = val1 + val2 + 1;
				}
				if (sumVal >= 10) {
					newVal = sumVal % 10;
					addOne = true;
				} else {
					newVal = sumVal;
					addOne = false;
				}
				if (result == null) {
					result = new ListNode(newVal, null);
					head = result;
				} else {
					newNode = new ListNode(newVal, null);
					result.next = newNode;
					result = newNode;
				}
				head1 = head1 == null ? null : head1.next;
				head2 = head2 == null ? null : head2.next;
			}
			if (addOne == true) {
				result.next = new ListNode(1);
			}
			return head;
		}
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

}
