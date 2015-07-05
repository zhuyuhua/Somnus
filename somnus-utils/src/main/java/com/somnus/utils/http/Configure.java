package com.somnus.utils.http;

public class Configure {

	public interface SEND_ERROR {
		final int SEND_SUCCESS = 0;
		final int SEND_DEFAULT = -1;
		final int SCHEDULE_CANCELLED = 9;
		final int API_RESPONSE_EMPTY = -10;

		final int SCHEDULE_TIME_FORMAT_ERROR = 8001;
		final int SEND_PLATFORM_ERROR = 8002;

		final int SEND_READ_TIME_OUT = -12; //Read Time Out

		final int SEND_ERROR_RICH_RESOURCE_OVERLENGTH = 8003; // ��ý����Դ��������
		final int SEND_ERROR_RICH_RESOURCE_TYPE = 8004; // ��ý����Դ���ʹ���

	}
}
