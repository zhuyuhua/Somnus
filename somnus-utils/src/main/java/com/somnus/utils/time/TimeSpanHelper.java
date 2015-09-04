/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.utils.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class TimeSpanHelper {

	private static Logger logger = LoggerFactory.getLogger(TimeSpanHelper.class);

	public static int getIntFromTimeSpan(String timeSpan) {
		int returnint = 0;
		String[] times = timeSpan.split(":");
		if (times.length == 3) {
			returnint += Integer.parseInt(times[0]) * 60 * 60 * 1000;
			returnint += Integer.parseInt(times[1]) * 60 * 1000;
			returnint += Integer.parseInt(times[2]) * 1000;
		}
		return returnint;
	}

	/**
	 * 00(秒) 00:00(分:秒) 00:00:00(时:分:秒) 00:00:00:00(时:分:秒:毫秒)
	 *
	 * @param timeSpan
	 * @return
	 */
	public static int getIntFromTimeMsSpan(String timeSpan) {
		int returnint = 0;
		String[] times = timeSpan.split(":");

		switch (times.length) {
		case 1:
			returnint += Integer.parseInt(times[0]) * 1000;
			break;
		case 2:
			returnint += Integer.parseInt(times[0]) * 60 * 1000;
			returnint += Integer.parseInt(times[1]) * 1000;
			break;
		case 3:
			returnint += Integer.parseInt(times[0]) * 60 * 60 * 1000;
			returnint += Integer.parseInt(times[1]) * 60 * 1000;
			returnint += Integer.parseInt(times[2]) * 1000;
			break;
		case 4:
			returnint += Integer.parseInt(times[0]) * 60 * 60 * 1000;
			returnint += Integer.parseInt(times[1]) * 60 * 1000;
			returnint += Integer.parseInt(times[2]) * 1000;
			returnint += Integer.parseInt(times[3]);
			break;
		}

		return returnint;
	}

}
