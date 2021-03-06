package CurrencyRates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class FindCurrencyAmt {

	int responseCode;
	static private String dateOfXml = "";
	static private double amt;
	static private List<String> ccyList = new ArrayList<String>();

	public FindCurrencyAmt(String date, String ccy) throws IOException {

		connectAppy(date, ccy);
	}

	private void connectAppy(String date, String curency) throws IOException {

		String url = "http://old.lb.lt/webservices/fxrates/FxRates.asmx/getFxRates?tp=EU&dt=" + date;
		String xml = crunchifyGetURLContents(url);
		// System.out.println(xml);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		responseCode = con.getResponseCode();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xml)));

			int sumOfFxRate = (int) countAllFxrate(document);
			amt = getRateOfCurency(document, sumOfFxRate, curency);
			ccyList.clear();
			ccyList = getAllCcyOfxml(document, sumOfFxRate);

		} catch (Exception e) {

			System.out.println("nėra ryšio su URL:" + url);
		}

	}

	private List<String> getAllCcyOfxml(Document document, int sumOfFxRate) throws XPathExpressionException {

		for (int i = 1; i <= sumOfFxRate; i++) {

			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/FxRates//FxRate[" + i + "]//CcyAmt[2]//Ccy");
			String ccy = expr.evaluate(document, XPathConstants.STRING).toString();

			ccyList.add(ccy);
		}

		return ccyList;
	}

	private static double getRateOfCurency(Document document, int sumOfFxRate, String curency)
			throws XPathExpressionException {
		double amt1 = 0.0;
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();

		for (int j = 1; j <= sumOfFxRate; j++) {

			XPathExpression expr2 = xpath.compile("FxRates//FxRate[" + j + "]//CcyAmt[2]//Ccy");
			String ccy = expr2.evaluate(document, XPathConstants.STRING).toString();

			if (ccy.equals(curency)) {
				XPathExpression expr3 = xpath.compile("/FxRates//FxRate[" + j + "]//CcyAmt[2]//Amt");
				String amt = expr3.evaluate(document, XPathConstants.STRING).toString();

				XPathExpression expr = xpath.compile("/FxRates//FxRate[" + j + "]/Dt");
				dateOfXml = expr.evaluate(document, XPathConstants.STRING).toString();
				amt1 = Double.parseDouble(amt);
				break;
			}
		}
		return amt1;
	}

	private static double countAllFxrate(Document document) throws XPathExpressionException {

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression exprr = xpath.compile("count(//FxRates/FxRate)");
		double result = (double) exprr.evaluate(document, XPathConstants.NUMBER);
		Double count = result;

		return count;

	}

	public static String crunchifyGetURLContents(String myURL) {

		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 10000000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);

					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("nėra ryšio su URL:" + myURL);

		}

		return sb.toString();
	}

	public double getAmt() {
		return amt;
	}

	public String getCcyDate() {
		return dateOfXml;
	}

	public List<String> getCcyList() {
		return ccyList;
	}

	public int getResponeCode() {
		return responseCode;
	}

}
