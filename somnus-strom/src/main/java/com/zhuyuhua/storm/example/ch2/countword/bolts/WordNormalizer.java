package com.zhuyuhua.storm.example.ch2.countword.bolts;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 第一个bolts：负责得到并标准化每行文本。它把文本行切分成单词，大写转化成小写，去掉头尾空白符。
 * 
 * @author zhuyh
 */
public class WordNormalizer extends BaseBasicBolt {

	private static final long serialVersionUID = 1366081866255942300L;

	/**
	 * bolt从单词文件接收到文本行，并标准化它。 文本行会全部转化成小写，并切分它，从中得到所有单词。
	 */
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String sentence = input.getString(0);
		String[] words = sentence.split(" ");
		for (String word : words) {
			word = word.trim();
			if (!word.isEmpty()) {
				word = word.toLowerCase();
				collector.emit(new Values(word));
			}
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

}
