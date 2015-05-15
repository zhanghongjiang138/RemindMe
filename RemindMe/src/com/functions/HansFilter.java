/************************************************************************
 *    Copyright (C) 2007 General Electric Company. All rights reserved    *
 *            File Name:HansFilter.java                           *
 *            Author Name:Tata Consultancy Services, Limited.            *
 *  Confidential and proprietary information of General Electric Company *
 ************************************************************************/

/*
 * Copyright (c) 2003 GE Power Systems. All rights reserved
 * @Application                                HAzardous characters Neutralization System
 * @FileName                  HansFilter.java
 * @Version                    1.0
 * @Author                     TATA CONSULTANCY SERVICES
 * @Creation date:           21/01/2004
 * @Purpose:                  Generic alteration class and SQL Injection
 * @Classes                   HansFilter
 */

// package  name
package com.functions;

import java.net.URLDecoder;

import org.apache.xerces.utils.regex.RegularExpression;

import com.ge.dsp.dsi.logging.LoggerService;
import com.ge.energy.common.constants.AppConstants;
import com.ge.energy.common.logging.LoggingServiceFactory;

// TODO: Auto-generated Javadoc
/**
 * This class is the generic html encoding class.
 * 
 * @author TCS
 * @version 1.0
 */
public class HansFilter {

	/** The Constant //LOGGER. */

	/** The l sql filters. */
	private static String[] lSqlFilters = { "\\w*('|\\-\\-)\\w*", "\\w*[ ]*JAVASCRIPT\\w*", "\\w*<[ ]*SCRIPT\\w*", "\\w*<[ ]*/*SCRIPT[ ]*>*\\w*", "\\w*<[ ]*META\\w*", "\\w*<[ ]*IMG\\w*",
			"\\w*!--*\\w*", "\\w*'\\s*or", "\\w*'\\s*union", "\\w*'\\s*insert", "\\w*'\\s*select", "\\w*'\\s*update", "\\w*'\\s*delete", "\\w*'\\s*drop", "\\w*\\s*char@39A\\+@SELECT",
			"\\w*\\s*char@.*@SELECT" };

	/** The gen sql filters. */
	private static String[] genSqlFilters = { "\\w*[ ]*JAVASCRIPT\\w*", "\\w*<[ ]*SCRIPT\\w*", "\\w*<[ ]*/*SCRIPT[ ]*>*\\w*", "\\w*!--*\\w*", "\\w*'\\s*or", "\\w*'\\s*union", "\\w*'\\s*insert",
			"\\w*'\\s*select", "\\w*'\\s*update", "\\w*'\\s*delete", "\\w*'\\s*drop", "\\w*\\s*char@39A\\+@SELECT", "\\w*\\s*char@.*@SELECT" };

	/** The special chars. */
	private static String[] specialChars = { ">", "\"", "<", "'" };
	/** The special chars in excel. */
	private static String[] excelSpecialChars = { ">", "\"", "<", "'", "," };

	/** The replacement. */
	private static String[] replacement = { "&gt;", "&quot;", "&lt;", "&#39;" };
	/** The special chars in excel. */
	private static String[] excelReplacement = { "&gt;", "&quot;", "&lt;", "&#39;", "&#044;" };

	static LoggerService LOGGER = LoggingServiceFactory.getDefaultService();

	/**
	 * This method replaces all occurrences of matched pattern with the
	 * replacement string.
	 * 
	 * @param saInput
	 *            the sa input
	 * @param saMatchPattern
	 *            the sa match pattern
	 * @param saReplaceString
	 *            the sa replace string
	 * 
	 * @return the string
	 */
	public static String replaceAllOccurrences(String saInput, String saMatchPattern, String saReplaceString) {
		if ( (null == saInput) || (saInput.indexOf(saMatchPattern) == AppConstants.NEG_ONE) || (null == saMatchPattern) || (saMatchPattern.length() <= 0)) {
			return saInput;
		}

		String slInput = saInput;

		// StringBuffer sblTemp = new StringBuffer();

		// int ilIndex = slInput.indexOf(saMatchPattern);

		StringBuffer sblTemp = new StringBuffer(slInput);
		int ilIndex = sblTemp.toString().indexOf(saMatchPattern);

		while (ilIndex >= 0) {
			sblTemp = sblTemp.delete(ilIndex, saMatchPattern.length() + ilIndex);
			sblTemp = sblTemp.insert(ilIndex, saReplaceString);

			ilIndex = sblTemp.toString().indexOf(saMatchPattern);
		}

		return sblTemp.toString();
	}

	/**
	 * Method alter alters the special characters into html equivalents.
	 * 
	 * @param saParams
	 *            the sa params
	 * 
	 * @return the string[]
	 */
	public static String[] alter(String[] saParams) {
		if (null == saParams) {
			return saParams;
		}

		String[] slParams = new String[saParams.length];
		int saLen = saParams.length;
		for (int ilCounter = 0; ilCounter < saLen; ilCounter++) {
			slParams[ilCounter] = alter(saParams[ilCounter]);
		}

		return slParams;
	}

	/**
	 * This method is a dummy variable, which does not alter any thing IN case
	 * an integer variable is used, it will return it back.
	 * 
	 * @param iaParam
	 *            the ia param
	 * 
	 * @return int
	 */
	public static int alter(int iaParam) {
		return iaParam;
	}

	/**
	 * Method unalter unalters html equivalents into the special characters.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return the string
	 */
	public static String unalter(String saInput) {
		if (null == saInput) {
			return "";
		}

		try {
			String slInputTmp = saInput;
			int specialLen = specialChars.length;
			for (int ilCounter = 0; ilCounter < specialLen; ilCounter++) {
				slInputTmp = replaceAllOccurrences(slInputTmp, replacement[ilCounter], specialChars[ilCounter]);
			}

			return slInputTmp;
		} catch (Exception e) {
			LOGGER.info("Exception:" + e);
			// LOGGER.error(e);
		}
		return "";
	}
	
	/**
	 * Method unalter unalters excel equivalents into the special characters.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return the string
	 */
	public static String excelunalter(String saInput) {
		if (null == saInput) {
			return "";
		}

		try {
			String slInputTmp = saInput;
			int specialLen = specialChars.length;
			for (int ilCounter = 0; ilCounter < specialLen; ilCounter++) {
				slInputTmp = replaceAllOccurrences(slInputTmp, excelReplacement[ilCounter], excelSpecialChars[ilCounter]);
			}

			return slInputTmp;
		} catch (Exception e) {
			LOGGER.info("Exception:" + e);
			// LOGGER.error(e);
		}
		return "";
	}

	/**
	 * Method alter alters the special characters into html equivalents.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return String
	 */
	public static String alter(String saInput) {
		if (null == saInput) {
			return null;
		}

		try {
			String slInputTmp = saInput;
			int specialLen = specialChars.length;
			for (int ilCounter = 0; ilCounter < specialLen; ilCounter++) {
				slInputTmp = replaceAllOccurrences(slInputTmp, specialChars[ilCounter], replacement[ilCounter]);
			}

			return slInputTmp;
		} catch (Exception e) {
			// LOGGER.error(e);
			LOGGER.info("Exception:" + e);
		}

		return "";
	}

	/**
	 * This will check if any param has special character patterns Return false,
	 * it is has. Returns true, if not.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return boolean
	 */
	public static boolean checkInput(String saInput) {
		return checkGenericPattern(saInput) && checkGenericPattern(unalter(saInput));
	}

	/**
	 * Method checkInputAlert checks for SQL Injection.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return boolean
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static boolean checkInputAlert(String saInput) throws Exception {
		boolean bool = checkInput(saInput);

		if (! bool) {
			StringBuffer sb = new StringBuffer("Hazardous characters found in the input: ");
			sb.append("\"").append(HansFilter.alter(saInput)).append("\"");

			throw new Exception(sb.toString());
		}

		return true;
	}

	/**
	 * Method checkInputAlert checks for SQL Injection This variation also
	 * checks for the length of variable.
	 * 
	 * @param saInput
	 *            the sa input
	 * @param ilLength
	 *            the il length
	 * 
	 * @return boolean
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static boolean checkInputAlert(String saInput, int ilLength) throws Exception {
		if ( (saInput != null) && (saInput.length() > ilLength)) {
			throw new Exception("The parameter's size is more than allowed length for " + saInput);
		}

		return checkInputAlert(saInput);
	}

	/**
	 * This method checks for all SQL keyword occurrences and returns false if
	 * any such input is found.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return boolean
	 */
	private static boolean checkGenericPattern(String paramSaInput) {
		String saInput = paramSaInput;
		if (null != saInput) {
			try {
				saInput = URLDecoder.decode(saInput);
			} catch (Exception e) {
				// LOGGER.error("Problem in URLDecode saInput is " + saInput);
				// LOGGER.error(e);
				LOGGER.info("Exception:" + e);
			}

			RegularExpression regex = null;
			try {
				for (int ilCounter = 0; ilCounter < lSqlFilters.length; ilCounter++) {
					regex = new RegularExpression(lSqlFilters[ilCounter], "i");

					if (regex.matches(saInput)) {
						return false;
					}
					// if
				}
			}
			// for
			catch (Exception e) {
				LOGGER.info("Exception" + e);
			}

		}
		// if not null

		return true;
	}

	/**
	 * This method checks for all SQL keyword occurrences and returns false if
	 * any such input is found.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return boolean
	 */
	private static boolean checkGenericPatternAll(String paramSaInput) {
		String saInput = paramSaInput;
		if (null != saInput) {
			try {
				saInput = URLDecoder.decode(saInput);
			} catch (Exception e) {
				// LOGGER.error("Problem in URLDecode saInput is " + saInput);
				// LOGGER.error(e);
				LOGGER.info("Exception:" + e);
			}

			RegularExpression regex = null;
			int genLen = genSqlFilters.length;
			try {
				for (int ilCounter = 0; ilCounter < genLen; ilCounter++) {
					regex = new RegularExpression(genSqlFilters[ilCounter], "i");

					if (regex.matches(saInput)) {
						return false;
					}
					// if
				}
			}
			// for
			catch (Exception e) {
				LOGGER.info("Exception" + e);
			}

		}
		// if not null

		return true;
	}

	/**
	 * This will check if any param has special character patterns Return false,
	 * it is has. Returns true, if not.
	 * 
	 * @param saInput
	 *            the sa input
	 * 
	 * @return boolean
	 */
	public static boolean checkInputAll(String saInput) {
		return checkGenericPatternAll(saInput) && checkGenericPatternAll(unalter(saInput));
	}

	/**
	 * toString
	 */

	public String toString() {
		return super.toString();
	}

}
