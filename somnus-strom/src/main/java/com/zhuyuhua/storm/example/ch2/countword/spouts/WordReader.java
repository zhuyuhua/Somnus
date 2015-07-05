package com.zhuyuhua.storm.example.ch2.countword.spouts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.zhuyuhua.storm.example.StormConstants;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 读取文本文字，并传送给spot
 * 
 * @author joe
 *
 */
public class WordReader extends BaseRichSpout {

	private static final long serialVersionUID = -3720552108663476842L;

	private SpoutOutputCollector collector;

	private FileReader fileReader;

	private boolean completed = false;

	private TopologyContext context;

	/**
	 * 我们将创建一个文件并维持一个collector对象
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		try {
			this.context = context;
			String file = conf.get(StormConstants.WORDS_FILE).toString();
			this.fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading file [" + conf.get("wordsFile") + "]");
		}
		this.collector = collector;

	}

	@Override
	public void close() {
	}

	/**
	 * 这个方法做的惟一一件事情就是分发文件中的文本行
	 */
	@Override
	public void nextTuple() {
		// 该方法会不断被调用，直到整个文件都读完，我们将等待并且返回
		if (completed) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			return;
		}

		String str;
		BufferedReader reader = new BufferedReader(fileReader);
		try {
			// 读取所有文本行
			while ((str = reader.readLine()) != null) {
				// 按行发布一个新值
				System.out.println("[emit]:" + str);
				this.collector.emit(new Values(str), str);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error reading tuple", e);
		} finally {
			completed = true;
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}

	@Override
	public void ack(Object msgId) {
		System.out.println("OK:" + msgId);
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("FAIL:" + msgId);
	}

}
