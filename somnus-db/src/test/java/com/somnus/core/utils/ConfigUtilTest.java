package com.somnus.core.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.somnus.core.bigdata.utils.ConfigUtil;

public class ConfigUtilTest {

	@Test
	public void testGetConfiguration() throws IOException {
		 ConfigUtil.getHBConfig();
	}

}
