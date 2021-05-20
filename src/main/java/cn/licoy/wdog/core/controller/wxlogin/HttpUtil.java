package cn.licoy.wdog.core.controller.wxlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
public class HttpUtil {

    private static String charset = "utf-8";
    private Integer connectTimeout = null;
    private Integer socketTimeout = null;
    private static String proxyHost = null;
    private static Integer proxyPort = null;
    private static HttpClient httpClient = null;

    private static GetMethod getMethod = null;

    private static PostMethod postMethod = null;

    /**
     * POST请求 json
     * @param url
     * @param params
     * @return
     */
    public static String doPostJson(String url, String params,boolean header) {
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            PostMethod postMethod = new PostMethod(url);
            if(header){
                postMethod.setRequestHeader("Content-Type","application/json");
                postMethod.setRequestHeader("Authorization","Basic YWRtaW46YWRtaW4=");
            }
            if(params != null && !params.trim().equals("")) {
                RequestEntity requestEntity = new StringRequestEntity(params,"application/json","UTF-8");
                postMethod.setRequestEntity(requestEntity);
            }
            postMethod.releaseConnection();
            httpClient.executeMethod(postMethod);
            BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while((str = reader.readLine())!=null){
                stringBuffer.append(str);
            }
            System.out.println("发送POST_JSON请求");
            System.out.println("URL："+url);
            System.out.println("参数："+params);
            System.out.println("响应结果："+stringBuffer.toString());
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送Get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        URL localURL = new URL(url);
        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        System.out.println("发送GET请求");
        System.out.println("URL："+url);
        System.out.println("响应结果："+resultBuffer.toString());
        return resultBuffer.toString();
    }

    /**
     * 发送Post请求
     *
     * @param url
     * @param parameterMap
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map parameterMap) throws Exception {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        URL localURL = new URL(url);
        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.length()));
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(parameterBuffer.toString());
            outputStreamWriter.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        System.out.println("发送POST请求");
        System.out.println("URL："+url);
        System.out.println("参数："+parameterBuffer.toString());
        System.out.println("响应结果："+resultBuffer.toString());
        return resultBuffer.toString();
    }

    private static URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;
        if (proxyHost != null && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            connection = localURL.openConnection(proxy);
        } else {
            connection = localURL.openConnection();
        }
        return connection;
    }

    /**
     * Render request according setting
     *
     */
    private void renderRequest(URLConnection connection) {

        if (connectTimeout != null) {
            connection.setConnectTimeout(connectTimeout);
        }

        if (socketTimeout != null) {
            connection.setReadTimeout(socketTimeout);
        }

    }

    /*
     * Getter & Setter
     */
    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

}
