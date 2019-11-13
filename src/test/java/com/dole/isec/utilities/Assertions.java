/**
 * 
 */
package com.dole.isec.utilities;

import org.testng.Assert;

/**
 * @author alagappan.n
 *
 */
public class Assertions {
	
	public static void verifyText(String actualText, String expectedText, String message)
	{
		Assert.assertEquals(actualText,expectedText,message);
	}
	
	public static void verifyNull(boolean status, String message)
	{
		Assert.assertNull(status,  message);
	}
	
	public static void verifyNotNull(boolean status, String message)
	{
		Assert.assertNotNull(status, message);
	}
	
	public static void verifyTrue(boolean status)
	{
		Assert.assertTrue(status, "The status is false");
	}
	
	public static void verifyFalse(boolean status)
	{
		Assert.assertFalse(status, "The status is true");
	}
	
	public static void markFail()
	{
		Assert.assertTrue(false);
	}
	
	public static void markPass()
	{
		Assert.assertTrue(true);
	}
	

}
