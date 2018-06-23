/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.test.TestEcho;
import net.jeremybrooks.jinx.response.test.TestLogin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class TestApiTest {
  private static TestApi testApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(TestApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
    String filename = p.getProperty("path.to.pro.oauth.token");
    assertNotNull(filename);
    File file = new File(filename);
    assertTrue(file.exists());
    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));
    assertNotNull(oAuthAccessToken);
    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        jinx.setVerboseLogging(true);
    JinxLogger.setLogger(new StdoutLogger());
    testApi = new TestApi(jinx);
  }

  @Test
  public void testEcho() throws Exception {
    TestEcho testEcho = testApi.testEcho();
    assertNotNull(testEcho);
    assertEquals("json", testEcho.getFormat());
    assertEquals("flickr.test.echo", testEcho.getMethod());
    assertEquals("ok", testEcho.getStat());
  }

  @Test
  public void testLogin() throws Exception {
    TestLogin testLogin = testApi.testLogin();
    assertNotNull(testLogin);
    assertNotNull(testLogin.getUserId());
    assertNotNull(testLogin.getUsername());
  }

  @Test
  public void testNull() throws Exception {
    Response response = testApi.testNull();
    assertNotNull(response);
    assertEquals("ok", response.getStat());
  }
}
