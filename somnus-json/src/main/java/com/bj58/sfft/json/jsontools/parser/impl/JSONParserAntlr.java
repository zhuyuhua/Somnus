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
package com.bj58.sfft.json.jsontools.parser.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONParserAntlr extends LLkParser implements JSONParserAntlrTokenTypes {
	public static final String[] _tokenNames = { "<0>", "EOF", "<2>", "NULL_TREE_LOOKAHEAD", "TRUE", "FALSE", "NULL",
			"STRING", "NUMBER", "LCURLY", "COLON", "COMMA", "RCURLY", "LBRACK", "RBRACK", "LPAREN", "RPAREN", "QUOTES",
			"ESC", "HEX_DIGIT", "ZERO", "NONZERO", "DIGIT", "INTEGER", "EXPONENT", "WS", "SL_COMMENT" };

	protected JSONParserAntlr(TokenBuffer tokenBuf, int k) {
		super(tokenBuf, k);
		this.tokenNames = _tokenNames;
	}

	public JSONParserAntlr(TokenBuffer tokenBuf) {
		this(tokenBuf, 1);
	}

	protected JSONParserAntlr(TokenStream lexer, int k) {
		super(lexer, k);
		this.tokenNames = _tokenNames;
	}

	public JSONParserAntlr(TokenStream lexer) {
		this(lexer, 1);
	}

	public JSONParserAntlr(ParserSharedInputState state) {
		super(state, 1);
		this.tokenNames = _tokenNames;
	}

	public final JSONValue value(String aStreamName) throws RecognitionException, TokenStreamException {
		JSONValue val = JSONNull.NULL;

		switch (LA(1)) {
		case 9:
			val = object(aStreamName);
			break;
		case 13:
			val = array(aStreamName);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			val = atomic(aStreamName);
			break;
		case 10:
		case 11:
		case 12:
		default:
			throw new NoViableAltException(LT(1), getFilename());
		}

		return val;
	}

	public final JSONObject object(String aStreamName) throws RecognitionException, TokenStreamException {
		JSONObject lResult = new JSONObject();

		Token begin = null;
		Token i = null;
		Token j = null;

		begin = LT(1);
		match(9);
		lResult.setLineCol(begin.getLine(), begin.getColumn());
		lResult.setStreamName(aStreamName);
		JSONValue lVal;
		switch (LA(1)) {
		case 7:
			i = LT(1);
			match(7);
			match(10);
			lVal = value(aStreamName);
			lResult.getValue().put(i.getText(), lVal);
		case 12:
		}

		while (LA(1) == 11) {
			match(11);
			j = LT(1);
			match(7);
			match(10);
			lVal = value(aStreamName);
			lResult.getValue().put(j.getText(), lVal);

			continue;

			break;

			throw new NoViableAltException(LT(1), getFilename());
		}

		match(12);
		return lResult;
	}

	public final JSONArray array(String aStreamName) throws RecognitionException, TokenStreamException {
		JSONArray lResult = new JSONArray();

		Token begin = null;

		begin = LT(1);
		match(13);
		lResult.setLineCol(begin.getLine(), begin.getColumn());
		lResult.setStreamName(aStreamName);
		JSONValue lVal;
		switch (LA(1)) {
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 13:
			lVal = value(aStreamName);
			lResult.getValue().add(lVal);
		case 14:
		case 10:
		case 11:
		case 12:
		}
		while (LA(1) == 11) {
			match(11);
			lVal = value(aStreamName);
			lResult.getValue().add(lVal);

			continue;

			break;

			throw new NoViableAltException(LT(1), getFilename());
		}

		match(14);
		return lResult;
	}

public final JSONValue atomic(String aStreamName)
  throws RecognitionException, TokenStreamException
{
  JSONValue val = JSONNull.NULL;

  Token t = null;
  Token f = null;
  Token n = null;
  Token str = null;
  Token num = null;

  switch (LA(1))
  {
  case 4:
    t = LT(1);
    match(4);
    val = new JSONBoolean(true); val.setLineCol(t.getLine(), t.getColumn()); val.setStreamName(aStreamName);
    break;
  case 5:
    f = LT(1);
    match(5);
    val = new JSONBoolean(false); val.setLineCol(f.getLine(), f.getColumn()); val.setStreamName(aStreamName);
    break;
  case 6:
    n = LT(1);
    match(6);
    val.setLineCol(n.getLine(), n.getColumn()); val.setStreamName(aStreamName);
    break;
  case 7:
    str = LT(1);
    match(7);
    val = new JSONString(str.getText()); val.setLineCol(str.getLine(), str.getColumn()); val.setStreamName(aStreamName);
    break;
  case 8:
    num = LT(1);
    match(8);
    String lTxt = num.getText().toLowerCase();
    if ((lTxt.indexOf('e') >= 0) || (lTxt.indexOf('.') >= 0)) {
		val = new JSONDecimal(new BigDecimal(lTxt));
	} else {
		val = new JSONInteger(new BigInteger(lTxt));
	}
    val.setLineCol(num.getLine(), num.getColumn());
    val.setStreamName(aStreamName);
    break;
  default:
    throw new NoViableAltException(LT(1), getFilename());
  }

  return val;
}
