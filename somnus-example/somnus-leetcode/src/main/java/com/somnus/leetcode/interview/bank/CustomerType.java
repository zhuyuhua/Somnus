package com.somnus.leetcode.interview.bank;

public enum CustomerType {
	COMMON, EXPRESS, VIP;

	@Override
	public String toString() { // switch 接收类型为int 或 Enum 类型
		switch (this) {
		case COMMON:
			return "普通";
		case EXPRESS:
			return "快速";
		}
		return "VIP";
	}
}
