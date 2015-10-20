package com.somnus.core.factory;

import static org.junit.Assert.*;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.junit.Test;

import com.somnus.core.hbase.factory.DefaultHBaseFactory;
import com.somnus.core.hbase.factory.HBaseFactory;

public class KarmaHBaseFactoryTest {

	private HBaseFactory karmaHBaseFactory;
	private HTableInterface hTable;
	
	@Test
	public void testKarmaHBaseFactory() {
		karmaHBaseFactory=new DefaultHBaseFactory();
	}

	@Test
	public void testGetHTable() {
		karmaHBaseFactory=new DefaultHBaseFactory();
	    hTable = karmaHBaseFactory.getHTable("testa");
		
	}

//	@Test
//	public void testCloseHTable() {
//		karmaHBaseFactory=new KarmaHBaseFactory();
//		karmaHBaseFactory.closeHTable(hTable);
//	}
//
//	@Test
//	public void testDeleteTable() {
//		karmaHBaseFactory=new KarmaHBaseFactory();
//		karmaHBaseFactory.deleteTable("testa");
//		karmaHBaseFactory.destroy();
//	}
//
//	@Test
//	public void testCreateTable() {
////		karmaHBaseFactory=new KarmaHBaseFactory();
////		karmaHBaseFactory.createTable("hahaha", "t1", 1);
//	}

	@Test
	public void testGetHBaseAdmin() {
		fail("Not yet implemented");
	}

}
