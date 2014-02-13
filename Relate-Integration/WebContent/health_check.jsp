<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.rim.integration.utils.ApplicationContextProvider"%><html>


<%!
	public static final String MAP_ERROR_KEY = "HTML";
	public static final String MAP_SITE_OK_KEY = "SITE_OK";
	
	public static HashMap checkDatabaseConnections(DataSource ds) 
{
	HashMap map = new HashMap();
	boolean siteOk = true;
	StringBuffer sb = new StringBuffer();
	String successfulConnection = "Connection to the database successful.";
	String failedConnection ="Error: Could not get a connection to the database!";
	HashMap dbConnMap = null;
	String errorMsg = "";

	dbConnMap = new HashMap();
	dbConnMap = validateDBConnection(ds, dbConnMap);
	errorMsg = (String)dbConnMap.get(MAP_ERROR_KEY);
	if (errorMsg!=null && !errorMsg.isEmpty())
		sb.append(errorMsg);
	if (((Boolean)dbConnMap.get(MAP_SITE_OK_KEY)).booleanValue()) {
		sb.append("<li><font color=green><strong>" +  "Database:" + " </strong>" + successfulConnection + "</font></li>");
	} else {
		sb.append("<li><font color=red><strong>" + "Database:" + " </strong>" + failedConnection + "</font></li>");
		siteOk = false;
	}
	sb.append("</ul>");
	sb.append("</td></tr><table><br/>");

	map.put(MAP_ERROR_KEY, sb.toString());
	map.put(MAP_SITE_OK_KEY, new Boolean(siteOk));

	return map;
}

public static HashMap validateDBConnection(DataSource ds, HashMap map) {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean siteOk = true;
	try {
		conn = ds.getConnection();

		if (conn == null) {
			siteOk = false;
			map.put(MAP_SITE_OK_KEY, new Boolean(siteOk));
			return map;
		}

		if (conn != null) {
			String sql = "SELECT * FROM TEST";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (!rs.next()) {
				siteOk = false;
				map.put(MAP_SITE_OK_KEY, new Boolean(siteOk));
			}
		}

	} catch (Throwable t) {
		siteOk = false;
		map.put(MAP_ERROR_KEY, "\n<!--\n*********************\n* Exception Message *\n*********************\n" + t.getMessage() + "\n-->\n");
		map.put(MAP_SITE_OK_KEY, new Boolean(siteOk));
		return map;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			map.put(MAP_ERROR_KEY, "\n<!--\n*********************\n* Exception Message *\n*********************\n" + e.getMessage() + "\n-->\n");
			map.put(MAP_SITE_OK_KEY, new Boolean(siteOk));
			return map;
		}
	}
	map.put(MAP_SITE_OK_KEY, new Boolean(siteOk));
	return map;
}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Health_check</title>
<style type="text/css">
BODY {
	margin-left: 11px;
	margin-top: 11px;
	font-family: arial;
	font-size: 16px;
}

tr {
	font-family: arial;
	font-size: 16px;
}

tr.smallText {
	font-family: arial;
	font-size: 10px;
}

.smallText {
	font-family: arial;
	font-size: 12px;
}
</style>
</head>
<body>

	<br/>
	<div align="center">
<%
	HashMap map = null;
	boolean siteOk = true;

	ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
	DataSource ds = (DataSource)ctx.getBean("dataSource");
	// Check Database Connections

		try {
			map = checkDatabaseConnections(ds);
		} finally {
		}
		out.println((String)map.get(MAP_ERROR_KEY));
		if (!((Boolean)map.get(MAP_SITE_OK_KEY)).booleanValue())
			siteOk = false;

%>
</div>	
</body>
</html>