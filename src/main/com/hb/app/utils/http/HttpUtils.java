package com.hb.app.utils.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ： http请求工具类
 */
public class HttpUtils {

    private String defaultContentEncoding;

    public HttpUtils() {
        this.defaultContentEncoding = Charset.defaultCharset().name();
    }

    private static HttpUtils httpUtils;

    public static HttpUtils get() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @return 响应对象
     * @throws IOException
     */
    public HttpResponse sendGet(String urlString) throws IOException {
        return this.send(urlString, "GET", null, null);
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpResponse sendGet(String urlString, Map<String, String> params) {
        try {
            return this.send(urlString, "GET", params, null);
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse();
        }
    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpResponse sendGet(String urlString, Map<String, String> params, Map<String, String> propertys)
            throws IOException {
        return this.send(urlString, "GET", params, propertys);
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @return 响应对象
     * @throws IOException
     */
    public HttpResponse sendPost(String urlString) throws IOException {
        return this.send(urlString, "POST", null, null);
    }

    /**
     * 发送POST请求
     *
     * @param urlString URL地址
     * @param params    参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpResponse sendPost(String urlString, Map<String, String> params) {
        try {
            HttpResponse post = this.send(urlString, "POST", params, null);
            System.out.println(post.content);
            return post;
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse();
        }
    }

    /**
     * 发送POST请求
     *
     * @param urlString  URL地址
     * @param params     参数集合
     * @param properties 请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpResponse sendPost(String urlString, Map<String, String> params, Map<String, String> properties)
            throws IOException {
        return this.send(urlString, "POST", params, properties);
    }

    /**
     * 发送HTTP请求
     *
     * @param urlString  地址
     * @param method     get/post
     * @param parameters 添加由键值对指定的请求参数
     * @param propertys  添加由键值对指定的一般请求属性
     * @return 响映对象
     * @throws IOException
     */
    private HttpResponse send(String urlString, String method, Map<String, String> parameters,
                              Map<String, String> propertys) throws IOException {
        HttpURLConnection urlConnection = null;

        if (method.equalsIgnoreCase("GET") && parameters != null) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (String key : parameters.keySet()) {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(parameters.get(key));
                i++;
            }
            urlString += param;
        }

        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        if (propertys != null)
            for (String key : propertys.keySet()) {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null) {
            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet()) {
                param.append("&");
                param.append(key).append("=").append(parameters.get(key));
            }
            urlConnection.getOutputStream().write(param.toString().getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }
        return this.makeContent(urlString, urlConnection);
    }

    /**
     * 得到响应对象
     *
     * @param urlConnection
     * @return 响应对象
     * @throws IOException
     */
    private HttpResponse makeContent(String urlString, HttpURLConnection urlConnection) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            httpResponse.contentCollection = new Vector<String>();
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
                httpResponse.contentCollection.add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String code = urlConnection.getContentEncoding();
            if (code == null)
                code = this.defaultContentEncoding;

            httpResponse.urlString = urlString;
            httpResponse.defaultPort = urlConnection.getURL().getDefaultPort();
            httpResponse.file = urlConnection.getURL().getFile();
            httpResponse.host = urlConnection.getURL().getHost();
            httpResponse.path = urlConnection.getURL().getPath();
            httpResponse.port = urlConnection.getURL().getPort();
            httpResponse.protocol = urlConnection.getURL().getProtocol();
            httpResponse.query = urlConnection.getURL().getQuery();
            httpResponse.ref = urlConnection.getURL().getRef();
            httpResponse.userInfo = urlConnection.getURL().getUserInfo();
            httpResponse.content = new String(temp.toString().getBytes(), code);
            httpResponse.contentEncoding = code;
            httpResponse.code = urlConnection.getResponseCode();
            httpResponse.message = urlConnection.getResponseMessage();
            httpResponse.contentType = urlConnection.getContentType();
            httpResponse.method = urlConnection.getRequestMethod();
            httpResponse.connectTimeout = urlConnection.getConnectTimeout();
            httpResponse.readTimeout = urlConnection.getReadTimeout();
            return httpResponse;
        } catch (IOException e) {
            throw e;
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    /**
     * 默认的响应字符集
     */
    public String getDefaultContentEncoding() {
        return this.defaultContentEncoding;
    }


    /**
     * 发送GET请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public static URLConnection sendGetRequest(
            String url,
            Map<String, String> params, Map<String, String> headers)
            throws Exception {
        StringBuilder buf = new StringBuilder(url);
        Set<Map.Entry<String, String>> entrys = null;
        // 如果是GET请求，则请求参数在URL中  
        if (params != null && !params.isEmpty()) {
            buf.append("?");
            entrys = params.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                buf.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
            buf.deleteCharAt(buf.length() - 1);
        }
        URL url1 = new URL(buf.toString());
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("GET");
        // 设置请求头  
        if (headers != null && !headers.isEmpty()) {
            entrys = headers.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        conn.getResponseCode();
        return conn;
    }

    /**
     * 发送POST请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public static URLConnection sendPostRequest(String url,
                                                Map<String, String> params, Map<String, String> headers)
            throws Exception {
        StringBuilder buf = new StringBuilder();
        Set<Map.Entry<String, String>> entrys = null;
        // 如果存在参数，则放在HTTP请求体，形如name=aaa&age=10  
        if (params != null && !params.isEmpty()) {
            entrys = params.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                buf.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
            buf.deleteCharAt(buf.length() - 1);
        }
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        out.write(buf.toString().getBytes("UTF-8"));
        if (headers != null && !headers.isEmpty()) {
            entrys = headers.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        conn.getResponseCode(); // 为了发送成功  
        return conn;
    }


}